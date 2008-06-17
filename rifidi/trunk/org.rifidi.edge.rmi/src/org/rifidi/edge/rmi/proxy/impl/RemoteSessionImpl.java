package org.rifidi.edge.rmi.proxy.impl;

import java.rmi.RemoteException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rifidi.edge.core.connection.ReaderConnection;
import org.rifidi.edge.core.readerPlugin.commands.ICustomCommand;
import org.rifidi.edge.rmi.proxy.RemoteReaderConnection;

public class RemoteSessionImpl implements RemoteReaderConnection {

	private Log logger = LogFactory.getLog(RemoteSessionRegistryImpl.class);

	private ReaderConnection readerConnection;

	public RemoteSessionImpl(ReaderConnection session) {
		this.readerConnection = session;
	}

	@Override
	public void sendCustomCommand(ICustomCommand customCommand)
			throws RemoteException {
		logger.debug("Remote Call: sendCustomCommand()");
		readerConnection.sendCustomCommand(customCommand);
	}

	@Override
	public void startTagStream() throws RemoteException {
		logger.debug("Remote Call: startTagStream()");
		readerConnection.startTagStream();
	}

	@Override
	public void stopTagStream() throws RemoteException {
		logger.debug("Remote Call: stopTagStream()");
		readerConnection.stopTagStream();
	}
	
	// TODO Think about a better place for this
	public ReaderConnection getSession() {
		return readerConnection;
	}

}
