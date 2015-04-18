
package com.polymtl.wsshoppingsolver.webservice_client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getAllProductsInShop complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getAllProductsInShop">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
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
@XmlType(name = "getAllProductsInShop", propOrder = {
    "idShop"
})
public class GetAllProductsInShop {

    protected long idShop;

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
