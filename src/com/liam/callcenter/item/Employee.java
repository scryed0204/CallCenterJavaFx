package com.liam.callcenter.item;

/**
 * Employee is the interface for all types of employees.
 * 
 * @author Liam
 *
 */
public interface Employee {

	/**
	 * An employee who is available will try to handle the call.
	 * 
	 * @param call
	 */
	public void proceedCall(Call call);

	/**
	 * If the call is beyond this employee's capability to handle, the employee will
	 * pass the call to a it's supervisor.
	 * 
	 * @param call
	 */
	public void passCall(Call call);

	/**
	 * Check if the employee is currently on a call.
	 * 
	 * @return boolean
	 */
	public boolean isAvailable();

	/**
	 * Get the employee's identifier.
	 * 
	 * @return String
	 */
	public String getEmployeeId();

}
