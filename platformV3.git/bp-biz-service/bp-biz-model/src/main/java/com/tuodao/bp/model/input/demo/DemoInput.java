package com.tuodao.bp.model.input.demo;

import java.io.Serializable;

import com.google.common.base.MoreObjects;

public class DemoInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("name", name).add("address", address).toString();
	}
}
