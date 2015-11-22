package cs320Starter.model;

import java.util.ArrayList;

public class Pledges 
{
	String fname;

	String lname;
	String title;
	int amount;
	String rewDes;
	ArrayList<ProjList> ownProjs = new ArrayList<ProjList>();

public ArrayList<ProjList> getOwnProjs() {
		return ownProjs;
	}

	public void setOwnProjs(ArrayList<ProjList> ownProjs) {
		this.ownProjs = ownProjs;
	}

public Pledges(String fn, String ln, String tit, int am, String rew)
{
	fname = fn;
	lname = ln;
	title = tit;
	amount = am;
	rewDes = rew;
}

public String getFname() {
	return fname;
}

public void setFname(String fname) {
	this.fname = fname;
}

public String getLname() {
	return lname;
}

public void setLname(String lname) {
	this.lname = lname;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public int getAmount() {
	return amount;
}

public void setAmount(int amount) {
	this.amount = amount;
}

public String getRewDes() {
	return rewDes;
}

public void setRewDes(String rewDes) {
	this.rewDes = rewDes;
}

}