package com.liam.callcenter.bean;

public interface Employee {

	/**
	 * @param call
	 */
	public void proceedCall(Call call);

	/**
	 * @param call
	 */
	public void passCall(Call call);

	/**
	 * @return
	 */
	public boolean isAvailiable();

	/**
	 * @return
	 */
	public String getEmployeeId();

}
