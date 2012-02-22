/**
 * 
 */
package com.suren.action;

import org.libvirt.Connect;
import org.libvirt.LibvirtException;

import com.suren.model.Domains;
import com.suren.util.ConnectUtil;
import com.suren.xml.Domain2XML;

/**
 * @author suren
 * Jan 4, 2012 -- 3:39:21 PM
 * Virtual
 * com.suren.action
 * DomainAction.java
 * TODO
 */
public class DomainAction extends BaseAction
{
	private String msg;
	private boolean success;
	private String url;
	
	private Domains domain;
	
	public String execute()
	{
		success = false;
		
		if(domain == null){
			msg = "failure";
			return JSON;
		}
		
		String xmlDesc = Domain2XML.cover(domain);
		
		try
		{
			Connect con = ConnectUtil.open(url);
			
			con.domainDefineXML(xmlDesc);
			
			msg = "创建成功";
			success = true;
		} catch (LibvirtException e)
		{
			msg = "创建失败";
			e.printStackTrace();
		}
		
		return JSON;
	}

	/**
	 * @return the domain
	 */
	public Domains getDomain()
	{
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(Domains domain)
	{
		this.domain = domain;
	}

	/**
	 * @return the msg
	 */
	public String getMsg()
	{
		return msg;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess()
	{
		return success;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url)
	{
		this.url = url;
	}
			

}
