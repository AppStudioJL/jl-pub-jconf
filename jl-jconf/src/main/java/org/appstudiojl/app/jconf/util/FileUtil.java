package org.appstudiojl.app.jconf.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * <code>FileUtil</code> contains utilities for processing actions in file system including:
 * <p>1) create folder(s)</p>
 * <p>2) copy file(s)</p>
 * <p>3) check whether the file/folder is latest</p>
 * <p>[NOT YET DONE] 4) check whether the file/folder is updated by author</p>
 * <p>5) check existence of file/folder</p>
 * <p>6) give full path of file</p>
 * <p>7) get file size of file if exist</p>
 * <p>8) reset last modified time of a file</p>
 * <p>9) give a list of files matching the regular expression in the folder</p>
 * @author	Jasonwaiting
 * @version	1.0.0
 * @since	2013-02-24
 */
/**
 * @author jasonwaiting
 *
 */
public class FileUtil {

	private static File tempFile;

	/**
	 * <code>isExists (filename, startPath, checkSubFolder)</code> checks whether the file/folder exists.
	 * With search feature, you can specify a start point for the program to search the file.
	 * @param filename	the file name you want to search
	 * @param startPath	root folder for searching
	 * @param checkSubFolder	whether sub-folder should be checked
	 * @return true if exists, false otherwise
	 */
	public static boolean isExists (String filename, String startPath, boolean checkSubFolder){

		/* check given condition */
		if (Util.isEmpty(filename) || Util.isEmpty(startPath)) return false;

		/* fill in a "\" if not given by user */
		startPath = FileUtil.getProperFolderPath(startPath);

		/* if don't check sub-folder, just call another function */
		if (!checkSubFolder) return FileUtil.isExists(startPath + filename);

		/* check start path */
		if (!FileUtil.isFolder(startPath)) return false;

		return FileUtil.isExists(filename, startPath);
	}

