package org.rifidi.edge.core.api.rmi.dto;
//TODO: Comments
import java.io.Serializable;
import java.util.Map;

import org.rifidi.edge.core.api.SessionStatus;

/**
 * A Data Transfer Object for information about a session
 * 
 * @author Kyle Neumeier - kyle@pramari.com
 * 
 */
public class SessionDTO implements Serializable {

	/** SerialVersionID */
	private static final long serialVersionUID = 1L;
	/** The status of this session */
	private SessionStatus status;
	/** The Commands being executed. Integer is processID, String is command ID */
	private Map<Integer, String> commands;
	/** The ID of the session */
	private String ID;

	/***
	 * Constructor
	 * 
	 * @param ID
	 *            The ID of the session
	 * @param status
	 *            The status of the session
	 * @param commands
	 *            The Commands being executed. Integer is processID, String is
	 *            command ID
	 */
	public SessionDTO(String ID, SessionStatus status,
			Map<Integer, String> commands) {
		this.ID = ID;
		this.status = status;
		this.commands = commands;
	}

	/**
	 * @return the status of the session
	 */
	public SessionStatus getStatus() {
		return status;
	}

	/**
	 * @return The Commands being executed. Integer is process ID for this
	 *         session, String is Command ID
	 */
	public Map<Integer, String> getCommands() {
		return commands;
	}

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

}
