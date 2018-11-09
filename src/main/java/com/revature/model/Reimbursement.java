package com.revature.model;

import java.io.Serializable;

public class Reimbursement implements Serializable {

	private static final long serialVersionUID = -7487666103861624681L;
	private int reimbID; 		// as rt
	private int emplID;			// as 
	private String reimbType;	// as r_type
	private double reimbCost;	// as r_cost
	private String reimbStatus;	// as r_status
	private int apprMngr;		
	private String mngrFname;	// as m_firstname
	private String mngrLname;	// as m_lastname
	private int empWorkID;		// as e_job_id
	private String empFname;	// as e_firstname
	private String empLname;	// as e_lastname
	private String emplEmail;	// as e_email
	
//	new Employee(
//			rs.getInt("e_job_id"),
//			rs.getString("e_firstname"),
//			rs.getString("e_lastname"),
//			rs.getString("e_email")).toStringJoin()
//	+ new Reimbursement(
//			rs.getInt("rt"),
//			rs.getString("r_type"),
//			rs.getDouble("r_cost"),
//			rs.getString("r_status")).toStringJoin()
//	+ new Manager(
//			rs.getString("m_firstname"),
//			rs.getString("m_lastname")).toStringJoin() 
	
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reimbursement(int empWorkID, String empFname, String empLname, String emplEmail, 
			int reimbID, String reimbType, double reimbCost, String reimbStatus, String mngrFname, String mngrLname) {
		super();
		this.reimbID = reimbID;
		this.reimbType = reimbType;
		this.reimbCost = reimbCost;
		this.reimbStatus = reimbStatus;
		this.mngrFname = mngrFname;
		this.mngrLname = mngrLname;
		this.empWorkID = empWorkID;
		this.empFname = empFname;
		this.empLname = empLname;
		this.emplEmail = emplEmail;
	}
	public Reimbursement(int reimbID, int emplID, String reimbType, double reimbCost, String reimbStatus, int apprMngr,
			String mngrFname, String mngrLname, int empWorkID, String empFname, String empLname, String emplEmail) {
			super();
			this.reimbID = reimbID;
			this.emplID = emplID;
			this.reimbType = reimbType;
			this.reimbCost = reimbCost;
			this.reimbStatus = reimbStatus;
			this.apprMngr = apprMngr;
			this.mngrFname = mngrFname;
			this.mngrLname = mngrLname;
			this.empWorkID = empWorkID;
			this.empFname = empFname;
			this.empLname = empLname;
			this.emplEmail = emplEmail;
		}
	public Reimbursement(int reimbID, int emplID, String reimbType, double reimbCost, String reimbStatus, int apprMngr) {
		super();
		this.reimbID = reimbID;
		this.emplID = emplID;
		this.reimbType = reimbType;
		this.reimbCost = reimbCost;
		this.reimbStatus = reimbStatus;
		this.apprMngr = apprMngr;
	}
	//for sql stored procedure
	public Reimbursement(int emplID, String reimbType, double reimbCost, String reimbStatus, int apprMngr) {
		super();
		this.emplID = emplID;
		this.reimbType = reimbType;
		this.reimbCost = reimbCost;
		this.reimbStatus = reimbStatus;
		this.apprMngr = apprMngr;
	}
	public Reimbursement(int reimblID, String reimbType, double reimbCost, String reimbStatus) {
		super();
		this.reimbID = reimblID;
		this.reimbType = reimbType;
		this.reimbCost = reimbCost;
		this.reimbStatus = reimbStatus;
	}
	public Reimbursement(int emplID, String reimbType, double reimbCost) {
		super();
		this.emplID = emplID;
		this.reimbType = reimbType;
		this.reimbCost = reimbCost;
	}
	public Reimbursement(String reimbType, double reimbCost) {
		super();
		this.reimbType = reimbType;
		this.reimbCost = reimbCost;
	}
	//
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
	public double getReimbCost() {
		return reimbCost;
	}
	public void setReimbCost(double reimbCost) {
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
	public String getMngrFname() {
		return mngrFname;
	}
	public void setMngrFname(String mngrFname) {
		this.mngrFname = mngrFname;
	}
	public String getMngrLname() {
		return mngrLname;
	}
	public void setMngrLname(String mngrLname) {
		this.mngrLname = mngrLname;
	}
	public int getEmpWorkID() {
		return empWorkID;
	}
	public void setEmpWorkID(int empWorkID) {
		this.empWorkID = empWorkID;
	}
	public String getEmpFname() {
		return empFname;
	}
	public void setEmpFname(String empFname) {
		this.empFname = empFname;
	}
	public String getEmpLname() {
		return empLname;
	}
	public void setEmpLname(String empLname) {
		this.empLname = empLname;
	}
	public String getEmplEmail() {
		return emplEmail;
	}
	public void setEmplEmail(String emplEmail) {
		this.emplEmail = emplEmail;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + apprMngr;
		result = prime * result + ((empFname == null) ? 0 : empFname.hashCode());
		result = prime * result + ((empLname == null) ? 0 : empLname.hashCode());
		result = prime * result + empWorkID;
		result = prime * result + ((emplEmail == null) ? 0 : emplEmail.hashCode());
		result = prime * result + emplID;
		result = prime * result + ((mngrFname == null) ? 0 : mngrFname.hashCode());
		result = prime * result + ((mngrLname == null) ? 0 : mngrLname.hashCode());
		long temp;
		temp = Double.doubleToLongBits(reimbCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (empFname == null) {
			if (other.empFname != null)
				return false;
		} else if (!empFname.equals(other.empFname))
			return false;
		if (empLname == null) {
			if (other.empLname != null)
				return false;
		} else if (!empLname.equals(other.empLname))
			return false;
		if (empWorkID != other.empWorkID)
			return false;
		if (emplEmail == null) {
			if (other.emplEmail != null)
				return false;
		} else if (!emplEmail.equals(other.emplEmail))
			return false;
		if (emplID != other.emplID)
			return false;
		if (mngrFname == null) {
			if (other.mngrFname != null)
				return false;
		} else if (!mngrFname.equals(other.mngrFname))
			return false;
		if (mngrLname == null) {
			if (other.mngrLname != null)
				return false;
		} else if (!mngrLname.equals(other.mngrLname))
			return false;
		if (Double.doubleToLongBits(reimbCost) != Double.doubleToLongBits(other.reimbCost))
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
		return "Reimbursement Info: ID = " + reimbID + ", Employee ID = " + emplID + ", Type = " + reimbType + ", Amount = $"
				+ reimbCost + ", Status = " + reimbStatus + ", Approving Manager = " + apprMngr + "\n";
	}
	public String toStringJoin() {
		return "Reimbursement ID: " + reimbID + ", Type: " + reimbType + ", Amount: $"
				+ reimbCost + ", Status: " + reimbStatus + " ";
	}
}
