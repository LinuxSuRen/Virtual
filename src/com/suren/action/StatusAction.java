/**
 * 
 */
package com.suren.action;

import org.libvirt.Connect;
import org.libvirt.Domain;
import org.libvirt.LibvirtException;

import com.suren.util.ConnectUtil;

/**
 * @author suren
 *         Jan 5, 2012 -- 5:03:41 PM
 *         Virtual
 *         com.suren.action
 *         StatusAction.java
 *         TODO
 */
public class StatusAction extends BaseAction
{
	private String	name;
	private String	url;
	private byte	per	= 1;
	private long	cpus;

	public String cpuInfo()
	{
		try
		{
			Connect con = ConnectUtil.open(url);

			Domain domain = con.domainLookupByName(name);

			long begin = domain.getInfo().cpuTime;

			Thread.sleep(1000 * per);

			cpus = ((domain.getInfo().cpuTime - begin) / (per * 1000 * 1000 * 10));
		} catch (LibvirtException e)
		{
			e.printStackTrace();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		return JSON;
	}

	public String memInfo()
	{
		return JSON;
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
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url)
	{
		this.url = url;
	}

	/**
	 * @return the cpus
	 */
	public long getCpus()
	{
		return cpus;
	}

	/**
	 * @param per
	 *            the per to set
	 */
	public void setPer(byte per)
	{
		this.per = per;
	}

}
