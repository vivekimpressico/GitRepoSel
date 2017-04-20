package com.wellevate.utilities;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GenericsMethods {

	// Config data file data
	public static String ConfigFile(String Value) {
		File file = new File("src\\com\\wellevate\\configuration\\WellevateProfileSetUp.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Properties prop = new Properties();
		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String Data = prop.getProperty(Value);

		return Data;
	}

	public ArrayList<String> api(File file) {
		ArrayList<String> arr = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				arr.add(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return arr;

	}

	// data properties file data
	public String dataprp(String Value) {
		File file = new File("src\\com\\wellevate\\configuration\\WellevateCredentialValue.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Properties prop = new Properties();
		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String Data = prop.getProperty(Value);

		return Data;
	}

	public void fileUpload(String path) {

		try {
			StringSelection ss = new StringSelection(path);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

			// imitate mouse events like ENTER, CTRL+C, CTRL+V
			Robot robot = new Robot();
			robot.delay(25000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(5000);
			robot.keyRelease(KeyEvent.VK_ENTER);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// color hexValue
	public String color(String color) {
		String[] hexValue = color.replace("rgba(", "").replace(")", "").split(",");

		int hexValue1 = Integer.parseInt(hexValue[0]);
		hexValue[1] = hexValue[1].trim();
		int hexValue2 = Integer.parseInt(hexValue[1]);
		hexValue[2] = hexValue[2].trim();
		int hexValue3 = Integer.parseInt(hexValue[2]);
		String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
		return actualColor;
	}

	// data properties file data
	public String signUP(String Value) {
		File file = new File("src\\com\\wellevate\\configuration\\SignUp.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Properties prop = new Properties();
		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String Data = prop.getProperty(Value);

		return Data;
	}

	public List<String> readDataFile(String filepath) throws IOException {
		BufferedReader in = null;
		List<String> myList = new ArrayList<String>();
		try {
			in = new BufferedReader(new FileReader(filepath));
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

	}

	// Config data file data
	public static String ApiCredential(String Value) {
		File file = new File("src\\com\\wellevate\\configuration\\ApiValidation.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Properties prop = new Properties();
		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String Data = prop.getProperty(Value);

		return Data;
	}
	// Config data file data
		public String GetApiCredential(String Value) {
			File file = new File("src\\com\\wellevate\\configuration\\ApiValidation2.properties");
			FileInputStream fileInput = null;
			try {
				fileInput = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			Properties prop = new Properties();
			// load properties file
			try {
				prop.load(fileInput);
			} catch (IOException e) {
				e.printStackTrace();
			}

			String Data = prop.getProperty(Value);

			return Data;
		}

	// Write file
	public void writeApiOutput(String key,String value) {
		Properties prop = new Properties();
		OutputStream output = null;
		try {

			output = new FileOutputStream("src\\com\\wellevate\\configuration\\ApiValidation2.properties");
			for (int i = 0; i < 1000; i++) {
			// set the properties value
			prop.setProperty(key, value);	
			// save properties to project root folder
			prop.store(output, null);
			}
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
