/**
 * 
 */
package com.suren.test;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.libvirt.Connect;
import org.libvirt.Domain;
import org.libvirt.DomainInfo;
import org.libvirt.LibvirtException;
import org.libvirt.Network;
import org.libvirt.StoragePool;
import org.libvirt.StoragePoolInfo;

import com.suren.util.ConnectUtil;

/**
 * @author suren
 * Jan 4, 2012 -- 2:23:29 PM
 * Virtual
 * com.suren.test
 * Test.java
 * TODO
 */
public class Test
{

	/**
	 * Jan 4, 2012
	 * 2:23:29 PM
	 * TODO
	 * @param args
	 * @throws LibvirtException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws Exception
	{
		Connect con = ConnectUtil.open("qemu:///system");
		
		Domain domain = con.domainLookupByName("suren");
		
//		System.out.println("memory : " + (domain.getInfo().memory / 1024));
//		System.out.println("maxmem : " + (domain.getInfo().maxMem / 1024));
////		System.out.println(con.getCapabilities());
////		System.out.println("freeMemory : " + (con.getFreeMemory() / 1024));
//		System.out.println("version : " + con.getVersion());
//		System.out.println("memory : " + (con.nodeInfo().memory / 1024));
//		System.out.println("mhz : " + con.nodeInfo().mhz);
//		System.out.println("model : " + con.nodeInfo().model);
//		System.out.println("cpus : " + con.nodeInfo().cpus);
		
//		System.out.println(domain.interfaceStats("lo"));
		
//		String[] inters = con.listDefinedStoragePools();
//		for(String i : inters)
//		{
//			System.out.println(i);
//		}
//		
//		inters = con.listStoragePools();
//		for(String i : inters)
//		{
//			System.out.println(i);
//		}
//		
//		StoragePool storage = con.storagePoolLookupByName("try");
//		
//		inters = storage.listVolumes();
//		for(String i : inters)
//		{
//			System.out.println(i);
//		}
		
		StoragePool storage = con.storagePoolLookupByName("try");
		
		StoragePoolInfo info = storage.getInfo();
		
		System.out.println("allocation : " + (info.allocation / 1024 / 1024 / 1024));
		System.out.println("available : " + (info.available / 1024 / 1024 / 1024));
		System.out.println("capacity : " + (info.capacity / 1024 / 1024 / 1024));
		
//		while(true)
//		{
//			long cpu = domain.getInfo().cpuTime;
//			
////			System.out.println(cpu);
//			
//			Thread.sleep(2000);
//			
//			double result = ((domain.getInfo().cpuTime - cpu) / (2000 * 1000 * 10));
//			
//			System.out.println(result);
//		}
//		
//		while(true)
//		{
//			System.out.println(System.nanoTime());
//			
//			Thread.sleep(1000);
//		}
	}

}

