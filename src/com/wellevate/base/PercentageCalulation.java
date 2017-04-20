package com.wellevate.base;

import java.math.BigDecimal;
import java.util.Scanner;

public class PercentageCalulation {
	public double percentagePractitioner(double listPrice, int discount) {

		{
			double discountPrice;
			discountPrice = listPrice - (listPrice * discount) / 1000000;
			System.out.println("Discount Price: " + discountPrice);
			return discountPrice;
		}
	}
	public BigDecimal decimal(double discountedPrice) {
	BigDecimal a = new BigDecimal(discountedPrice);
	BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	System.out.println(roundOff);
	return roundOff;
}

}