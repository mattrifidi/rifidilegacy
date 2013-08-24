/*
 * Copyright (c) 2013 Transcends, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */
package org.rifidi.edge.adapter.awid.awid2010.communication.commands;

/**
 * Command sent to the Awid reader that provides the ability to read EPC Gen2
 * tags. Responses are sent back as tags are seen
 * 
 * @author Kyle Neumeier - kyle@pramari.com
 * 
 */
public class Gen2PortalIDCommand extends AbstractAwidCommand {

	/**
	 * Create a new Gen2PortalID command
	 * 
	 * @param timeout
	 *            Execute this command for timeout*100 ms. Because the session
	 *            must ping the reader to make sure it is still connected to it,
	 *            and the AWID reader doesn't respond to anything but a stop
	 *            command when the portal ID is processing, a timeout of 0x00 is
	 *            not allowed.
	 * @param repeat
	 *            Return results every repeat*100 ms. If set to 0x00
	 *            continuously return tags.
	 */
	public Gen2PortalIDCommand(byte timeout, byte repeat) {
		if (timeout == 0x00) {
			throw new IllegalArgumentException(
					"A timeout byte of 0x00 is not allowed");
		}
		rawmessage = new byte[] { 0x07, 0x20, 0x1E, timeout, repeat };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Gen2 Portal ID Command";
	}

}
