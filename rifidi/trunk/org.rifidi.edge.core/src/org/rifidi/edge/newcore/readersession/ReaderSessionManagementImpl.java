/**
 * 
 */
package org.rifidi.edge.newcore.readersession;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.osgi.framework.BundleContext;
import org.rifidi.edge.newcore.commands.CommandFactory;
import org.rifidi.edge.newcore.exceptions.NonExistentCommandFactoryException;
import org.rifidi.edge.newcore.exceptions.NonExistentReaderConfigurationException;
import org.rifidi.edge.newcore.internal.ReaderSession;
import org.rifidi.edge.newcore.internal.impl.ReaderSessionImpl;
import org.rifidi.edge.newcore.readers.ReaderConfiguration;

/**
 * @author Jochen Mader - jochen@pramari.com
 * 
 */
public class ReaderSessionManagementImpl implements ReaderSessionManagement {

	/** Reference to the bundle context. */
	private BundleContext context;
	/** Counter for session ids. */
	private Integer counter = 0;
	/** Set of readers that is currently available. */
	private Map<String, ReaderConfiguration<?>> readerConfigurationsByName;
	/** Set of currently available commands. */
	private Map<String, CommandFactory<?>> commandFactoriesByName;
	/** Executor for reader sessions. */
	private ThreadPoolExecutor executor;
	/** Currently available reader sessions by reader configs. */
	private Map<String, List<ReaderSession>> readerSessionByReaderConfig;
	/** Currently available reader sessions by command factories. */
	private Map<String, List<ReaderSession>> readerSessionByCommandFactory;

	/**
	 * Constructor.
	 */
	public ReaderSessionManagementImpl() {
		readerConfigurationsByName = new HashMap<String, ReaderConfiguration<?>>();
		commandFactoriesByName = new HashMap<String, CommandFactory<?>>();
		readerSessionByCommandFactory = new HashMap<String, List<ReaderSession>>();
		readerSessionByReaderConfig = new HashMap<String, List<ReaderSession>>();
		executor = new ThreadPoolExecutor(5, 15, 200, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.rifidi.edge.newcore.readersession.ReaderSessionManagement#
	 * createAndStartReaderSession(java.lang.String, java.lang.String)
	 */
	@Override
	public void createAndStartReaderSession(String readerConfigurationID,
			String commandFactoryID) throws NonExistentCommandFactoryException,
			NonExistentReaderConfigurationException {
		synchronized (this) {
			ReaderConfiguration<?> reader = readerConfigurationsByName
					.get(readerConfigurationID);
			CommandFactory<?> command = commandFactoriesByName
					.get(commandFactoryID);
			if (reader == null) {
				throw new NonExistentReaderConfigurationException(
						"Tried to use a reader that doesn't exist: "
								+ readerConfigurationID);
			}
			if (command == null) {
				throw new NonExistentCommandFactoryException(
						"Tried to use a command that doesn't exist: "
								+ commandFactoryID);
			}

			ReaderSessionImpl session = new ReaderSessionImpl();
			session.setReaderFactory(reader);
			session.setCommmandFactory(command);
			Dictionary<String, String> params = new Hashtable<String, String>();
			params.put("id", counter.toString());
			counter++;
			session.setRegistration(context.registerService(ReaderSession.class
					.getName(), session, params));
			if (readerSessionByCommandFactory.get(commandFactoryID) == null) {
				readerSessionByCommandFactory.put(commandFactoryID,
						new ArrayList<ReaderSession>());
			}
			if (readerSessionByReaderConfig.get(readerConfigurationID) == null) {
				readerSessionByReaderConfig.put(readerConfigurationID,
						new ArrayList<ReaderSession>());
			}
			readerSessionByCommandFactory.get(commandFactoryID).add(session);
			readerSessionByReaderConfig.get(readerConfigurationID).add(session);
			// TODO: store future
			Future<?> future = executor.submit(session);
		}
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public void setContext(BundleContext context) {
		this.context = context;
	}

	/**
	 * Bind a new factory to this service.
	 * 
	 * @param readerFactory
	 * @param parameters
	 */
	public void bind(ReaderConfiguration<?> readerConfiguration,
			Dictionary<String, String> parameters) {
		synchronized (this) {
			readerConfigurationsByName.put(readerConfiguration.getName(),
					readerConfiguration);
		}
	}

	/**
	 * Unbind a disapearing service from this service.
	 * 
	 * @param readerFactory
	 * @param parameters
	 */
	public void unbind(ReaderConfiguration<?> readerConfiguration,
			Dictionary<String, String> parameters) {
		synchronized (this) {
			readerConfigurationsByName.remove(readerConfiguration.getName());
			if (readerSessionByReaderConfig.get(readerConfiguration) != null) {
				for (ReaderSession session : readerSessionByReaderConfig
						.get(readerConfiguration)) {
					session.destroy();
					for (List<ReaderSession> sessionList : readerSessionByCommandFactory
							.values()) {
						if (sessionList.contains(session)) {
							sessionList.remove(session);
						}
					}
				}
				readerSessionByReaderConfig.get(readerConfiguration).clear();
			}
		}
	}

	/**
	 * Used by spring to give the initial list of configs.
	 * 
	 * @param configurations
	 */
	public void setReaderConfigurations(
			Set<ReaderConfiguration<?>> configurations) {
		synchronized (this) {
			for (ReaderConfiguration<?> config : configurations) {
				readerConfigurationsByName.put(config.getName(), config);
			}
		}
	}

	/**
	 * Bind a new command factory to this service.
	 * 
	 * @param readerFactory
	 * @param parameters
	 */
	public void bindCommand(CommandFactory<?> commandFactory,
			Dictionary<String, String> parameters) {
		synchronized (this) {
			commandFactoriesByName.put(commandFactory.getCommandName(),
					commandFactory);
		}
	}

	/**
	 * Unbind a disapearing command service from this service.
	 * 
	 * @param readerFactory
	 * @param parameters
	 */
	public void unbindCommand(CommandFactory<?> commandFactory,
			Dictionary<String, String> parameters) {
		synchronized (this) {
			commandFactoriesByName.remove(commandFactory.getCommandName());
			if (readerSessionByCommandFactory.get(commandFactory) != null) {
				for (ReaderSession session : readerSessionByCommandFactory
						.get(commandFactory)) {
					session.destroy();
					for (List<ReaderSession> sessionList : readerSessionByReaderConfig
							.values()) {
						if (sessionList.contains(session)) {
							sessionList.remove(session);
						}
					}
				}
				readerSessionByCommandFactory.get(commandFactory).clear();
			}
		}
	}

	/**
	 * Used by spring to give the initial list of command factories.
	 * 
	 * @param factories
	 */
	public void setCommandFactories(Set<CommandFactory<?>> factories) {
		synchronized (this) {
			for (CommandFactory<?> factory : factories) {
				commandFactoriesByName.put(factory.getCommandName(), factory);
			}
		}
	}
}
