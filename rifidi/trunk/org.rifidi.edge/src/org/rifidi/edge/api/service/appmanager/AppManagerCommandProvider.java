/*
 * Copyright (c) 2013 Transcends, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */
package org.rifidi.edge.api.service.appmanager;

import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;
import org.rifidi.edge.api.RifidiApp;

/**
 * This provides OSGi commands for the AppManager
 * 
 * @author Kyle Neumeier - kyle@pramari.com
 * 
 */
public class AppManagerCommandProvider implements CommandProvider {

	/** The AppManager */
	private AppManager appManager;

	/**
	 * Start the app
	 * 
	 * @param intp
	 * @return
	 */
	public Object _startapp(CommandInterpreter intp) {
		String arg = intp.nextArgument();
		if (arg == null) {
			intp.println("Usage: startApp <AppID>");
			return null;
		}
		Integer appID = Integer.parseInt(arg);
		appManager.startApp(appID);
		return null;
	}

	/**
	 * Stop the app
	 * 
	 * @param intp
	 * @return
	 */
	public Object _stopapp(CommandInterpreter intp) {
		String arg = intp.nextArgument();
		if (arg == null) {
			intp.println("Usage: stopApp <AppID>");
			return null;
		}
		Integer appID = Integer.parseInt(arg);
		appManager.stopApp(appID);
		return null;

	}

	/**
	 * list the apps
	 * 
	 * @param intp
	 * @return
	 */
	public Object _apps(CommandInterpreter intp) {
		Map<Integer, RifidiApp> appMap = appManager.getApps();
		for (Entry<Integer, RifidiApp> entry : appMap.entrySet()) {
			intp.println(entry.getKey() + ":" + entry.getValue());
		}
		return null;
	}

	/**
	 * @param appManager
	 *            the appManager to set
	 */
	public void setAppManager(AppManager appManager) {
		this.appManager = appManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.osgi.framework.console.CommandProvider#getHelp()
	 */
	@Override
	public String getHelp() {
		StringBuffer retVal = new StringBuffer();
		retVal.append("  ---Rifidi App Manager Commands---\n");
		retVal.append("\tapps - "
				+ "List the installed Rifidi Applications and their states \n");
		retVal.append("\tstartapp <appID>"
				+ " - Starts the app with the given ID \n");
		retVal.append("\tstopapp <appID>"
				+ " - Stops the app with the given ID \n");
		return retVal.toString();
	}

}
