package com.suren.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.libvirt.Connect;
import org.libvirt.Domain;
import org.libvirt.DomainInfo.DomainState;
import org.libvirt.LibvirtException;

import com.suren.model.Domains;
import com.suren.model.Host;
import com.suren.util.ConnectUtil;

/**
 * @author suren
 *         Dec 30, 2011 -- 9:44:34 AM
 *         Virtual
 *         com.suren.action
 *         VirtAction.java
 *         TODO Virt Core Action
 */
public class VirtAction extends BaseAction
{
	public static Map<DomainState, String>	STATE;

	static
	{
		STATE = new HashMap<DomainState, String>();

		STATE.put(DomainState.VIR_DOMAIN_BLOCKED, "VIR_DOMAIN_BLOCKED");
		STATE.put(DomainState.VIR_DOMAIN_CRASHED, "VIR_DOMAIN_CRASHED");
		STATE.put(DomainState.VIR_DOMAIN_NOSTATE, "VIR_DOMAIN_NOSTATE");
		STATE.put(DomainState.VIR_DOMAIN_PAUSED, "挂起");
		STATE.put(DomainState.VIR_DOMAIN_RUNNING, "运行");
		STATE.put(DomainState.VIR_DOMAIN_SHUTDOWN, "VIR_DOMAIN_SHUTDOWN");
		STATE.put(DomainState.VIR_DOMAIN_SHUTOFF, "停止");
	}

	private String							url;
	private String							name;
	private byte							state;
	private Host							host;
	private List<Domains>					domains;
	private boolean							success;
	private String msg;

	/**
	 * Dec 31, 2011
	 * 9:45:56 AM
	 * TODO 获取某个连接(connect)关联主机的信息
	 * 
	 * @return
	 * @throws LibvirtException
	 */
	public String execute()
	{
		try
		{
			Connect con = ConnectUtil.open(url);

			host = new Host();

			host.setName(con.getHostName());
			host.setType(con.getType());
			host.setMemory(con.nodeInfo().memory);
			host.setModel(con.nodeInfo().model);
			host.setCpus(con.nodeInfo().cpus);
			host.setMhz(con.nodeInfo().mhz);
			
			success = true;
		} catch (LibvirtException e)
		{
			success = false;
			e.printStackTrace();
		}
		
		return "json";
	}

	/**
	 * Dec 31, 2011
	 * 9:45:12 AM
	 * TODO 列出已经被定义的(defined)的domain
	 * 
	 * @return
	 * @throws LibvirtException
	 */
	public String domains() throws LibvirtException
	{
		Connect connect = ConnectUtil.open(url);

		String[] doms = connect.listDefinedDomains();

		domains = new ArrayList<Domains>();

		for (String name : doms)
		{
			Domain dom = connect.domainLookupByName(name);

			domains.add(fillDomains(dom));
		}

		int[] domIDs = connect.listDomains();

		for (int id : domIDs)
		{
			Domain dom = connect.domainLookupByID(id);

			domains.add(fillDomains(dom));
		}
		
		success = true;

		return "json";
	}

	/**
	 * Dec 31, 2011
	 * 9:43:09 AM
	 * TODO 将libvirt库中的Domain对象及其相关信息封装到本地的Domains对象中
	 * 
	 * @param dom
	 * @return
	 * @throws LibvirtException
	 */
	private Domains fillDomains(Domain dom) throws LibvirtException
	{
		Domains domains = new Domains();

		domains.setName(dom.getName());
		domains.setId(dom.getID() + dom.getName());
		domains.setOsType(dom.getOSType());
		domains.setUuidString(dom.getUUIDString());
		domains.setAutoStart(dom.getAutostart());
		domains.setState(STATE.get(dom.getInfo().state));
		domains.setMaxMemory(dom.getMaxMemory());
		domains.setUrl(dom.getConnect().getURI());
		
		return domains;
	}

	/**
	 * Dec 31, 2011
	 * 9:44:17 AM
	 * TODO 对虚拟机（domain）实例的启动、暂停、关闭等操作
	 * state参数分别代表的意义如下：
	 * 1:启动
	 * 2:停止
	 * 3:重启
	 * 4:恢复
	 * 5:挂起
	 * 6:删除
	 * 
	 * @return
	 * @throws LibvirtException
	 */
	public String control() throws LibvirtException
	{
		try
		{
			Connect con = ConnectUtil.open(url);
			Domain domain = con.domainLookupByName(name);

			switch (state)
			{
				case 1:
					domain.create();
					msg = "success control for start";
					break;
				case 2:
					domain.destroy();
					msg = "success control for shutdown";
					break;
				case 3:
					domain.reboot(0);
					msg = "success control";
					break;
				case 4:
					domain.resume();
					msg = "success control for resume";
					break;
				case 5:
					domain.suspend();
					msg = "success control for pause";
					break;
				case 6:
					domain.undefine();
					msg = "success control";
					break;
			}
			
			state = 0;
		} catch (LibvirtException e)
		{
			state = -1;
			msg = e.getMessage();
			e.printStackTrace();
		}

		return "json";
	}

	public Host getHost()
	{
		return host;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
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
	 * @param state
	 *            the state to set
	 */
	public void setState(byte state)
	{
		this.state = state;
	}

	/**
	 * @return the state
	 */
	public byte getState()
	{
		return state;
	}

	/**
	 * @return the domains
	 */
	/**
	 * Jan 4, 2012
	 * 9:28:12 AM
	 * TODO
	 * 
	 * @return
	 */
	public List<Domains> getDomains()
	{
		return domains;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess()
	{
		return success;
	}

	/**
	 * @return the msg
	 */
	public String getMsg()
	{
		return msg;
	}
}
