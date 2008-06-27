package org.rifidi.edge.core.junit.connection;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rifidi.edge.core.communication.service.CommunicationService;
import org.rifidi.edge.core.communication.service.impl.CommunicationServiceImpl;
import org.rifidi.edge.core.connection.IReaderConnection;
import org.rifidi.edge.core.connection.registry.ReaderConnectionRegistryService;
import org.rifidi.edge.core.exception.RifidiException;
import org.rifidi.edge.core.exception.readerConnection.RifidiConnectionException;
import org.rifidi.edge.core.exception.readerConnection.RifidiConnectionIllegalStateException;
import org.rifidi.edge.core.readerPlugin.enums.EReaderAdapterState;
import org.rifidi.edge.core.readerPluginService.ReaderPluginRegistryService;
import org.rifidi.edge.readerplugin.dummy.DummyReaderInfo;
import org.rifidi.edge.readerplugin.dummy.DummyReaderPluginFactory;
import org.rifidi.edge.readerplugin.dummy.EDummyError;
import org.rifidi.edge.readerplugin.dummy.commands.DummyCustomCommand;
import org.rifidi.services.annotations.Inject;
import org.rifidi.services.registry.ServiceRegistry;

/**
 * @author Jerry Maine - jerry@pramari.com
 *
 */
public class SessionBreakageTest {
	// private static final Log logger =
	// LogFactory.getLog(SessionBreakageTest.class);

	private ConnectionFactory connectionFactory;

	private ReaderConnectionRegistryService sessionRegistryService;

	private ReaderPluginRegistryService readerPluginRegistryService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceRegistry.getInstance().service(this);
		// to forcefully load the communication service implementation
		System.out.println(CommunicationServiceImpl.class);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSessionConnectionErrorHandling() {

		readerPluginRegistryService.registerReaderAdapter(
				DummyReaderInfo.class, new DummyReaderPluginFactory());

		DummyReaderInfo info = new DummyReaderInfo();
		info.setIPAddress("127.0.0.1");
		info.setPort(10001);
		
		info.setErrorToSet(EDummyError.CONNECT.toString());

		IReaderConnection s = sessionRegistryService
				.createReaderConnection(info);
		
		Assert.assertNotNull(s);

		try {
			s.connect();
		} catch (RifidiException e) {
			e.printStackTrace();
			Assert.assertTrue(s.getState() == EReaderAdapterState.ERROR);
		}
		

		
	}

	@Test
	public void testSessionConnectionErrorHandlingRuntime() {

		readerPluginRegistryService.registerReaderAdapter(
				DummyReaderInfo.class, new DummyReaderPluginFactory());

		DummyReaderInfo info = new DummyReaderInfo();
		info.setIPAddress("127.0.0.1");
		info.setPort(10002);
		
		info.setErrorToSet(EDummyError.CONNECT_RUNTIME.toString());

		IReaderConnection s = sessionRegistryService
				.createReaderConnection(info);

		Assert.assertNotNull(s);
		
		try {
			s.connect();
		} catch (RifidiException e) {
			
			e.printStackTrace();
			Assert.assertTrue(s.getState() == EReaderAdapterState.ERROR);
			Assert.assertTrue(s.getErrorCause().getCause() instanceof RuntimeException);
		}
		


	}

