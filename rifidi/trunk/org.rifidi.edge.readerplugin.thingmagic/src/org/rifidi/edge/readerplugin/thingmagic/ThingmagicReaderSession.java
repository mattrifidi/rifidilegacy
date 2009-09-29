/*
 *  ThingmagicReaderSession.java
 *
 *  Created:	Sep 15, 2009
 *  Project:	Rifidi Edge Server - A middleware platform for RFID applications
 *  				http://www.rifidi.org
 *  				http://rifidi.sourceforge.net
 *  Copyright:	Pramari LLC and the Rifidi Project
 *  License:	GNU Public License (GPL)
 *  				http://www.opensource.org/licenses/gpl-3.0.html
 */
package org.rifidi.edge.readerplugin.thingmagic;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.rifidi.edge.core.sensors.base.AbstractIPSensorSession;
import org.rifidi.edge.core.sensors.base.AbstractSensor;
import org.rifidi.edge.core.sensors.base.threads.MessageParsingStrategyFactory;
import org.rifidi.edge.core.sensors.commands.AbstractCommandConfiguration;
import org.rifidi.edge.core.services.notification.NotifierService;
import org.springframework.jms.core.JmsTemplate;

/**
 * 
 * 
 * @author Matthew Dean
 */
public class ThingmagicReaderSession extends AbstractIPSensorSession {

	/** Logger for this class. */
	// private static final Log logger = LogFactory
	// .getLog(ThingmagicReaderSession.class);
	/** Set to true if the session is destroied. */
	private AtomicBoolean destroied = new AtomicBoolean(false);
	/** Each command needs to be terminated with a newline. */
	public static final String NEWLINE = "\n";
	/** Welcome string. */
	public static final String WELCOME = "Alien";
	/** Service used to send out notifications */
	private volatile NotifierService notifierService;
	/** The FACTORY_ID of the reader this session belongs to */
	private final String readerID;

	/**
	 * Constructor
	 * 
	 * @param sensor
	 * @param id
	 *            The FACTORY_ID of the session
	 * @param host
	 *            The IP to connect to
	 * @param port
	 *            The port to connect to
	 * @param reconnectionInterval
	 *            The wait time between reconnect attempts
	 * @param maxConAttempts
	 *            The maximum number of times to try to connect
	 * @param username
	 *            The Alien username
	 * @param password
	 *            The Alien password
	 * @param destination
	 *            The JMS destination for tags
	 * @param template
	 *            The JSM template for tags
	 * @param notifierService
	 *            The service for sending client notifications
	 * @param readerID
	 *            The FACTORY_ID of the reader that created this session
	 * @param commands
	 *            A thread safe set containing all available commands
	 */
	public ThingmagicReaderSession(AbstractSensor<?> sensor, String id,
			String host, int port, int reconnectionInterval,
			int maxConAttempts, JmsTemplate template,
			NotifierService notifierService, String readerID,
			Set<AbstractCommandConfiguration<?>> commands) {
		super(sensor, id, host, port, reconnectionInterval, maxConAttempts,
				template.getDefaultDestination(), template, commands);
		this.notifierService = notifierService;
		this.readerID = readerID;
		// this.messageParser = new AlienMessageParsingStrategy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.rifidi.edge.core.sensors.base.AbstractIPSensorSession#
	 * getMessageParsingStrategyFactory()
	 */
	@Override
	public MessageParsingStrategyFactory getMessageParsingStrategyFactory() {
		return new ThingmagicMessageParsingStrategyFactory();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rifidi.edge.core.sensors.base.AbstractIPSensorSession#onConnect()
	 */
	@Override
	public boolean onConnect() throws IOException {
		//FIXME: Do something about this.  
		return true;
	}

}