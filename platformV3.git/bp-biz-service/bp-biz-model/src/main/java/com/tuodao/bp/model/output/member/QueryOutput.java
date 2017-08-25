package com.tuodao.bp.model.output.member;

import java.io.Serializable;

import com.google.common.base.MoreObjects;

/**
 * 查询输出
 * 
 * @author hechuan
 *
 * @created 2017年5月31日
 *
 * @version 1.0.0
 */
public class QueryOutput implements Serializable {

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

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				.add("userName", userName)
				.add("idCard", idCard)
				.add("nickName", nickName)
				.toString();
	}
}
