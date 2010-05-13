/*
 *  SerialBarcodeSensorSession.java
 *
 *  Created:	May 12, 2010
 *  Project:	Rifidi Edge Server - A middleware platform for RFID applications
 *  				http://www.rifidi.org
 *  				http://rifidi.sourceforge.net
 *  Copyright:	Pramari LLC and the Rifidi Project
 *  License:	GNU Public License (GPL)
 *  				http://www.opensource.org/licenses/gpl-3.0.html
 */
package org.rifidi.edge.readerplugin.opticon;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.rifidi.edge.api.SessionStatus;
import org.rifidi.edge.core.sensors.base.AbstractSensor;
import org.rifidi.edge.core.sensors.commands.AbstractCommandConfiguration;
import org.rifidi.edge.core.sensors.messages.ByteMessage;
import org.rifidi.edge.core.sensors.sessions.AbstractSerialSensorSession;
import org.rifidi.edge.core.sensors.sessions.MessageParsingStrategy;
import org.rifidi.edge.core.sensors.sessions.MessageParsingStrategyFactory;
import org.rifidi.edge.core.services.notification.NotifierService;
import org.rifidi.edge.core.services.notification.data.ReadCycle;
import org.rifidi.edge.core.services.notification.data.ReadCycleMessageCreator;
import org.rifidi.edge.readerplugin.opticon.tags.OpticonTagHandler;
import org.springframework.jms.core.JmsTemplate;

/**
 * 
 * 
 * @author Matthew Dean - matt@pramari.com
 */
public class OpticonSensorSession extends AbstractSerialSensorSession {

	/** Service used to send out notifications */
	private volatile NotifierService notifierService;

	/** The ID for the reader. */
	private String readerID = null;

	/** The JMS Template */
	private JmsTemplate template = null;

	/**
	 * 
	 */
	private OpticonTagHandler taghandler = null;

	/**
	 * 
	 * @param sensor
	 * @param ID
	 * @param destination
	 * @param template
	 * @param commandConfigurations
	 * @param commPortName
	 * @param baud
	 * @param databits
	 * @param stopbits
	 * @param parity
	 */
	public OpticonSensorSession(AbstractSensor<?> sensor, String ID,
			JmsTemplate template, NotifierService notifierService,
			String readerID,
			Set<AbstractCommandConfiguration<?>> commandConfigurations,
			String commPortName) {
		super(sensor, ID, template.getDefaultDestination(), template,
				commandConfigurations, commPortName, OpticonConstants.BAUD,
				OpticonConstants.DATA_BITS, OpticonConstants.STOP_BITS,
				OpticonConstants.PARITY);
		this.readerID = readerID;
		this.notifierService = notifierService;
		this.template = template;
		this.taghandler = new OpticonTagHandler(readerID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rifidi.edge.core.readers.impl.AbstractReaderSession#setStatus(org
	 * .rifidi.edge.core.api.SessionStatus)
	 */
	@Override
	protected void setStatus(SessionStatus status) {
		super.setStatus(status);
		// TODO: Remove this once we have aspectJ
		notifierService.sessionStatusChanged(this.readerID, this.getID(),
				status);
	}

	/**
	 * Returns the JMSTemplate.
	 */
	public JmsTemplate getTemplate() {
		return template;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.rifidi.edge.core.sensors.sessions.AbstractSerialSensorSession#
	 * getMessageParsingStrategyFactory()
	 */
	@Override
	protected MessageParsingStrategyFactory getMessageParsingStrategyFactory() {
		return new OpticonMessageParsingStrategyFactory();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.rifidi.edge.core.sensors.sessions.AbstractSerialSensorSession#
	 * messageReceived(org.rifidi.edge.core.sensors.messages.ByteMessage)
	 */
	@Override
	protected void messageReceived(ByteMessage message) {

		System.out.println("message recieved!: " + message.toString());

		ReadCycle rc = this.taghandler.processTag(message.message);

		System.out.println("Sending the message: " + message.toString());

		this.getSensor().send(rc);

		System.out.println("Sending the message to the default destination: "
				+ message.toString());

		this.template.send(this.template.getDefaultDestination(),
				new ReadCycleMessageCreator(rc));
	}

	/**
	 * Factory class for creating AmbientBarcodeMessageParsingStrategies.
	 * 
	 * @author Matthew Dean - matt@pramari.com
	 */
	private class OpticonMessageParsingStrategyFactory implements
			MessageParsingStrategyFactory {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.rifidi.edge.core.sensors.sessions.MessageParsingStrategyFactory
		 * #createMessageParser()
		 */
		@Override
		public MessageParsingStrategy createMessageParser() {
			return new OpticonMessageProcessingStrategy();
		}
	}

	/**
	 * All messages coming back from the barcode reader are 10 characters long.
	 * 
	 * @author Matthew Dean - matt@pramari.com
	 */
	private class OpticonMessageProcessingStrategy implements
			MessageParsingStrategy {
		/** The message currently being processed. */
		private List<Byte> messagebuilder = new ArrayList<Byte>();
		/** Once we hit this size, we have a completed message */
		private static final int MSGSIZE = 12;
		
		

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.rifidi.edge.core.sensors.sessions.MessageParsingStrategy#isMessage
		 * (byte)
		 */
		@Override
		public byte[] isMessage(byte message) {
			messagebuilder.add(message);
			if (messagebuilder.size() == MSGSIZE) {
				System.out.println("It is a message!  ");
				List<Byte> actualmessage = new ArrayList<Byte>();
				for(Byte b:messagebuilder) {
					if(Character.isDigit((char)b.byteValue())) {
						actualmessage.add(b);
					}
				}
				System.out.println("Done checking digits");
				
				byte retval[] = new byte[actualmessage.size()];
				for (int i = 0; i < actualmessage.size(); i++) {
					retval[i] = actualmessage.get(i);
				}
				messagebuilder.clear();
				return retval;
			}
			return null;
		}
	}

}