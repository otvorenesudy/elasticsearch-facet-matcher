package sk.opencourts.elasticsearch;

import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;

public class StringUtils {
	public static String unaccent(String s) {
		int size = s.length();
		char[] result = new char[size];
		
		ASCIIFoldingFilter.foldToASCII(s.toCharArray(), 0, result, 0, size);
		
		return new String(result);
	}
	
	public static String[] tokenize(String s) {
		return s.split("\\s+");
	}
}
