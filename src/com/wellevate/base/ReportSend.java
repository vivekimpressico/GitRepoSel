package com.wellevate.base;

import java.io.IOException;
import java.lang.*;

import org.junit.Assert;

import com.wellevate.base.SendReport;
import com.wellevate.utilities.CreatingRandomEmailAddress;

public class ReportSend {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		SendReport sendreport = new SendReport();
		sendreport.sendReport();

	}

}
