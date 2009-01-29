
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package org.rifidi.edge.epcglobal.ale.api.lr.ws;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.1.3
 * Thu Jan 29 11:03:18 EST 2009
 * Generated source version: 2.1.3
 * 
 */

@javax.jws.WebService(
                      serviceName = "ALELRService",
                      portName = "ALELRServicePort",
                      targetNamespace = "urn:epcglobal:alelr:wsdl:1",
                      wsdlLocation = "file:./ale/EPCglobal-ale-1_1-alelr.wsdl",
                      endpointInterface = "org.rifidi.edge.epcglobal.ale.api.lr.ws.ALELRServicePortType")
                      
public class ALELRServicePortTypeImpl implements ALELRServicePortType {

    private static final Logger LOG = Logger.getLogger(ALELRServicePortTypeImpl.class.getName());

    /* (non-Javadoc)
     * @see org.rifidi.edge.epcglobal.ale.api.lr.ws.ALELRServicePortType#getLRSpec(org.rifidi.edge.epcglobal.ale.api.lr.ws.GetLRSpec  parms )*
     */
    public org.rifidi.edge.epcglobal.ale.api.lr.data.LRSpec getLRSpec(GetLRSpec parms) throws SecurityExceptionResponse , ImplementationExceptionResponse , NoSuchNameExceptionResponse    { 
        LOG.info("Executing operation getLRSpec");
        System.out.println(parms);
        try {
            org.rifidi.edge.epcglobal.ale.api.lr.data.LRSpec _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SecurityExceptionResponse("SecurityExceptionResponse...");
        //throw new ImplementationExceptionResponse("ImplementationExceptionResponse...");
        //throw new NoSuchNameExceptionResponse("NoSuchNameExceptionResponse...");
    }

    /* (non-Javadoc)
     * @see org.rifidi.edge.epcglobal.ale.api.lr.ws.ALELRServicePortType#getStandardVersion(org.rifidi.edge.epcglobal.ale.api.lr.ws.EmptyParms  parms )*
     */
    public java.lang.String getStandardVersion(EmptyParms parms) throws ImplementationExceptionResponse    { 
        LOG.info("Executing operation getStandardVersion");
        System.out.println(parms);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new ImplementationExceptionResponse("ImplementationExceptionResponse...");
    }

    /* (non-Javadoc)
     * @see org.rifidi.edge.epcglobal.ale.api.lr.ws.ALELRServicePortType#undefine(org.rifidi.edge.epcglobal.ale.api.lr.ws.Undefine  parms )*
     */
    public org.rifidi.edge.epcglobal.ale.api.lr.ws.UndefineResult undefine(Undefine parms) throws SecurityExceptionResponse , InUseExceptionResponse , ImplementationExceptionResponse , ImmutableReaderExceptionResponse , NoSuchNameExceptionResponse    { 
        LOG.info("Executing operation undefine");
        System.out.println(parms);
        try {
            org.rifidi.edge.epcglobal.ale.api.lr.ws.UndefineResult _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SecurityExceptionResponse("SecurityExceptionResponse...");
        //throw new InUseExceptionResponse("InUseExceptionResponse...");
        //throw new ImplementationExceptionResponse("ImplementationExceptionResponse...");
        //throw new ImmutableReaderExceptionResponse("ImmutableReaderExceptionResponse...");
        //throw new NoSuchNameExceptionResponse("NoSuchNameExceptionResponse...");
    }

    /* (non-Javadoc)
     * @see org.rifidi.edge.epcglobal.ale.api.lr.ws.ALELRServicePortType#define(org.rifidi.edge.epcglobal.ale.api.lr.ws.Define  parms )*
     */
    public org.rifidi.edge.epcglobal.ale.api.lr.ws.DefineResult define(Define parms) throws SecurityExceptionResponse , ImplementationExceptionResponse , DuplicateNameExceptionResponse , ValidationExceptionResponse    { 
        LOG.info("Executing operation define");
        System.out.println(parms);
        try {
            org.rifidi.edge.epcglobal.ale.api.lr.ws.DefineResult _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SecurityExceptionResponse("SecurityExceptionResponse...");
        //throw new ImplementationExceptionResponse("ImplementationExceptionResponse...");
        //throw new DuplicateNameExceptionResponse("DuplicateNameExceptionResponse...");
        //throw new ValidationExceptionResponse("ValidationExceptionResponse...");
    }

    /* (non-Javadoc)
     * @see org.rifidi.edge.epcglobal.ale.api.lr.ws.ALELRServicePortType#setProperties(org.rifidi.edge.epcglobal.ale.api.lr.ws.SetProperties  parms )*
     */
    public org.rifidi.edge.epcglobal.ale.api.lr.ws.SetPropertiesResult setProperties(SetProperties parms) throws SecurityExceptionResponse , InUseExceptionResponse , ImplementationExceptionResponse , ImmutableReaderExceptionResponse , NoSuchNameExceptionResponse , ValidationExceptionResponse    { 
        LOG.info("Executing operation setProperties");
        System.out.println(parms);
        try {
            org.rifidi.edge.epcglobal.ale.api.lr.ws.SetPropertiesResult _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SecurityExceptionResponse("SecurityExceptionResponse...");
        //throw new InUseExceptionResponse("InUseExceptionResponse...");
        //throw new ImplementationExceptionResponse("ImplementationExceptionResponse...");
        //throw new ImmutableReaderExceptionResponse("ImmutableReaderExceptionResponse...");
        //throw new NoSuchNameExceptionResponse("NoSuchNameExceptionResponse...");
        //throw new ValidationExceptionResponse("ValidationExceptionResponse...");
    }

    /* (non-Javadoc)
     * @see org.rifidi.edge.epcglobal.ale.api.lr.ws.ALELRServicePortType#removeReaders(org.rifidi.edge.epcglobal.ale.api.lr.ws.RemoveReaders  parms )*
     */
    public org.rifidi.edge.epcglobal.ale.api.lr.ws.RemoveReadersResult removeReaders(RemoveReaders parms) throws NonCompositeReaderExceptionResponse , SecurityExceptionResponse , InUseExceptionResponse , ImplementationExceptionResponse , ImmutableReaderExceptionResponse , NoSuchNameExceptionResponse    { 
        LOG.info("Executing operation removeReaders");
        System.out.println(parms);
        try {
            org.rifidi.edge.epcglobal.ale.api.lr.ws.RemoveReadersResult _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new NonCompositeReaderExceptionResponse("NonCompositeReaderExceptionResponse...");
        //throw new SecurityExceptionResponse("SecurityExceptionResponse...");
        //throw new InUseExceptionResponse("InUseExceptionResponse...");
        //throw new ImplementationExceptionResponse("ImplementationExceptionResponse...");
        //throw new ImmutableReaderExceptionResponse("ImmutableReaderExceptionResponse...");
        //throw new NoSuchNameExceptionResponse("NoSuchNameExceptionResponse...");
    }

    /* (non-Javadoc)
     * @see org.rifidi.edge.epcglobal.ale.api.lr.ws.ALELRServicePortType#addReaders(org.rifidi.edge.epcglobal.ale.api.lr.ws.AddReaders  parms )*
     */
    public org.rifidi.edge.epcglobal.ale.api.lr.ws.AddReadersResult addReaders(AddReaders parms) throws ReaderLoopExceptionResponse , NonCompositeReaderExceptionResponse , SecurityExceptionResponse , InUseExceptionResponse , ImplementationExceptionResponse , ImmutableReaderExceptionResponse , NoSuchNameExceptionResponse , ValidationExceptionResponse    { 
        LOG.info("Executing operation addReaders");
        System.out.println(parms);
        try {
            org.rifidi.edge.epcglobal.ale.api.lr.ws.AddReadersResult _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new ReaderLoopExceptionResponse("ReaderLoopExceptionResponse...");
        //throw new NonCompositeReaderExceptionResponse("NonCompositeReaderExceptionResponse...");
        //throw new SecurityExceptionResponse("SecurityExceptionResponse...");
        //throw new InUseExceptionResponse("InUseExceptionResponse...");
        //throw new ImplementationExceptionResponse("ImplementationExceptionResponse...");
        //throw new ImmutableReaderExceptionResponse("ImmutableReaderExceptionResponse...");
        //throw new NoSuchNameExceptionResponse("NoSuchNameExceptionResponse...");
        //throw new ValidationExceptionResponse("ValidationExceptionResponse...");
    }

    /* (non-Javadoc)
     * @see org.rifidi.edge.epcglobal.ale.api.lr.ws.ALELRServicePortType#update(org.rifidi.edge.epcglobal.ale.api.lr.ws.Update  parms )*
     */
    public org.rifidi.edge.epcglobal.ale.api.lr.ws.UpdateResult update(Update parms) throws ReaderLoopExceptionResponse , SecurityExceptionResponse , InUseExceptionResponse , ImplementationExceptionResponse , ImmutableReaderExceptionResponse , NoSuchNameExceptionResponse , ValidationExceptionResponse    { 
        LOG.info("Executing operation update");
        System.out.println(parms);
        try {
            org.rifidi.edge.epcglobal.ale.api.lr.ws.UpdateResult _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new ReaderLoopExceptionResponse("ReaderLoopExceptionResponse...");
        //throw new SecurityExceptionResponse("SecurityExceptionResponse...");
        //throw new InUseExceptionResponse("InUseExceptionResponse...");
        //throw new ImplementationExceptionResponse("ImplementationExceptionResponse...");
        //throw new ImmutableReaderExceptionResponse("ImmutableReaderExceptionResponse...");
        //throw new NoSuchNameExceptionResponse("NoSuchNameExceptionResponse...");
        //throw new ValidationExceptionResponse("ValidationExceptionResponse...");
    }

    /* (non-Javadoc)
     * @see org.rifidi.edge.epcglobal.ale.api.lr.ws.ALELRServicePortType#setReaders(org.rifidi.edge.epcglobal.ale.api.lr.ws.SetReaders  parms )*
     */
    public org.rifidi.edge.epcglobal.ale.api.lr.ws.SetReadersResult setReaders(SetReaders parms) throws ReaderLoopExceptionResponse , NonCompositeReaderExceptionResponse , SecurityExceptionResponse , InUseExceptionResponse , ImplementationExceptionResponse , ImmutableReaderExceptionResponse , NoSuchNameExceptionResponse , ValidationExceptionResponse    { 
        LOG.info("Executing operation setReaders");
        System.out.println(parms);
        try {
            org.rifidi.edge.epcglobal.ale.api.lr.ws.SetReadersResult _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new ReaderLoopExceptionResponse("ReaderLoopExceptionResponse...");
        //throw new NonCompositeReaderExceptionResponse("NonCompositeReaderExceptionResponse...");
        //throw new SecurityExceptionResponse("SecurityExceptionResponse...");
        //throw new InUseExceptionResponse("InUseExceptionResponse...");
        //throw new ImplementationExceptionResponse("ImplementationExceptionResponse...");
        //throw new ImmutableReaderExceptionResponse("ImmutableReaderExceptionResponse...");
        //throw new NoSuchNameExceptionResponse("NoSuchNameExceptionResponse...");
        //throw new ValidationExceptionResponse("ValidationExceptionResponse...");
    }

    /* (non-Javadoc)
     * @see org.rifidi.edge.epcglobal.ale.api.lr.ws.ALELRServicePortType#getVendorVersion(org.rifidi.edge.epcglobal.ale.api.lr.ws.EmptyParms  parms )*
     */
    public java.lang.String getVendorVersion(EmptyParms parms) throws ImplementationExceptionResponse    { 
        LOG.info("Executing operation getVendorVersion");
        System.out.println(parms);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new ImplementationExceptionResponse("ImplementationExceptionResponse...");
    }

    /* (non-Javadoc)
     * @see org.rifidi.edge.epcglobal.ale.api.lr.ws.ALELRServicePortType#getLogicalReaderNames(org.rifidi.edge.epcglobal.ale.api.lr.ws.EmptyParms  parms )*
     */
    public org.rifidi.edge.epcglobal.ale.api.lr.ws.ArrayOfString getLogicalReaderNames(EmptyParms parms) throws SecurityExceptionResponse , ImplementationExceptionResponse    { 
        LOG.info("Executing operation getLogicalReaderNames");
        System.out.println(parms);
        try {
            org.rifidi.edge.epcglobal.ale.api.lr.ws.ArrayOfString _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SecurityExceptionResponse("SecurityExceptionResponse...");
        //throw new ImplementationExceptionResponse("ImplementationExceptionResponse...");
    }

    /* (non-Javadoc)
     * @see org.rifidi.edge.epcglobal.ale.api.lr.ws.ALELRServicePortType#getPropertyValue(org.rifidi.edge.epcglobal.ale.api.lr.ws.GetPropertyValue  parms )*
     */
    public java.lang.String getPropertyValue(GetPropertyValue parms) throws SecurityExceptionResponse , ImplementationExceptionResponse , NoSuchNameExceptionResponse    { 
        LOG.info("Executing operation getPropertyValue");
        System.out.println(parms);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new SecurityExceptionResponse("SecurityExceptionResponse...");
        //throw new ImplementationExceptionResponse("ImplementationExceptionResponse...");
        //throw new NoSuchNameExceptionResponse("NoSuchNameExceptionResponse...");
    }

}
