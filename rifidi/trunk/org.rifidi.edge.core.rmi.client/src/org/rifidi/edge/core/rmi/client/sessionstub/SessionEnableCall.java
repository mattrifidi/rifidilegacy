/*
 *  SessionEnableCall.java
 *
 *  Project: Rifidi - A developer tool for RFID
 *  http://www.rifidi.org
 *  http://rifidi.sourceforge.net
 *  Copyright: Pramari LLC and the Rifidi Project
 *  License: Lesser GNU Public License (LGPL)
 *  http://www.opensource.org/licenses/lgpl-license.html
 */

package org.rifidi.edge.core.rmi.client.sessionstub;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.rifidi.edge.core.rmi.readerconnection.ReaderSessionStub;
import org.rifidi.rmi.utils.cache.ServerDescription;
import org.rifidi.rmi.utils.remotecall.ServerDescriptionBasedRemoteMethodCall;

/**
 * 
 * This moves the reader session from the configured state to the OK state
 * 
 * @author Kyle Neumeier - kyle@pramari.com
 * 
 */
public class SessionEnableCall extends
		ServerDescriptionBasedRemoteMethodCall<Object, RuntimeException> {

	/**
	 * This moves the reader session from the configured state to the OK state.
	 * It returns null.
	 * 
	 * @param serverDescription
	 *            The description of the RMI stub
	 */
	public SessionEnableCall(ServerDescription serverDescription) {
		super(serverDescription);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rifidi.rmi.utils.remotecall.AbstractRemoteMethodCall#performRemoteCall
	 * (java.rmi.Remote)
	 */
	@Override
	protected Object performRemoteCall(Remote remoteObject)
			throws RemoteException, RuntimeException {
		((ReaderSessionStub) remoteObject).enable();
		return null;
	}

}
