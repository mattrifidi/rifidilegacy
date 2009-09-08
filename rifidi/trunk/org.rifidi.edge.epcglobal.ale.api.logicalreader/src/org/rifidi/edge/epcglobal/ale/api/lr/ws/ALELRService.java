/*
 * 
 * ALELRService.java
 *  
 * Created:     July 8th, 2009
 * Project:       Rifidi Edge Server - A middleware platform for RFID applications
 *                   http://www.rifidi.org
 *                   http://rifidi.sourceforge.net
 * Copyright:   Pramari LLC and the Rifidi Project
 * License:      The software in this package is published under the terms of the GPL License
 *                   A copy of the license is included in this distribution under RifidiEdge-License.txt 
 */

/*
 * 
 */

package org.rifidi.edge.epcglobal.ale.api.lr.ws;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by Apache CXF 2.1.3
 * Thu Jan 29 11:03:18 EST 2009
 * Generated source version: 2.1.3
 * 
 */


@WebServiceClient(name = "ALELRService", 
                  wsdlLocation = "file:./ale/EPCglobal-ale-1_1-alelr.wsdl",
                  targetNamespace = "urn:epcglobal:alelr:wsdl:1") 
public class ALELRService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("urn:epcglobal:alelr:wsdl:1", "ALELRService");
    public final static QName ALELRServicePort = new QName("urn:epcglobal:alelr:wsdl:1", "ALELRServicePort");
    static {
        URL url = null;
        try {
            url = new URL("file:./ale/EPCglobal-ale-1_1-alelr.wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:./ale/EPCglobal-ale-1_1-alelr.wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public ALELRService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ALELRService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ALELRService() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns ALELRServicePortType
     */
    @WebEndpoint(name = "ALELRServicePort")
    public ALELRServicePortType getALELRServicePort() {
        return super.getPort(ALELRServicePort, ALELRServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ALELRServicePortType
     */
    @WebEndpoint(name = "ALELRServicePort")
    public ALELRServicePortType getALELRServicePort(WebServiceFeature... features) {
        return super.getPort(ALELRServicePort, ALELRServicePortType.class, features);
    }

}
