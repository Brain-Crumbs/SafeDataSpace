package com.sds;

public class Contract {
	
	private String name;
	private int contractID;
	private long amtUsed;
	
	public Contract() {
		this.name = null;
		this.contractID = -1;
		this.amtUsed = -1;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setID(int contractID) {
		this.contractID = contractID;
	}
	public void setAmtUsed(long used) {
		this.amtUsed = used;
	}
	
	public String getName() {
		return this.name;
	}
	public int getID() {
		return this.contractID;
	}
	public long getAmtUsed() {
		return this.amtUsed;
	}

}
