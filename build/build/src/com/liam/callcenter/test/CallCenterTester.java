package com.liam.callcenter.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.liam.callcenter.item.Call;
import com.liam.callcenter.util.CallGenerator;
import com.liam.callcenter.util.CallHandler;
import com.liam.callcenter.util.TextAreaPrinter;

/**
 * This class is the test case of the Call Center question. The user can enter
 * how many calls to be generated in every 3 seconds for the test run.
 * 
 * @author Liam
 */
public class CallCenterTester {
	static ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	static ScheduledFuture<?> t;
	static Integer callQty = null;
	static Integer attempts = 1;
	static CallHandler callHandler = CallHandler.getInstance(3);

	public static void StartTest(int inputCnt) {
		callQty = inputCnt;
		TextAreaPrinter.getInstance()
				.printText("Generate a Call every 3 seconds and totally " + inputCnt + " calls...");

		// Generate a Call every 3 seconds
		t = executorService.scheduleAtFixedRate(CallCenterTester::testTask, 0, 3, TimeUnit.SECONDS);

	}

	private static void testTask() {
		Call call = CallGenerator.getNewCall();
		TextAreaPrinter.getInstance().printText("Call[" + call.getCallId() + "] is calling in...");
		callHandler.handleCall(call);

		attempts++;
		if (attempts > callQty) {
			t.cancel(false);
		}
	}

}
