package com.guidomaiola.springboot.SpringBootRestWithH2.exception;

import java.util.NoSuchElementException;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		if (e instanceof NoSuchElementException) {
			System.out.print("The hero does not exist.");
		} else if (e instanceof Exception) {
			System.out.print("An Exception was thrown.");
		} else if (e instanceof Error) {
			System.out.print("Error occured.");
		}

	}
}
