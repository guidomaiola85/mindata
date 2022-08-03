package com.guidomaiola.springboot.SpringBootRestWithH2.exception;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		if (e instanceof Exception) {
			System.out.print("Exception 1 occured.");
		}
		if (e instanceof Error) {
			System.out.print("Exception 1 occured.");
		}

	}
}
