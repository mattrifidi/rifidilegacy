
package org.rifidi.edge.core.api.tags;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * TODO: Class level comment.  
 * 
 * @author Kyle Neumeier - kyle@pramari.com
 */
public class TagDTO implements Serializable {

	/** SerialVersionID */
	private static final long serialVersionUID = 1L;

	private BigInteger tagID;

	private int antennaNumber;

	private long timestamp;

	/**
	 * Constructor.  
	 * 
	 * @param tagID
	 * @param antennaNumber
	 * @param timestamp
	 */
	public TagDTO(BigInteger tagID, int antennaNumber, long timestamp) {
		this.tagID = tagID;
		this.antennaNumber = antennaNumber;
		this.timestamp = timestamp;
	}

	/**
	 * Returns the ID for this tag.  
	 * 
	 * @return the tagID
	 */
	public BigInteger getTagID() {
		return tagID;
	}

	/**
	 * Returns the antenna that this tag was seen on.  
	 * 
	 * @return the antennaNumber
	 */
	public int getAntennaNumber() {
		return antennaNumber;
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
