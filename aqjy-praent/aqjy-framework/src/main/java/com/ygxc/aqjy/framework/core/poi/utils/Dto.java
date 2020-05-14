package com.ygxc.aqjy.framework.core.poi.utils;

public class Dto {

	private String name;
	
	private String no;

	private String aac;

	private String msr;

	private String acr;

	private String tech;

	private String p90;

	private String m200;

	private String smg;

	private String mk;

	public String getAac() {
		return aac;
	}

	public void setAac(String aac) {
		this.aac = aac;
	}

	public String getMsr() {
		return msr;
	}

	public void setMsr(String msr) {
		this.msr = msr;
	}

	public String getAcr() {
		return acr;
	}

	public void setAcr(String acr) {
		this.acr = acr;
	}

	public String getTech() {
		return tech;
	}

	public void setTech(String tech) {
		this.tech = tech;
	}

	public String getP90() {
		return p90;
	}

	public void setP90(String p90) {
		this.p90 = p90;
	}

	public String getM200() {
		return m200;
	}

	public void setM200(String m200) {
		this.m200 = m200;
	}

	public String getSmg() {
		return smg;
	}

	public void setSmg(String smg) {
		this.smg = smg;
	}

	public String getMk() {
		return mk;
	}

	public void setMk(String mk) {
		this.mk = mk;
	}

	public Dto(String name, String no) {
		super();
		this.name = name;
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
}
