package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_PrivateView extends PO_NavView {
	static public void fillFormAddOffer(WebDriver driver, String titulo, String descripcion, String precio) {
		
		
		WebElement title = driver.findElement(By.name("title"));
		title.clear();
		title.sendKeys(titulo);
		
		
		WebElement description = driver.findElement(By.name("description"));
		description.clear();
		description.sendKeys(descripcion);
		
		WebElement price = driver.findElement(By.name("price"));
		price.click();
		price.clear();
		price.sendKeys(precio);
		
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	public static void enterFormAddMark(WebDriver driver) {
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'marks-menu')]/a");
		elementos.get(0).click();
		// Esperamos a aparezca la opción de añadir nota: //a[contains(@href,
		// 'mark/add')]
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'mark/add')]");
		// Pinchamos en agregar Nota.
		elementos.get(0).click();
	}

	public static void selectPaginationPage (WebDriver driver, int page) {
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'marks-menu')]/a");
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		// Nos vamos a la última página
		elementos.get(page).click();
		
	}
	
	public static void deleteOfferFromList(WebDriver driver, String nota) {
		List<WebElement> elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), '" + nota + "')]/following-sibling::*/a[contains(@href, 'offer/delete')]");
		elementos.get(0).click();
		
	}

	public static void search(WebDriver driver, String element, String string) {
		WebElement search = driver.findElement(By.name(element));
		search.clear();
		if (string.length() > 1) {
			search.sendKeys(string);
		}
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	public static int count(WebDriver driver, String texto) {
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + texto + "')]"));		
		return list.size();
	}
	
}