
package org.rifidi.edge.epcglobal.ale.api.lr.ws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.1.3
 * Thu Jan 29 11:03:18 EST 2009
 * Generated source version: 2.1.3
 * 
 */

@WebFault(name = "ReaderLoopException", targetNamespace = "urn:epcglobal:alelr:wsdl:1")
public class ReaderLoopExceptionResponse extends Exception {
    public static final long serialVersionUID = 20090129110318L;
    
    private org.rifidi.edge.epcglobal.ale.api.lr.ws.ReaderLoopException readerLoopException;

    public ReaderLoopExceptionResponse() {
        super();
    }
    
    public ReaderLoopExceptionResponse(String message) {
        super(message);
    }
    
    public ReaderLoopExceptionResponse(String message, Throwable cause) {
        super(message, cause);
    }

    public ReaderLoopExceptionResponse(String message, org.rifidi.edge.epcglobal.ale.api.lr.ws.ReaderLoopException readerLoopException) {
        super(message);
        this.readerLoopException = readerLoopException;
    }

    public ReaderLoopExceptionResponse(String message, org.rifidi.edge.epcglobal.ale.api.lr.ws.ReaderLoopException readerLoopException, Throwable cause) {
        super(message, cause);
        this.readerLoopException = readerLoopException;
    }

    public org.rifidi.edge.epcglobal.ale.api.lr.ws.ReaderLoopException getFaultInfo() {
        return this.readerLoopException;
    }
}
