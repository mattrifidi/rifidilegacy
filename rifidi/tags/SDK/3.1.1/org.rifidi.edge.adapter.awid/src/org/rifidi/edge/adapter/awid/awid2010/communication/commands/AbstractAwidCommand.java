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
package org.rifidi.edge.adapter.awid.awid2010.communication.commands;

import java.util.Arrays;

import org.apache.commons.codec.binary.Hex;

/**
 * A AwidCommand is a message that is sent to the Awid reader. This class should
 * be extended for any concrete command to send to the Awid reader. Typically,
 * subclasses should just set the rawmessage member in the constructor. By
 * default the getCommand method will calculate the CRC and append it to the
 * rawMessage.
 * 
 * It is recommended that subclasses also override the toString() method for
 * debugging purposes.
 * 
 * @author Kyle Neumeier - kyle@pramari.com
 * 
 */
public class AbstractAwidCommand {

	/** The message to be sent */
	protected byte[] rawmessage;

	/**
	 * This method takes the bytes in rawmessage and appends two CRC bytes to
	 * it.
	 * 
	 * @return The command to send, complete with the CRC
	 */
	public byte[] getCommand() {
		if (rawmessage == null) {
			throw new NullPointerException("message has not been set");
		}
		byte[] crc = getCRC(rawmessage);
		byte[] cmdToSend = Arrays.copyOf(rawmessage, rawmessage.length + 2);
		cmdToSend[rawmessage.length] = crc[0];
		cmdToSend[rawmessage.length + 1] = crc[1];
		return cmdToSend;
	}

	/**
	 * A helper method to calculate the CRC-16 bytes for the given array of
	 * bytes
	 * 
	 * @param bytes
	 *            The bytes to calculate the CRC for
	 * @return Two bytes. The first is the "high" CRC byte. The second is the
	 *         "low" CRC byte
	 */
	protected byte[] getCRC(byte[] bytes) {
		int crc = 0xFFFF;
		for (byte b : bytes) {
			crc = (b << 8) ^ crc;
			for (int i = 0; i < 8; i++) {
				if ((crc & 0x8000) != 0) {
					crc = (crc << 1) ^ 0x1021;
				} else {
					crc = crc << 1;
				}
			}
		}
		crc = (crc ^ 0xFFFF) & 0x0000FFFF;
		byte highbyte = (byte) (crc >> 8);
		byte lowbyte = (byte) crc;
		return new byte[] { highbyte, lowbyte };
	}
	
	/**
	 * A method to print the bytes of the command
	 * @return
	 */
	protected String getCommandAsString(){
		StringBuilder sb = new StringBuilder();
		char[] chars = Hex.encodeHex(getCommand());
		for(int i=0; i<chars.length; i=i+2){
			sb.append("["+chars[i]+chars[i+1]+"]");
		}
		return sb.toString();
	}

}
