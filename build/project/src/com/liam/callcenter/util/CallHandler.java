package com.liam.callcenter.util;

import java.util.ArrayList;
import java.util.List;

import com.liam.callcenter.bean.Call;
import com.liam.callcenter.bean.Employee;
import com.liam.callcenter.bean.Fresher;
import com.liam.callcenter.bean.ProductManager;
import com.liam.callcenter.bean.TechnicalLead;

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
			if (fresher.isAvailiable()) {
				respondent = fresher;
				break;
			}
		}

		if (null == respondent && tl.isAvailiable()) {
			respondent = tl;
		}

		if (null == respondent && pm.isAvailiable()) {
			respondent = pm;
		}

		if (null == respondent) {
			System.out.println("Everyone is busy so a call is missed: " + call.getCallId());
		} else {
			respondent.proceedCall(call);
		}
	}

	public void escalateCall(Employee passer, Call call) {
		System.out.println(
				"Call escalated by " + passer.getEmployeeId() + ": " + call.getCallLvl() + " / " + call.getCallId());

		Employee respondent = null;
		if (passer instanceof Fresher) {
			if (tl.isAvailiable()) {
				respondent = tl;
			} else if (pm.isAvailiable()) {
				respondent = pm;
			}
		} else if (passer instanceof TechnicalLead) {
			if (pm.isAvailiable()) {
				respondent = pm;
			}
		}

		if (null == respondent) {
			System.out.println("No employee is availiable to handle this call: " + call.getCallId());
		} else {
			respondent.proceedCall(call);
		}
	}

}
