package com.suren.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.suren.model.Node;

/**
 * @author suren
 *         Dec 30, 2011 -- 9:45:03 AM
 *         Virtual
 *         com.suren.action
 *         ConnectAction.java
 *         TODO 处理有关连接的业务逻辑
 */
public class ConnectAction extends BaseAction
{
	private static final String	CONFIG	= "/home/suren/conf.ini";

	private List<Node>			connects;
	private byte				state;
	private String				id;
	private String				connect;
	private boolean				auto;
	private boolean				success;

	/**
	 * Dec 30, 2011
	 * 11:19:54 AM
	 * TODO 列出已经存在的connect列表
	 * state:
	 * 0代表读取配置文件成功
	 * -1代表读取出错
	 * 
	 * @return
	 */
	public String connectList()
	{
		connects = new ArrayList<Node>();

		Properties pro = new Properties();

		try
		{
			pro.load(new FileInputStream(new File(CONFIG)));

			Iterator<Object> iterator = pro.keySet().iterator();

			while (iterator.hasNext())
			{
				String id = iterator.next().toString();

				connects.add(new Node(id, pro.getProperty(id), true));
			}
		} catch (IOException e)
		{
			state = -1;
			e.printStackTrace();
		}

		success = true;
		state = 0;

		return "json";
	}

	/**
	 * Dec 30, 2011
	 * 11:24:03 AM
	 * TODO 新增、编辑、删除连接
	 * 
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String edit() throws IOException, URISyntaxException
	{
		Properties pro = new Properties();

		pro.load(new FileInputStream(new File(CONFIG)));

		if (id == null || "".equals(id))
		{
			id = System.nanoTime() + "";
		}
		
		if(connect == null){
			pro.remove(id);
		}else{
			pro.setProperty(id, connect);
		}

		File file = new File(CONFIG);

		OutputStream out = new FileOutputStream(file);

		pro.store(out, null);

		success = true;

		return "json";
	}

	/**
	 * @return the connects
	 */
	public List<Node> getConnects()
	{
		return connects;
	}

	/**
	 * @return the state
	 */
	public byte getState()
	{
		return state;
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
	 * @param connect
	 *            the connect to set
	 */
	public void setConnect(String connect)
	{
		this.connect = connect;
	}

	/**
	 * @param auto
	 *            the auto to set
	 */
	public void setAuto(boolean auto)
	{
		this.auto = auto;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess()
	{
		return success;
	}
}
