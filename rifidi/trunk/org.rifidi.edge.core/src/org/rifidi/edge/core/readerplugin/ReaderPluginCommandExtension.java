package org.rifidi.edge.core.readerplugin;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReaderPluginCommandExtension {

	private String plugin;

	private ArrayList<CommandDescription> commandList;

	private ArrayList<CommandDescription> propertyList;

	public String getPlugin() {
		return plugin;
	}

	public void setPlugin(String plugin) {
		this.plugin = plugin;
	}

	@XmlElement(name = "property")
	public ArrayList<CommandDescription> getPropertyList() {
		return propertyList;
	}

	@XmlElement(name = "command")
	public ArrayList<CommandDescription> getCommandList() {
		return commandList;
	}

	public void setCommandList(ArrayList<CommandDescription> commandList) {
		this.commandList = commandList;
	}

	public void setPropertyList(ArrayList<CommandDescription> propertyList) {
		this.propertyList = propertyList;
	}

}
