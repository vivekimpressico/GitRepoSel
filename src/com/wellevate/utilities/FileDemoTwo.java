package com.wellevate.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileDemoTwo {
	
	public static Object Read(String PATH) throws IOException {
		BufferedReader in = null;
		List<String> myList = new ArrayList<String>();
		try {   
		    in = new BufferedReader(new FileReader(PATH));
		    String str;
		    while ((str = in.readLine()) != null) {
		        myList.add(str);
		        
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (in != null) {
		        in.close();
		    }
		}
		System.out.println(myList);
		return myList;
		
	}}
