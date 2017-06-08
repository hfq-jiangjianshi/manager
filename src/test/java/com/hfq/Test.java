package com.hfq;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

	public static void main(String[] args) {

		ExecutorService ex = Executors.newFixedThreadPool(1);
		
		ex.execute(new TestThread());
//		for (int i = 0; i < 10; i++) {
//			ex.execute(new TestThread());
//		}
	}
}
