package com.suren.util;

import org.libvirt.Connect;
import org.libvirt.LibvirtException;

/**
 * @author suren
 *         Dec 30, 2011 -- 9:46:24 AM
 *         Virtual
 *         com.suren.util
 *         ConnectUtil.java
 *         TODO 建立连接
 */
public class ConnectUtil
{

	private static Connect con;

	public static Connect open(String uri) throws LibvirtException
	{
		con = con == null ? new Connect(uri) : con;

		return con;
	}

}
