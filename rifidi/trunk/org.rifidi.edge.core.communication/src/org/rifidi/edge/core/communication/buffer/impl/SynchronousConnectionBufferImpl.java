/*
 *  SynchronousConnectionBufferImpl.java
 *
 *  Created:	Jun 27, 2008
 *  Project:	RiFidi Emulator - A Software Simulation Tool for RFID Devices
 *  				http://www.rifidi.org
 *  				http://rifidi.sourceforge.net
 *  Copyright:	Pramari LLC and the Rifidi Project
 *  License:	Lesser GNU Public License (LGPL)
 *  				http://www.opensource.org/licenses/lgpl-license.html
 */
package org.rifidi.edge.core.communication.buffer.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rifidi.edge.core.exception.readerConnection.RifidiIllegalOperationException;
import org.rifidi.edge.core.service.communication.buffer.ConnectionBuffer;

public class SynchronousConnectionBufferImpl implements ConnectionBuffer,
		Thread.UncaughtExceptionHandler {

	private Log logger = LogFactory
			.getLog(AsynchronousConnectionBufferImpl.class);

	private BlockingQueue<Object> readQueue;
	private BlockingQueue<Object> writeQueue;

	private IOException exception = null;

	private Set<Thread> currentThreads;

	public SynchronousConnectionBufferImpl(BlockingQueue<Object> readQueue,
			BlockingQueue<Object> writeQueue) {
		this.readQueue = readQueue;
		this.writeQueue = writeQueue;

		this.currentThreads = new HashSet<Thread>();
	}

	@Override
	public Object recieve() throws RifidiIllegalOperationException, IOException {
		throw new RifidiIllegalOperationException("recieve is not allowed in Synchronous Mode");
	}

	@Override
	public void send(Object message) throws RifidiIllegalOperationException,
			IOException {
		throw new RifidiIllegalOperationException("recieve is not allowed in Synchronous Mode");
	}

	@Override
	public Object sendAndRecieve(Object message)
			throws RifidiIllegalOperationException, IOException {
		checkForException();
		synchronized (currentThreads) {
			currentThreads.add(Thread.currentThread());
		}	
		try {
			
			//make sure this message is sent out.
			synchronized (readQueue) {
				writeQueue.put(message);
				readQueue.notifyAll();
			}

			return readQueue.take();
		} catch (InterruptedException e) {	
		} finally {
			synchronized (currentThreads) {
				checkForException();
			
				currentThreads.remove(Thread.currentThread());
			}	
		}
		
		return null;
	}

	private void checkForException() throws IOException {
		if (exception != null)
			throw exception;
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		
		if (e instanceof Exception) {
			synchronized (currentThreads) {
				logger.debug("There was an Exception in "
						+ "the underlying communication layer: "
						+ e.getClass().getName(), e);
				exception = (IOException) e;
				
				// Cause all waiting threads to interrupt to get notification about that
				// exception we just catch
				for (Thread interruptThread : currentThreads) {
					interruptThread.interrupt();
				}
			}
		} else {
			t.getThreadGroup().uncaughtException(t, e);
		}

	}
}
