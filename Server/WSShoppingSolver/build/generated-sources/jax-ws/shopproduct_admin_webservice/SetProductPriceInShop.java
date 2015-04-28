
package shopproduct_admin_webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for setProductPriceInShop complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="setProductPriceInShop">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productBarCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idShop" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="newPrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setProductPriceInShop", propOrder = {
    "productBarCode",
    "idShop",
    "newPrice"
})
public class SetProductPriceInShop {

    protected String productBarCode;
    protected long idShop;
    protected double newPrice;

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

    /**
     * Gets the value of the newPrice property.
     * 
     */
    public double getNewPrice() {
        return newPrice;
    }

    /**
     * Sets the value of the newPrice property.
     * 
     */
    public void setNewPrice(double value) {
        this.newPrice = value;
    }

}
