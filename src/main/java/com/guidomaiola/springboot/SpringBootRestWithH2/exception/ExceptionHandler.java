package com.guidomaiola.springboot.SpringBootRestWithH2.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.NoSuchElementException;

@Slf4j
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		if (e instanceof NoSuchElementException) {
			log.info("The hero does not exist.");
		} else if (e instanceof Exception) {
			log.info("An Exception was thrown.");
		} else if (e instanceof Error) {
			log.info("Error occured.");
		}

	}
}
