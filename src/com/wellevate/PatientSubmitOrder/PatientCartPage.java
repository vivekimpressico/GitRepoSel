package com.wellevate.PatientSubmitOrder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.omg.Messaging.SyncScopeHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wellevate.base.BasePage;
import com.wellevate.base.PercentageCalulation;
import com.wellevate.utilities.SoftAssertions;

public class PatientCartPage extends BasePage {
	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;
	static double[] elementList1;
	PercentageCalulation percentagecal = new PercentageCalulation();
	List<WebElement> myList;
	static List<WebElement> elementProductName;
	List<String> actQty;
	List<String> actproductprice;
	List<String> productprice;
	static List<WebElement> elementListQty;
	static List<String> qtyProduct;
	static List<WebElement> elementlistactprice;
	static List<String> discountproductprice;
	static List<String> productnameCart;

	public PatientCartPage(WebDriver driver) {
		PatientCartPage.driver = driver;

	}

	// Product Remove from cart
	public WebElement productRemovecart() {
		return getElementfluent(By.xpath("//a[@title='Remove']"));
	}

	// add products to cart
	@SuppressWarnings("unchecked")
	public static List<WebElement> productRemovecart1(int i) throws InterruptedException {
		try {
			elementList = driver.findElements(By.xpath("//a[@title='Remove']"));
			elementList.get(i).click();
			Thread.sleep(3000);

		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		return elementList;
	}

	// remove products from cart
	@SuppressWarnings("unchecked")
	public static void productRemovecartAll() throws InterruptedException {
		try {
			List<WebElement> removeButtons = driver.findElements(By.xpath("//a[@title='Remove']"));
			;
			while (removeButtons.size() > 0) {
				removeButtons = driver.findElements(By.xpath("//a[@title='Remove']"));
				elementWait(3000);
				removeButtons.get(0).click();
				elementWait(5000);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	// Product Quantity Box
	public static WebElement productQtyBox() {
		return getElementfluent(By.xpath("//input[@title='Qty']"));
	}

	// Product Quantity Box
	public static ArrayList<String> productQtyBoxItem() {
		ArrayList<String> qtyProduct = null;
		try {
			elementListQty = driver.findElements(By.xpath(
					"//*[@id='mainViewOverflow']/div/div[3]/div/div/div/div/div[2]/form/div/div[1]/div[4]/input"));
			qtyProduct = new ArrayList<String>();
			// Product Qty From View Cart Page
			for (int i = 0; i < elementListQty.size(); i++) {
				qtyProduct.add(elementListQty.get(i).getAttribute("value"));
				System.out.println(elementListQty.get(i).getAttribute("value"));

			}
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}

		return qtyProduct;
	}

	// Product Price
	public static ArrayList<String> productDiscountPrice() {
		ArrayList<String> discountproductprice = null;
		try {
			elementlistactprice = driver.findElements(By.xpath(
					"//div[@class='col-md-2 col-sm-2 col-xs-9 product-cart-price']/span[2]/span[@class='price']"));
			discountproductprice = new ArrayList<String>();
			// Product Price From View Cart Page
			for (int i = 0; i < elementlistactprice.size(); i++) {
				discountproductprice.add(elementlistactprice.get(i).getText().replace("$", ""));
				System.out.println(elementlistactprice.get(i).getText());

			}
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();

		}

		return discountproductprice;
	}

	// Product Qty Change in view cart page
	@SuppressWarnings("unchecked")
	public static void productQtyBoxChange(int itemsbox, String qty) throws InterruptedException {
		elementList = driver.findElements(By.xpath("//input[@title='Qty']"));
		try {
			for (int j = 0; j <= elementList.size(); j++) {
				elementList.get(itemsbox).click();
				elementList.get(itemsbox).clear();
				elementList.get(itemsbox).sendKeys(qty);
				productQtyUpdate1(itemsbox);
				elementWait(3000);
				break;
			}
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
			System.out.println("lo p");

		}
	}

	// Get the product price in View Cart Page
	@SuppressWarnings({ "unchecked", "unused" })
	public static String productQtyBoxChangePrice(int itemsbox, String qty) throws InterruptedException {
		elementList = driver.findElements(By.xpath("//input[@title='Qty']"));
		try {
			for (int j = 0; j <= elementList.size() - 1; j++) {
				elementList.get(itemsbox).click();
				elementWait(3000);
				elementList.get(itemsbox).clear();
				elementWait(3000);
				elementList.get(itemsbox).sendKeys(qty);
				productQtyUpdate1(itemsbox);
				elementWait(3000);
				String listprice1 = productdiscountPrice1().get(itemsbox).getText();
				String discountproductPrice = listprice1.substring(listprice1.indexOf("$") + 1);
				double listprice = Double.parseDouble(discountproductPrice);
				DecimalFormat format = new DecimalFormat("0.00");
				elementWait(3000);
				System.out.println(format.format(Double.parseDouble(qty)));
				double productprice1 = (listprice * Double.parseDouble(format.format(Double.parseDouble(qty))));
				double productSumTotal = (double) Math.round(productprice1 * 1000000) / 1000000;
				String productprice = Double.toString(productSumTotal);

				elementWait(3000);
				return productprice;

			}
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
			System.out.println("l  op");
		}
		return null;
	}

	// Product Qty Update
	public WebElement productQtyUpdate() {
		return getElementfluent(By.xpath("//button[@value='update_qty']"));
	}

	// Product Qty Update
	@SuppressWarnings("unchecked")
	public static List<WebElement> productQtyUpdate1(int updateqty) throws InterruptedException {
		try {
			elementList = driver.findElements(By.xpath("//button[@value='update_qty']"));
			elementList.get(updateqty).click();

		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		return elementList;
	}

	// Product names in cart
	public WebElement productnames() {
		return getElementfluent(By.xpath("//h2[@class='product-name cursor-pointer']"));
	}
	// Number of products Names
	@SuppressWarnings("null")
	public static ArrayList<String> productnamesInCart() throws InterruptedException {
		ArrayList<String> productnameCart = new ArrayList<String>();
		try {

			List<WebElement> productList = driver.findElements(By.cssSelector(".product-cart-brand.cursor-pointer"));
			for (int i = 0; i < productList.size(); i++) {
				System.out.println("Produc is : " + productList.get(i).getText());
				Thread.sleep(3000);
				productList.get(i).click();
				Thread.sleep(5000);
				String ProductID = driver.findElement(By.cssSelector("[product-sku]")).getAttribute("product-sku");
				System.out.println("Product ID is : " + ProductID);
				productnameCart.add(ProductID);
				driver.findElement(By.className("close")).click();
				Thread.sleep(40);

			}
			System.out.println(productnameCart);

			// logic to delete cart item

			// logic to re-evaluate cart

			// for(int i=0;i<productList.size();i++){
			// System.out.println("Produc is : " +
			// productList.get(i).getText());
			// Thread.sleep(3000);
			// productList.get(i).click();
			// Thread.sleep(40);
			// String ProductID =
			// driver.findElement(By.cssSelector("[product-sku]")).getAttribute("product-sku");
			// System.out.println("Product ID is : " + ProductID);
			// productnameCartAfter.add(ProductID);
			// driver.findElement(By.className("close")).click();
			// }
			//
			// System.out.println(productnameCartAfter);
			//
			// // logic to compare array if array is equal= product is still in
			// the cart else working fine
			// if(productnameCartAfter.size() < productnameCartBefore.size()){
			// System.out.println("cart item is deleted success.");
			// SoftAssertions.verifyEquals(productnameCartAfter.size() <
			// productnameCartBefore.size(),true, "All Product name is matches",
			// "All Product name name is not matches");
			// }else{
			// System.out.println("cart item not deleted !");
			// }

			// elementProductName = driver.findElements(By.xpath(""));
			// //span[@class="ms-cui-ctl-largelabel" and normalize-space()="New
			// Map"]
			// elementProductName =
			// driver.findElement(By.xpath("//table[@id='PowerReserveTables2_0']/tbody/tr/td[6]/div")).Text.split("<br></br>")[1].trim();
			// productnameCart = new ArrayList<String>();
			// for (int i = 0; i < elementProductName.size(); i++) {
			// productnameCart.add(elementProductName.get(i).getText().replace("$",
			// ""));
			// System.out.println(elementProductName.get(i).getText());
			//
			// }
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
			System.out.println("lo p");
		}

		return productnameCart;
	}

	// product discount Price
	public WebElement productdiscountPrice() {
		return getElementfluent(
				By.xpath("//div[@class='col-md-2 col-sm-2 col-xs-9 product-cart-price']/span[2]/span[@class='price']"));
	}

	// product discount Price
	@SuppressWarnings("unchecked")
	public static List<WebElement> productdiscountPrice1() throws InterruptedException {
		try {
			elementList = driver.findElements(By.xpath(
					"//div[@class='col-md-2 col-sm-2 col-xs-9 product-cart-price']/span[2]/span[@class='price']"));

		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		return elementList;
	}

	// Subtoatal price
	public WebElement productsubtotalPrice() {
		return getElementfluent(By
				.xpath("//div[@class='col-md-2 col-sm-2 col-xs-9 product-cart-total']/span[@class='cart-price']/span"));
	}

	// Subtoatal price
	@SuppressWarnings("unchecked")
	public static List<WebElement> productsubtotalPrice1() throws InterruptedException {
		try {
			elementList = driver.findElements(By.xpath(
					"//div[@class='col-md-2 col-sm-2 col-xs-9 product-cart-total']/span[@class='cart-price']/span"));

		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		return elementList;
	}

	// add products to carts
	@SuppressWarnings("unchecked")
	public static List<WebElement> productInCartPage() throws InterruptedException {
		try {
			elementList = driver.findElements(
					By.xpath("//form[@class='ng-pristine ng-valid']/div[@class='row shadowed-container']"));

		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		return elementList;
	}

	// product discount Price
	public WebElement productSumSubTotal() {
		return getElementfluent(By.xpath("//*[@id='shopping-cart-totals-table']/tbody/tr[1]/td/span[@class='price']"));
	}

	// Product Quantity Box
	public static WebElement noproductInCartMessage() {
		return getElementfluent(By.xpath("//p[contains(text(),'You have no items in your shopping cart.')]"));
	}
}
