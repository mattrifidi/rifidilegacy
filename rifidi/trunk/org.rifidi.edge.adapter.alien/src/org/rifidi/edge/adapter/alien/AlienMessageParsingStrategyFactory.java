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
/**
 * 
 */
package org.rifidi.edge.adapter.alien;

import org.rifidi.edge.sensors.sessions.MessageParsingStrategy;
import org.rifidi.edge.sensors.sessions.MessageParsingStrategyFactory;

/**
 * Produces new AlienMessageParsingStrategies
 * 
 * @author Kyle Neumeier - kyle@pramari.com
 * 
 */
public class AlienMessageParsingStrategyFactory implements
		MessageParsingStrategyFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rifidi.edge.sensors.base.threads.MessageParsingStrategyFactory
	 * #createMessageParser()
	 */
	@Override
	public MessageParsingStrategy createMessageParser() {
		return new AlienMessageParsingStrategy();
	}

}
