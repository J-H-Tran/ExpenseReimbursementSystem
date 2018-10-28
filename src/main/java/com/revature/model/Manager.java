package com.revature.model;

import java.io.Serializable;

public class Manager implements Serializable{

	private static final long serialVersionUID = 5363426635806780769L;
	private String apprMngr;
	private int mngrID;
	private String jobDescr;
	private String firName;
	private String lasName;
	private String emailAddr;
	private String passWord;
	
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Manager(String apprMngr, int mngrID, String jobDescr, String firName, String lasName, String emailAddr,
			String passWord) {
		super();
		this.apprMngr = apprMngr;
		this.mngrID = mngrID;
		this.jobDescr = jobDescr;
		this.firName = firName;
		this.lasName = lasName;
		this.emailAddr = emailAddr;
		this.passWord = passWord;
	}
	public String getApprMngr() {
		return apprMngr;
	}
	public void setApprMngr(String apprMngr) {
		this.apprMngr = apprMngr;
	}
	public int getMngrID() {
		return mngrID;
	}
	public void setMngrID(int mngrID) {
		this.mngrID = mngrID;
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
		result = prime * result + ((apprMngr == null) ? 0 : apprMngr.hashCode());
		result = prime * result + ((emailAddr == null) ? 0 : emailAddr.hashCode());
		result = prime * result + ((firName == null) ? 0 : firName.hashCode());
		result = prime * result + ((jobDescr == null) ? 0 : jobDescr.hashCode());
		result = prime * result + ((lasName == null) ? 0 : lasName.hashCode());
		result = prime * result + mngrID;
		result = prime * result + ((passWord == null) ? 0 : passWord.hashCode());
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
		Manager other = (Manager) obj;
		if (apprMngr == null) {
			if (other.apprMngr != null)
				return false;
		} else if (!apprMngr.equals(other.apprMngr))
			return false;
		if (emailAddr == null) {
			if (other.emailAddr != null)
				return false;
		} else if (!emailAddr.equals(other.emailAddr))
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
		if (lasName == null) {
			if (other.lasName != null)
				return false;
		} else if (!lasName.equals(other.lasName))
			return false;
		if (mngrID != other.mngrID)
			return false;
		if (passWord == null) {
			if (other.passWord != null)
				return false;
		} else if (!passWord.equals(other.passWord))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Manager [apprMngr=" + apprMngr + ", mngrID=" + mngrID + ", jobDescr=" + jobDescr + ", firName="
				+ firName + ", lasName=" + lasName + ", emailAddr=" + emailAddr + ", passWord=" + passWord + "]";
	}
}
