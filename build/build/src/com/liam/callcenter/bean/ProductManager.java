package com.liam.callcenter.bean;

import java.util.concurrent.TimeUnit;

import com.liam.callcenter.util.CallHandler;

public class ProductManager implements Employee {

	public static final int manageableCallLvl = 100;

	private boolean isAvailiable = true;
	private String employeeId;
	private CallHandler callHandler;

	// Singleton
	private static ProductManager instance;

	private ProductManager(CallHandler callHandler) {
		this.callHandler = callHandler;
		this.employeeId = this.getClass().getSimpleName();
	}

	public synchronized static ProductManager getInstance(CallHandler callHandler) {

		if (instance == null) {
			instance = new ProductManager(callHandler);
		}

		return instance;
	}

	@Override
	public synchronized void proceedCall(Call call) {
		if (call.getCallLvl() > manageableCallLvl) {
			passCall(call);
		} else {
			isAvailiable = false;

			System.out.println(employeeId + " is now on a call: " + call.getCallLvl() + " / " + call.getCallId());
			Thread thread = new Thread() {
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.println(employeeId + " handled a call " + call.getCallLvl() + " / " + call.getCallId());

					isAvailiable = true;
				}
			};

			thread.start();
		}

	}

	@Override
	public void passCall(Call call) {
		callHandler.escalateCall(this, call);
	}

	@Override
	public boolean isAvailiable() {
		return isAvailiable;
	}

	@Override
	public String getEmployeeId() {
		return employeeId;
	}

}