package com.wellevate.base;

public class SendReport {

	private ZipDirectory zip = new ZipDirectory();
	private EmailAttachmentSender attachment = new EmailAttachmentSender();

	//Send Report Through Email And Convert File To Zip
	public void sendReport() {
		try {
			zip.zip();
			attachment.Send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");
	}

}
