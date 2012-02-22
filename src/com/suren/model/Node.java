/**
 * 
 */
package com.suren.model;

/**
 * @author suren
 *         Dec 30, 2011 -- 2:29:24 PM
 *         Virtual
 *         com.suren.model
 *         Node.java
 *         TODO
 */
public class Node
{
	private String	id;
	private String	text;
	private boolean	leaf;

	/**
	 * 
	 */
	public Node()
	{
		super();
	}

	/**
	 * @param id
	 * @param text
	 * @param leaf
	 */
	public Node(String id, String text, boolean leaf)
	{
		super();
		this.id = id;
		this.text = text;
		this.leaf = leaf;
	}

	/**
	 * @param text
	 * @param leaf
	 */
	public Node(String text, boolean leaf)
	{
		super();
		this.text = text;
		this.leaf = leaf;
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
	 * @return the text
	 */
	public String getText()
	{
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text)
	{
		this.text = text;
	}

	/**
	 * @return the leaf
	 */
	public boolean isLeaf()
	{
		return leaf;
	}

	/**
	 * @param leaf
	 *            the leaf to set
	 */
	public void setLeaf(boolean leaf)
	{
		this.leaf = leaf;
	}

}
