package com.polymtl.shoppingsolver.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import com.polymtl.shoppingsolver.MainActivity;
import com.polymtl.shoppingsolver.database.ClientDataSource;
import com.polymtl.shoppingsolver.model.Client;
import com.polymtl.shoppingsolver.model.Product;
import com.polymtl.shoppingsolver.util.HandleXML;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;


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

        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        String command = intent.getStringExtra("command");


        switch (command) {
            case "login" :
                loginByEmail(intent, receiver);
                break;

            case "productInfo":
                getProductInfo(intent, receiver);
                break;
            default:
                break;
        }

       /* if (command.equals("productInfo")) {
            // TODO: request product information
            //Initialize soap request and parameters
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME1);
            //add parameters
            request.addProperty("productInfo", data); // here data should be product code
            //Declare the version of the SOAP request
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;

            Bundle bundle = new Bundle();

            try {
                HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

                //this is the actual part that will call the webservice
                androidHttpTransport.call(SOAP_ACTION1, envelope);

                // Get the SoapResult from the envelope body.
                SoapObject result = (SoapObject)envelope.bodyIn;

                if(result != null)
                {
                    //Get the property
                    //bundle.putString("data", result.getProperty(0).toString());


                    receiver.send(2, bundle);
                }
                else
                {
                    // return ERROR
                    receiver.send(0, bundle);
                }
            } catch (Exception e) {
                e.printStackTrace();
                receiver.send(0, bundle);
            }


        }*/

        this.stopSelf();
    }



    private void loginByEmail(Intent intent, ResultReceiver receiver) {

        boolean checked = false;
        intent.getBooleanExtra("checked", checked);
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

            SoapObject response = (SoapObject) envelope.bodyIn;
            String result = response.getProperty(0).toString();
            if (result.equals("null")) {
                Log.i("err", "email_err");
                bundle.putString("err", "email_err");
                receiver.send(0, bundle);
                return;
            }
            HandleXML xml = new HandleXML(result);
            Client client = xml.getClient();

            Log.i("enter", password);
            Log.i("get", client.getPassword());
            if (!client.getPassword().equals(password)) {
                bundle.putString("err", "password_err");
                receiver.send(0, bundle);
                return;
            }

            // if checked, save client info to database
            if (checked) {
                ClientDataSource clientDataSource = new ClientDataSource(getApplicationContext());
                clientDataSource.open();
                clientDataSource.saveClientInfo(client);
                clientDataSource.close();
            }


            bundle.putSerializable("client", client);
            receiver.send(1, bundle);


        } catch (IOException e) {
            Log.i("Login", "exception");
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            //Bundle bundle = new Bundle();
            //receiver.send(0, bundle);
            e.printStackTrace();
        }

    }



    private void getProductInfo(Intent intent, ResultReceiver receiver) {

        String barCode = intent.getStringExtra("productBarCode");
        Log.i("barCode", "" + barCode);
        long idShop = 0;
        intent.getLongExtra("idShop", idShop);
        Log.i("idShop", "" + idShop);

        // Create SoapObject to build a SOAP request
        // and method name to be invoked in the SoapObject constructor
        SoapObject request = new SoapObject(NAMESPACE, GETPRODUCTINFO_METHOD_NAME);

        request.addProperty("productBarCode", "068200010311"); // for test
        request.addProperty("idShop", 3);
        //request.addProperty("productBarCode", barCode);
        //request.addProperty("idShop", MainActivity.getShopId());
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        Log.i("request", request.toString());
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            Bundle bundle = new Bundle();
            androidHttpTransport.call(SOAP_GETPRODUCTINFO_ACTION, envelope);

            SoapObject response = (SoapObject) envelope.bodyIn;
            String result = response.getProperty(0).toString();
            Log.i("product result", result);

            if (result.equals("null")) {
                Log.i("err", "not_exist");
                bundle.putString("err", "not_exist");
                receiver.send(0, bundle);
                return;
            }
            HandleXML xml = new HandleXML(result);
            Product product = xml.getProduct();

            bundle.putSerializable("product", product);
            receiver.send(1, bundle);


        } catch (IOException e) {
            Log.i("Login", "exception");
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            //Bundle bundle = new Bundle();
            //receiver.send(0, bundle);
            e.printStackTrace();
        }

    }









}
