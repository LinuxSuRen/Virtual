package com.suren.model;

public class Host
{

	private String	name;
	private String	type;
	private long	libVer;
	private int		cpus;
	private long	memory;
	private String	model;
	private int mhz;

	/**
	 * get the host of name
	 * 
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public long getLibVer()
	{
		return libVer;
	}

	public void setLibVer(long libVer)
	{
		this.libVer = libVer;
	}

	public int getCpus()
	{
		return cpus;
	}

	public void setCpus(int cpus)
	{
		this.cpus = cpus;
	}

	public long getMemory()
	{
		return memory;
	}

	public void setMemory(long memory)
	{
		this.memory = memory;
	}

	/**
	 * string indicating the CPU model
	 * 
	 * @return
	 */
	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}

	/**
	 * @return the mhz
	 */
	public int getMhz()
	{
		return mhz;
	}

	/**
	 * @param mhz the mhz to set
	 */
	public void setMhz(int mhz)
	{
		this.mhz = mhz;
	}

}
