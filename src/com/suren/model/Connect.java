/**
 * 
 */
package com.suren.model;

/**
 * @author suren
 *         Dec 30, 2011 -- 11:22:14 AM
 *         Virtual
 *         com.suren.model
 *         Connect.java
 *         TODO 连接对象
 */
public class Connect
{
	private String name;
	private String url;
	private String type;

	/**
	 * 
	 */
	public Connect()
	{
		super();
	}

	/**
	 * @param name
	 * @param url
	 */
	public Connect(String name, String url)
	{
		super();
		this.name = name;
		this.url = url;
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

	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}

}
