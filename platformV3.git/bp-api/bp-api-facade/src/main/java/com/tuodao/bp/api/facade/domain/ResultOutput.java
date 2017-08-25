package com.tuodao.bp.api.facade.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ResultOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 用户名 */
	private String userName;

	/** 身份证号 */
	private String idCard;

	/** 昵称 */
	private String nickName;

	/** 账号 */
	private String accout;

	/** 金钱 */
	private BigDecimal money;

	/** 交易时间 */
	private Date payTime;

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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAccout() {
		return accout;
	}

	public void setAccout(String accout) {
		this.accout = accout;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

}
