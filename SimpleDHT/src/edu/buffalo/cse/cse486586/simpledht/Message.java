package edu.buffalo.cse.cse486586.simpledht;




import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	int value;
	String data;
	String phone;
	int type;
	String predec;
	String success;
	String leastp;
	String highs;
//	ArrayList <Object> emubuf1 = new ArrayList<Object>();
//	ArrayList <Object> emubuf2 = new ArrayList<Object>();
//	ArrayList <Object> emubuf3 = new ArrayList<Object>();
//	ArrayList <Object> emubuf4 = new ArrayList<Object>();
//	ArrayList <Object> emubuf5 = new ArrayList<Object>();
	public Message(int v,String d,String pho,int t,String pr,String su,String le,String hi)
	{
		this.value = v;
		this.data = d;
		this.phone = pho;
		this.type = t;
		this.predec = pr;
		this.success = su;
		this.leastp = le;
		this.highs = hi;
	}

}