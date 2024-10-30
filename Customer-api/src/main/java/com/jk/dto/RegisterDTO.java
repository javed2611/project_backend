package com.jk.dto;

public class RegisterDTO {
	private String name;
	private String email;
	private String pwd;
	private String phno;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	@Override
	public String toString() {
		return "RegisterDTO [name=" + name + ", email=" + email + ", pwd=" + pwd + ", phno=" + phno + "]";
	}
	
}