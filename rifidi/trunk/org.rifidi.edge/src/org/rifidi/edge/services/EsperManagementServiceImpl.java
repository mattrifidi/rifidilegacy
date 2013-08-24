/*
 * Copyright (c) 2013 Transcends, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */
package org.rifidi.edge.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rifidi.edge.notification.BarcodeTagEvent;
import org.rifidi.edge.notification.DatacontainerEvent;
import org.rifidi.edge.notification.EPCGeneration1Event;
import org.rifidi.edge.notification.EPCGeneration2Event;
import org.rifidi.edge.notification.GPIEvent;
import org.rifidi.edge.notification.GPOEvent;
import org.rifidi.edge.notification.ReadCycle;
import org.rifidi.edge.notification.SensorConnectedEvent;
import org.rifidi.edge.notification.SensorDisconnectedEvent;
import org.rifidi.edge.notification.SensorStatusEvent;
import org.rifidi.edge.notification.TagReadEvent;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

/**
 * Service that configures and manages the esper provider.
 * 
 * @author Jochen Mader - jochen@pramari.com
 */
public class EsperManagementServiceImpl implements EsperManagementService {
	/** Logger for this class. */
	private static final Log logger = LogFactory
			.getLog(EsperManagementServiceImpl.class);

	private final Configuration config;

	/**
	 * Constructor.
	 */
	public EsperManagementServiceImpl() {
		logger.info("EsperManagementServiceImpl created.");
		config = new Configuration();
		config.addEventType("StopEvent", StopEvent.class);
		config.addEventType("StartEvent", StartEvent.class);
		config.addEventType("DestroyEvent", DestroyEvent.class);
		config.addEventType("TagReadEvent", TagReadEvent.class);
		config.addEventType("DatacontainerEvent", DatacontainerEvent.class);
		config.addEventType("EPCGeneration1Event", EPCGeneration1Event.class);
		config.addEventType("EPCGeneration2Event", EPCGeneration2Event.class);
		config.addEventType("BarcodeTagEvent", BarcodeTagEvent.class);
		config.addEventType("ReadCycle", ReadCycle.class);
		config.addEventType("GPIEvent", GPIEvent.class);
		config.addEventType("GPOEvent", GPOEvent.class);
		config.addEventType("SensorStatusEvent",SensorStatusEvent.class);
		config.addEventType("SensorDisconnectedEvent", SensorDisconnectedEvent.class);
		config.addEventType("SensorConnectedEvent", SensorConnectedEvent.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rifidi.edge.services.esper.EsperManagementService#getProvider()
	 */
	@Override
	public EPServiceProvider getProvider() {
		return EPServiceProviderManager.getDefaultProvider(config);
	}

}
