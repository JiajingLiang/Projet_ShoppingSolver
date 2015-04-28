package com.polymtl.shoppingsolver.util;

import android.util.Log;
import com.polymtl.shoppingsolver.model.BriefTransaction;
import com.polymtl.shoppingsolver.model.Client;
import com.polymtl.shoppingsolver.model.ShoppingRecord;
import com.polymtl.shoppingsolver.model.Transaction;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Zoe on 15-04-08.
 */
public class HandleXML {

    private String data;
    private XmlPullParserFactory xmlFactoryObject;

    public HandleXML (String data) {
        this.data = data;
        Log.i("data", data);
    }

    public void setCurrentProductInfo()  throws XmlPullParserException, IOException{
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        xpp.setInput ( new StringReader ( data ) );
        int eventType = xpp.getEventType();
        String text = null;
        while (eventType != xpp.END_DOCUMENT) {
            if(eventType == xpp.START_DOCUMENT) {
                System.out.println("Start document");
            } else if(eventType == xpp.END_DOCUMENT) {
                System.out.println("End document");
            } else if(eventType == xpp.START_TAG) {
                System.out.println("Start tag "+xpp.getName());
            } else if(eventType == xpp.END_TAG) {
                System.out.println("End tag "+xpp.getName());

                if (xpp.getName().equals("BarCode")) {

                    System.out.println("next text "+ text);
                } else if (xpp.getName().equals("Description")) {
                    ShoppingSolverApplication.getInstance().getCurrentRecord().setDescription(text);

                } else if (xpp.getName().equals("CategoryId")) {
                    System.out.print("CategoryId" + text);
                } else if (xpp.getName().equals("CategoryName")) {
                    ShoppingSolverApplication.getInstance().getCurrentRecord().setCategoryName(text);

                } else if (xpp.getName().equals("ShopId")) {
                    System.out.print("next text " + text);
                } else if (xpp.getName().equals("Street")) {
                    System.out.print("next text " + text);
                } else if (xpp.getName().equals("City")) {
                    System.out.print("next text " + text);
                } else if (xpp.getName().equals("Postcode")) {
                    System.out.print("next text " + text);
                } else if (xpp.getName().equals("Country")) {
                    System.out.print("next text " + text);
                } else if (xpp.getName().equals("BrandId")) {
                    System.out.print("next text " + text);
                } else if (xpp.getName().equals("BrandName")) {
                    System.out.print("next text " + text);
                } else if (xpp.getName().equals("Price")) {
                    ShoppingSolverApplication.getInstance().getCurrentRecord().setUnit_price(Double.parseDouble(text));

                } else if (xpp.getName().equals("RatioTaxFederal")) {
                    ShoppingSolverApplication.getInstance().getCurrentRecord().setFederalTaxRatio(Float.parseFloat(text));

                } else if (xpp.getName().equals("RatioTaxProvincial")) {
                    ShoppingSolverApplication.getInstance().getCurrentRecord().setProvincialTaxRatio(Float.parseFloat(text));

                }


            } else if(eventType == xpp.TEXT) {
                text = xpp.getText();
                Log.i("text", text);
            }
            eventType = xpp.next();
        }

    }

