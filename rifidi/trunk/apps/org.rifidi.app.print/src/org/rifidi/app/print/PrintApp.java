/*
 * Copyright (c) 2013 Transcends, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */
package org.rifidi.app.print;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.rifidi.edge.api.AbstractRifidiApp;
import org.rifidi.edge.api.service.tagmonitor.ReadZone;
import org.rifidi.edge.api.service.tagmonitor.ReadZoneMonitoringService;
import org.rifidi.edge.api.service.tagmonitor.ReadZoneSubscriber;
import org.rifidi.edge.daos.CommandDAO;
import org.rifidi.edge.daos.ReaderDAO;
import org.rifidi.edge.sensors.AbstractCommandConfiguration;
import org.rifidi.edge.sensors.AbstractSensor;
import org.rifidi.edge.sensors.Command;
import org.rifidi.edge.sensors.SensorSession;

/**
 * @author Matthew Dean - matt@transcends.co
 * 
 */
public class PrintApp extends AbstractRifidiApp {

	/** The service for monitoring arrival and departure events */
	private ReadZoneMonitoringService readZoneMonitoringService;
	private List<ReadZoneSubscriber> subscriberList;
	private ReaderDAO readerDAO;
	private CommandDAO commandDAO;

	public PrintApp(String group, String name) {
		super(group, name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rifidi.edge.api.AbstractRifidiApp#_start()
	 */
	@Override
	public void _start() {
		System.out.println("Starting PrintApp");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(readerDAO.getReaderFactories());
		
		//Execute this LLRP_Push_Stop command (AKA "Delete ROSpec") on reader LLRP_1 session 1
		//Make sure the settings for this command are correct
		this.executeCommand("LLRP_Push_Stop_1", "LLRP_1", "1");
		
		//Now that we have deleted the old ROSpec we can add a new one.  Like the other command,
		//this command has to already exist.  
		this.executeCommand("LLRP_ADD_ROSPEC_File_2", "LLRP_1", "1");

		super._start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rifidi.edge.api.AbstractRifidiApp#_stop()
	 */
	@Override
	public void _stop() {
		for (ReadZoneSubscriber s : this.subscriberList) {
			this.readZoneMonitoringService.unsubscribe(s);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rifidi.edge.api.AbstractRifidiApp#initialize()
	 */
	@Override
	public void initialize() {
		PrintSubscriber sub = new PrintSubscriber();
		this.subscriberList = new LinkedList<ReadZoneSubscriber>();
		this.subscriberList.add(sub);
		this.readZoneMonitoringService.subscribe(sub,
				new LinkedList<ReadZone>(), 4.0f, TimeUnit.SECONDS);
	}

	/**
	 * Called by spring. This method injects the ReadZoneMonitoringService into
	 * the application.
	 * 
	 * @param rzms
	 */
	public void setReadZoneMonitoringService(ReadZoneMonitoringService rzms) {
		this.readZoneMonitoringService = rzms;
	}

	public void setReaderDAO(ReaderDAO readerDAO) {
		this.readerDAO = readerDAO;
	}

	public void setCommandDAO(CommandDAO commandDAO) {
		this.commandDAO = commandDAO;
	}

	//Execute a given commmand on a given reader and session
	private void executeCommand(String commandID, String reader, String sessionID) {		
		AbstractSensor<?> llrpReader = readerDAO.getReaderByID(reader);
		SensorSession session = llrpReader.getSensorSessions().get(sessionID);
		session.submit(commandID, -1, TimeUnit.MILLISECONDS);
	}
}
