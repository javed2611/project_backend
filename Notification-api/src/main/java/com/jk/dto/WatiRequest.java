package com.jk.dto;

import java.util.List;

public class WatiRequest {

    private String template_name;
    private String broadcast_name;
    private List<WatiParameters> parameters;
	public String getTemplate_name() {
		return template_name;
	}
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}
	public String getBroadcast_name() {
		return broadcast_name;
	}
	public void setBroadcast_name(String broadcast_name) {
		this.broadcast_name = broadcast_name;
	}
	public List<WatiParameters> getParameters() {
		return parameters;
	}
	public void setParameters(List<WatiParameters> parameters) {
		this.parameters = parameters;
	}
    
}