    public ShoppingRecord getShoppingRecord() throws XmlPullParserException, IOException{

        ShoppingRecord product = new ShoppingRecord();
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        xpp.setInput ( new StringReader ( data ) );
        int eventType = xpp.getEventType();
        String text = null;
        while (eventType != xpp.END_DOCUMENT) {
            if(eventType == xpp.START_DOCUMENT) {
                System.out.println("Start document");
            } else if(eventType == xpp.END_DOCUMENT) {
                System.out.println("End document");
            } else if(eventType == xpp.START_TAG) {
                System.out.println("Start tag "+xpp.getName());
            } else if(eventType == xpp.END_TAG) {
                System.out.println("End tag "+xpp.getName());

                if (xpp.getName().equals("BarCode")) {

                    System.out.println("next text "+ text);
                } else if (xpp.getName().equals("Description")) {
                    product.setDescription(text);

                } else if (xpp.getName().equals("CategoryId")) {
                    System.out.print("CategoryId" + text);
                } else if (xpp.getName().equals("CategoryName")) {
                    product.setCategoryName(text);

                } else if (xpp.getName().equals("ShopId")) {
                    System.out.print("next text " + text);
                } else if (xpp.getName().equals("Street")) {
                    System.out.print("next text " + text);
                } else if (xpp.getName().equals("City")) {
                    System.out.print("next text " + text);
                } else if (xpp.getName().equals("Postcode")) {
                    System.out.print("next text " + text);
                } else if (xpp.getName().equals("Country")) {
                    System.out.print("next text " + text);
                } else if (xpp.getName().equals("BrandId")) {
                    System.out.print("next text " + text);
                } else if (xpp.getName().equals("BrandName")) {
                    System.out.print("next text " + text);
                } else if (xpp.getName().equals("BrandName")) {
                    product.setUnit_price(Double.parseDouble(text));

                } else if (xpp.getName().equals("RatioTaxFederal")) {
                    product.setFederalTaxRatio(Float.parseFloat(text));

                } else if (xpp.getName().equals("RatioTaxProvincial")) {
                    product.setProvincialTaxRatio(Float.parseFloat(text));

                }


            } else if(eventType == xpp.TEXT) {
                text = xpp.getText();
                Log.i("text", text);
            }
            eventType = xpp.next();
        }

        return product;
    }

    public void updateClientInfo()
            throws XmlPullParserException, IOException
    {
        Client client = new Client();
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        xpp.setInput ( new StringReader ( data ) );
        int eventType = xpp.getEventType();
        String text = null;
        while (eventType != xpp.END_DOCUMENT) {
            if(eventType == xpp.START_DOCUMENT) {
                System.out.println("Start document");
            } else if(eventType == xpp.END_DOCUMENT) {
                System.out.println("End document");
            } else if(eventType == xpp.START_TAG) {
                System.out.println("Start tag "+xpp.getName());
            } else if(eventType == xpp.END_TAG) {
                System.out.println("End tag "+xpp.getName());
                if (xpp.getName().equals("ClientId")) {
                    //long id = Long.parseLong(xpp.nextText());
                    long id = Long.parseLong(text);
                    System.out.println("ClientId " + id);
                    client.setClientId(id);

                } else if (xpp.getName().equals("Name")) {
                    client.setName(text);
                    Log.i("name", client.getName());
                } else if (xpp.getName().equals("Email")) {

                    client.setEmail(text);
                    Log.i("client", client.getEmail());

                } else if (xpp.getName().equals("Password")) {

                    client.setPassword(text);
                    Log.i("client", client.getPassword());

                } else if (xpp.getName().equals("Telephone")) {

                    client.setTelephone(text);
                } else if (xpp.getName().equals("Street")) {

                    client.setStreet(text);
                } else if (xpp.getName().equals("City")) {

                    client.setCity(text);
                    Log.i("client", client.getCity());
                } else if (xpp.getName().equals("Postcode")) {

                    client.setPostcode(text);
                } else if (xpp.getName().equals("Country")) {

                    client.setCountry(text);
                } else if (xpp.getName().equals("Balance")) {
                    double balance = Double.parseDouble(text);
                    System.out.print("Balance " + balance);
                    client.setBalance(balance);
                }
                
            } else if(eventType == xpp.TEXT) {
                text = xpp.getText();
                Log.i("text", text);
            }
            eventType = xpp.next();
        }

        Log.i("ClientInfo:", client.toString());

        ShoppingSolverApplication.getInstance().setNewCount(client);

    }

