package com.uniovi.tests.pageobjects;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_NavView extends PO_View {
	/**
	 * CLicka una de las opciones principales (a href) y comprueba que se vaya a la
	 * vista con el elemento de tipo type con el texto Destino
	 * 
	 * @param driver:       apuntando al navegador abierto actualmente.
	 * @param textOption:   Texto de la opción principal.
	 * @param criterio:     "id" or "class" or "text" or "@attribute" or "free". Si
	 *                      el valor de criterio es free es una expresion xpath
	 *                      completa.
	 * @param textoDestino: texto correspondiente a la búsqueda de la página
	 *                      destino.
	 */
	public static void clickOption(WebDriver driver, String textOption, String criterio, String textoDestino) {
		// CLickamos en la opción de registro y esperamos a que se cargue el enlace dec
		// registro.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "@href", textOption, getTimeout());
		// Tiene que haber un sólo elemento.
		assertTrue(elementos.size() == 1);
		// Ahora lo clickamos
		elementos.get(0).click();
		// Esperamos a que sea visible un elemento concreto
		elementos = SeleniumUtils.EsperaCargaPagina(driver, criterio, textoDestino, getTimeout());
		// Tiene que haber un sólo elemento.
		assertTrue(elementos.size() == 1);
	}
	
	public static void clickOption(WebDriver driver, String textOption, String criterio) {
		// CLickamos en la opción de registro y esperamos a que se cargue el enlace dec
		// registro.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "@href", textOption, getTimeout());
		// Tiene que haber un sólo elemento.
		assertTrue(elementos.size() == 1);
		// Ahora lo clickamos
		elementos.get(0).click();
	}
	
	public static void clickProfile(WebDriver driver) {
		// CLickamos en la opción de registro y esperamos a que se cargue el enlace dec
		// registro.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", "Perfil", getTimeout());
		// Tiene que haber un sólo elemento.
		assertTrue(elementos.size() == 1);
		// Ahora lo clickamos
		elementos.get(0).click();
	}
	
	public static void clickProfile(WebDriver driver, int language) {
		// CLickamos en la opción de registro y esperamos a que se cargue el enlace dec
		// registro.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "text",  p.getString("nav.profile", language), getTimeout());
		// Tiene que haber un sólo elemento.
		assertTrue(elementos.size() == 1);
		// Ahora lo clickamos
		elementos.get(0).click();
	}
	
	public static void clickMisOfertas(WebDriver driver, int language) {
		// CLickamos en la opción de registro y esperamos a que se cargue el enlace dec
		// registro.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "text",  p.getString("nav.offers.mine", language), getTimeout());
		// Tiene que haber un sólo elemento.
		assertTrue(elementos.size() == 1);
		// Ahora lo clickamos
		elementos.get(0).click();
	}
	
	public static void clickAddOferta(WebDriver driver, int language) {
		// CLickamos en la opción de registro y esperamos a que se cargue el enlace dec
		// registro.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "text",  p.getString("nav.offers.mine.add", language), getTimeout());
		// Tiene que haber un sólo elemento.
		assertTrue(elementos.size() == 1);
		// Ahora lo clickamos
		elementos.get(0).click();
	}
	
	public static void clickUserList(WebDriver driver, int language) {
		// CLickamos en la opción de registro y esperamos a que se cargue el enlace dec
		// registro.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "text",  p.getString("nav.users.list", language), getTimeout());
		// Tiene que haber un sólo elemento.
		assertTrue(elementos.size() == 1);
		// Ahora lo clickamos
		elementos.get(0).click();
	}
	
	public static void clickDisconnect(WebDriver driver, int language) {
		// CLickamos en la opción de registro y esperamos a que se cargue el enlace dec
		// registro.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "text",  p.getString("nav.disconnect", language), getTimeout());
		// Tiene que haber un sólo elemento.
		assertTrue(elementos.size() == 1);
		// Ahora lo clickamos
		elementos.get(0).click();
	}
	
	public static void clickMisOfertas(WebDriver driver) {
		// CLickamos en la opción de registro y esperamos a que se cargue el enlace dec
		// registro.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", "Mis Ofertas", getTimeout());
		// Tiene que haber un sólo elemento.
		assertTrue(elementos.size() == 1);
		// Ahora lo clickamos
		elementos.get(0).click();

	}
	
	public static void clickOtrasOfertas(WebDriver driver) {
		// CLickamos en la opción de registro y esperamos a que se cargue el enlace dec
		// registro.
	//	List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", "Buscar Ofertas", getTimeout());
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'otherOffers')]/a");
		// Tiene que haber un sólo elemento.
		assertTrue(elementos.size() == 1);
		// Ahora lo clickamos
		elementos.get(0).click();
	}
	
	public static void clickIdioma(WebDriver driver, int language) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "text",  p.getString("language.change", language), getTimeout());
		// Tiene que haber dos elementos.
		assertTrue(elementos.size() == 1);
		// Ahora elegimos idioma 0 español 1 ingles
		elementos.get(0).click();
	}
	
	public static void clickCambiaIdiomaEsEn(WebDriver driver) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", "Ingles", getTimeout());
		// Tiene que haber dos elementos.
		assertTrue(elementos.size() == 1);
		// Ahora elegimos idioma 0 español 1 ingles
		elementos.get(0).click();
	}
	
	public static void clickCambiaIdiomaEnEs(WebDriver driver) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", "Spanish", getTimeout());
		// Tiene que haber dos elementos.
		assertTrue(elementos.size() == 1);
		// Ahora elegimos idioma 0 español 1 ingles
		elementos.get(0).click();
	}

	/**
	 * Selecciona el enlace de idioma correspondiente al texto textLanguage
	 * 
	 * @param driver:       apuntando al navegador abierto actualmente.
	 * @param textLanguage: el texto que aparece en el enlace de idioma ("English" o
	 *                      "Spanish")
	 */
	public static void changeIdiom(WebDriver driver, String textLanguage) {
		// clickamos la opción Idioma.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "btnLanguage", getTimeout());
		elementos.get(0).click();
		// Esperamos a que aparezca el menú de opciones.
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "languageDropdownMenuButton", getTimeout());
		// SeleniumUtils.esperarSegundos(driver, 2);
		// CLickamos la opción Inglés partiendo de la opción Español
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", textLanguage, getTimeout());
		elementos.get(0).click();
	}
	
	static public void checkWelcome(WebDriver driver, int language) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("welcome.message", language), getTimeout());
	}
	
	static public void checkProfile(WebDriver driver, int language) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("nav.profile", language), getTimeout());
	}
	
	static public void checkDisconnect(WebDriver driver, int language) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("nav.disconnect", language), getTimeout());
	}
	
	static public void checkUserList(WebDriver driver, int language) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("nav.users.list", language), getTimeout());
	}
	
	static public void checkUserText(WebDriver driver, int language) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("users.title.long", language), getTimeout());
	}
	
	static public void checkAddOfertaTitulo(WebDriver driver, int language) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("offers.add", language), getTimeout());
	}
	
	static public void checkAddOfertaDescripcion(WebDriver driver, int language) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("offers.add.detail", language), getTimeout());
	}
}
