package com.example.speech;

public class Function {
	String name;
	String sstatic;
	String privacy;
	String returnType;
	Variable [] parametrs;
	
	/**
	 * constructor for class function 
	 * @param nm
	 * @param st
	 * @param pr
	 * @param rT
	 * @param p
	 */
	public Function(String nm, String st, String pr, String rT, Variable[] p) {
		name = nm;
		sstatic = (st.length() != 0) ? st: "";
		privacy = (pr.length() != 0) ? pr : "";
		returnType =(rT.length() != 0) ? rT : "";
		parametrs = (p.length != 0) ? p : new Variable[0];
	}
	
	
}
