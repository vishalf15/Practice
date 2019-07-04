package com.pract.thread;

import java.util.concurrent.locks.Lock;

public class PrinterThread implements Runnable {

	private final int threadNo;
	private Lock lock = null;
	private PrinterRegistry printDetails = null;

	public PrinterThread(int tn, Lock l, PrinterRegistry printDetails) {
		this.threadNo = tn;
		this.lock = l;
		this.printDetails = printDetails;
	}

	@Override
	public void run() {
		while (this.printDetails.getPrintVal() < 15) {
			this.lock.lock();
			if (this.printDetails.isValidThreadToPrintDetails(this.threadNo)) {
				System.out.println("ThreadNo = " + this.threadNo + ", Val = " + this.printDetails.getAndAddPrintVal());
				this.printDetails.increamentActiveThreadNo();
			}
			this.lock.unlock();
		}

	}

}
