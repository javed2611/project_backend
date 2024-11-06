package com.jk.dto;

import java.util.List;

public class WatiResponse {

	private String result;
	private String phone_number;
	private List<WatiParameters> parameters;
	private boolean validWhatsAppNumber;
	private String name;
	private String orderNumber;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public List<WatiParameters> getParameters() {
		return parameters;
	}

	public void setParameters(List<WatiParameters> parameters) {
		this.parameters = parameters;
	}

	public boolean isValidWhatsAppNumber() {
		return validWhatsAppNumber;
	}

	public void setValidWhatsAppNumber(boolean validWhatsAppNumber) {
		this.validWhatsAppNumber = validWhatsAppNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

}
