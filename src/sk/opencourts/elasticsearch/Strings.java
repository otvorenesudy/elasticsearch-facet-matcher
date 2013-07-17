package sk.opencourts.elasticsearch;

import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;

public class Strings
{
	private Strings()
	{
		throw new AssertionError();
	}
	
	public static final String unaccent(final String s)
	{
		int    size   = s.length();
		char[] result = new char[size];

		ASCIIFoldingFilter.foldToASCII(s.toCharArray(), 0, result, 0, size);

		return new String(result);
	}

	public static final String[] tokenize(final String s)
	{
		return s.split("\\s+");
	}
}
