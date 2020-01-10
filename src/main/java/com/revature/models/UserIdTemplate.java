package com.revature.models;

import java.io.Serializable;

public class UserIdTemplate implements Serializable{
	private static final long serialVersionUID = 6101492362637545408L;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserIdTemplate() {
		super();
	}

	public UserIdTemplate(int id) {
		super();
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof UserIdTemplate))
			return false;
		UserIdTemplate other = (UserIdTemplate) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserIdTemplate [id=" + id + "]";
	}
	
	
}
