package me.hii488.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Formatter;

public class FileHandler {

	public static void serialize(String path, Object obj) throws Exception {
		FileOutputStream fileOut = new FileOutputStream(path);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(obj);
		out.close();
		fileOut.close();
	}
	
	public static Object deserialize(String path) throws Exception {
		FileInputStream fileIn = new FileInputStream(path);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		Object result = in.readObject();
		in.close();
		fileIn.close();
		return result;
	}
	
	public static String readFile(String filename){
		String content = "";
		try {
			File file = new File(filename);
		    FileReader reader = new FileReader(file);
		    char[] chars = new char[(int) file.length()];
		    reader.read(chars);
		    content = new String(chars);
		    reader.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return content;
	}
	
	public static void writeFile(String filename, String content){
		try {
			Formatter x = new Formatter(filename);
			x.format("%s", content);
			x.close();
		} catch (FileNotFoundException e) {e.printStackTrace();}
	}
	
}