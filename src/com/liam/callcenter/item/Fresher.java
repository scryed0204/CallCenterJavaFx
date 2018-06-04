package com.liam.callcenter.item;

import java.util.concurrent.TimeUnit;

import com.liam.callcenter.util.CallHandler;
import com.liam.callcenter.util.TextAreaPrinter;

/**
 * Fresher is an implementation of {@link Employee}.
 * It can handle the {@link Call} which has callLevel not greater than 50;
 * 
 * @author Liam
 *
 */
public class Fresher implements Employee {

	public final int manageableCallLvl = 50;

	private boolean isAvailable = true;
	private String employeeId;
	private CallHandler callHandler;

	public Fresher(CallHandler callHandler, int fresherNo) {
		this.callHandler = callHandler;
		this.employeeId = this.getClass().getSimpleName() + fresherNo;
	}

	@Override
	public synchronized void proceedCall(Call call) {
		if (call.getCallLvl() > manageableCallLvl) {
			passCall(call);
		} else {
			isAvailable = false;
			TextAreaPrinter.getInstance().printText("[" + call.getCallId() + "]" + employeeId + " is now on this call");

			Thread thread = new Thread() {
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					TextAreaPrinter.getInstance()
							.printText("[" + call.getCallId() + "]" + employeeId + " is free from this call ");
					isAvailable = true;
				}
			};

			thread.start();
		}

	}

	@Override
	public void passCall(Call call) {
		callHandler.escalateCall(this, call);
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	@Override
	public String getEmployeeId() {
		return employeeId;
	}

}
