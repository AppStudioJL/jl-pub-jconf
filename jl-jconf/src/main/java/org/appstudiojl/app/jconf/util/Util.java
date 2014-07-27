package org.appstudiojl.app.jconf.util;

import java.util.Collection;
import java.util.List;

/**
 * <code>Util</code> is a Collection of String method including:
 * <p>1) check empty string (both null and spaces only)</p>
 * <p>2) check empty string list (both null and spaces only)</p>
 * <p>3) compare two string (allow trimming options)</p>
 * @author Jasonwaiting
 * @version	1.0.0
 * @since	2012-11-08
 */
public class Util {

	/**
	 * <code>isEmpty (String):</code> check whether the String input is null or just blank
	 * @param text	a String text
	 * @return true if empty, false otherwise
	 */
	public static boolean isEmpty(String text) {
		if ((text == null) || text.trim().equalsIgnoreCase("")) {
			return true;
		}
		return false;
	}

	/**
	 * <code>isEmpty (List<String>):</code> check whether a list of string is null or either
	 * one of the list value is blank
	 * @param textList	List<String> lines
	 * @return true if empty, false otherwise
	 */
	public static boolean isEmpty(List<String> textList) {
		if (textList == null){
			return true;
		}
		for (String s : textList) {
			if (isEmpty(s))
				return true;
		}
		return false;
	}

	/**
	 * <code>isSame (String):</code> compares two String
	 * @param text1	String needed to be compared
	 * @param text2	reference String to be compared
	 * @param trimming	allows trimming of Strings before comparison
	 * @return true if two Strings are the same, false otherwise
	 */
	public static boolean isSame (String text1, String text2, boolean trimming){
		if ((text1 == null) && (text2 == null)) return false;
		if ((text1 == null) || (text2 == null)) return false;
		if (trimming){
			return ((text1.trim()).equals(text2.trim()));
		} else {
			return (text1.equals(text2));
		}
	}

	/**
	 * <code>isSame (String):</code> compares two String
	 * @param text1	String needed to be compared
	 * @param text2	reference String to be compared
	 * @return true if two Strings are the same, false otherwise
	 */
	public static boolean isSame (String text1, String text2){
		return Util.isSame(text1, text2, false);
	}

	/**
	 * <code>isEmpty (Collection):</code> checks whether the collection is empty (either null or size 0)
	 * @param objectCollection	collections needed checking
	 * @return true if the collection has at least 1 element, false otherwise
	 */
	public static boolean isEmpty (Collection<Object> objectCollection){
		if ((objectCollection == null) || (objectCollection.size() == 0)) return true;
		return false;
	}

	/**
	 * <code>isEmpty (object array):</code> checks whether the object array is empty (either null or size 0)
	 * @param objArray	Object array to be compared
	 * @return true if the array has at least 1 element, false otherwise
	 */
	public static boolean isEmpty (Object [] objArray){
		if ((objArray == null) || (objArray.length == 0)) return true;
		return false;
	}

}
