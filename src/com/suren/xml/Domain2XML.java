/**
 * 
 */
package com.suren.xml;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.suren.model.Domains;

/**
 * @author suren
 * Jan 4, 2012 -- 3:59:17 PM
 * Virtual
 * com.suren.xml
 * Domain2XML.java
 * TODO
 */
public class Domain2XML
{
	public static String cover(Domains domains)
	{
		Document document = DocumentHelper.createDocument();
		
		Element domain = document.addElement("domain");
		domain.addAttribute("type", "qemu");
		
		Element name = domain.addElement("name");
		name.addText(domains.getName());
		
		Element currentMemory = domain.addElement("memory");
		currentMemory.addText(domains.getCurMem() + "");
		
		Element vcpu = domain.addElement("vcpu");
		vcpu.addText(domains.getCpus() + "");
		
		Element os = domain.addElement("os");
		os.addElement("type").addAttribute("arch", "i686").addText("hvm");
		
		Element devices = domain.addElement("devices");
		
		Element disk = devices.addElement("disk");
		disk.addAttribute("type", "file").addAttribute("device", "cdrom");
		
		disk.addElement("source").addAttribute("file", "/home/suren/Try/TinyCore-4.2.iso");
		disk.addElement("target").addAttribute("dev", "hdc");
		
		return document.asXML(); 
	}

}
