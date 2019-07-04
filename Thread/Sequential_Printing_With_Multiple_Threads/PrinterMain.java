package com.pract.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterMain {

	public static final int THREAD_COUNT = 3;

	public static void main(String[] args) {

		final PrinterRegistry printDetails = PrinterRegistry.getPrintDetails(PrinterMain.THREAD_COUNT);
		final Lock lock = new ReentrantLock(true);

		for (int i = 1; i <= PrinterMain.THREAD_COUNT; i++) {
			new Thread(new PrinterThread(i, lock, printDetails)).start();
		}
	}

}
