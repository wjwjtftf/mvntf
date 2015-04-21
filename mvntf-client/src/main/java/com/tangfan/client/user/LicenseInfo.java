
package com.tangfan.client.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for licenseInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="licenseInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="loginUser" type="{http://www.tangfan.org/user}user"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "licenseInfo", propOrder = {
    "loginUser"
})
public class LicenseInfo {

    @XmlElement(required = true)
    protected User loginUser;

    /**
     * Gets the value of the loginUser property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getLoginUser() {
        return loginUser;
    }

    /**
     * Sets the value of the loginUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setLoginUser(User value) {
        this.loginUser = value;
    }

}
