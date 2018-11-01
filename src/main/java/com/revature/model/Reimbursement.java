package com.revature.model;

import java.io.Serializable;

public class Reimbursement implements Serializable {

	private static final long serialVersionUID = -7487666103861624681L;
	private int reimbID;
	private int emplID;
	private String reimbType;
	private int reimbCost;
	private String reimbStatus;
	private int apprMngr;
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reimbursement(int reimbID, int emplID, String reimbType, int reimbCost, String reimbStatus, int apprMngr) {
		super();
		this.reimbID = reimbID;
		this.emplID = emplID;
		this.reimbType = reimbType;
		this.reimbCost = reimbCost;
		this.reimbStatus = reimbStatus;
		this.apprMngr = apprMngr;
	}
	//for sql stored procedure
	public Reimbursement(int emplID, String reimbType, int reimbCost, String reimbStatus, int apprMngr) {
		super();
		this.emplID = emplID;
		this.reimbType = reimbType;
		this.reimbCost = reimbCost;
		this.reimbStatus = reimbStatus;
		this.apprMngr = apprMngr;
	}
	public int getReimbID() {
		return reimbID;
	}
	public void setReimbID(int reimbID) {
		this.reimbID = reimbID;
	}
	public int getEmplID() {
		return emplID;
	}
	public void setEmplID(int emplID) {
		this.emplID = emplID;
	}
	public String getReimbType() {
		return reimbType;
	}
	public void setReimbType(String reimbType) {
		this.reimbType = reimbType;
	}
	public int getReimbCost() {
		return reimbCost;
	}
	public void setReimbCost(int reimbCost) {
		this.reimbCost = reimbCost;
	}
	public String getReimbStatus() {
		return reimbStatus;
	}
	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}
	public int getApprMngr() {
		return apprMngr;
	}
	public void setApprMngr(int apprMngr) {
		this.apprMngr = apprMngr;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + apprMngr;
		result = prime * result + emplID;
		result = prime * result + reimbCost;
		result = prime * result + reimbID;
		result = prime * result + ((reimbStatus == null) ? 0 : reimbStatus.hashCode());
		result = prime * result + ((reimbType == null) ? 0 : reimbType.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (apprMngr != other.apprMngr)
			return false;
		if (emplID != other.emplID)
			return false;
		if (reimbCost != other.reimbCost)
			return false;
		if (reimbID != other.reimbID)
			return false;
		if (reimbStatus == null) {
			if (other.reimbStatus != null)
				return false;
		} else if (!reimbStatus.equals(other.reimbStatus))
			return false;
		if (reimbType == null) {
			if (other.reimbType != null)
				return false;
		} else if (!reimbType.equals(other.reimbType))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reimbursement [reimbID=" + reimbID + ", emplID=" + emplID + ", reimbType=" + reimbType + ", reimbCost="
				+ reimbCost + ", reimbStatus=" + reimbStatus + ", apprMngr=" + apprMngr + "]";
	}
	
}