	@Test
	public void testGetTagsError() {

		// set up dummy connection Info
		DummyReaderInfo info = new DummyReaderInfo();
		info.setIPAddress("127.0.0.1");
		info.setPort(10003);

		info.setErrorToSet(EDummyError.GET_NEXT_TAGS.toString());

		// create the dummy reader adapter


		// create the reader Session
		IReaderConnection s = sessionRegistryService
				.createReaderConnection(info);

		try {
			s.connect();
		} catch (RifidiException e1) {
			e1.printStackTrace();
			Assert.fail();
		}

		try {
			s.startTagStream();
		} catch (RifidiException e1) {
			e1.printStackTrace();
			Assert.fail();
		}

		Connection c;
		try {
			c = connectionFactory.createConnection();
			c.start();

			javax.jms.Session jmsSession = c.createSession(false,
					javax.jms.Session.AUTO_ACKNOWLEDGE);
			Destination dest = jmsSession.createQueue(Integer.toString(s
					.getSessionID()));
			MessageConsumer consumer = jmsSession.createConsumer(dest);

			// Get the next message from the queue
			Message m = consumer.receive(500);

			System.out.println(((TextMessage) m).getText() + "== END ==");

			m = consumer.receive(5000);

			System.out.println(((TextMessage) m).getText() + "== END ==");

			// If the queue is not null, then we have started the tag stream
			// correctly
			Assert.assertNotNull(m);

			try {
				s.stopTagStream();
			} catch (RifidiException e) {
				e.printStackTrace();
				Assert.fail();
			} finally {
				System.out.println("The state of the plugin is" + s.getState());
			}

			// see if there is anything in the queue after it has been stopped.
			m = consumer.receive(5000);

			Assert.assertNull(m);

		} catch (JMSException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (NullPointerException e) {

			Assert.assertSame(EReaderAdapterState.ERROR, s.getState());
			Assert.assertNotNull(s.getErrorCause());
			Assert.assertTrue(s.getErrorCause() instanceof RifidiConnectionIllegalStateException);
		}

	}

	@Test
	public void testGetTagsErrorRuntime() {

		// set up dummy connection Info
		DummyReaderInfo info = new DummyReaderInfo();
		info.setIPAddress("127.0.0.1");
		info.setPort(10004);

		info.setErrorToSet(EDummyError.GET_NEXT_TAGS_RUNTIME.toString());

		// create the reader Session
		IReaderConnection s = sessionRegistryService
				.createReaderConnection(info);

		try {
			s.connect();
		} catch (RifidiException e1) {
			e1.printStackTrace();
			Assert.fail();
		}

		try {
			s.startTagStream();
		} catch (RifidiException e1) {
			e1.printStackTrace();
			Assert.fail();
		}

		Connection c;
		try {
			c = connectionFactory.createConnection();
			c.start();

			javax.jms.Session jmsSession = c.createSession(false,
					javax.jms.Session.AUTO_ACKNOWLEDGE);
			Destination dest = jmsSession.createQueue(Integer.toString(s
					.getSessionID()));
			MessageConsumer consumer = jmsSession.createConsumer(dest);

			// Get the next message from the queue
			Message m = consumer.receive(500);

			System.out.println(((TextMessage) m).getText() + "== END ==");

			m = consumer.receive(5000);

			System.out.println(((TextMessage) m).getText() + "== END ==");

			// If the queue is not null, then we have started the tag stream
			// correctly
			Assert.assertNotNull(m);

			try {
				s.stopTagStream();
			} catch (RifidiException e) {
				e.printStackTrace();
				Assert.fail();
			}

			// see if there is anything in the queue after it has been stopped.
			m = consumer.receive(5000);

			Assert.assertNull(m);

		} catch (JMSException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (NullPointerException e) {

			Assert.assertSame(EReaderAdapterState.ERROR, s.getState());
			Assert.assertNotNull(s.getErrorCause());
			Assert.assertTrue(s.getErrorCause().getCause() instanceof RuntimeException);
		}

	}

	@Test
	public void testSessionSendCustomCommand() {

		readerPluginRegistryService.registerReaderAdapter(
				DummyReaderInfo.class, new DummyReaderPluginFactory());

		DummyReaderInfo info = new DummyReaderInfo();
		info.setIPAddress("127.0.0.1");
		info.setPort(10005);
		
		info.setErrorToSet(EDummyError.SEND_CUSTOM_COMMAND.toString());

		IReaderConnection s = sessionRegistryService
				.createReaderConnection(info);

		try {
			s.connect();
		} catch (RifidiException e) {
			e.printStackTrace();
		}

		try {
			s.sendCustomCommand(new DummyCustomCommand("Command"));
		} catch (RifidiException e) {
			e.printStackTrace();
		}

		System.out.println(s.getState());

		Assert.assertSame(s.getState(), EReaderAdapterState.ERROR);
		System.out.println(s.getErrorCause().getClass().getSimpleName());
		Assert.assertTrue(s.getErrorCause() instanceof RifidiConnectionIllegalStateException);
	}

	@Test
	public void testSessionSendCustomCommandRealTime() {

		readerPluginRegistryService.registerReaderAdapter(
				DummyReaderInfo.class, new DummyReaderPluginFactory());

		DummyReaderInfo info = new DummyReaderInfo();
		info.setIPAddress("127.0.0.1");
		info.setPort(10006);
		
		info.setErrorToSet(EDummyError.SEND_CUSTOM_COMMAND_RUNTIME.toString());

		IReaderConnection s = sessionRegistryService
				.createReaderConnection(info);

		try {
			s.connect();
		} catch (RifidiException e) {
			e.printStackTrace();
		}

		try {
			s.sendCustomCommand(new DummyCustomCommand("Command"));
		} catch (RifidiException e) {
			e.printStackTrace();
		}

		System.out.println(s.getState());

		Assert.assertSame(s.getState(), EReaderAdapterState.ERROR);
		Assert.assertTrue(s.getErrorCause().getCause() instanceof RuntimeException);
	}

	@Test
	public void testSessionDisconnectCommand() {

		readerPluginRegistryService.registerReaderAdapter(
				DummyReaderInfo.class, new DummyReaderPluginFactory());

		DummyReaderInfo info = new DummyReaderInfo();
		info.setIPAddress("127.0.0.1");
		info.setPort(10007);
		
		info.setErrorToSet(EDummyError.DISCONNECT.toString());

		IReaderConnection s = sessionRegistryService
				.createReaderConnection(info);

		try {
			s.connect();
		} catch (RifidiException e) {
			e.printStackTrace();
		}

		try {
			s.disconnect();
		} catch (RifidiException e) {
			e.printStackTrace();
		}

		Assert.assertSame(s.getState(), EReaderAdapterState.ERROR);
		Assert.assertTrue(s.getErrorCause() instanceof RifidiConnectionException);
	}

	@Test
	public void testSessionDisconnectRealTime() {

		readerPluginRegistryService.registerReaderAdapter(
				DummyReaderInfo.class, new DummyReaderPluginFactory());

		DummyReaderInfo info = new DummyReaderInfo();
		info.setIPAddress("127.0.0.1");
		info.setPort(10008);
		
		info.setErrorToSet(EDummyError.DISCONNECT_RUNTIME.toString());

		IReaderConnection s = sessionRegistryService
				.createReaderConnection(info);

		try {
			s.connect();
		} catch (RifidiException e) {
			e.printStackTrace();
			Assert.fail();
		}

		try {
			s.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Assert.assertSame(s.getState(), EReaderAdapterState.ERROR);
		System.out.println(s.getErrorCause().getCause().getClass().getSimpleName());
		Assert.assertTrue(s.getErrorCause().getCause() instanceof RuntimeException);
	}

	@Inject
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	@Inject
	public void setSessionRegistryService(ReaderConnectionRegistryService regSer) {
		this.sessionRegistryService = regSer;
	}

	@Inject
	public void setAdapterRegistryService(
			ReaderPluginRegistryService readerPluginRegistryService) {
		this.readerPluginRegistryService = readerPluginRegistryService;

		readerPluginRegistryService.registerReaderAdapter(
				DummyReaderInfo.class, new DummyReaderPluginFactory());
	}

}
