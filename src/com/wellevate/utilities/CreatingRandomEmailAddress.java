package com.wellevate.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class CreatingRandomEmailAddress {

	static Properties prop;
	static InputStream input;
	GenericsMethods genericMethods = new GenericsMethods();
	
	// email id for new Practitoner creation
		public void emailPractitoner() throws IOException {
			prop = new Properties();
			input = null;
			input = new FileInputStream(System.getProperty("user.dir") + genericMethods.ConfigFile("pathToOR"));

			prop.load(input);

			String dummy = prop.getProperty("default");

			prop.setProperty("emailPractitioner", dummy);

			String email = prop.getProperty("emailPractitioner") + RandomStringUtils.randomNumeric(6) + "@yopmail.com";
			// String email = prop.getProperty("email");
			input.close();

			FileOutputStream out = new FileOutputStream(
					System.getProperty("user.dir") + genericMethods.ConfigFile("pathToOR"));
			// setting email value in property file for further use
			prop.setProperty("emailPractitioner", email);

			prop.store(out, null);
		}

	// email id for new patients creation
	public void emailPatients() throws IOException {
		prop = new Properties();
		input = null;
		input = new FileInputStream(System.getProperty("user.dir") + genericMethods.ConfigFile("pathToOR"));
		prop.load(input);

		String dummy = prop.getProperty("default1");

		prop.setProperty("emailPatient", dummy);

		String email = prop.getProperty("emailPatient") + RandomStringUtils.randomNumeric(6) + "@yopmail.com";
		// String email = prop.getProperty("email");
		input.close();

		FileOutputStream out = new FileOutputStream(
				System.getProperty("user.dir") + genericMethods.ConfigFile("pathToOR"));
		// setting email value in property file for further use
		prop.setProperty("emailPatient", email);

		prop.store(out, null);
	}

	// email id for new patients order items
	public void emailPatientsOrder() throws IOException {
		prop = new Properties();
		input = null;
		input = new FileInputStream(System.getProperty("user.dir") + genericMethods.ConfigFile("pathToOR"));
		prop.load(input);

		String dummy = prop.getProperty("default");

		prop.setProperty("emailPatientOrder", dummy);

		String email = prop.getProperty("emailPatientOrder") + RandomStringUtils.randomNumeric(6) + "@yopmail.com";
		// String email = prop.getProperty("email");
		input.close();

		FileOutputStream out = new FileOutputStream(
				System.getProperty("user.dir") + genericMethods.ConfigFile("pathToOR"));
		// setting email value in property file for further use
		prop.setProperty("emailPatientOrder", email);

		prop.store(out, null);
	}

	// email id for new patients order items
	public void name() throws IOException {
		prop = new Properties();
		input = null;
		input = new FileInputStream(System.getProperty("user.dir") + genericMethods.ConfigFile("pathToOR"));
		prop.load(input);

		String dummy = prop.getProperty("default2");

		prop.setProperty("patientsName", dummy);

		String name = prop.getProperty("patientsName") + RandomStringUtils.randomNumeric(6);
		// String email = prop.getProperty("email");
		input.close();

		FileOutputStream out = new FileOutputStream(
				System.getProperty("user.dir") + genericMethods.ConfigFile("pathToOR"));
		// setting email value in property file for further use
		prop.setProperty("patientsName", name);

		prop.store(out, null);

	}

	public void randomNumber() throws IOException {

		prop = new Properties();
		input = null;
		input = new FileInputStream(System.getProperty("user.dir") + genericMethods.ConfigFile("pathToOR"));
		prop.load(input);

		String dummy = prop.getProperty("default3");
		prop.setProperty("PatientDiscountField", dummy);

		String number = getRandomNumberInRange(1, 34).toString() + "%";
		
		input.close();

		FileOutputStream out = new FileOutputStream(
				System.getProperty("user.dir") + genericMethods.ConfigFile("pathToOR"));
		// setting email value in property file for further use
		prop.setProperty("PatientDiscountField", number);

		prop.store(out, null);
		
	}
	
	 public static Integer getRandomNumberInRange(int min, int max) {

		  Random r = new Random();
		  return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();

		 }
	// email id for new Practitoner creation
			public void emailApi() throws IOException {
				prop = new Properties();
				input = null;
				input = new FileInputStream(System.getProperty("user.dir") + genericMethods.ConfigFile("pathToApi"));

				prop.load(input);

				String dummy = prop.getProperty("default");

				prop.setProperty("emailapi", dummy);

				String email = prop.getProperty("emailapi") + RandomStringUtils.randomNumeric(6) + "@yopmail.com";
				// String email = prop.getProperty("email");
				input.close();

				FileOutputStream out = new FileOutputStream(
						System.getProperty("user.dir") + genericMethods.ConfigFile("pathToApi"));
				// setting email value in property file for further use
				prop.setProperty("emailapi", email);

				prop.store(out, null);
			}

}
