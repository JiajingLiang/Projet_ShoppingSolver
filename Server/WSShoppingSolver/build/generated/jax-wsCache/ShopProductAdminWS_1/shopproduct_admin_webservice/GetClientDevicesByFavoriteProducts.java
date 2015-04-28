
package shopproduct_admin_webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getClientDevicesByFavoriteProducts complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getClientDevicesByFavoriteProducts">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productBarCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getClientDevicesByFavoriteProducts", propOrder = {
    "productBarCode"
})
public class GetClientDevicesByFavoriteProducts {

    protected String productBarCode;

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

}
