package com.pract.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class PrinterRegistry {

	private AtomicInteger activeThreadNo = null;
	private AtomicInteger printVal = null;
	private int totalThreadCount = 0;
	private static PrinterRegistry printDetails = null;

	private PrinterRegistry(int totalThreadCount) {
		this.activeThreadNo = new AtomicInteger(1);
		this.printVal = new AtomicInteger(1);
		this.totalThreadCount = totalThreadCount;
	}

	public static PrinterRegistry getPrintDetails(int totalThreadCount) {
		if (PrinterRegistry.printDetails == null) {
			synchronized (PrinterRegistry.class) {
				if (PrinterRegistry.printDetails == null) {
					PrinterRegistry.printDetails = new PrinterRegistry(totalThreadCount);
				}
			}
		}
		return PrinterRegistry.printDetails;
	}

	public boolean isValidThreadToPrintDetails(int threadNo) {
		return this.activeThreadNo.get() == threadNo;
	}

	public int getAndAddPrintVal() {
		return this.printVal.getAndAdd(1);
	}

	public void increamentActiveThreadNo() {
		this.activeThreadNo.incrementAndGet();
		this.activeThreadNo.compareAndSet(this.totalThreadCount + 1, 1);
	}

	public int getPrintVal() {
		return this.printVal.get();
	}
}
