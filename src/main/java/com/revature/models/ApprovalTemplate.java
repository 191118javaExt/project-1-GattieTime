package com.revature.models;

public class ApprovalTemplate {
	
	private int reimId;
	private int approval;
	private int approver;
	public int getReimId() {
		return reimId;
	}
	public void setReimId(int reimId) {
		this.reimId = reimId;
	}
	public int getApproval() {
		return approval;
	}
	public void setApproval(int approval) {
		this.approval = approval;
	}
	public int getApprover() {
		return approver;
	}
	public void setApprover(int approver) {
		this.approver = approver;
	}
	public ApprovalTemplate(int reimId, int approval, int approver) {
		super();
		this.reimId = reimId;
		this.approval = approval;
		this.approver = approver;
	}
	public ApprovalTemplate() {
		super();
	}
	
	

}
