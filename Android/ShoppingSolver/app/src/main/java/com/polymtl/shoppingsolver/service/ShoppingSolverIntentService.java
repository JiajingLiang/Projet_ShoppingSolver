package com.polymtl.shoppingsolver.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.polymtl.shoppingsolver.LoginActivity;
import com.polymtl.shoppingsolver.R;
import com.polymtl.shoppingsolver.database.ClientDataSource;
import com.polymtl.shoppingsolver.database.HabitDataSource;
import com.polymtl.shoppingsolver.model.Client;
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
    private static String URL="http://jjliang-1005.jelastic.servint.net/ShoppingWS/ShoppingWS?WSDL";

    private static final String LOGIN_METHOD_NAME = "findClientByEmail";
    private static final String SOAP_LOGIN_ACTION = "http://shopping_webservice/ShoppingWS/findClientByEmailRequest";

    private static final String GETPRODUCTINFO_METHOD_NAME = "getProductPriceInShop";
    private static final String SOAP_GETPRODUCTINFO_ACTION = "http://shopping_webservice/ShoppingWS/getProductPriceInShopRequest";

    private static final String CREATETRANSACTION_METHOD_NAME = "createTransaction";
    private static final String SOAP_CREATETRANSACTION_ACTION = "http://shopping_webservice/ShoppingWS/createTransactionRequest";

    private static final String CREATECLIENTWITHDEVICE_METHOD_NAME = "createClientWithDevice";
    private static final String SOAP_CREATECLIENTWITHDEVICE_ACTION = "http://shopping_webservice/ShoppingWS/createClientWithDeviceRequest";

    private static final String FINDTRANSACTION_METHOD_NAME = "findTransactionByClient";
    private static final String SOAP_FINDTRANSACTION_ACTION = "http://shopping_webservice/ShoppingWS/findTransactionByClientRequest";

    private static final String SETFAVORETEPRODUCT_METHOD_NAME = "setClientFavoriteProduct";
    private static final String SOAP_SETFAVORETEPRODUCT_ACTION = "http://shopping_webservice/ShoppingWS/setClientFavoriteProductRequest";

    private static final String FINDSHOPBYID_METHOD_NAME = "findShopById";
    private static final String SOAP_FINDSHOPBYID_ACTION = "http://shopping_webservice/ShoppingWS/findShopByIdRequest";

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
                // This loop represents the service doing some work.
                for (int i = 0; i < 5; i++) {
                    Log.i(TAG, "Working... " + (i + 1)
                            + "/5 @ " + SystemClock.elapsedRealtime());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }
                }
                Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());
                // Post notification of received message.
                sendNotification("Received: " + extras.toString());
                Log.i(TAG, "Received: " + extras.toString());
            }
        }
        ///////////////////

        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        String command = intent.getStringExtra("command");

        if (!command.isEmpty()) {
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
                return;
            }

            switch (command) {
                case "login":
                    loginByEmail(intent, receiver);
                    break;

                case "productInfo":
                    getProductInfo(intent, receiver);
                    break;

                case "shopInfo":
                    getShopInfo(intent, receiver);
                    break;

                case "payment":
                    createTransaction(intent, receiver);
                    break;

                case "createCount":
                    createCount(intent, receiver);
                    break;

                case "getTransaction":
                    getTransaction(intent, receiver);
                    break;

                default:
                    break;
            }
        }

        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);

        this.stopSelf();
    }

    // Put the message into a notification and post it.
    // This is just one simple example of what you might choose to do with
    // a GCM message.
    private void sendNotification(String msg) {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, LoginActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_reception)
                        .setContentTitle("GCM Notification")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(msg))
                        .setContentText(msg);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
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
        try {
            Bundle bundle = new Bundle();
            androidHttpTransport.call(SOAP_LOGIN_ACTION, envelope);

            if (envelope.bodyIn instanceof SoapFault) {
                String str = ((SoapFault) envelope.bodyIn).faultstring;
                Log.i("err", str);
            } else {
                SoapObject response = (SoapObject) envelope.bodyIn;

                Log.i("Login", response.getProperty(0).toString());

                String result = response.getProperty(0).toString();
                if (result == null) {
                    Log.i("err", "email_err");
                    bundle.putString("err", "email_err");
                    receiver.send(0, bundle);
                    return;
                }
                HandleXML xml = new HandleXML(result);
                xml.updataClientInfo();

                Log.i("enter", password);

                if (!ShoppingSolverApplication.getInstance().getNewCount().getPassword().equals(password)) {
                    bundle.putString("err", "password_err");
                    receiver.send(0, bundle);
                    return;
                }

                // save client info to database
                ClientDataSource clientDataSource = new ClientDataSource(getApplicationContext());
                clientDataSource.open();
                clientDataSource.saveClientInfo(ShoppingSolverApplication.getInstance().getNewCount());
                clientDataSource.close();

                receiver.send(1, bundle);
            }


        } catch (IOException e) {
            Log.i("Login", "exception");
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            //Bundle bundle = new Bundle();
            //bundle.putString("err", "exception");
            //receiver.send(0, bundle);
            e.printStackTrace();
        }

    }



    private void getProductInfo(Intent intent, ResultReceiver receiver) {

        //String barCode = intent.getStringExtra("productBarCode");
        String barCode = ShoppingSolverApplication.getInstance().getCurrentRecord().getProductBarCode();
        long idShop = ShoppingSolverApplication.getInstance().getShop().getShopId();

        // Create SoapObject to build a SOAP request
        // and method name to be invoked in the SoapObject constructor
        SoapObject request = new SoapObject(NAMESPACE, GETPRODUCTINFO_METHOD_NAME);

        request.addProperty("productBarCode", "068100047219"); // for test
        request.addProperty("idShop", 3); // for test
        //request.addProperty("productBarCode", barCode);
        //request.addProperty("idShop", ShoppingSolverApplication.getInstance().getShop().getShopId());
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        Log.i("request", request.toString());
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            Bundle bundle = new Bundle();
            bundle.putString("requestType", "getProductInfo");
            androidHttpTransport.call(SOAP_GETPRODUCTINFO_ACTION, envelope);

            if (envelope.bodyIn instanceof SoapFault) {
                SoapFault fault = (SoapFault) envelope.bodyIn;
                Log.e("getProductInfo", fault.toString());
            } else {
                SoapObject response = (SoapObject) envelope.bodyIn;
                String result = response.getProperty(0).toString();
                Log.i("product result", result);

                if (request == null) {
                    Log.i("err", "not_exist");

                    receiver.send(0, bundle);
                    return;
                }
                HandleXML xml = new HandleXML(result);
                xml.setCurrentProductInfo();
                receiver.send(1, bundle);
            }


        } catch (IOException e) {
            Log.i("getProductInfo", "exception");
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            //Bundle bundle = new Bundle();
            //receiver.send(0, bundle);
            e.printStackTrace();
        }

    }



    private void getShopInfo(Intent intent, ResultReceiver receiver) {

        ShoppingSolverApplication app = (ShoppingSolverApplication) getApplicationContext();

        // Create SoapObject to build a SOAP request
        // and method name to be invoked in the SoapObject constructor
        SoapObject request = new SoapObject(NAMESPACE, FINDSHOPBYID_METHOD_NAME);

        // TEST
        long id = 3l;
        request.addProperty("shopBranchId", id); //

        //request.addProperty("idShop", app.getShop().getShopId());
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        Log.i("request", request.toString());
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            Bundle bundle = new Bundle();
            bundle.putString("requestType", "getShopInfo");
            androidHttpTransport.call(SOAP_FINDSHOPBYID_ACTION, envelope);

            if (envelope.bodyIn instanceof SoapFault) {
                SoapFault fault = (SoapFault) envelope.bodyIn;
                Log.e("getShopInfo", fault.toString());
            } else {
                SoapObject response = (SoapObject) envelope.bodyIn;
                String result = response.getProperty(0).toString();
                Log.i("getShopInfo", result);


                if (request == null) {
                    Log.i("getShopInfo", "is null");
                    receiver.send(0, bundle);
                    return;
                }
                HandleXML xml = new HandleXML(result);
                xml.getShopInfo();

                receiver.send(1, bundle);
            }

        } catch (IOException e) {
            Log.i("getShopInfo", "exception");
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            //Bundle bundle = new Bundle();
            //receiver.send(0, bundle);
            e.printStackTrace();
        }

    }


    private void createTransaction(Intent intent, ResultReceiver receiver) {

        ShoppingSolverApplication app = ShoppingSolverApplication.getInstance();
        ClientDataSource clientDataSource = new ClientDataSource(getApplicationContext());
        clientDataSource.open();
        Client client = clientDataSource.getClient();
        clientDataSource.close();

        String password = client.getPassword();
        long clientId = client.getClientId();
        long shopId = app.getShop().getShopId();
        ArrayList<ShoppingRecord> records = app.getShoppingRecords();
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
        request.addProperty("shopId", 3); // for test
        //request.addProperty("shopId", shopId);
        request.addProperty("listProducts", strListProducts);
        request.addProperty("listQuantities", strListQuantity);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        Log.i("request", request.toString());
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            Bundle bundle = new Bundle();
            androidHttpTransport.call(SOAP_CREATETRANSACTION_ACTION, envelope);

            if (envelope.bodyIn instanceof SoapFault) {
                SoapFault fault = (SoapFault) envelope.bodyIn;
                Log.i("createTransaction err", fault.toString());
            } else {
                SoapObject response = (SoapObject) envelope.bodyIn;
                String result = response.getProperty(0).toString();

                Log.i("transaction result", result);

                if (Boolean.parseBoolean(result)) {

                    receiver.send(1, bundle);

                    // send consumption habit to server
                    sendConsumptionHabit();

                } else {
                    receiver.send(0, bundle);
                }
            }

        } catch (IOException e) {
            Log.i("createTransaction", "exception");
            //Bundle bundle = new Bundle();
            //receiver.send(0, bundle);
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            //Bundle bundle = new Bundle();
           // receiver.send(0, bundle);
            e.printStackTrace();
        }

    }


    private void createCount(Intent intent, ResultReceiver receiver) {

        ShoppingSolverApplication app = ShoppingSolverApplication.getInstance();

        Client client = app.getNewCount();


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
        request.addProperty("deviceKey", ShoppingSolverApplication.getInstance().getRegId());
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        Log.i("request", request.toString());
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            Bundle bundle = new Bundle();
            androidHttpTransport.call(SOAP_CREATECLIENTWITHDEVICE_ACTION, envelope);

            Log.i("CC result", envelope.bodyIn.getClass().getName());
            if (envelope.bodyIn instanceof SoapFault) {
                String str = ((SoapFault) envelope.bodyIn).faultstring;
                receiver.send(0, bundle);
                Log.i("CC result", str);
            } else {

                SoapObject response = (SoapObject) envelope.bodyIn;
                String result = response.getProperty(0).toString();

                if (!Boolean.parseBoolean(result)) {
                    Log.e("CC err", "Create client error");
                } else {

                    HandleXML xml = new HandleXML(result);
                    xml.getClientId();
                    // save client info to database
                    ClientDataSource clientDataSource = new ClientDataSource(getApplicationContext());
                    clientDataSource.open();
                    clientDataSource.saveClientInfo(ShoppingSolverApplication.getInstance().getNewCount());
                    clientDataSource.close();

                    receiver.send(1, bundle);
                }
            }


        } catch (IOException e) {
            Log.i("create client", "exception");
            //Bundle bundle = new Bundle();
            //receiver.send(0, bundle);
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            //Bundle bundle = new Bundle();
            //receiver.send(0, bundle);
            e.printStackTrace();
        }

    }


    private void getTransaction(Intent intent, ResultReceiver receiver) {

        ShoppingSolverApplication app = ShoppingSolverApplication.getInstance();
        ClientDataSource clientDataSource = new ClientDataSource(getApplicationContext());
        clientDataSource.open();
        Client client = clientDataSource.getClient();
        clientDataSource.close();

        String password = client.getPassword();
        long clientId = client.getClientId();
        String dateBegin = ShoppingSolverApplication.getInstance().getDateBegin();
        String dateEnd = ShoppingSolverApplication.getInstance().getDateEnd();


        // Create SoapObject to build a SOAP request
        // and method name to be invoked in the SoapObject constructor
        SoapObject request = new SoapObject(NAMESPACE, FINDTRANSACTION_METHOD_NAME);

        request.addProperty("clientId", clientId);
        request.addProperty("password", password);
        request.addProperty("dateBegin", dateBegin);
        request.addProperty("dateEnd", dateEnd);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        Log.i("request", request.toString());
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            Bundle bundle = new Bundle();
            androidHttpTransport.call(SOAP_FINDTRANSACTION_ACTION, envelope);

            if (envelope.bodyIn instanceof SoapFault) {
                String str = ((SoapFault) envelope.bodyIn).faultstring;
                Log.i("reception err", str);
            } else {
                SoapObject response = (SoapObject) envelope.bodyIn;
                String result = response.getProperty(0).toString();


            }
            //SoapObject response = (SoapObject) envelope.bodyIn;
            //String result = response.getProperty(0).toString();

            //Log.i("reception result", result);

            //TODO return fault, need to verify


        } catch (IOException e) {
            Log.i("getTransaction", "exception");
            //Bundle bundle = new Bundle();
            //receiver.send(0, bundle);
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            //Bundle bundle = new Bundle();
            //receiver.send(0, bundle);
            e.printStackTrace();
        }

    }


    public void sendConsumptionHabit() {

        ShoppingSolverApplication app = ShoppingSolverApplication.getInstance();

        Client client = app.getNewCount();

        // get productsCode

        HabitDataSource habitDataSource = new HabitDataSource(getApplicationContext());
        habitDataSource.open();
        ArrayList<String> productsCodeList = habitDataSource.getNecessaryProductsCode();
        habitDataSource.close();
        XStream xStream = new XStream();
        String strProductsCode = xStream.toXML(productsCodeList);

        // Create SoapObject to build a SOAP request
        // and method name to be invoked in the SoapObject constructor
        SoapObject request = new SoapObject(NAMESPACE, SETFAVORETEPRODUCT_METHOD_NAME);

        request.addProperty("clientId", client.getClientId());

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


}
