/**
 * 
 */
package com.suren.model;

/**
 * @author suren
 *         Dec 30, 2011 -- 4:52:59 PM
 *         Virtual
 *         com.suren.model
 *         Domains.java
 *         TODO
 */
public class Domains
{
	private String	id;
	private String	name;
	private String	type;
	private String	isoFile;
	private boolean	autoStart;
	private String	curMem;
	private long	maxMemory;
	private String	cpus;
	private String	osType;
	private String	uuidString;
	private String	xmlDesc;
	private String	state;
	private String	url;

	/**
	 * 
	 */
	public Domains()
	{
		super();
	}

	/**
	 * @param name
	 */
	public Domains(String name)
	{
		super();
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * @return the isoFile
	 */
	public String getIsoFile()
	{
		return isoFile;
	}

	/**
	 * @param isoFile
	 *            the isoFile to set
	 */
	public void setIsoFile(String isoFile)
	{
		this.isoFile = isoFile;
	}

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the autoStart
	 */
	public boolean isAutoStart()
	{
		return autoStart;
	}

	/**
	 * @param autoStart
	 *            the autoStart to set
	 */
	public void setAutoStart(boolean autoStart)
	{
		this.autoStart = autoStart;
	}

	/**
	 * @return the maxMemory
	 */
	public long getMaxMemory()
	{
		return maxMemory;
	}

	/**
	 * @param maxMemory
	 *            the maxMemory to set
	 */
	public void setMaxMemory(long maxMemory)
	{
		this.maxMemory = maxMemory;
	}

	/**
	 * @return the curMem
	 */
	public String getCurMem()
	{
		return curMem;
	}

	/**
	 * @param curMem
	 *            the curMem to set
	 */
	public void setCurMem(String curMem)
	{
		this.curMem = curMem;
	}

	/**
	 * @return the cpus
	 */
	public String getCpus()
	{
		return cpus;
	}

	/**
	 * @param cpus
	 *            the cpus to set
	 */
	public void setCpus(String cpus)
	{
		this.cpus = cpus;
	}

	/**
	 * @return the osType
	 */
	public String getOsType()
	{
		return osType;
	}

	/**
	 * @param osType
	 *            the osType to set
	 */
	public void setOsType(String osType)
	{
		this.osType = osType;
	}

	/**
	 * @return the uuidString
	 */
	public String getUuidString()
	{
		return uuidString;
	}

	/**
	 * @param uuidString
	 *            the uuidString to set
	 */
	public void setUuidString(String uuidString)
	{
		this.uuidString = uuidString;
	}

	/**
	 * @return the xmlDesc
	 */
	public String getXmlDesc()
	{
		return xmlDesc;
	}

	/**
	 * @param xmlDesc
	 *            the xmlDesc to set
	 */
	public void setXmlDesc(String xmlDesc)
	{
		this.xmlDesc = xmlDesc;
	}

	/**
	 * @return the state
	 */
	public String getState()
	{
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state)
	{
		this.state = state;
	}

	/**
	 * @return the url
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url)
	{
		this.url = url;
	}

}
