/*
 *  IReaderConnection.java
 *
 *  Created:	Jun 19, 2008
 *  Project:	RiFidi Emulator - A Software Simulation Tool for RFID Devices
 *  				http://www.rifidi.org
 *  				http://rifidi.sourceforge.net
 *  Copyright:	Pramari LLC and the Rifidi Project
 *  License:	Lesser GNU Public License (LGPL)
 *  				http://www.opensource.org/licenses/lgpl-license.html
 */
package org.rifidi.edge.core.connection;

import org.rifidi.edge.core.exception.RifidiException;
import org.rifidi.edge.core.readerPlugin.AbstractReaderInfo;
import org.rifidi.edge.core.readerPlugin.IReaderPlugin;
import org.rifidi.edge.core.readerPlugin.commands.ICustomCommand;
import org.rifidi.edge.core.readerPlugin.commands.ICustomCommandResult;
import org.rifidi.edge.core.readerPlugin.enums.EReaderAdapterState;


public interface IReaderConnection {

	//TODO Andreas: Need to change the name of this method.
	/**
	 * @return the adapter
	 */
	public abstract IReaderPlugin getAdapter();

	//TODO Andreas: Need to change the name of this method.
	/**
	 * @param adapter
	 *            the adapter to set
	 */
	public abstract void setAdapter(IReaderPlugin adapter);

	//TODO Andreas: Need to change the name of this method.
	/**
	 * @return the sessionID
	 */
	public abstract int getSessionID();

	//TODO Andreas: Need to change the name of this method.
	/**
	 * @param sessionID
	 *            the sessionID to set
	 */
	public abstract void setSessionID(int sessionID);

	/**
	 * @return the connectionInfo
	 */
	public abstract AbstractReaderInfo getConnectionInfo();

	/**
	 * @param connectionInfo
	 *            the connectionInfo to set
	 */
	public abstract void setConnectionInfo(AbstractReaderInfo connectionInfo);

	/**
	 * @param customCommand
	 * @return
	 */
	public abstract ICustomCommandResult sendCustomCommand(
			ICustomCommand customCommand) throws RifidiException;

	/**
	 * Starts the tag streaming
	 */
	public abstract void startTagStream() throws RifidiException;

	/**
	 * Stops the tag streaming.
	 */
	public abstract void stopTagStream() throws RifidiException;

	/**
	 * @return The currect state of the reader.
	 */
	public abstract EReaderAdapterState getState();

	/**
	 * Connect to the reader
	 */
	public abstract void connect() throws RifidiException;

	/**
	 * Disconnect from the reader
	 */
	public abstract void disconnect() throws RifidiException;

	/**
	 * @return The cause of the error, null if there was none.
	 */
	public abstract Exception getErrorCause();

	/**
	 * Just for internal use
	 * 
	 * @param errorCause
	 *            the errorCause to set
	 */
	public abstract void setErrorCause(RifidiException errorCause);

}