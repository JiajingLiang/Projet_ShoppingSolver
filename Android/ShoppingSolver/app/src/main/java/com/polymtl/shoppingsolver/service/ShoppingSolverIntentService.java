package com.polymtl.shoppingsolver.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import com.polymtl.shoppingsolver.model.Category;
import com.polymtl.shoppingsolver.model.Product;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zoe on 15-04-03.
 * IntentService is a base class for Services that handle asynchronous requests (expressed as Intents)
 * on demand. Clients send requests through startService(Intent) calls;
 * the service is started as needed, handles each Intent in turn using a worker thread,
 * and stops itself when it runs out of work.
 */
public class ShoppingSolverIntentService  extends IntentService{

    // TEST mock info
    private static String SOAP_ACTION1 = "http://tempuri.org/FahrenheitToCelsius";
    private static String SOAP_ACTION2 = "http://tempuri.org/CelsiusToFahrenheit";
    private static String NAMESPACE = "http://tempuri.org/";
    private static String METHOD_NAME1 = "FahrenheitToCelsius";
    private static String METHOD_NAME2 = "CelsiusToFahrenheit";
    private static String URL = "http://www.w3schools.com/webservices/tempconvert.asmx?WSDL";
    /////////////

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
        String data = intent.getStringExtra("data");



        if (command.equals("productInfo")) {
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


        }

        this.stopSelf();
    }
















}
