/*******************************************************************************
 * Copyright (c) 2014 Transcends, LLC.
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU 
 * General Public License as published by the Free Software Foundation; either version 2 of the 
 * License, or (at your option) any later version. This program is distributed in the hope that it will 
 * be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You 
 * should have received a copy of the GNU General Public License along with this program; if not, 
 * write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, 
 * USA. 
 * http://www.gnu.org/licenses/gpl-2.0.html
 *******************************************************************************/
package org.rifidi.edge.notification;

import javax.management.AttributeList;

import org.rifidi.edge.api.SessionStatus;

/**
 * Interface for a Service that other components can use to send notifications
 * to the edge server client of certain events happening
 * 
 * @author Kyle Neumeier - kyle@pramari.com
 */
public interface NotifierService {

	/**
	 * Send a notification that a Session has been added
	 * 
	 * @param readerID
	 *            The ID of the reader to which the session was added
	 * @param sessionID
	 *            The ID of the session
	 */
	public void addSessionEvent(String readerID, String sessionID);

	/**
	 * Send a notification that a session has been removed
	 * 
	 * @param readerID
	 *            The ID of the reader from which the session was removed
	 * @param sessionID
	 *            The ID of the session that was removed
	 */
	public void removeSessionEvent(String readerID, String sessionID);

	/**
	 * Send a notification that a Reader was added
	 * 
	 * @param readerID
	 *            The ID of the reader
	 */
	public void addReaderEvent(String readerID);

	/**
	 * Send a notification that a reader was removed
	 * 
	 * @param readerID
	 *            the ID of the reader
	 */
	public void removeReaderEvent(String readerID);

	/**
	 * Send a notification that a command was added.
	 * 
	 * @param commandID
	 *            the ID of the command
	 */
	public void addCommandEvent(String commandID);

	/**
	 * Send a notification that a command was removed
	 * 
	 * @param commandID
	 *            The ID of the command
	 */
	public void removeCommandEvent(String commandID);

	/**
	 * Send a notification that a reader factory became available
	 * 
	 * @param readerFactoryID
	 *            The ID of the readerFactory that became available
	 */
	public void addReaderFactoryEvent(String readerFactoryID);

	/**
	 * Send a notification that a reader factory became unavailable.
	 * 
	 * @param readerFactoryID
	 *            The ID of the readerFactory that is no longer available
	 */
	public void removeReaderFactoryEvent(String readerFactoryID);

	/**
	 * Send a notification that the state of a session has changed
	 * 
	 * @param readerID
	 *            The ID of the reader that contains the session
	 * @param sessionID
	 *            The ID of the session that changed
	 * @param sessionStatus
	 *            The new state of the session
	 */
	public void sessionStatusChanged(String readerID, String sessionID,
			SessionStatus sessionStatus);

	/**
	 * Send a notification that a CommandConfigurationFactory has become
	 * available
	 * 
	 * @param readerFactoryID
	 *            the ID of the readerFactory this command configuration factory
	 *            works with
	 * @param commandConfigFactoryID
	 *            The ID of the command Configuration factory
	 */
	public void addCommandConfigFactoryEvent(String readerFactoryID,
			String commandConfigFactoryID);

	/**
	 * Send a notification that a CommandCofifugrationFactory has become
	 * unavailable.
	 * 
	 * @param readerFactoryID
	 *            the ID of the readerFactory the command factory works with
	 * @param commandConfigFactoryID
	 *            The ID of the commandConfigurationFactory that is no longer
	 *            available
	 */
	public void removeCommandConfigFactoryEvent(String readerFactoryID,
			String commandFactoryID);

	/**
	 * Send a notification that a job has been submitted to a session for
	 * execution
	 * 
	 * @param readerID
	 *            The ID of the reader that has the session
	 * @param sessionID
	 *            The ID of the session which the job is executing on
	 * @param jobID
	 *            The ID of the job
	 * @param commandID
	 *            The ID of the command configuration which produced the job
	 * @param recurring
	 *            True if the command is a recurring command
	 */
	public void jobSubmitted(String readerID, String sessionID, Integer jobID,
			String commandID, boolean recurring);

	/**
	 * Send a notification that a job has been killed.
	 * 
	 * @param readerID
	 *            The ID of the reader which contains the session
	 * @param sessionID
	 *            The ID of the session the job was executing on
	 * @param jobID
	 *            The ID of the job that was killed
	 */
	public void jobDeleted(String readerID, String sessionID, Integer jobID);

	/**
	 * Called when attributes of a configuration change
	 * 
	 * @param configurationID
	 *            the ID of the configuration
	 * @param attributes
	 *            The changed attributes
	 */
	void attributesChanged(String configurationID, AttributeList attributes);
	
	void tagSeen(ReadCycle cycle);

}