    public void getClientId()
            throws XmlPullParserException, IOException
    {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        xpp.setInput ( new StringReader ( data ) );
        int eventType = xpp.getEventType();
        String text = null;
        while (eventType != xpp.END_DOCUMENT) {
            if(eventType == xpp.START_DOCUMENT) {
                System.out.println("Start document");
            } else if(eventType == xpp.END_DOCUMENT) {
                System.out.println("End document");
            } else if(eventType == xpp.START_TAG) {
                System.out.println("Start tag "+xpp.getName());
            } else if(eventType == xpp.END_TAG) {
                System.out.println("End tag "+xpp.getName());
                if (xpp.getName().equals("ClientId")) {
                    //long id = Long.parseLong(xpp.nextText());
                    long id = Long.parseLong(text);
                    System.out.println("ClientId " + id);
                    ShoppingSolverApplication.getInstance().getNewCount().setClientId(id);

                }
            } else if(eventType == xpp.TEXT) {
                text = xpp.getText();
                Log.i("text", text);
            }
            eventType = xpp.next();
        }


    }

    public void getShopInfo() throws XmlPullParserException, IOException
    {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        xpp.setInput ( new StringReader ( data ) );
        int eventType = xpp.getEventType();
        String text = null;

        String street = null, city = null, postcode = null, country = null;

        while (eventType != xpp.END_DOCUMENT) {
            if(eventType == xpp.START_DOCUMENT) {
                System.out.println("Start document");
            } else if(eventType == xpp.END_DOCUMENT) {
                System.out.println("End document");
            } else if(eventType == xpp.START_TAG) {
                System.out.println("Start tag "+xpp.getName());
            } else if(eventType == xpp.END_TAG) {
                System.out.println("End tag "+xpp.getName());
                if (xpp.getName().equals("BrandName")) {

                    System.out.println("BrandName " + text);
                    ShoppingSolverApplication.getInstance().getShop().setName(text);

                } else if (xpp.getName().equals("Street")) {
                    street = text;
                } else if (xpp.getName().equals("city")) {
                    city = text;
                } else if (xpp.getName().equals("PostCode")) {
                    postcode = text;
                } else if (xpp.getName().equals("Country")) {
                    country = text;
                }

            } else if(eventType == xpp.TEXT) {
                text = xpp.getText();
                Log.i("text", text);
            }
            eventType = xpp.next();
        }

        String address = street + ", " + city + ", " + postcode + ", " + country;

        ShoppingSolverApplication.getInstance().getShop().setAddress(address);

    }


    public void saveTransaction() throws XmlPullParserException, IOException
    {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        xpp.setInput ( new StringReader ( data ) );
        int eventType = xpp.getEventType();
        String text = null;

        String street = null, city = null, postcode = null, country = null;
        Transaction transaction = new Transaction();

        while (eventType != xpp.END_DOCUMENT) {
            if(eventType == xpp.START_DOCUMENT) {
                System.out.println("Start document");
            } else if(eventType == xpp.END_DOCUMENT) {
                System.out.println("End document");
            } else if(eventType == xpp.START_TAG) {
                System.out.println("Start tag "+xpp.getName());
            } else if(eventType == xpp.END_TAG) {
                System.out.println("End tag "+xpp.getName());
                if (xpp.getName().equals("Id")) {
                    System.out.println("Id " + text);
                    long id = Long.parseLong(text);
                    transaction.setId(id);

                } else if (xpp.getName().equals("Total")) {
                    double total = Double.parseDouble(text);
                    transaction.setTotalPriceWithTax(total);
                } else if (xpp.getName().equals("BrandName")) {

                    System.out.println("BrandName " + text);
                    transaction.setStoreName(text);

                } else if (xpp.getName().equals("Street")) {
                    street = text;
                } else if (xpp.getName().equals("city")) {
                    city = text;
                } else if (xpp.getName().equals("PostCode")) {
                    postcode = text;
                } else if (xpp.getName().equals("Country")) {
                    country = text;
                } /*else if (xpp.getName().equals("TransactionTime")) {
                    String time = text;
                    Log.i("UTC", time);
                    // change UTC time to local time
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s z"); //2015-04-19 19:12:50.0 UTC
                    df.setTimeZone(TimeZone.getDefault());
                    String timeLocal = df.format(time);
                    Log.i("Local Time", timeLocal);
                    transaction.setTime(timeLocal);
                }*/

                String address = street + ", " + city + ", " + postcode + ", " + country;
                transaction.setAddress(address);
                ShoppingSolverApplication.getInstance().setTheLastTransaction(transaction);


            } else if(eventType == xpp.TEXT) {
                text = xpp.getText();
                Log.i("text", text);
            }
            eventType = xpp.next();
        }


    }

