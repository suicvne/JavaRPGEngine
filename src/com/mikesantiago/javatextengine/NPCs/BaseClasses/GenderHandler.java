package com.mikesantiago.javatextengine.NPCs.BaseClasses;

public final class GenderHandler
{
	
	public static Gender ParseGender(String toParse)
	{
		if(toParse.indexOf("Male") != -1)
			return Gender.Male;
		else if(toParse.indexOf("Female") != -1)
			return Gender.Female;
		else 
			return Gender.Unknown;
	}
	
	public static String GetGenderPronoun(Gender gen)
    {
        switch(gen)
        {
            case Male:
                return "he";
            case Female:
                return "she";
            case Unknown:
                return "it";
        }
        return null;
    }
    public static String GetPosessivePronoun(Gender gen)
    {
        switch (gen)
        {
            case Male:
                return "his";
            case Female:
                return "her";
            case Unknown:
                return "their";
        }
        return null;
    }
	
	public enum Gender
	{
		Male, Female, Unknown
	}
}
