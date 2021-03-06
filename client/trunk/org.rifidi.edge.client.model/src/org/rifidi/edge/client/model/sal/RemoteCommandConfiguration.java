/*
 * RemoteCommandConfiguration.java
 * 
 * Created:     July 8th, 2009
 * Project:       Rifidi Edge Server - A middleware platform for RFID applications
 *                    http://www.rifidi.org
 *                    http://rifidi.sourceforge.net
 * Copyright:   Pramari LLC and the Rifidi Project
 * License:      The software in this package is published under the terms of the EPL License
 *                    A copy of the license is included in this distribution under Rifidi-License.txt 
 */
package org.rifidi.edge.client.model.sal;

import javax.management.AttributeList;

import org.rifidi.edge.api.CommandConfigurationDTO;
import org.rifidi.edge.client.model.sal.commands.RequestExecuterSingleton;

/**
 * A model object for RemoteCommandConfigurations
 * 
 * @author Kyle Neumeier - kyle@pramari.com
 */
public class RemoteCommandConfiguration extends
		AbstractAttributeContributorModelObject {

	/** The ID of the command */
	private String id;
	/** The Configuraiton Factory which created this configuration */
	private RemoteCommandConfigFactory factory;

	/**
	 * Constructor
	 * 
	 * @param dto
	 *            The DTO for the RemoteCommandConfiguration
	 * @param factory
	 *            The factory that created this commandconfig
	 */
	public RemoteCommandConfiguration(CommandConfigurationDTO dto,
			RemoteCommandConfigFactory factory) {
		super(dto.getCommandConfigID(), dto.getAttributes());
		System.out.println("Creating a remote command configuration!");
		this.id = dto.getCommandConfigID();
		this.factory = factory;
	}

	/**
	 * Returns the command type.
	 * 
	 * @return The ID of the type
	 */
	public String getCommandType() {
		return factory.getCommandConfigFactoryID();
	}

	/**
	 * Returns the ID.
	 * 
	 * @return The ID of the command configuration
	 */
	public String getID() {
		return id;
	}

	/**
	 * @return the readerFactoryID
	 */
	public String getReaderFactoryID() {
		return factory.getReaderFactoryID();
	}

	/**
	 * Returns the factory.
	 * 
	 * @return the factory
	 */
	public RemoteCommandConfigFactory getFactory() {
		return factory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rifidi.edge.client.model.sal.AbstractAttributeContributorModelObject
	 * #doSynchAttributeChange
	 * (org.rifidi.edge.client.model.sal.RemoteEdgeServer, java.lang.String,
	 * javax.management.AttributeList)
	 */
	@Override
	protected void doSynchAttributeChange(RemoteEdgeServer server,
			String modelID, AttributeList list) {
		System.out.println("Calling dosynchattributechange");
		Command_SynchCommandConfigPropertyChanges command = new Command_SynchCommandConfigPropertyChanges(
				server, modelID, list);
		RequestExecuterSingleton.getInstance().scheduleRequest(command);
		System.out.println("Done calling dosynchattributechange");
	}

}
