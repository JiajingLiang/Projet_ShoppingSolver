
package com.polymtl.wsshoppingsolver.webservice_client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addProductToShop complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addProductToShop">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idProduct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idShop" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="ratioTaxFederal" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="ratioTaxProvincial" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addProductToShop", propOrder = {
    "idProduct",
    "idShop",
    "price",
    "ratioTaxFederal",
    "ratioTaxProvincial"
})
public class AddProductToShop {

    protected String idProduct;
    protected long idShop;
    protected Double price;
    protected Float ratioTaxFederal;
    protected Float ratioTaxProvincial;

    /**
     * Gets the value of the idProduct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdProduct() {
        return idProduct;
    }

    /**
     * Sets the value of the idProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdProduct(String value) {
        this.idProduct = value;
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

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPrice(Double value) {
        this.price = value;
    }

    /**
     * Gets the value of the ratioTaxFederal property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getRatioTaxFederal() {
        return ratioTaxFederal;
    }

    /**
     * Sets the value of the ratioTaxFederal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setRatioTaxFederal(Float value) {
        this.ratioTaxFederal = value;
    }

    /**
     * Gets the value of the ratioTaxProvincial property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getRatioTaxProvincial() {
        return ratioTaxProvincial;
    }

    /**
     * Sets the value of the ratioTaxProvincial property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setRatioTaxProvincial(Float value) {
        this.ratioTaxProvincial = value;
    }

}
