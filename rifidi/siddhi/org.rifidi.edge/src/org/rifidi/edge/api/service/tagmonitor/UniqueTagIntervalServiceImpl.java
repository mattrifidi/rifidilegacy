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
package org.rifidi.edge.api.service.tagmonitor;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.rifidi.edge.api.service.RifidiAppEsperFactory;
import org.rifidi.edge.api.service.RifidiAppService;
import org.rifidi.edge.notification.TagReadEvent;


/**
 * This is an implementation of the UniqueTagIntervalService
 * 
 * @author Kyle Neumeier - kyle@pramari.com
 * 
 */
public class UniqueTagIntervalServiceImpl extends
		RifidiAppService<UniqueTagIntervalSubscriber> implements
		UniqueTagIntervalService {

	/**
	 * Constructor
	 * 
	 * @param group
	 *            the group this application is a part of
	 * @param name
	 *            The name of the application
	 */
	public UniqueTagIntervalServiceImpl(String group, String name) {
		super(group,name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rifidi.edge.api.service.RifidiAppService#createListener(
	 * org.rifidi.edge.api.service.RifidiAppSubscriber)
	 */
	//FIXME SIDDHI
	/*
	@Override
	protected StatementAwareUpdateListener createListener(
			final UniqueTagIntervalSubscriber subscriber) {
		return new StatementAwareUpdateListener() {

			@Override
			public void update(EventBean[] arg0, EventBean[] arg1,
					EPStatement arg2, EPServiceProvider arg3) {
				if (arg0 != null) {
					for (EventBean b : arg0) {
						TagReadEvent tag = (TagReadEvent) b.getUnderlying();
						subscriber.tagSeen(tag);
					}
				}

			}
		};
	}
	*/

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.rifidi.edge.api.service.tagmonitor.
	 * UniqueTagIntervalService
	 * #subscribe(org.rifidi.edge.api
	 * .service.tagmonitor.UniqueTagIntervalNotificationSubscriber,
	 * java.util.List, java.lang.Float, java.util.concurrent.TimeUnit)
	 */
	@Override
	public void subscribe(UniqueTagIntervalSubscriber subscriber,
			List<ReadZone> readZones, Float notifyInterval, TimeUnit timeUnit) {
		RifidiAppEsperFactory esperFactory = new UniqueTagIntervalEsperFactory(
				readZones, notifyInterval, timeUnit, getCounter());
		subscribe(subscriber, esperFactory);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.rifidi.edge.api.service.tagmonitor.
	 * UniqueTagIntervalService
	 * #subscribe(org.rifidi.edge.api
	 * .service.tagmonitor.UniqueTagIntervalNotificationSubscriber)
	 */
	@Override
	public void subscribe(UniqueTagIntervalSubscriber subscriber) {
		subscribe(subscriber, Collections.EMPTY_LIST, 5f, TimeUnit.SECONDS);

	}

}
