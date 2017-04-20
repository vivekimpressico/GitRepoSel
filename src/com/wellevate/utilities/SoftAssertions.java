package com.wellevate.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.Reporter;

import com.mchange.util.AssertException;

public class SoftAssertions {
	public static int isAnyAssertFail = 0;
	public static Throwable exp;
	private static String FOLDER_NAME = System.getProperty("user.dir") + "\\Screenshots";

	public static void verifyEquals1(Object actual, Object expected, String successMessage, String failureMessage) {
		try {
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			Assert.assertEquals(actual, expected);
			printLogs("<font color = \"00ff00\">PASS : " + successMessage + "</font>");
		} catch (Throwable e) {
			System.out.println(
					"FAIL : " + failureMessage + " Expected Result : " + expected + " Actual Result : " + actual);
			printLogs("<font color = \"ff0000\">FAIL : " + failureMessage + " Expected Result : " + expected
					+ " Actual Result : " + actual + "</font>");
			// GlobalMethodUs.takeScreenShotOnFailure();
			printLogs("Detailed:" + e);
			drawLine();
			// throw new AssertionError();
		}
	}

	public static void verifyEquals(Object actual, Object expected, String successMessage, String failureMessage) {
		try {
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			Assert.assertEquals(actual, expected);
			printLogs("<font color = \"#006400\">PASS : " + successMessage + "</font>");
		} catch (Throwable e) {
			isAnyAssertFail++;
			takeScreenShotOnFailure();
			System.out.println(
					"FAIL : " + failureMessage + " Expected Result : " + expected + " Actual Result : " + actual);
			printLogs("<font color = \"ff0000\">FAIL : " + failureMessage + " Expected Result : " + expected
					+ " Actual Result : " + actual + "</font>");
			e.printStackTrace();
			printLogs("Detailed:" + e);
			if (isAnyAssertFail == 1)
				exp = e;
		}
	}

	public static void verifyEqualsApi(Object actual, Object expected, String successMessage, String failureMessage) {
		try {
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			Assert.assertEquals(actual, expected);
			printLogs("<font color = \"#006400\">PASS : " + successMessage + "</font>");
		} catch (Throwable e) {
			isAnyAssertFail++;
			System.out.println(
					"FAIL : " + failureMessage + " Expected Result : " + expected + " Actual Result : " + actual);
			printLogs("<font color = \"ff0000\">FAIL : " + failureMessage + " Expected Result : " + expected
					+ " Actual Result : " + actual + "</font>");
			e.printStackTrace();
			printLogs("Detailed:" + e);
			if (isAnyAssertFail == 1)
				exp = e;
		}
	}

	public static void throwAsserationOnFailure() {

		if (isAnyAssertFail > 0) {
			isAnyAssertFail = 0;
			exp.printStackTrace();
			throw new AssertException(exp.toString());
		}
	}

	public static void printInfoMSG(String msg) {
		printLogs("<font color = \"#FF892E\">PASS : " + msg + "</font>");
	}

	public static void printLogs(String msg) {
		Reporter.log(msg);
	}

	public static void drawLine() {
		printLogs("------------------------------------------");
	}

	

	@Deprecated
	public static void verifyTrue(boolean value) {
		try {
			Assert.assertTrue(value);
			Reporter.log("Pass");
		} catch (AssertionError e) {
			Reporter.log("Fail");
			Reporter.log("Detailed:" + e);
		}
	}

	@Deprecated
	public static void verifyFalse(boolean value, String msg) {
		try {
			Assert.assertFalse(value);
			Reporter.log("Pass:" + msg);
		} catch (AssertionError e) {
			Reporter.log("Fail:" + msg);
			Reporter.log("Detailed:" + e);
		}
	}

	@Deprecated
	public static void verifyFalse(boolean value) {
		try {
			Assert.assertFalse(value);
			Reporter.log("Pass");
		} catch (AssertionError e) {
			Reporter.log("Fail");
			Reporter.log("Detailed:" + e);
		}
	}

	@Deprecated
	public static void verifyEquals(String actual, String expected, String msg) {
		try {
			Assert.assertEquals(actual, expected);
			Reporter.log("Pass:" + msg);
		} catch (AssertionError e) {
			Reporter.log("Fail:" + msg + actual);
			Reporter.log("Detailed:" + e);
		}
	}

	@Deprecated
	public static void verifyNotEquals(String actual, String expected, String msg) {
		try {
			Assert.assertNotEquals(actual, expected);
			Reporter.log("Pass:" + msg);
		} catch (AssertionError e) {
			Reporter.log("Fail:" + msg + actual);
			Reporter.log("Detailed:" + e);
		}
	}

	@Deprecated
	public static void verifyEquals(List<String> actual, List<String> expected, String msg) {
		try {
			Assert.assertEquals(actual, expected);
			Reporter.log("Pass:" + msg);
		} catch (AssertionError e) {
			Reporter.log("Fail:" + msg + actual);
			Reporter.log("Detailed:" + e);
		}
	}

	public static void reporterLogLine() {
		Reporter.log("---------------------------------------------------------------");
	}

	@Deprecated
	public static void verifyEquals(int size, int donations, String msg) {
		try {
			Assert.assertEquals(size, donations);
			Reporter.log("Pass:" + msg);
		} catch (AssertionError e) {
			Reporter.log("Fail:" + msg + size);
			Reporter.log("Detailed:" + e);
		}

	}

	@Deprecated
	public static void verifyEquals(List<String> actCreatorName, String expected) {
		// TODO Auto-generated method stub
		String actual = null;
		String msg = null;
		try {
			Assert.assertEquals(actual, expected);

			Reporter.log("Pass:" + msg);
		} catch (AssertionError e) {

			Reporter.log("Fail:" + msg + actual);
			Reporter.log("Detailed:" + e);
		}
	}

	public static String takeScreenShotOnFailure() {
		File snapshot = ((TakesScreenshot) ProjectSetup.getDriver()).getScreenshotAs(OutputType.FILE);
		String dateNow = getCurrentDate();
		String snapShotDirectory = "source\\snapshots_" + FOLDER_NAME;
		String location = snapShotDirectory + "\\failure_screenshot_" + dateNow + ".png";
		File file = new File(location);
		try {
			FileUtils.copyFile(snapshot, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String screenShotPath = "\nScreenshot Location :: " + file.getAbsolutePath() + "\n";
		printLogs(screenShotPath);
		System.out.println(screenShotPath);
		return screenShotPath;
	}

	public static String getCurrentDate() {
		String DATE_FORMAT_NOW = "yyyy/MMM/dd";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		FOLDER_NAME = sdf.format(date).replace("/", "_");
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
		return formatter.format(currentDate.getTime()).replace("/", "_").replace(":", "_").replace(" ", "");
	}
}
