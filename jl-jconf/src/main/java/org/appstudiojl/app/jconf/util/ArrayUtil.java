package org.appstudiojl.app.jconf.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * <code>ArrayUtil</code> contains utilities for processing arrays including:
 * <p>1) checks whether the element exists in the array </p>
 * <p>2) adds element to array (with/without repetition)</p>
 * <p>3) convert between string, list and array</p>
 * <p>4) extends an array</p>
 * @author	Jasonwaiting
 * @version	1.0.0
 * @since	2013-02-24
 */
public class ArrayUtil {

	/**
	 * <code>contains(Collection, object):</code> checks whether the object is in object collection
	 * (create empty collection if the array is null)
	 * @param objectArray
	 * @param element
	 * @return true if the object array has this element, false otherwise
	 */
	public static boolean contains (Collection<Object> objectArray, Object element){

		/* If string array is null, create new array to avoid NullPointerException */
		if (objectArray == null){
			objectArray = new ArrayList<Object>();
		}

		/* check existence */
		return objectArray.contains(element);
	}

	/**
	 * <code>add(Collection, object, boolean):</code> adds an object to the object array without repetition [Override equal() for comparison]
	 * (create empty list if object array is null)
	 * @param objectArray	existing object array
	 * @param element	element to be added to the array
	 * @param allowRepeat	true if allow repetition
	 */
	public static void add (Collection<Object> objectArray, Object element, boolean allowRepeat){

		if (!ArrayUtil.contains(objectArray,element)){
			objectArray.add(element);
		} else {
			/* add object if repetition is allowed */
			if (allowRepeat) objectArray.add(element);
		}

	}

	/**
	 * <code>listToArray(Collection):</code> converts a given object collection to an object array
	 * @param objects	collection of object
	 * @return object with size same as the collection, null if the given collection is null or with size 0
	 */
	public static Object [] listToArray (Collection<Object> objects){
		if (Util.isEmpty(objects)) return null;
		return objects.toArray();
	}

	/**
	 * <code>listToString (Collection, delimiter):</code> expands object collection into a string with specified delimiter
	 * [Please override toString function for self-defined object]
	 * @param objects	collections to be expanded
	 * @param delimiter	separator of object
	 * @return null if not specifying delimiter, a string with objects inside collection otherwise
	 */
	public static String listToString (Collection<Object> objects, String delimiter){

		StringBuilder sb = new StringBuilder ();

		if (Util.isEmpty(delimiter)) return null;

		if (Util.isEmpty(objects)) return (sb.toString());

		boolean firstElement = true;
		for (Object obj:objects){
			if (firstElement){
				firstElement = false;
			} else {
				sb.append(delimiter);
			}
			sb.append(obj.toString());
		}

		return sb.toString();
	}

	/**
	 * <code>listToString (Collection):</code> expands object collection into a string with "," (Default delimiter)
	 * [Please override toString function for self-defined object]
	 * @param objects	collections to be expanded
	 * @return null if not specifying delimiter, a string with objects inside collection otherwise
	 */
	/*public static String listToString (Collection<Object> objects){
		return (ArrayUtil.listToString(objects, GlobalLibConst.DEFAULT_DELMIITER));
	}*/

	/**
	 * <code>arrayToString(Object []):</code> expands object array into a string with "," (Default delimiter)
	 * [Please override toString function for self-defined object]
	 * @param objects	objects array to be expanded
	 * @return null if not specifying delimiter, a string with objects inside collection otherwise
	 */
	/*public static String arrayToString (Object [] objects){
		if (Util.isEmpty(objects)) return new String ();
		return (ArrayUtil.listToString(Arrays.asList(objects)));
	}*/

	/**
	 * <code>arrayToString (Object [], delimiter):</code> expands object array into a string with specified delimiter
	 * [Please override toString function for self-defined object]
	 * @param objects	array to be expanded
	 * @param delimiter	separator of object
	 * @return null if not specifying delimiter, a string with objects inside collection otherwise
	 */
	public static String arrayToString (Object [] objects, String delimiter){
		if (Util.isEmpty(delimiter)) return null;
		if (Util.isEmpty(objects)) return new String ();
		return (ArrayUtil.listToString(Arrays.asList(objects),delimiter));
	}

	/**
	 * <code>stringToList (text,delimiter):</code> extracts the string into a string list by a delimiter
	 * @param text	string to be extracted
	 * @param delimiter	separator of item
	 * @return null if either text or delimiter undefined, list of string otherwise
	 */
	public static List<String> stringToList (String text, String delimiter){
		List<String> stringList =  new ArrayList<String>();
		String [] tempList = stringToArray(text, delimiter);
		if (tempList == null) return null;
		for (String temp:tempList){
			stringList.add(temp);
		}
		return stringList;
	}

	/**
	 * <code>stringToArray (text,delimiter):</code> extracts the string into a string array by a delimiter
	 * @param text	string to be extracted
	 * @param delimiter	separator of item
	 * @return null if either text or delimiter undefined, array of string otherwise
	 */
	public static String [] stringToArray (String text, String delimiter){
		if (Util.isEmpty(text) || Util.isEmpty(delimiter)){
			return null;
		}
		return (text.split(delimiter));
	}

	/**
	 * <code>extendArraySize (array, new_size):</code> extends the size of the array
	 * @param originalArray	array to be replaced
	 * @param size	size of new array
	 * @return new array with specific size
	 */
	public static Object [] extendArraySize (Object [] originalArray, int size){
		if (Util.isEmpty(originalArray)){
			originalArray = new Object [1];
			originalArray [0] = new Object ();
		}
		Object [] extendedArray = null;
		if (originalArray.length < size){
			System.arraycopy(originalArray, 0, extendedArray, 0, originalArray.length);
		}
		return extendedArray;
	}
}
