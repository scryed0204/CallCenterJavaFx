package com.liam.callcenter.bean;

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
