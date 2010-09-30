/*
 * 
 * UINTHEXGROUPPatternMatcher.java
 *  
 * Created:     July 8th, 2009
 * Project:       Rifidi Edge Server - A middleware platform for RFID applications
 *                   http://www.rifidi.org
 *                   http://rifidi.sourceforge.net
 * Copyright:   Pramari LLC and the Rifidi Project
 * License:      The software in this package is published under the terms of the GPL License
 *                   A copy of the license is included in this distribution under RifidiEdge-License.txt 
 */
/**
 * 
 */
package org.rifidi.edge.epcglobal.aleread.groups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.rifidi.edge.core.services.notification.DatacontainerEvent;

/**
 * Group matcher for UINT HEX patterns. See ALE 1.1 6.2.2.3.
 * 
 * @author Jochen Mader - jochen@pramari.com
 * 
 */
public class UINTHEXGROUPPatternMatcher implements GroupMatcher {
	private static Pattern range = Pattern
			.compile("^\\[x([A-D0-9]*)-x([A-D0-9]*)\\]$");
	private boolean always = false;
	private String match = null;
	private Long lo;
	private Long hi;
	private Map<String, List<DatacontainerEvent>> groupsToTags;

	/**
	 * Constructor.
	 * 
	 * @param input
	 */
	public UINTHEXGROUPPatternMatcher(String input) {
		groupsToTags = new HashMap<String, List<DatacontainerEvent>>();
		if (input.equals("*")) {
			always = true;
			return;
		}
		Matcher mat = range.matcher(input);
		if (mat.find()) {
			lo = Long.parseLong(mat.group(1));
			hi = Long.parseLong(mat.group(2));
			return;
		}
		match = input;
	}



	/* (non-Javadoc)
	 * @see org.rifidi.edge.epcglobal.aleread.groups.GroupMatcher#match(java.lang.String, org.rifidi.edge.core.messages.DatacontainerEvent)
	 */
	@Override
	public boolean match(String input, DatacontainerEvent event) {
		if (always) {
			addToGroup("always", event);
			return true;
		}
		if (input.equals(match)) {
			addToGroup(input, event);
			return true;
		}
		Long val = Long.parseLong(input, 16);
		if (val >= lo && val <= hi) {
			addToGroup(input, event);
			return true;
		}
		return false;
	}

	/**
	 * Add to the group.
	 * 
	 * @param key
	 * @param value
	 */
	private void addToGroup(String key, DatacontainerEvent value) {
		if (!groupsToTags.containsKey(key)) {
			groupsToTags.put(key, new ArrayList<DatacontainerEvent>());
		}
		groupsToTags.get(key).add(value);
	}

	/* (non-Javadoc)
	 * @see org.rifidi.edge.epcglobal.aleread.groups.GroupMatcher#getGrouped()
	 */
	@Override
	public Map<String, List<DatacontainerEvent>> getGrouped() {
		Map<String, List<DatacontainerEvent>> ret = new HashMap<String, List<DatacontainerEvent>>(
				groupsToTags);
		groupsToTags.clear();
		return ret;
	}

}