	/**
	 * Check the existence of file by searching from the root folder specified
	 * @param filename	filename to be searched
	 * @param folder	root folder for searching
	 * @return true if exists, false otherwise
	 */
	public static boolean isExists (String filename, String folder){

		if (Util.isEmpty(filename) || Util.isEmpty(folder)) return false;

		/* fill in a "\" if not given by user */
		folder = FileUtil.getProperFolderPath(folder);

		File file = new File (folder);
		if (!FileUtil.isFolder(folder)) return false;

		/* init */
		FileUtil.resetCacheFile();

		String [] files = file.list();
		File checkFile = null;
		for (String f:files){
			checkFile = new File (f);
			if (checkFile.isDirectory()){
				if (FileUtil.isExists(filename, FileUtil.getProperFolderPath(folder + f))){
					return true;
				}
			} else {
				if (Util.isSame(f, filename)){
					FileUtil.setCacheFile(checkFile);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * <code>isExists (path)</code> checks whether the file/folder exists
	 * @param path	the full path of file
	 * @return true if exists, false otherwise
	 */
	public static boolean isExists (String path){
		File file = new File (path);
		return file.exists();
	}

	/**
	 * <code>getFullPath (path)</code> returns the absolute path of the file
	 * @param path	relative file path
	 * @return full path of file if exists, null otherwise
	 */
	public static String getFullPath (String path){

		/* check whether the path is valid */
		if (Util.isEmpty(path)) return null;
		if (!FileUtil.isExists(path)) return null;

		File file = new File (path);
		return file.getAbsolutePath();
	}

	/**
	 * <code>getFullPath (filename, root, check sub-folder)</code> returns the full path of a file with searching functionality
	 * @param filename	filename to be searched
	 * @param rootFolder	starting point for searching
	 * @param checkSubFolder	whether sub-folder is checked
	 * @return full path of file if found, null otherwise
	 */
	public static String getFullPath (String filename, String rootFolder, boolean checkSubFolder){

		/* check input path */
		if (Util.isEmpty(filename) || Util.isEmpty(rootFolder)) return null;

		if (FileUtil.isExists(filename, rootFolder, checkSubFolder)){
			return FileUtil.getCacheFile().getAbsolutePath();
		}
		return null;
	}

	/**
	 * Reset the cache file to null
	 */
	private static void resetCacheFile (){
		FileUtil.tempFile = null;
	}

	/**
	 * set the cache to a specified file
	 * @param file	file
	 */
	private static void setCacheFile (File file){
		FileUtil.tempFile = file;
	}

	/**
	 * get the cache file
	 * @return the searching result in cache
	 */
	private static File getCacheFile (){
		return FileUtil.tempFile;
	}

	/**
	 * <code>isUpdated (path, refDate) </code> checks whether the file is updated after the ref. Date
	 * @param path	path of file
	 * @param refDate	reference date
	 * @return true if it is updated after the ref. date, false otherwise
	 */
	public static boolean isUpdated (String path, Date refDate){
		if (Util.isEmpty(path) || (refDate == null)) return false;

		if (!FileUtil.isExists(path)) return false;
		File file = new File (path);

		Date fileDate = new Date (file.lastModified());
		return (refDate.before(fileDate));
	}

	/**
	 * <code>getProperFolderPath (path) </code> will add \ at the end of the path (proper path)
	 * @param path	original path
	 * @return proper path, null if the input is also null
	 */
	public static String getProperFolderPath (String path){
		if (Util.isEmpty(path)) return null;
		path = path.replace("/", "\\");
		if (!path.endsWith("\\")) path = path + "\\";
		return path;
	}

	/**
	 * <code>isFolder (path)</code> checks whether the given path is a folder
	 * @param path	check path
	 * @return true if it is a folder, false otherwise
	 */
	public static boolean isFolder (String path){

		if (Util.isEmpty(path)) return false;
		if (!FileUtil.isExists(path)) return false;

		File file = new File (path);
		return (FileUtil.isFolder(file));
	}

	/**
	 * <code>isFolder (File)</code> checks whether the given path is a folder
	 * @param file	File
	 * @return true if it is a folder, false otherwise
	 */
	public static boolean isFolder (File file){
		if (file == null) return false;
		return file.isDirectory();
	}

	/**
	 * <code>isFile (path)</code> checks whether the given path is a file
	 * @param path	check path
	 * @return true if it is a file, false otherwise
	 */
	public static boolean isFile (String path){

		if (Util.isEmpty(path)) return false;
		if (FileUtil.isExists(path)) return false;

		File file = new File (path);
		return (FileUtil.isFile(file));
	}

	/**
	 * <code>isFile (File)</code> checks whether the given path is a file
	 * @param file	File
	 * @return true if it is a file, false otherwise
	 */
	public static boolean isFile (File file){
		if (file == null) return false;
		return file.isFile();
	}

	/**
	 * <code>copyFile (source,dest)</code> copies the file from one location to another one
	 * @param sourceFile	file to be copied
	 * @param destFile	copy location
	 * @throws IOException file not found, copy error
	 */
	public static void copyFile (String sourceFile, String destFile) throws IOException {
		InputStream in = new FileInputStream(sourceFile);
		OutputStream out = new FileOutputStream(destFile);

		byte[] buffer = new byte[1024];

		int length;
		//copy the file content in bytes
		while ((length = in.read(buffer)) > 0){
			out.write(buffer, 0, length);
		}
		in.close();
		out.close();
	}

	/**
	 * <code>copyFolders (source,dest,copyFile,includeSubFolder)</code> copies folder content to another location
	 * @param source	source folder
	 * @param dest	destination folder
	 * @param copyFile	true if aims to copy the file inside the folder, false otherwise
	 * @param includeSubFolder	true if aims to copy sub folder content, false otherwise
	 * @throws IOException copy action failed
	 */
	public static void copyFolders (String source, String dest, boolean copyFile, boolean includeSubFolder) throws IOException {

		if (Util.isEmpty(source) || Util.isEmpty(dest)) return;

		source = FileUtil.getProperFolderPath(source);
		dest = FileUtil.getProperFolderPath(dest);
		if (Util.isSame(source, dest)) return;

		if (!FileUtil.isExists(source)) return;

		File sourceFile = new File (source);
		File destFile = new File (dest);

		/* create root folder */
		if (!FileUtil.isExists(dest)) destFile.mkdirs();

		if (FileUtil.isFolder(source) && FileUtil.isFolder(dest)){
			FileUtil.transverseFolders (sourceFile, destFile, copyFile,includeSubFolder);
		}

	}

	/**
	 * <code>copyFolders (source, dest, copyFile)</code> copies folder content to another location. Sub-folder will also be included.
	 * @param source	source folder
	 * @param dest	destination folder
	 * @param copyFile	true if aims to copy the file inside the folder, false otherwise
	 * @throws IOException copy action failed
	 */
	public static void copyFolders (String source, String dest, boolean copyFile) throws IOException {
		FileUtil.copyFolders(source, dest, copyFile,true);
	}

	/**
	 * <code>copyFolders (source, dest, copyFile)</code> copies folder content to another location. All contents inside will be copied.
	 * @param source	source folder
	 * @param dest	destination folder
	 * @throws IOException copy action failed
	 */
	public static void copyFolders (String source, String dest) throws IOException {
		FileUtil.copyFolders(source, dest, true, true);
	}

	/**
	 * <code>transverseFolders (source, dest, copyFile, copySubFolder)</code> transverse each item in the folder and copy file/folder
	 * @param source	source folder
	 * @param dest	destination folder
	 * @param copyFile	true if aims to copy file
	 * @param copySubFolder	true if aims to go through sub-folder
	 * @throws IOException copy action failed
	 */
	private static void transverseFolders (File source, File dest, boolean copyFile, boolean copySubFolder) throws IOException {
		File [] files = source.listFiles();
		for (File f: files){
			if (FileUtil.isFolder(f) && !Util.isSame(f.getAbsolutePath(), dest.getAbsolutePath())){
				/* create folder */
				File destFile = new File (FileUtil.getProperFolderPath(dest.getAbsolutePath()) + f.getName());
				if (!destFile.exists()) destFile.mkdirs();
				/* transverse sub-folder */
				if (copySubFolder){
					FileUtil.transverseFolders (f,destFile,copyFile, true);
				}
			} else if (FileUtil.isFile(f) && copyFile){
				FileUtil.copyFile(f.getAbsolutePath(), FileUtil.getProperFolderPath(dest.getAbsolutePath()) + f.getName());
			}
		}
	}

	/**
	 * <code>createFolders (path)</code> creates folder(s) with given path
	 * @param path	full path
	 */
	public static void createFolders (String path){
		if (Util.isEmpty(path)) return;
		FileUtil.createFolders(new File(path));
	}

	/**
	 * <code>createFolders(file)</code> creates folder(s) with given File object
	 * @param path	File object with full path
	 */
	public static void createFolders (File path){
		if (path == null) return;
		if (!path.exists()) path.mkdirs();
	}

	/**
	 * <code>getFileSize (file_path)</code> gives the file size of the file if exists
	 * @param filePath	file path
	 * @return file size in words, e.g. 10KB
	 */
	/*public static String getFileSize (String filePath){
		String fileSize = GlobalLibConst.STR_UNKNOWN_VALUE;
		if (Util.isEmpty(filePath) || FileUtil.isExists(filePath)) return fileSize;

		File file = new File (filePath);
		fileSize = FileUtils.byteCountToDisplaySize(file.length());
		return fileSize;
	}*/

	/**
	 * <code>resetFileLastUpdateTime (file path)</code> resets the last updated time to now
	 * @param filePath	file path
	 * @throws GeneralUtilException filepath not specified, file cannot be saved
	 */
	/*public static void resetFileLastUpdateTime (String filePath) throws GeneralUtilException {
		if (Util.isEmpty(filePath)) throw new GeneralUtilException ("File path not specified");
		File file = new File (filePath);
		try {
			FileUtils.touch(file);
		} catch (IOException e){
			throw new GeneralUtilException(e);
		}
	}*/

	/**
	 * <code>getFolderFileList (folderPath, regex)<code> gets a list of files matching the regular expression
	 * @param folderPath	root folder path
	 * @param regex	regular expression
	 * @return Full file list matching the condition
	 * @throws GeneralUtilException
	 */
	/*public static String [] getFolderFileList (String folderPath, String regex) throws GeneralUtilException{
		if (Util.isEmpty(folderPath)){
			throw new GeneralUtilException("Folder path / regex not specified");
		}

		if (!FileUtil.isFolder(folderPath)){
			throw new GeneralUtilException(folderPath + " is not a folder");
		}

		String [] resultList = null;
		File folder = new File (folderPath);

		if (Util.isEmpty(regex)){
			resultList = folder.list();
		} else {
			resultList = folder.list();
		}

		return resultList;
	}*/

}