    public boolean isNull() throws XmlPullParserException, IOException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        xpp.setInput ( new StringReader ( data ) );
        int eventType = xpp.getEventType();
        String text;
        boolean isNull = false;

        while (eventType != xpp.END_DOCUMENT) {
            if(eventType == xpp.START_DOCUMENT) {
                System.out.println("Start document");
            } else if(eventType == xpp.END_DOCUMENT) {
                System.out.println("End document");
            } else if(eventType == xpp.START_TAG) {
                System.out.println("Start tag "+xpp.getName());
            } else if(eventType == xpp.END_TAG) {
                System.out.println("End tag "+xpp.getName());
                if ("null".equals(xpp.getName())){
                    isNull = true;
                }

            } else if(eventType == xpp.TEXT) {
                text = xpp.getText();
                Log.i("text", text);
            }
            eventType = xpp.next();
        }

        return isNull;

    }

    public void getRecentTransaction() throws XmlPullParserException, IOException
    {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        xpp.setInput ( new StringReader ( data ) );

        int eventType = xpp.getEventType();
        String text = null;

        BriefTransaction transaction = null;

        while (eventType != xpp.END_DOCUMENT) {
            if(eventType == xpp.START_DOCUMENT) {
                System.out.println("Start document");
            } else if(eventType == xpp.END_DOCUMENT) {
                System.out.println("End document");
            } else if(eventType == xpp.START_TAG) {
                System.out.println("Start tag "+xpp.getName());
                if ("Transaction".equals(xpp.getName())) {
                    transaction = new BriefTransaction();
                    Log.i("Create ", " new Transaction");
                }

            } else if(eventType == xpp.END_TAG) {

                System.out.println("End tag "+xpp.getName());
                if ("Transaction".equals(xpp.getName())) {
                    ShoppingSolverApplication.getInstance().addBriefTransaction(transaction);
                } else if (xpp.getName().equals("Id")) {
                    System.out.println("Id " + text);
                    long id = Long.parseLong(text);
                    transaction.setTransactionId(id);

                } else if (xpp.getName().equals("Total")) {
                    double total = Double.parseDouble(text);
                    transaction.setTotal(total);
                } else if (xpp.getName().equals("BrandName")) {

                    System.out.println("BrandName " + text);
                    transaction.setBrandName(text);

                } else if (xpp.getName().equals("Street")) {
                    transaction.setStreet(text);
                } else if (xpp.getName().equals("City")) {
                    transaction.setCity(text);
                } else if (xpp.getName().equals("PostCode")) {
                    transaction.setPostcode(text);
                } else if (xpp.getName().equals("Country")) {
                    transaction.setCountry(text);
                } else if (xpp.getName().equals("TransactionTime")) {

                    Log.i("UTC", text);
                    // change UTC time to local time

                    Date desiredDate = null;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s z");

                    try {
                        desiredDate = simpleDateFormat.parse(text);
                        SimpleDateFormat desiredSimpleDeteFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
                        desiredSimpleDeteFormat.setTimeZone(TimeZone.getDefault());

                        desiredSimpleDeteFormat.format(desiredDate);
                        String strDate = desiredDate.toString();
                        Log.i("Local date", strDate);
                        transaction.setTime(strDate);
                    } catch (ParseException e) {
                        Log.i("Local date", "exception");
                        e.printStackTrace();
                    }

                }


            } else if(eventType == xpp.TEXT) {
                text = xpp.getText();
                Log.i("text", text);
            }
            eventType = xpp.next();
        }

    }


}
