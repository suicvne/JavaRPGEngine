package com.mikesantiago.javarpgengine.core;

public final class OSDetection
{
	public static OSType GetCurrentOSName()
	{
		String osName = System.getProperty("os.name").toLowerCase();
		
		if(osName.indexOf("win") >= 0)
		{
			return OSType.windows;
		}
		else if(osName.indexOf("linux") >= 0)
		{
			return OSType.linux;
		}
		else if(osName.indexOf("mac") >= 0)
		{
			return OSType.macosx;
		}
		
		return OSType.other;
		//return OSType.windows;
	}
	
	public enum OSType
	{
		windows, macosx, linux, solaris, other
	}
}