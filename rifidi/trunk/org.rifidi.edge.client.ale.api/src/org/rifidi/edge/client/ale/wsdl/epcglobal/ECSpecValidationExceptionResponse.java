
package org.rifidi.edge.client.ale.wsdl.epcglobal;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.1.3
 * Mon Feb 09 15:04:22 CET 2009
 * Generated source version: 2.1.3
 * 
 */

@WebFault(name = "ECSpecValidationException", targetNamespace = "urn:epcglobal:ale:wsdl:1")
public class ECSpecValidationExceptionResponse extends Exception {
    public static final long serialVersionUID = 20090209150422L;
    
    private org.rifidi.edge.client.ale.wsdl.epcglobal.ECSpecValidationException ecSpecValidationException;

    public ECSpecValidationExceptionResponse() {
        super();
    }
    
    public ECSpecValidationExceptionResponse(String message) {
        super(message);
    }
    
    public ECSpecValidationExceptionResponse(String message, Throwable cause) {
        super(message, cause);
    }

    public ECSpecValidationExceptionResponse(String message, org.rifidi.edge.client.ale.wsdl.epcglobal.ECSpecValidationException ecSpecValidationException) {
        super(message);
        this.ecSpecValidationException = ecSpecValidationException;
    }

    public ECSpecValidationExceptionResponse(String message, org.rifidi.edge.client.ale.wsdl.epcglobal.ECSpecValidationException ecSpecValidationException, Throwable cause) {
        super(message, cause);
        this.ecSpecValidationException = ecSpecValidationException;
    }

    public org.rifidi.edge.client.ale.wsdl.epcglobal.ECSpecValidationException getFaultInfo() {
        return this.ecSpecValidationException;
    }
}
