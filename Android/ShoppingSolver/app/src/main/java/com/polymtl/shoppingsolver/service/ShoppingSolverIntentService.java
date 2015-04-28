package com.polymtl.shoppingsolver.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.maps.model.LatLng;
import com.polymtl.shoppingsolver.ui.MapsShopsActivity;
import com.polymtl.shoppingsolver.R;
import com.polymtl.shoppingsolver.database.ClientDataSource;
import com.polymtl.shoppingsolver.database.HabitDataSource;
import com.polymtl.shoppingsolver.model.Client;
import com.polymtl.shoppingsolver.model.HabitRecord;
import com.polymtl.shoppingsolver.model.SaleInfo;
import com.polymtl.shoppingsolver.model.Shop;
import com.polymtl.shoppingsolver.model.ShoppingRecord;
import com.polymtl.shoppingsolver.util.DetectConnectivity;
import com.polymtl.shoppingsolver.util.GcmBroadcastReceiver;
import com.polymtl.shoppingsolver.util.HandleXML;
import com.polymtl.shoppingsolver.util.ShoppingSolverApplication;
import com.thoughtworks.xstream.XStream;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zoe on 15-04-03.
 * IntentService is a base class for Services that handle asynchronous requests (expressed as Intents)
 * on demand. Clients send requests through startService(Intent) calls;
 * the service is started as needed, handles each Intent in turn using a worker thread,
 * and stops itself when it runs out of work.
 */
public class ShoppingSolverIntentService  extends IntentService{

    private static final String NAMESPACE = "http://shopping_webservice/";

    private static String URL="http://jjliang1005.whelastic.net/ShoppingWS/ShoppingWS?WSDL";
//    private static String URL="http://132.207.220.50:80/ShoppingWS/ShoppingWS?WSDL";

    private static final String LOGIN_METHOD_NAME = "findClientByEmail";
    private static final String SOAP_LOGIN_ACTION = "http://shopping_webservice/ShoppingWS/findClientByEmailRequest";

    private static final String GETPRODUCTINFO_METHOD_NAME = "getProductPriceInShop";
    private static final String SOAP_GETPRODUCTINFO_ACTION = "http://shopping_webservice/ShoppingWS/getProductPriceInShopRequest";

    private static final String CREATETRANSACTION_METHOD_NAME = "createTransaction";
    private static final String SOAP_CREATETRANSACTION_ACTION = "http://shopping_webservice/ShoppingWS/createTransactionRequest";

    private static final String CREATECLIENTWITHDEVICE_METHOD_NAME = "createClientWithDevice";
    private static final String SOAP_CREATECLIENTWITHDEVICE_ACTION = "http://shopping_webservice/ShoppingWS/createClientWithDeviceRequest";

    private static final String FINDTRECENTRANSACTION_METHOD_NAME = "findRecentTransactionByClient";
    private static final String SOAP_FINDRCENTTRANSACTION_ACTION = "http://shopping_webservice/ShoppingWS/findRecentTransactionByClientRequest";


    private static final String SETFAVORETEPRODUCT_METHOD_NAME = "setClientFavoriteProduct";
    private static final String SOAP_SETFAVORETEPRODUCT_ACTION = "http://shopping_webservice/ShoppingWS/setClientFavoriteProductRequest";

    private static final String FINDSHOPBYID_METHOD_NAME = "findShopById";
    private static final String SOAP_FINDSHOPBYID_ACTION = "http://shopping_webservice/ShoppingWS/findShopByIdRequest";


    private static final String ADDDEVICETOCLIENT_METHOD_NAME = "addDeviceToClient";
    private static final String SOAP_ADDDEVICETOCLIENT_ACTION = "http://shopping_webservice/ShoppingWS/addDeviceToClientRequest";

    //for Gcm
    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;
    public static final String TAG = "GCM Test";
    /**
     * A constructor is required, and must call the super IntentService(String)
     * constructor with a name for the worker thread.
     */
    public ShoppingSolverIntentService() {
        super("ShoppingSolverIntentService");
    }

