
package com.polymtl.wsshoppingsolver.webservice_client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getProductPriceFromShop complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getProductPriceFromShop">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productBarCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idShop" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getProductPriceFromShop", propOrder = {
    "productBarCode",
    "idShop"
})
public class GetProductPriceFromShop {

    protected String productBarCode;
    protected long idShop;

    /**
     * Gets the value of the productBarCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductBarCode() {
        return productBarCode;
    }

    /**
     * Sets the value of the productBarCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductBarCode(String value) {
        this.productBarCode = value;
    }

    /**
     * Gets the value of the idShop property.
     * 
     */
    public long getIdShop() {
        return idShop;
    }

    /**
     * Sets the value of the idShop property.
     * 
     */
    public void setIdShop(long value) {
        this.idShop = value;
    }

}
