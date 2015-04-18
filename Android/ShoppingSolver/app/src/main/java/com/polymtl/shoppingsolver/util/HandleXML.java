package com.polymtl.shoppingsolver.util;

import android.util.Log;

import com.polymtl.shoppingsolver.database.ClientDataSource;
import com.polymtl.shoppingsolver.model.Client;
import com.polymtl.shoppingsolver.model.ShoppingRecord;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

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

    public void updataClientInfo()
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

}
