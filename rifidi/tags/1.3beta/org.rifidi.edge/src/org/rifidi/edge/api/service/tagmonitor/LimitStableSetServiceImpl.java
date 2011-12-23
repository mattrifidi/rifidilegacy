/**
 * 
 */
package org.rifidi.edge.api.service.tagmonitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.rifidi.edge.api.service.RifidiAppService;
import org.rifidi.edge.notification.TagReadEvent;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.StatementAwareUpdateListener;

/**
 * @author Matthew Dean - matt@pramari.com
 * 
 */
public class LimitStableSetServiceImpl extends
		RifidiAppService<LimitStableSetSubscriber> implements
		LimitStableSetService {

	/**
	 * 
	 * @param group
	 * @param name
	 */
	public LimitStableSetServiceImpl(String group, String name) {
		super(group, name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rifidi.edge.api.service.RifidiAppService#createListener(org.rifidi
	 * .edge.api.service.RifidiAppSubscriber)
	 */
	@Override
	protected StatementAwareUpdateListener createListener(
			final LimitStableSetSubscriber subscriber) {
		return new StatementAwareUpdateListener() {

			@Override
			public void update(EventBean[] arg0, EventBean[] arg1,
					EPStatement arg2, EPServiceProvider arg3) {
				if (arg0 != null) {
					Set<TagReadEvent> stableSet = new HashSet<TagReadEvent>();
					for (EventBean b : arg0) {
						stableSet.add((TagReadEvent) b.getUnderlying());
					}
					subscriber.stableSetReached(stableSet);
				}

			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rifidi.edge.api.service.tagmonitor.LimitStableSetService#subscribe
	 * (org.rifidi.edge.api.service.tagmonitor.StableSetSubscriber,
	 * java.util.List, java.lang.Float, java.util.concurrent.TimeUnit, int)
	 */
	@Override
	public void subscribe(LimitStableSetSubscriber subscriber, List<ReadZone> zones,
			Float stableSetTime, TimeUnit stableSetTimeUnit, int limit) {
		List<ReadZone> readZones = new ArrayList<ReadZone>();
		if (zones != null) {
			readZones.addAll(zones);
		}
		subscribe(subscriber, new LimitStableSetEsperFactory(readZones,
				stableSetTime, stableSetTimeUnit, limit, getCounter()));
		System.out.println("subscribed to limitStableset");
	}

}