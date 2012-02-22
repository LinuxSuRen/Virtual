/**
 * 
 */
package com.suren.test;

import org.libvirt.Connect;
import org.libvirt.ConnectAuth;
import org.libvirt.ConnectAuthDefault;

/**
 * @author suren
 * Jan 6, 2012 -- 5:34:10 PM
 * Virtual
 * com.suren.test
 * RemoteConnection.java
 * TODO
 */
public class RemoteConnection
{

	/**
	 * Jan 6, 2012
	 * 5:34:10 PM
	 * TODO
	 * @param args
	 */
	public static void main(String[] args)throws Exception
	{
		ConnectAuthDefault auth = new ConnectAuthDefault();

		Connect con = new Connect("qemu+ssh://root@192.168.10.38/system", auth, 0);
		
		con.close();
	}

}
