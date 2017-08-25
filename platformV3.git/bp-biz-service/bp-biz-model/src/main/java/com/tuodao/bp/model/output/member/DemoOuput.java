package com.tuodao.bp.model.output.member;

import java.io.Serializable;

import com.google.common.base.MoreObjects;

public class DemoOuput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String outName;

	private String outAddress;

	public String getOutName() {
		return outName;
	}

	public void setOutName(String outName) {
		this.outName = outName;
	}

	public String getOutAddress() {
		return outAddress;
	}

	public void setOutAddress(String outAddress) {
		this.outAddress = outAddress;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("outName", outName).add("outAddress", outAddress).omitNullValues().toString();
	}
}
