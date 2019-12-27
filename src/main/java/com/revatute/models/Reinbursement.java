package com.revatute.models;

import java.io.InputStream;
import java.sql.Timestamp;



public class Reinbursement {
	private int id;
	private double amount;
	private Timestamp submit;
	private Timestamp resolved;
	private String descript;
	private InputStream receipt;
	private int author;
	private String authorName;
	private int resolver;
	private String resolverName;
	private int status;
	private String statusName;
	private int type;
	private String typeName;
	
	public Reinbursement(int id, double amount, Timestamp submit, Timestamp resolved, String descript,
			InputStream receipt, int author, int status, int type) {
		super();
		this.id = id;
		this.amount = amount;
		this.submit = submit;
		this.resolved = resolved;
		this.descript = descript;
		this.receipt = receipt;
		this.author = author;
		this.status = status;
		this.type = type;
	}

	public Reinbursement(int id, double amount, Timestamp submit, Timestamp resolved, String descript,
			InputStream receipt, int author, int resolver, int status, int type) {
		super();
		this.id = id;
		this.amount = amount;
		this.submit = submit;
		this.resolved = resolved;
		this.descript = descript;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}
	
	

	public Reinbursement(double amount, Timestamp submit, String descript, InputStream receipt, int author,
			String authorName, int status, String statusName, int type, String typeName) {
		super();
		this.amount = amount;
		this.submit = submit;
		this.descript = descript;
		this.receipt = receipt;
		this.author = author;
		this.authorName = authorName;
		this.status = status;
		this.statusName = statusName;
		this.type = type;
		this.typeName = typeName;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getSubmit() {
		return submit;
	}

	public void setSubmit(Timestamp submit) {
		this.submit = submit;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public InputStream getReceipt() {
		return receipt;
	}

	public void setReceipt(InputStream receipt) {
		this.receipt = receipt;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public String getResolverName() {
		return resolverName;
	}

	public void setResolverName(String resolverName) {
		this.resolverName = resolverName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "Reinburshment [id=" + id + ", amount=" + amount + ", submit=" + submit + ", resolved=" + resolved
				+ ", descript=" + descript + ", receipt=" + receipt + ", authorName=" + authorName + ", resolverName="
				+ resolverName + ", statusName=" + statusName + ", typeName=" + typeName + "]";
	}
	
	
	

}
