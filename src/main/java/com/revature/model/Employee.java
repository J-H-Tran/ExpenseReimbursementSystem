package com.revature.model;

import java.io.Serializable;

public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1299135238534624823L;
	private int emplID;
	private int jobID;
	private String jobDescr;
	private String firName;
	private String lasName;
	private String emailAddr;
	private String usrName;
	private String passWord;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(int emplID, int jobID, String jobDescr, String firName, String lasName, String emailAddr,
			String usrName, String passWord) {
		super();
		this.emplID = emplID;
		this.jobID = jobID;
		this.jobDescr = jobDescr;
		this.firName = firName;
		this.lasName = lasName;
		this.emailAddr = emailAddr;
		this.usrName = usrName;
		this.passWord = passWord;
	}
	public Employee(int jobID, String jobDescr, String firName, String lasName, String emailAddr,
			String usrName, String passWord) {
		super();
		this.jobID = jobID;
		this.jobDescr = jobDescr;
		this.firName = firName;
		this.lasName = lasName;
		this.emailAddr = emailAddr;
		this.usrName = usrName;
		this.passWord = passWord;
	}
	public int getEmplID() {
		return emplID;
	}
	public void setEmplID(int emplID) {
		this.emplID = emplID;
	}
	public int getJobID() {
		return jobID;
	}
	public void setJobID(int jobID) {
		this.jobID = jobID;
	}
	public String getJobDescr() {
		return jobDescr;
	}
	public void setJobDescr(String jobDescr) {
		this.jobDescr = jobDescr;
	}
	public String getFirName() {
		return firName;
	}
	public void setFirName(String firName) {
		this.firName = firName;
	}
	public String getLasName() {
		return lasName;
	}
	public void setLasName(String lasName) {
		this.lasName = lasName;
	}
	public String getEmailAddr() {
		return emailAddr;
	}
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailAddr == null) ? 0 : emailAddr.hashCode());
		result = prime * result + emplID;
		result = prime * result + ((firName == null) ? 0 : firName.hashCode());
		result = prime * result + ((jobDescr == null) ? 0 : jobDescr.hashCode());
		result = prime * result + jobID;
		result = prime * result + ((lasName == null) ? 0 : lasName.hashCode());
		result = prime * result + ((passWord == null) ? 0 : passWord.hashCode());
		result = prime * result + ((usrName == null) ? 0 : usrName.hashCode());
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
		Employee other = (Employee) obj;
		if (emailAddr == null) {
			if (other.emailAddr != null)
				return false;
		} else if (!emailAddr.equals(other.emailAddr))
			return false;
		if (emplID != other.emplID)
			return false;
		if (firName == null) {
			if (other.firName != null)
				return false;
		} else if (!firName.equals(other.firName))
			return false;
		if (jobDescr == null) {
			if (other.jobDescr != null)
				return false;
		} else if (!jobDescr.equals(other.jobDescr))
			return false;
		if (jobID != other.jobID)
			return false;
		if (lasName == null) {
			if (other.lasName != null)
				return false;
		} else if (!lasName.equals(other.lasName))
			return false;
		if (passWord == null) {
			if (other.passWord != null)
				return false;
		} else if (!passWord.equals(other.passWord))
			return false;
		if (usrName == null) {
			if (other.usrName != null)
				return false;
		} else if (!usrName.equals(other.usrName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ID: " + emplID + ", Work ID: " + jobID + ", Job Title: " + jobDescr 
				+ ", Firstname: " + firName + ", Lastname: " + lasName + ", Email: " 
				+ emailAddr + ", Username: " + usrName + ", Password: " + passWord;
	}
	
}
