package com.tuodao.bp.api.facade.domain;

import java.io.Serializable;

import com.google.common.base.MoreObjects;

public class ResultInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 用户名 */
	private String userName;

	/** 身份证号 */
	private String idCard;

	/** 账号 */
	private String accout;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getAccout() {
		return accout;
	}

	public void setAccout(String accout) {
		this.accout = accout;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				.add("userName", userName)
				.add("accout", accout)
				.add("idCard", idCard).toString();
	}

}
