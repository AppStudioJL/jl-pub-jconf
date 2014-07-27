package org.appstudiojl.app.jconf.util;

import java.util.ArrayList;
import java.util.List;

/**
 * <code>ByteUtil</code> Contains utility for byte including:
 * <p>1) Store byte[] and get concatenated byte[]</p>
 * <p>2) Gets sub-byte[] from a given byte[]</p>
 * @author	Jasonwaiting
 * @version	1.0.0
 * @since	2013-08-25
 */
public class ByteUtil {

	private static List<byte[]> byteArrays;
	private static boolean newSession = true;

	/**
	 * Adds new Byte array to the list for concatenation
	 * @param bytes	byte[] to be added
	 */
	public static void addByteArray (byte [] bytes){
		if (bytes == null) return;
		if (byteArrays == null){
			byteArrays = new ArrayList<byte[]>();
			newSession = false;
		}
		byteArrays.add(bytes);
	}

	/**
	 * Gets a concatenated byte[] from a list of byte array in buffer,
	 * and clears all bytes[] in buffer afterwards
	 * @return concatenated byte[]
	 */
	public static byte[] getByteArrays (){

		int totalLength = 0;
		byte[] concatByte;

		if (byteArrays == null){
			byteArrays = new ArrayList<byte[]>();
		}

		for (byte[] bytes : byteArrays){
			totalLength += bytes.length;
		}

		concatByte = new byte [totalLength];
		int i = 0;
		for (byte[] bytes : byteArrays){
			for (int j = 0; j < bytes.length; j++){
				concatByte [i + j] = bytes [j];
			}
			i += bytes.length;
		}

		/* Clear bytes in array */
		byteArrays = null;
		newSession = true;

		return concatByte;
	}

	/**
	 * Checks the status of byte[] buffer in Util
	 * @return true if no data in buffer, false otherwise
	 */
	public static boolean isNewSession (){

		if (newSession && (byteArrays == null)) return true;
		if (!newSession && (byteArrays != null)) return false;

		/* status correction */
		if (byteArrays == null){
			newSession = true;
			return true;
		}
		newSession = false;
		return false;
	}

	/**
	 * Flushes the session in byte[] buffer
	 */
	public static void flushSession (){
		byteArrays = null;
		newSession = true;
	}

	/**
	 * Return a new byte array containing a sub-portion of the source array
	 * 
	 * @param srcBegin	The beginning index (inclusive)
	 * @param source	The source byte []
	 * @return The new, populated byte array
	 */
	public static byte[] subbytes(byte[] source, int srcBegin) {
		return subbytes(source, srcBegin, source.length);
	}

	/**
	 * Return a new byte array containing a sub-portion of the source array
	 * 
	 * @param source	The source byte []
	 * @param srcBegin	The beginning index (inclusive)
	 * @param srcEnd	The ending index (exclusive)
	 * @return The new, populated byte array
	 */
	public static byte[] subbytes(byte[] source, int srcBegin, int srcEnd) {

		byte destination[];

		destination = new byte[srcEnd - srcBegin];
		getBytes(source, srcBegin, srcEnd, destination, 0);

		return destination;
	}

	/**
	 * Copies bytes from the source byte array to the destination array
	 * 
	 * @param source	The source array
	 * @param srcBegin	Index of the first source byte to copy
	 * @param srcEnd	Index after the last source byte to copy
	 * @param destination	The destination array
	 * @param dstBegin	The starting offset in the destination array
	 */
	public static void getBytes(byte[] source, int srcBegin, int srcEnd,
			byte[] destination, int dstBegin) {
		System.arraycopy(source, srcBegin, destination, dstBegin, srcEnd
				- srcBegin);
	}

}
