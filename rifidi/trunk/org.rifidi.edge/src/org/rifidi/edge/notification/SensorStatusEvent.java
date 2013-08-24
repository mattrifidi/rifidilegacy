/*
 * Copyright (c) 2013 Transcends, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */
package org.rifidi.edge.notification;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * This is an abstract class for Sensor Status Events (such as when a session is
 * connected or disconnected) to implement. These objects are submitted to
 * esper.
 * 
 * @author Kyle Neumeier - kyle@pramari.com
 * 
 */
public abstract class SensorStatusEvent implements Serializable {
	/** the serial Version ID */
	private static final long serialVersionUID = 1L;
	/** The ID of the sensor */
	private final String sensorID;
	/** The timestamp that went down */
	private final Long timestamp;
	/** A date format used in the toString method */
	protected static SimpleDateFormat dateFormat;
	static {
		dateFormat = new SimpleDateFormat("yyyyy.MMMMM.dd HH:mm:ss");
	}

	/**
	 * Constructor
	 * 
	 * @param sensorID
	 *            The ID of the sensor for this event.
	 * @param timestamp
	 *            The timestamp of when this event happened.
	 */
	public SensorStatusEvent(String readerID, Long timestamp) {
		super();
		this.sensorID = readerID;
		this.timestamp = timestamp;
	}

	/**
	 * @return the ID of the Sensor for this event
	 */
	public String getSensorID() {
		return sensorID;
	}

	/**
	 * @return the time this event happened.
	 */
	public Long getTimestamp() {
		return timestamp;
	}

}
