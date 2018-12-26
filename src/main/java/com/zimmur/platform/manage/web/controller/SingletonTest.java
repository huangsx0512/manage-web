package com.zimmur.platform.manage.web.controller;

public class SingletonTest {
	private static SingletonTest instance;
	private SingletonTest() {}
	public static SingletonTest getIstance() {
		if (instance == null) {
			synchronized (SingletonTest.class) {
				if (instance == null) {
					instance = new SingletonTest();
				}
			}
		}
		return instance;
	}
}