/**
 * 
 */
package org.rifidi.edge.core.services.notification.data;

import java.io.Serializable;

/**
 * This class acts as a wrapper around a DataContainerEvent (which stores tag
 * data). This class stores extra information about a tag read event, such as
 * the antenna that the tag was seen on
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
	 * Constructor.
	 * 
	 * @param tag
	 * @param antennaID
	 * @param timestamp
	 */
	public TagReadEvent(String readerID, DatacontainerEvent tag, int antennaID,
			long timestamp) {
		this.tag = tag;
		this.antennaID = antennaID;
		this.timestamp = timestamp;
		this.readerID = readerID;
	}

	/**
	 * Returns the ID for the reader.
	 * 
	 * @return the readerID
	 */
	public String getReaderID() {
		return readerID;
	}

	/**
	 * Returns the tag
	 * 
	 * @return the tag
	 */
	public DatacontainerEvent getTag() {
		return tag;
	}

	/**
	 * Returns the ID of the antenna.
	 * 
	 * @return the antennaID
	 */
	public int getAntennaID() {
		return antennaID;
	}

	/**
	 * Returns the timestamp.
	 * 
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

}