    /**
     * This method is invoked on the worker thread with a request to process.
     * Only one Intent is processed at a time, but the processing happens on a worker thread
     * that runs independently from other application logic. So, if this code takes a long time,
     * it will hold up other requests to the same IntentService, but it will not hold up anything else.
     * When all requests have been handled, the IntentService stops itself,
     * so you should not call stopSelf().
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {

        receiveGCM(intent);
        ///////////////////

        receiveRequest(intent);

        this.stopSelf();
    }

    private void receiveRequest(Intent intent) {
        if (intent.hasExtra("command")) {
            final ResultReceiver receiver = intent.getParcelableExtra("receiver");
            String command = intent.getStringExtra("command");


            // verify if connected
            if (!DetectConnectivity.isConnected(getApplicationContext())) {

                Log.e("connect err", "WIFI is not open");

                Handler handler = new Handler(Looper.getMainLooper());

                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Offline. Please check your network connection.",
                                Toast.LENGTH_SHORT).show();
                    }
                });

            } else {

                switch (command) {
                    case "login":
                        loginByEmail(intent, receiver);
                        break;

                    case "productInfo":
                        getProductInfo(receiver);
                        break;

                    case "shopInfo":
                        getShopInfo(receiver);
                        break;

                    case "payment":
                        createTransaction(receiver);
                        break;

                    case "createCount":
                        createCount(receiver);
                        break;

                    case "getRecentTransactions":
                        getRecentTransactions(receiver);

                    case "sendConsumptionHabit":
                        // send consumption habit to server
                        sendConsumptionHabit();
                        break;

                    case "addDeviceKey":
                        addDeviceToClient();
                        break;

                    default:
                        break;
                }
            }
        }
    }

    private void receiveGCM(Intent intent) {
        // for Gcm
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);
        if (!extras.isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                sendNotification("Send error: " + extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                sendNotification("Deleted messages on server: " + extras.toString());
                // If it's a regular GCM message, do some work.
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {

                Log.i(TAG, "Received: " + extras.toString());
                SaleInfo saleInfo = new SaleInfo();

                // verify if connected
                if (!DetectConnectivity.isConnected(getApplicationContext())) {

                    Log.e("connect err", "WIFI is not open");

                    Handler handler = new Handler(Looper.getMainLooper());

                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Offline. Please check your network connection.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    LatLng latLng = getLocationFromAddress(extras.getString("shopAddress"));
                    saleInfo.setLatLng(latLng);
                }

                Log.i("shopAddress", extras.getString("shopAddress"));
                saleInfo.setAddress(extras.getString("shopAddress"));
                Log.i("productDescription", extras.getString("productDescription"));
                saleInfo.setDescription(extras.getString("productDescription"));
                Log.i("newPrice", extras.getString("newPrice"));
                saleInfo.setPrice(extras.getString("newPrice"));

                ShoppingSolverApplication.getInstance().setSaleInfo(saleInfo);

                // Post notification of received message.
                String msg = saleInfo.getDescription() + "is special! " + saleInfo.getPrice() + "$";
                sendNotification("Received: " + msg);

            }
        }
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }
    // Put the message into a notification and post it.
    // This is just one simple example of what you might choose to do with
    // a GCM message.
    private void sendNotification(String msg) {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(this, MapsShopsActivity.class);
        intent.putExtra("msg", msg);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MapsShopsActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.visualcommerce_icon)
                        .setContentTitle("Sales")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(msg))
                        .setContentText(msg)
                        .setAutoCancel(true);

        Log.i("msg", msg);
        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

    public LatLng getLocationFromAddress(String strAddress) {
        if (strAddress == null || strAddress.isEmpty()) {
            return null;
        }
        Geocoder geocoder = new Geocoder(this);
        List<Address> addresses;
        LatLng point = null;

        try {
            addresses = geocoder.getFromLocationName(strAddress, 5);
            if (addresses == null || addresses.size() <= 0) { //didn't find location by this address
                return null;
            }
            Address location = addresses.get(0);
            point = new LatLng(location.getLatitude(), location.getLongitude());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return point;
    }

    private void loginByEmail(Intent intent, ResultReceiver receiver) {

        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");

        // Create SoapObject to build a SOAP request
        // and method name to be invoked in the SoapObject constructor
        SoapObject request = new SoapObject(NAMESPACE, LOGIN_METHOD_NAME);

        request.addProperty("clientEmail", email);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        Bundle bundle = new Bundle();

        try {

            androidHttpTransport.call(SOAP_LOGIN_ACTION, envelope);

            if (envelope.bodyIn instanceof SoapFault) {
                String str = ((SoapFault) envelope.bodyIn).faultstring;
                Log.i("loginByEmail err", str);
            } else {
                SoapObject response = (SoapObject) envelope.bodyIn;

                Log.i("loginByEmail", response.getProperty(0).toString());

                String result = response.getProperty(0).toString();
                if ("null".equals(result)) {
                    Log.i("loginByEmail err", "email_err");
                    bundle.putString("err", "email_err");
                    receiver.send(0, bundle);
                    return;
                }
                HandleXML xml = new HandleXML(result);
                xml.updateClientInfo();

                if (!ShoppingSolverApplication.getInstance().getNewCount().getPassword().equals(password)) {
                    bundle.putString("err", "password_err");
                    receiver.send(0, bundle);
                    return;
                }

                // save client info to database
                ClientDataSource clientDataSource = new ClientDataSource(getApplicationContext());
                clientDataSource.open();
                clientDataSource.saveClientInfo(ShoppingSolverApplication.getInstance().getNewCount());
                ShoppingSolverApplication.getInstance().setCurrentClient(clientDataSource.getClient());
                clientDataSource.close();

                receiver.send(1, bundle);
            }


        } catch (IOException e) {
            Log.i("loginByEmail", "exception");
            receiver.send(3, bundle); // 3: IOException
            e.printStackTrace();
        } catch (XmlPullParserException e) {

            receiver.send(4, bundle); // 4: XmlPullParserException
            e.printStackTrace();
        }

    }



    private void getProductInfo(ResultReceiver receiver) {

        ShoppingSolverApplication application = ShoppingSolverApplication.getInstance();
        String barCode = application.getCurrentRecord().getProductBarCode();
        long idShop = application.getShop().getShopId();

        // Create SoapObject to build a SOAP request
        // and method name to be invoked in the SoapObject constructor
        SoapObject request = new SoapObject(NAMESPACE, GETPRODUCTINFO_METHOD_NAME);

        request.addProperty("productBarCode", barCode);
        request.addProperty("idShop", idShop);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        Log.i("request", request.toString());
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        Bundle bundle = new Bundle();

        try {

            bundle.putString("requestType", "getProductInfo");
            androidHttpTransport.call(SOAP_GETPRODUCTINFO_ACTION, envelope);

            if (envelope.bodyIn instanceof SoapFault) {
                SoapFault fault = (SoapFault) envelope.bodyIn;
                Log.e("getProductInfo", fault.toString());
            } else {

                SoapObject response = (SoapObject) envelope.bodyIn;
                String result = response.getProperty(0).toString();
                Log.i("product result", result);

                HandleXML xml = new HandleXML(result);
                if (xml.isNull()) {
                    Log.i("err", "not_exist");

                    receiver.send(0, bundle);
                    return;
                }

                xml.setCurrentProductInfo();
                receiver.send(1, bundle);
            }


        } catch (IOException e) {
            Log.i("getProductInfo", "exception");
            receiver.send(3, bundle); // 3: IOException
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            receiver.send(4, bundle); // 4: XmlPullParserException
            e.printStackTrace();
        }

    }



    private void getShopInfo(ResultReceiver receiver) {

        ShoppingSolverApplication application = ShoppingSolverApplication.getInstance();

        // Create SoapObject to build a SOAP request
        // and method name to be invoked in the SoapObject constructor
        SoapObject request = new SoapObject(NAMESPACE, FINDSHOPBYID_METHOD_NAME);

        Log.i("shopId", "id" + application.getShop().getShopId());
        request.addProperty("shopBranchId", application.getShop().getShopId());
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        Log.i("request", request.toString());
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        Bundle bundle = new Bundle();

        try {

            bundle.putString("requestType", "getShopInfo");
            androidHttpTransport.call(SOAP_FINDSHOPBYID_ACTION, envelope);

            if (envelope.bodyIn instanceof SoapFault) {
                SoapFault fault = (SoapFault) envelope.bodyIn;
                Log.e("getShopInfo", fault.toString());
                receiver.send(0, bundle);
            } else {
                SoapObject response = (SoapObject) envelope.bodyIn;
                String result = response.getProperty(0).toString();
                Log.i("getShopInfo", result);

                HandleXML xml = new HandleXML(result);
                if (xml.isNull()) {
                    Log.i("getShopInfo", "is null");
                    receiver.send(0, bundle);
                    return;
                }

                xml.getShopInfo();

                receiver.send(1, bundle);
            }

        } catch (IOException e) {
            Log.i("getShopInfo", "exception");
            receiver.send(3, bundle); // 3: IOException
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            receiver.send(4, bundle); // 4: XmlPullParserException
            e.printStackTrace();
        }

    }


    // create a transaction, return back transaction info
    private void createTransaction(ResultReceiver receiver) {

        ShoppingSolverApplication application = ShoppingSolverApplication.getInstance();
        ClientDataSource clientDataSource = new ClientDataSource(getApplicationContext());
        clientDataSource.open();
        Client client = clientDataSource.getClient();
        clientDataSource.close();

        String password = client.getPassword();
        long clientId = client.getClientId();
        long shopId = application.getShop().getShopId();
        ArrayList<ShoppingRecord> records = application.getShoppingRecords();
        List<String> listProducts = new ArrayList<>();
        List<Float> listQuantities = new ArrayList<>();

        Log.i("Transaction", "records " + records.size());
        for (ShoppingRecord record: records) {
            listProducts.add(record.getProductBarCode());
            listQuantities.add(record.getQuantity());
        }

        XStream xStream = new XStream();
        String strListProducts = xStream.toXML(listProducts);
        String strListQuantity = xStream.toXML(listQuantities);


        // Create SoapObject to build a SOAP request
        // and method name to be invoked in the SoapObject constructor
        SoapObject request = new SoapObject(NAMESPACE, CREATETRANSACTION_METHOD_NAME);

        request.addProperty("clientId", clientId);
        request.addProperty("password", password);
        request.addProperty("shopId", shopId);
        request.addProperty("listProducts", strListProducts);
        request.addProperty("listQuantities", strListQuantity);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        Log.i("request", request.toString());
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        Bundle bundle = new Bundle();

        try {

            androidHttpTransport.call(SOAP_CREATETRANSACTION_ACTION, envelope);

            if (envelope.bodyIn instanceof SoapFault) {
                SoapFault fault = (SoapFault) envelope.bodyIn;
                Log.i("createTransaction err", fault.toString());
                receiver.send(0, bundle);
            } else {
                SoapObject response = (SoapObject) envelope.bodyIn;
                String result = response.getProperty(0).toString();

                HandleXML xml = new HandleXML(result);
                if (xml.isNull()) {
                    Log.i("create Tran", "is null");
                    receiver.send(0, bundle);
                    return;
                }

                    // save transaction Id

                    xml.saveTransaction();

                    receiver.send(1, bundle);

            }

        } catch (IOException e) {
            Log.i("createTransaction", "exception");
            receiver.send(3, bundle); // 3: IOException
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            receiver.send(4, bundle); // 4: XmlPullParserException
            e.printStackTrace();
        }

    }


    private void createCount(ResultReceiver receiver) {

        ShoppingSolverApplication application = ShoppingSolverApplication.getInstance();

        Client client = application.getNewCount();


        // Create SoapObject to build a SOAP request
        // and method name to be invoked in the SoapObject constructor
        SoapObject request = new SoapObject(NAMESPACE, CREATECLIENTWITHDEVICE_METHOD_NAME);

        request.addProperty("email", client.getEmail());
        request.addProperty("name", client.getName());
        request.addProperty("password", client.getPassword());
        request.addProperty("telephone", client.getTelephone());
        request.addProperty("street", client.getStreet());
        request.addProperty("city", client.getCity());
        request.addProperty("postcode", client.getPostcode());
        request.addProperty("country", client.getCountry());
        request.addProperty("deviceKey", application.getRegId());
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        Log.i("request", request.toString());
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        Bundle bundle = new Bundle();

        try {

            androidHttpTransport.call(SOAP_CREATECLIENTWITHDEVICE_ACTION, envelope);

            Log.i("CC result", envelope.bodyIn.getClass().getName());
            if (envelope.bodyIn instanceof SoapFault) {
                String str = ((SoapFault) envelope.bodyIn).faultstring;
                receiver.send(0, bundle);
                Log.i("CC result", str);
            } else {

                SoapObject response = (SoapObject) envelope.bodyIn;
                String result = response.getProperty(0).toString();


                if ("null".equals(result)) {
                    Log.e("CC ", "Create client error");
                    receiver.send(0, bundle);
                    return;
                }

                    Log.i("CC ", "Create client success");
                    HandleXML xml = new HandleXML(result);
                    xml.getClientId();
                    // save client info to database
                    ClientDataSource clientDataSource = new ClientDataSource(getApplicationContext());
                    clientDataSource.open();
                    clientDataSource.saveClientInfo(ShoppingSolverApplication.getInstance().getNewCount());
                    clientDataSource.close();

                    receiver.send(1, bundle);
                }



        } catch (IOException e) {
            Log.i("create client", "exception");
            receiver.send(3, bundle); // 3: IOException
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            receiver.send(4, bundle); // 4: XmlPullParserException
            e.printStackTrace();
        }

    }


    private void getRecentTransactions(ResultReceiver receiver) {

        ClientDataSource clientDataSource = new ClientDataSource(getApplicationContext());
        clientDataSource.open();
        Client client = clientDataSource.getClient();
        clientDataSource.close();

        String password = client.getPassword();
        long clientId = client.getClientId();


        // Create SoapObject to build a SOAP request
        // and method name to be invoked in the SoapObject constructor
        SoapObject request = new SoapObject(NAMESPACE, FINDTRECENTRANSACTION_METHOD_NAME);

        request.addProperty("clientId", clientId);
        request.addProperty("password", password);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        Log.i("request", request.toString());
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        Bundle bundle = new Bundle();
        bundle.putString("requestType", "getRecentTransactions");
        try {

            androidHttpTransport.call(SOAP_FINDRCENTTRANSACTION_ACTION, envelope);

            if (envelope.bodyIn instanceof SoapFault) {
                String str = ((SoapFault) envelope.bodyIn).faultstring;
                Log.i("recentTransaction err", str);
                receiver.send(0, bundle);
            } else {
                SoapObject response = (SoapObject) envelope.bodyIn;
                String result = response.getProperty(0).toString();

                HandleXML xml = new HandleXML(result);
                if (xml.isNull()) {
                    receiver.send(0, bundle);
                    return;
                }

                ShoppingSolverApplication.getInstance().clearBriefTransaction();

                xml.getRecentTransaction();

                receiver.send(1, bundle);
                Log.i("recentTransaction", result);

            }


        } catch (IOException e) {
            Log.i("getTransaction", "exception");

            receiver.send(3, bundle);
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            receiver.send(4, bundle);
            e.printStackTrace();
        }

    }

    public void sendConsumptionHabit() {

        ShoppingSolverApplication application = ShoppingSolverApplication.getInstance();

        // Save consumption habit to database
        HabitDataSource habitDataSource = new HabitDataSource(getApplicationContext());
        habitDataSource.open();
        for (ShoppingRecord record: application.getShoppingRecords()) {
            HabitRecord habitRecord = new HabitRecord();
            habitRecord.setProductBarCode(record.getProductBarCode());
            habitRecord.setQuantity(record.getQuantity());
            habitRecord.setDescription(record.getDescription());
            habitRecord.setClientId(application.getCurrentClient().getClientId());
            habitDataSource.addRecord(habitRecord);
        }

        Client client = application.getNewCount();

        // get productsCode
        ArrayList<String> productsCodeList = habitDataSource.getNecessaryProductsCode(client.getClientId());
        Log.i("productsCodeList", productsCodeList.toString());
        habitDataSource.close();

        // clear the records
        application.clearShoppingRecords();
        application.setShop(new Shop());

        application.setTheLastTransaction(null);

        XStream xStream = new XStream();
        String strProductsCode = xStream.toXML(productsCodeList);

        // Create SoapObject to build a SOAP request
        // and method name to be invoked in the SoapObject constructor
        SoapObject request = new SoapObject(NAMESPACE, SETFAVORETEPRODUCT_METHOD_NAME);

        request.addProperty("clientId", client.getClientId());
        request.addProperty("password", client.getPassword());
        request.addProperty("productsCode", strProductsCode);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        Log.i("request", request.toString());
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {

            androidHttpTransport.call(SOAP_SETFAVORETEPRODUCT_ACTION, envelope);

            Log.i("sendConsumptionHabit", envelope.bodyIn.getClass().getName());
            if (envelope.bodyIn instanceof SoapFault) {
                String str = ((SoapFault) envelope.bodyIn).faultstring;

                Log.i("sendConsumptionHabit", str);
            } else {

                SoapObject response = (SoapObject) envelope.bodyIn;
                String result = response.getProperty(0).toString();

                Log.i("sendConsumptionHabit", result);
            }


        } catch (IOException e) {
            Log.i("sendConsumptionHabit", "exception");

            e.printStackTrace();
        } catch (XmlPullParserException e) {

            e.printStackTrace();
        }
    }

    public void addDeviceToClient() {

        ShoppingSolverApplication application = ShoppingSolverApplication.getInstance();
        // Create SoapObject to build a SOAP request
        // and method name to be invoked in the SoapObject constructor
        SoapObject request = new SoapObject(NAMESPACE, ADDDEVICETOCLIENT_METHOD_NAME);

        request.addProperty("clientId", application.getCurrentClient().getClientId());
        request.addProperty("deviceKey", application.getRegId());
        Log.i("clientId", "" + application.getCurrentClient().getClientId());
        Log.i("deviceKey", application.getRegId());
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        //Bundle bundle = new Bundle();

        try {

            androidHttpTransport.call(SOAP_ADDDEVICETOCLIENT_ACTION, envelope);

            if (envelope.bodyIn instanceof SoapFault) {
                String str = ((SoapFault) envelope.bodyIn).faultstring;
                Log.i("addDeviceKey err", str);
            } else {
                SoapObject response = (SoapObject) envelope.bodyIn;

                Log.i("addDeviceKey", response.getProperty(0).toString());

                String result = response.getProperty(0).toString();

                Log.i("addDeviceKey", "" + Boolean.parseBoolean(result));

            }


        } catch (IOException e) {
            Log.i("addDeviceKey", "exception");
            //receiver.send(3, bundle); // 3: IOException
            e.printStackTrace();
        } catch (XmlPullParserException e) {

            //receiver.send(4, bundle); // 4: XmlPullParserException
            e.printStackTrace();
        }


    }

}
