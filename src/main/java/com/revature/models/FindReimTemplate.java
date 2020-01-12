package com.revature.models;

import java.io.Serializable;

public class FindReimTemplate implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FindReimTemplate [status=" + status + "]";
	}

	public FindReimTemplate() {
		super();
	}

	public FindReimTemplate(int status) {
		super();
		this.status = status;
	} 
	
	
	

}
