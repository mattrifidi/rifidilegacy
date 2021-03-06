/*
 * ESMS_GetStartupTimestampCommand.java
 * 
 * Created:     July 8th, 2009
 * Project:       Rifidi Edge Server - A middleware platform for RFID applications
 *                    http://www.rifidi.org
 *                    http://rifidi.sourceforge.net
 * Copyright:   Pramari LLC and the Rifidi Project
 * License:      The software in this package is published under the terms of the EPL License
 *                    A copy of the license is included in this distribution under Rifidi-License.txt 
 */
package org.rifidi.edge.core.rmi.client.edgeserverstub;

import org.rifidi.edge.api.EdgeServerManagerService;
import org.rifidi.rmi.proxycache.cache.AbstractRMICommandObject;

/**
 * This call gets the timestamp of when the server started up
 * 
 * @author Kyle Neumeier - kyle@pramari.com
 */
public class ESMS_GetStartupTimestampCommand extends
		AbstractRMICommandObject<Long, RuntimeException> {

	/**
	 * Constructor.
	 * 
	 * @param serverDescription
	 *            the server description to use
	 */
	public ESMS_GetStartupTimestampCommand(ESMS_ServerDescription serverDescription) {
		super(serverDescription);
	}

	/* (non-Javadoc)
	 * @see org.rifidi.rmi.proxycache.cache.AbstractRMICommandObject#performRemoteCall(java.lang.Object)
	 */
	@Override
	protected Long performRemoteCall(Object remoteObject)
			throws RuntimeException {
		return ((EdgeServerManagerService)remoteObject).getStartupTime();
	}
	
	
	
}
