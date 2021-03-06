/*
 * 
 * TagReadEvent.java
 *  
 * Created:     July 8th, 2009
 * Project:       Rifidi Edge Server - A middleware platform for RFID applications
 *                   http://www.rifidi.org
 *                   http://rifidi.sourceforge.net
 * Copyright:   Pramari LLC and the Rifidi Project
 * License:      The software in this package is published under the terms of the GPL License
 *                   A copy of the license is included in this distribution under RifidiEdge-License.txt 
 */
/**
 * 
 */
package org.rifidi.edge.core.services.notification.data;

import java.io.Serializable;
import java.util.HashMap;

import org.rifidi.edge.api.tags.TagDTO;

/**
 * This class acts as a wrapper around a DataContainerEvent (which stores tag
 * data). This class stores extra information about a tag read event, such as
 * the antenna that the tag was seen on.
 * 
 * @author Kyle Neumeier - kyle@pramari.com
 */
public class TagReadEvent implements Serializable {

	/** Serial Version ID for this class */
	private static final long serialVersionUID = 1L;
	/** The tag event */
	private DatacontainerEvent tag;
	/** The antenna the tag was seen on */
	private int antennaID;
	/** The time the tag was read */
	private long timestamp;
	/** The ID of the reader that saw the tags */
	private String readerID;
	/**
	 * Any extra information that a tag contains would be stored here. Velocity
	 * or Distance information, or anything else about the tag which is not in
	 * the regular interface can go in here
	 */
	private HashMap<String, Serializable> extraInformation = null;

	/**
	 * Constructor
	 * 
	 * @param readerID
	 *            The ID of the reader that saw the tag
	 * @param tag
	 *            The tag data
	 * @param antennaID
	 *            The antenna the Tag was seen on
	 * @param timestamp
	 *            When the tag was seen.
	 */
	public TagReadEvent(String readerID, DatacontainerEvent tag, int antennaID,
			long timestamp) {
		this.tag = tag;
		this.antennaID = antennaID;
		this.timestamp = timestamp;
		this.readerID = readerID;
		this.extraInformation = new HashMap<String, Serializable>();
	}

	/**
	 * @return The ID of the reader that saw the tag
	 */
	public String getReaderID() {
		return readerID;
	}

	/**
	 * @return the Tag data
	 */
	public DatacontainerEvent getTag() {
		return tag;
	}

	/**
	 * @return The ID of the antenna that saw the tag
	 */
	public int getAntennaID() {
		return antennaID;
	}

	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @return A TagDTO which contains the ID for the tag, the Antenna ID, and
	 *         the timestamp the tag was seen on. This may be expanded later,
	 *         but currently the only client is the Edge Client, and this is all
	 *         the information it needs.
	 */
	public TagDTO getTagDTO() {
		return new TagDTO(tag.getID(), getAntennaID(), timestamp);
	}

	/**
	 * This returns the HashMap that stores the extra information for the
	 * TagReadEvent. An example of some extra information that a TagReadEvent
	 * might contain is the Velocity or RSSI information for an Alien tag.
	 * 
	 * @return
	 */
	public HashMap<String, Serializable> getExtraInformation() {
		return extraInformation;
	}

	/**
	 * Adds a value to the Extra Information HashMap.
	 * 
	 * @param key
	 * @param value
	 */
	public void addExtraInformation(String key, Serializable value) {
		this.extraInformation.put(key, value);
	}
}
