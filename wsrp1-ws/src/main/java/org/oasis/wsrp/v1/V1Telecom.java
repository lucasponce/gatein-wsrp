
/*
 * JBoss, a division of Red Hat
 * Copyright 2010, Red Hat Middleware, LLC, and individual
 * contributors as indicated by the @authors tag. See the
 * copyright.txt in the distribution for a full listing of
 * individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.oasis.wsrp.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Telecom complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Telecom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="telephone" type="{urn:oasis:names:tc:wsrp:v1:types}TelephoneNum" minOccurs="0"/>
 *         &lt;element name="fax" type="{urn:oasis:names:tc:wsrp:v1:types}TelephoneNum" minOccurs="0"/>
 *         &lt;element name="mobile" type="{urn:oasis:names:tc:wsrp:v1:types}TelephoneNum" minOccurs="0"/>
 *         &lt;element name="pager" type="{urn:oasis:names:tc:wsrp:v1:types}TelephoneNum" minOccurs="0"/>
 *         &lt;element name="extensions" type="{urn:oasis:names:tc:wsrp:v1:types}Extension" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Telecom", propOrder = {
    "telephone",
    "fax",
    "mobile",
    "pager",
    "extensions"
})
public class V1Telecom {

    protected V1TelephoneNum telephone;
    protected V1TelephoneNum fax;
    protected V1TelephoneNum mobile;
    protected V1TelephoneNum pager;
    protected List<V1Extension> extensions;

    /**
     * Gets the value of the telephone property.
     * 
     * @return
     *     possible object is
     *     {@link V1TelephoneNum }
     *     
     */
    public V1TelephoneNum getTelephone() {
        return telephone;
    }

    /**
     * Sets the value of the telephone property.
     * 
     * @param value
     *     allowed object is
     *     {@link V1TelephoneNum }
     *     
     */
    public void setTelephone(V1TelephoneNum value) {
        this.telephone = value;
    }

    /**
     * Gets the value of the fax property.
     * 
     * @return
     *     possible object is
     *     {@link V1TelephoneNum }
     *     
     */
    public V1TelephoneNum getFax() {
        return fax;
    }

    /**
     * Sets the value of the fax property.
     * 
     * @param value
     *     allowed object is
     *     {@link V1TelephoneNum }
     *     
     */
    public void setFax(V1TelephoneNum value) {
        this.fax = value;
    }

    /**
     * Gets the value of the mobile property.
     * 
     * @return
     *     possible object is
     *     {@link V1TelephoneNum }
     *     
     */
    public V1TelephoneNum getMobile() {
        return mobile;
    }

    /**
     * Sets the value of the mobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link V1TelephoneNum }
     *     
     */
    public void setMobile(V1TelephoneNum value) {
        this.mobile = value;
    }

    /**
     * Gets the value of the pager property.
     * 
     * @return
     *     possible object is
     *     {@link V1TelephoneNum }
     *     
     */
    public V1TelephoneNum getPager() {
        return pager;
    }

    /**
     * Sets the value of the pager property.
     * 
     * @param value
     *     allowed object is
     *     {@link V1TelephoneNum }
     *     
     */
    public void setPager(V1TelephoneNum value) {
        this.pager = value;
    }

    /**
     * Gets the value of the extensions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extensions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtensions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link V1Extension }
     * 
     * 
     */
    public List<V1Extension> getExtensions() {
        if (extensions == null) {
            extensions = new ArrayList<V1Extension>();
        }
        return this.extensions;
    }

}
