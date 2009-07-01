package org.rifidi.edge.core.rmi.client.readerconfigurationstub;

import org.rifidi.edge.api.rmi.SensorManagerService;
import org.rifidi.rmi.proxycache.cache.AbstractRMICommandObject;

/**
 * This command starts a session on the reader. It returns null.
 * 
 * @author Kyle Nuemeier - kyle@pramari.com
 */
public class RS_StartSession extends
		AbstractRMICommandObject<Object, RuntimeException> {

	/** The ID of the reader */
	private String readerID;
	/** The index of the session to start */
	private String sessionID;

	/**
	 * @param serverDescription
	 *            The serverDescription
	 * @param readerID
	 *            The ID of the reader to start the session on
	 * @param sessionID
	 *            The index number of the session to start
	 */
	public RS_StartSession(RS_ServerDescription serverDescription,
			String readerID, String sessionID) {
		super(serverDescription);
		this.readerID = readerID;
		this.sessionID = sessionID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rifidi.rmi.proxycache.cache.AbstractRMICommandObject#performRemoteCall
	 * (java.lang.Object)
	 */
	@Override
	protected Object performRemoteCall(Object remoteObject)
			throws RuntimeException {
		SensorManagerService stub = (SensorManagerService) remoteObject;
		stub.startSession(readerID, sessionID);
		return null;
	}

}
