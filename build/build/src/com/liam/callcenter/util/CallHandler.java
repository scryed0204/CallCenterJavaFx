package com.liam.callcenter.util;

import java.util.ArrayList;
import java.util.List;

import com.liam.callcenter.item.Call;
import com.liam.callcenter.item.Employee;
import com.liam.callcenter.item.Fresher;
import com.liam.callcenter.item.ProductManager;
import com.liam.callcenter.item.TechnicalLead;

/**
 * This class is the handler to distribute the {@link Call} to the correct
 * respondent.
 * 
 * @author Liam
 */
public class CallHandler {

	private static CallHandler instance;

	private List<Fresher> fresherList;
	private TechnicalLead tl;
	private ProductManager pm;

	// singleton
	private CallHandler() {
	}

	private CallHandler(int fresherNumber) {
		fresherList = new ArrayList<Fresher>();
		for (int i = 1; i <= fresherNumber; i++) {
			fresherList.add(new Fresher(this, i));
		}
		tl = TechnicalLead.getInstance(this);
		pm = ProductManager.getInstance(this);
	}

	/**
	 * Get a instance of the singleton class {@link CallHandler}
	 * 
	 * @param fresherNumber
	 *            how many freshers you want within the test
	 * @return
	 */
	public synchronized static CallHandler getInstance(int fresherNumber) {

		if (instance == null) {
			instance = new CallHandler(fresherNumber);
		}

		return instance;
	}

	public void handleCall(Call call) {
		Employee respondent = null;

		for (Fresher fresher : fresherList) {
			if (fresher.isAvailable()) {
				respondent = fresher;
				break;
			}
		}

		if (null == respondent && tl.isAvailable()) {
			respondent = tl;
		}

		if (null == respondent && pm.isAvailable()) {
			respondent = pm;
		}

		if (null == respondent) {
			TextAreaPrinter.getInstance().printText("[" + call.getCallId() + "]Everyone is busy so a call is missed.");

		} else {
			respondent.proceedCall(call);
		}
	}

	public void escalateCall(Employee passer, Call call) {
		TextAreaPrinter.getInstance()
				.printText("[" + call.getCallId() + "]" + "This call is escalated by " + passer.getEmployeeId());

		Employee respondent = null;
		if (passer instanceof Fresher) {
			if (tl.isAvailable()) {
				respondent = tl;
			} else if (pm.isAvailable()) {
				respondent = pm;
			}
		} else if (passer instanceof TechnicalLead) {
			if (pm.isAvailable()) {
				respondent = pm;
			}
		}

		if (null == respondent) {
			TextAreaPrinter.getInstance()
					.printText("[" + call.getCallId() + "]" + "No employee is availiable to handle this call.");
		} else {
			respondent.proceedCall(call);
		}
	}

}
