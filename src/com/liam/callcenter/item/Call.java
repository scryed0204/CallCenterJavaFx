package com.liam.callcenter.item;

/**
 * Call is the class demonstrate a single incoming call.
 * callLvl is the attribute to represent how hard is this call to handle.
 * callId is the unique identifier of the call object.
 * 
 * @author Liam
 *
 */
public class Call {

	private Integer callLvl;

	private Integer callId;

	/**
	 * Constructor.
	 * 
	 * @param callLvl
	 * @param callId
	 */
	public Call(int callLvl, int callId) {
		this.callLvl = callLvl;
		this.callId = callId;
	}

	public Integer getCallLvl() {
		return callLvl;
	}

	public Integer getCallId() {
		return callId;
	}

	public void setCallId(Integer callId) {
		this.callId = callId;
	}

}
