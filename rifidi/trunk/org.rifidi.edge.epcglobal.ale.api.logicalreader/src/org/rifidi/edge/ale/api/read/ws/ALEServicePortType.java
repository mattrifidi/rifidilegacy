/*
 * 
 * ALEServicePortType.java
 *  
 * Created:     July 8th, 2009
 * Project:       Rifidi Edge Server - A middleware platform for RFID applications
 *                   http://www.rifidi.org
 *                   http://rifidi.sourceforge.net
 * Copyright:   Pramari LLC and the Rifidi Project
 * License:      The software in this package is published under the terms of the GPL License
 *                   A copy of the license is included in this distribution under RifidiEdge-License.txt 
 */
package org.rifidi.edge.ale.api.read.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.1.3
 * Wed Jan 28 17:30:03 EST 2009
 * Generated source version: 2.1.3
 * 
 */
 
@WebService(targetNamespace = "urn:epcglobal:ale:wsdl:1", name = "ALEServicePortType")
@XmlSeeAlso({org.rifidi.edge.ale.api.read.ObjectFactory.class,org.rifidi.edge.ale.api.read.data.ObjectFactory.class,ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface ALEServicePortType {

    @WebResult(name = "VoidHolder", targetNamespace = "urn:epcglobal:ale:wsdl:1", partName = "undefineReturn")
    @WebMethod
    public VoidHolder undefine(
        @WebParam(partName = "parms", name = "Undefine", targetNamespace = "urn:epcglobal:ale:wsdl:1")
        Undefine parms
    ) throws ImplementationExceptionResponse, NoSuchNameExceptionResponse, SecurityExceptionResponse;

    @WebResult(name = "GetECSpecNamesResult", targetNamespace = "urn:epcglobal:ale:wsdl:1", partName = "getECSpecNamesReturn")
    @WebMethod
    public ArrayOfString getECSpecNames(
        @WebParam(partName = "parms", name = "GetECSpecNames", targetNamespace = "urn:epcglobal:ale:wsdl:1")
        EmptyParms parms
    ) throws ImplementationExceptionResponse, SecurityExceptionResponse;

    @WebResult(name = "ImmediateResult", targetNamespace = "urn:epcglobal:ale:wsdl:1", partName = "immediateReturn")
    @WebMethod
    public org.rifidi.edge.ale.api.read.data.ECReports immediate(
        @WebParam(partName = "parms", name = "Immediate", targetNamespace = "urn:epcglobal:ale:wsdl:1")
        Immediate parms
    ) throws ImplementationExceptionResponse, SecurityExceptionResponse, ECSpecValidationExceptionResponse;

    @WebResult(name = "VoidHolder", targetNamespace = "urn:epcglobal:ale:wsdl:1", partName = "defineReturn")
    @WebMethod
    public VoidHolder define(
        @WebParam(partName = "parms", name = "Define", targetNamespace = "urn:epcglobal:ale:wsdl:1")
        Define parms
    ) throws ImplementationExceptionResponse, SecurityExceptionResponse, ECSpecValidationExceptionResponse, DuplicateNameExceptionResponse;

    @WebResult(name = "VoidHolder", targetNamespace = "urn:epcglobal:ale:wsdl:1", partName = "unsubscribeReturn")
    @WebMethod
    public VoidHolder unsubscribe(
        @WebParam(partName = "parms", name = "Unsubscribe", targetNamespace = "urn:epcglobal:ale:wsdl:1")
        Unsubscribe parms
    ) throws ImplementationExceptionResponse, NoSuchNameExceptionResponse, NoSuchSubscriberExceptionResponse, SecurityExceptionResponse, InvalidURIExceptionResponse;

    @WebResult(name = "GetECSpecResult", targetNamespace = "urn:epcglobal:ale:wsdl:1", partName = "getECSpecReturn")
    @WebMethod
    public org.rifidi.edge.ale.api.read.data.ECSpec getECSpec(
        @WebParam(partName = "parms", name = "GetECSpec", targetNamespace = "urn:epcglobal:ale:wsdl:1")
        GetECSpec parms
    ) throws ImplementationExceptionResponse, NoSuchNameExceptionResponse, SecurityExceptionResponse;

    @WebResult(name = "GetVendorVersionResult", targetNamespace = "urn:epcglobal:ale:wsdl:1", partName = "getVendorVersionReturn")
    @WebMethod
    public java.lang.String getVendorVersion(
        @WebParam(partName = "parms", name = "GetVendorVersion", targetNamespace = "urn:epcglobal:ale:wsdl:1")
        EmptyParms parms
    ) throws ImplementationExceptionResponse;

    @WebResult(name = "GetSubscribersResult", targetNamespace = "urn:epcglobal:ale:wsdl:1", partName = "getSubscribersReturn")
    @WebMethod
    public ArrayOfString getSubscribers(
        @WebParam(partName = "parms", name = "GetSubscribers", targetNamespace = "urn:epcglobal:ale:wsdl:1")
        GetSubscribers parms
    ) throws ImplementationExceptionResponse, NoSuchNameExceptionResponse, SecurityExceptionResponse;

    @WebResult(name = "PollResult", targetNamespace = "urn:epcglobal:ale:wsdl:1", partName = "pollReturn")
    @WebMethod
    public org.rifidi.edge.ale.api.read.data.ECReports poll(
        @WebParam(partName = "parms", name = "Poll", targetNamespace = "urn:epcglobal:ale:wsdl:1")
        Poll parms
    ) throws ImplementationExceptionResponse, NoSuchNameExceptionResponse, SecurityExceptionResponse;

    @WebResult(name = "GetStandardVersionResult", targetNamespace = "urn:epcglobal:ale:wsdl:1", partName = "getStandardVersionReturn")
    @WebMethod
    public java.lang.String getStandardVersion(
        @WebParam(partName = "parms", name = "GetStandardVersion", targetNamespace = "urn:epcglobal:ale:wsdl:1")
        EmptyParms parms
    ) throws ImplementationExceptionResponse;

    @WebResult(name = "VoidHolder", targetNamespace = "urn:epcglobal:ale:wsdl:1", partName = "subscribeReturn")
    @WebMethod
    public VoidHolder subscribe(
        @WebParam(partName = "parms", name = "Subscribe", targetNamespace = "urn:epcglobal:ale:wsdl:1")
        Subscribe parms
    ) throws ImplementationExceptionResponse, NoSuchNameExceptionResponse, DuplicateSubscriptionExceptionResponse, SecurityExceptionResponse, InvalidURIExceptionResponse;
}
