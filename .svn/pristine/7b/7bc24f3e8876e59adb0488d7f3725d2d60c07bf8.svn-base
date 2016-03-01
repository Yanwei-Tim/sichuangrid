package com.tianque.core.datatransfer.util;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

/**
 * 
 * CircularDoubleBufferedQueue.java 囧囧有神
 * 
 * @param <E>2010-6-12
 */
public class CircularDoubleBufferedQueue<E> extends AbstractQueue<E> implements
		BlockingQueue<E>, java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(CircularDoubleBufferedQueue.class
			.getName());

	private static CircularDoubleBufferedQueue circularDoubleBufferedQueue;
	private final static int MAX_QUEUE_NUM = 4;//

	/** The queued items */
	private final E[] itemsA;
	private final E[] itemsB;

	private ReentrantLock readLock, writeLock;
	private Condition notEmpty;
	private Condition notFull;
	private Condition awake;

	private E[] writeArray, readArray;
	private volatile int writeCount, readCount;
	private int writeArrayHP, writeArrayTP, readArrayHP, readArrayTP;

	private CircularDoubleBufferedQueue(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException(
					"Queue initial capacity can't less than 0!");
		}

		itemsA = (E[]) new Object[capacity];
		itemsB = (E[]) new Object[capacity];

		readLock = new ReentrantLock();
		writeLock = new ReentrantLock();

		notEmpty = readLock.newCondition();
		notFull = writeLock.newCondition();
		awake = writeLock.newCondition();

		readArray = itemsA;
		writeArray = itemsB;
	}

	public static synchronized CircularDoubleBufferedQueue getInstance(
			int capacity) {
		if (circularDoubleBufferedQueue == null) {
			circularDoubleBufferedQueue = new CircularDoubleBufferedQueue(
					capacity);
		}
		return circularDoubleBufferedQueue;
	}

	public static synchronized CircularDoubleBufferedQueue getInstance() {
		return getInstance(MAX_QUEUE_NUM);
	}

	public int getDefaultMaxCapacity() {
		return MAX_QUEUE_NUM;
	}

	@Override
	public Iterator<E> iterator() {
		return null;
	}

	@Override
	public int size() {
		readLock.lock();
		try {
			return readCount;
		} finally {
			readLock.unlock();
		}
	}

	/**
	 * nonblocking interface
	 */
	public boolean offer(E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		writeLock.lock();
		try {
			if (writeCount == writeArray.length) {
				return false;
			} else {
				insert(e);
				return true;
			}
		} finally {
			writeLock.unlock();
		}
	}

	private void insert(E e) {
		writeArray[writeArrayTP] = e;
		++writeArrayTP;
		++writeCount;
	}

	private E extract() {
		E e = readArray[readArrayHP];
		readArray[readArrayHP] = null;
		++readArrayHP;
		--readCount;
		return e;
	}

	/**
	 * nonblocking interface
	 */
	public E peek() {
		readLock.lock();
		try {
			if (readCount <= 0) {
				return null;
			} else {
				return readArray[readArrayHP];
			}
		} finally {
			readLock.unlock();
		}
	}

	/**
	 * nonblocking interface
	 */
	public E poll() {
		readLock.lock();
		try {
			if (readCount <= 0) {
				return null;
			} else {
				return extract();
			}
		} finally {
			readLock.unlock();
		}
	}

	/**
	 * switch condition: read queue is empty && write queue is not empty
	 * 
	 * Notice:This function can only be invoked after readLock is grabbed, or
	 * may cause dead lock
	 * 
	 * @param timeout
	 * @param isInfinite
	 *            : whether need to wait forever until some other thread awake
	 *            it
	 * @return
	 * @throws InterruptedException
	 */
	private long queueSwitch(long timeout, boolean isInfinite)
			throws InterruptedException {
		writeLock.lock();
		try {
			if (writeCount <= 0) {
				logger.debug("Write Count:" + writeCount
						+ ", Write Queue is empty, do not switch!");
				try {
					logger.debug("Queue is empty, need wait....");
					if (isInfinite && timeout <= 0) {
						awake.await();
						return -1;
					} else {
						return awake.awaitNanos(timeout);
					}
				} catch (InterruptedException ie) {
					awake.signal();
					throw ie;
				}
			} else {
				E[] tmpArray = readArray;
				readArray = writeArray;
				writeArray = tmpArray;

				readCount = writeCount;
				readArrayHP = 0;
				readArrayTP = writeArrayTP;

				writeCount = 0;
				writeArrayHP = readArrayHP;
				writeArrayTP = 0;

				notFull.signal();
				logger.debug("Queue switch successfully!");
				return -1;
			}
		} finally {
			writeLock.unlock();
		}
	}

	public int drainTo(Collection<? super E> c) {
		throw new UnsupportedOperationException();
	}

	public int drainTo(Collection<? super E> c, int maxElements) {
		throw new UnsupportedOperationException();
	}

	public boolean offer(E e, long timeout, TimeUnit unit)
			throws InterruptedException {
		if (e == null) {
			throw new NullPointerException();
		}

		long nanoTime = unit.toNanos(timeout);
		writeLock.lockInterruptibly();
		try {
			for (;;) {
				if (writeCount < writeArray.length) {
					insert(e);
					if (writeCount == 1) {
						awake.signal();
					}
					return true;
				}

				// Time out
				if (nanoTime <= 0) {
					logger.debug("offer wait time out!");
					return false;
				}
				// keep waiting
				try {
					logger.debug("Queue is full, need wait....");
					nanoTime = notFull.awaitNanos(nanoTime);
				} catch (InterruptedException ie) {
					notFull.signal();
					throw ie;
				}
			}
		} finally {
			writeLock.unlock();
		}
	}

	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		long nanoTime = unit.toNanos(timeout);
		readLock.lockInterruptibly();

		try {
			for (;;) {
				if (readCount > 0) {
					return extract();
				}

				if (nanoTime <= 0) {
					logger.debug("poll time out!");
					return null;
				}
				nanoTime = queueSwitch(nanoTime, false);
			}
		} finally {
			readLock.unlock();
		}
	}

	/**
	 * blocking interface
	 */
	public void put(E e) throws InterruptedException {
		if (e == null) {
			throw new NullPointerException();
		}
		writeLock.lockInterruptibly();
		try {
			try {
				while (writeCount >= writeArray.length) {
					notFull.await();
				}
			} catch (InterruptedException ie) {
				notFull.signal();
				throw ie;
			}

			insert(e);

			if (writeCount == 1) {
				awake.signal();
			}
		} finally {
			writeLock.unlock();
		}
	}

	public int remainingCapacity() {
		writeLock.lock();
		try {
			return writeArray.length - writeCount;
		} finally {
			writeLock.unlock();
		}
	}

	/**
	 * blocking interface
	 */
	public E take() throws InterruptedException {
		readLock.lockInterruptibly();
		try {
			for (;;) {
				if (readCount > 0) {
					return extract();
				}

				queueSwitch(-1, true);
			}
		} finally {
			readLock.unlock();
		}
	}
}
