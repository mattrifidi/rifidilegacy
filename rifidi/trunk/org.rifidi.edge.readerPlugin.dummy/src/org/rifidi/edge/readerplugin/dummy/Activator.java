package org.rifidi.edge.readerplugin.dummy;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.rifidi.edge.core.service.readerplugin.ReaderPluginRegistryService;
import org.rifidi.services.annotations.Inject;
import org.rifidi.services.registry.ServiceRegistry;

public class Activator implements BundleActivator {

	private ReaderPluginRegistryService readerPluginRegistryService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		System.out.println("== Bundle " + this.getClass().getName()
				+ " loaded ==");
		ServiceRegistry.getInstance().service(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		readerPluginRegistryService
				.unregisterReaderAdapter(DummyReaderInfo.class);
		System.out.println("== Bundle " + this.getClass().getName()
				+ " stopped ==");
	}

	/**
	 * @return
	 */
	public ReaderPluginRegistryService getReaderAdapterRegistryService() {
		return readerPluginRegistryService;
	}

	/**
	 * @param readerPluginRegistryService
	 */
	@Inject
	public void setReaderAdapterRegistryService(
			ReaderPluginRegistryService readerPluginRegistryService) {
		this.readerPluginRegistryService = readerPluginRegistryService;

		System.out.println("Registering ReaderPlugin: DummyReaderPlugin");
		// register ReaderAdapter to the Services Registry
		readerPluginRegistryService.registerReaderAdapter(
				DummyReaderInfo.class, new DummyReaderPluginFactory());
	}

}
