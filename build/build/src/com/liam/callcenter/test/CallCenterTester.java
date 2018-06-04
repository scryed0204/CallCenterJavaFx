package com.liam.callcenter.test;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.liam.callcenter.bean.Call;
import com.liam.callcenter.util.CallGenerator;
import com.liam.callcenter.util.CallHandler;

/**
 * This class is the test case of the Call Center question. The user can enter
 * how many calls to be generated every 3 seconds to test the logic.
 * 
 * @author Liam
 */
public class CallCenterTester {
	static ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	static ScheduledFuture<?> t;
	static Integer callQty = null;
	static Integer attempts = 1;
	static CallHandler callHandler = CallHandler.getInstance(2);

	public static void StartTest(int inputCnt) {

		// Scanner scanner = new Scanner(System.in);
		// System.out.println("Please input the quantity of the calls you want to test:
		// ");
		// callQty = scanner.nextInt();

		callQty = inputCnt;
		// Generate a Call every 3 seconds
		t = executorService.scheduleAtFixedRate(CallCenterTester::testTask, 0, 3, TimeUnit.SECONDS);

	}

	private static void testTask() {
		Call call = CallGenerator.getNewCall();
		callHandler.handleCall(call);

		attempts++;
		if (attempts > callQty) {
			t.cancel(false);
		}
	}

}
