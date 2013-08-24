/*
 * Copyright (c) 2013 Transcends, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */
package org.rifidi.edge.adapter.awid.awid2010.communication.commands;

/**
 * @author kyle
 * 
 */
public class AntennaSwitchRateCommand extends AbstractAwidCommand {

	public AntennaSwitchRateCommand(byte antenna1Rate, byte antenna2Rate) {

		rawmessage = new byte[] { 0x07, 0x00, 0x1D, antenna1Rate, antenna2Rate };

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Antenna Switch Rate Command";
	}

}
