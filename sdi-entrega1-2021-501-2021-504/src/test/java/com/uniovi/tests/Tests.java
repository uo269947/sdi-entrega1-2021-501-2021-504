package com.uniovi.tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;
import com.uniovi.services.RolesService;
import com.uniovi.services.UsersService;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class Tests {
	@Autowired
	private UsersService usersService;
	@Autowired
	private RolesService rolesService;
	@Autowired
	private UsersRepository usersRepository;

	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\Eric\\Desktop\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
	// En MACOSX (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas):
	// static String PathFirefox65 =
	// "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
	// static String Geckdriver024 = "/Users/delacal/selenium/geckodriver024mac";
	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	@Before
	public void setUp() {
		initdb();
		driver.navigate().to(URL);
	}

	public void initdb() {
		User user1 = new User("user", "Pedro", "Díaz");
		user1.setPassword("user");
		user1.setSaldo(100.0);
		user1.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user1);

		User user2 = new User("Juan@gmail.com", "Juan", "Díaz");
		user2.setPassword("user");
		user2.setSaldo(100.0);
		user2.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user2);

		User user3 = new User("pepe@hotmail.com", "Pepe", "Perez");
		user3.setPassword("user");
		user3.setSaldo(100.0);
		user3.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user3);

		User admin = new User("admin@email.es", "admin", "admin");
		admin.setPassword("admin");
		admin.setRole(rolesService.getRoles()[1]);
		usersService.addUser(admin);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	@Test
	public void test1() { // Registro con datos validos
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "prueba@uniovi.es", "Charles", "Leclerc", "ferrari", "ferrari");
		PO_View.checkElement(driver, "text", "Bienvenido");
	}

	@Test
	public void test2() { // Registro con datos invalidos vacios
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");

		// Email vacio
		PO_RegisterView.fillForm(driver, "", "Charles", "Leclerc", "ferrari", "ferrari");
		PO_LoginView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());

		// Nombre vacio
		PO_RegisterView.fillForm(driver, "prueba@uniovi.es", "", "Leclerc", "ferrari", "ferrari");
		PO_LoginView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());

		// Apellido vacios
		PO_RegisterView.fillForm(driver, "prueba@uniovi.es", "Charles", "", "ferrari", "ferrari");
		PO_LoginView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());

	}

	@Test
	public void test3() { // Registro con contraseñas diferentes
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");

		PO_RegisterView.fillForm(driver, "prueba@uniovi.es", "Charles", "Leclerc", "ferrarista", "ferrari");
		PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
	}

	@Test
	public void test4() { // Registro de un email que ya existe
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");

		PO_RegisterView.fillForm(driver, "pepe@hotmail.com", "Charles", "Leclerc", "ferrari", "ferrari");
		PO_LoginView.checkKey(driver, "Error.signup.dni.duplicate", PO_Properties.getSPANISH());

	}

	@Test
	public void test5() { // Login con admin
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		PO_LoginView.fillForm(driver, "admin@email.es", "admin");

		PO_LoginView.checkElement(driver, "text", "Listado de usuarios"); // Podemos aceder al listado de usuarios porque
																		// somos admin
		PO_LoginView.checkNoText(driver, "Mis ofertas");
	}

	@Test
	public void test6() { // Login con usuario normal
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		PO_LoginView.fillForm(driver, "pepe@hotmail.com", "user");
		PO_LoginView.checkElement(driver, "text", "Mis Ofertas"); // Podemos aceder a mis ofertas porque somos un usuario
																// normal
		PO_LoginView.checkNoText(driver, "Listado de usuarios");

	}

	@Test
	public void test7() { // Login con campos vacios
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Email vacio
		PO_LoginView.fillForm(driver, "", "user");
		PO_LoginView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());

		// Contraseña vacia
		PO_LoginView.fillForm(driver, "pepe@hotmail.com", "");
		PO_LoginView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());

	}
	
	@Test
	public void test8() { // Login con contraseña incorrecta
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Email vacio
		PO_LoginView.fillForm(driver, "pepe@hotmail.com", "ur");
		PO_LoginView.checkKey(driver, "Error.login", PO_Properties.getSPANISH());

	}
	@Test
	public void test9() { // Login con email incorrecto
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Email vacio
		PO_LoginView.fillForm(driver, "@hotmail.com", "user");
		PO_LoginView.checkKey(driver, "Error.login", PO_Properties.getSPANISH());

	}
	
	@Test
	public void test10() { // Logout
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Email vacio
		PO_LoginView.fillForm(driver, "pepe@hotmail.com", "user");
		
		PO_HomeView.clickProfile(driver);
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_View.checkElement(driver, "text", "Identificate");

	}
	
	@Test
	public void test11() { // Logout no sale
		PO_HomeView.checkElement(driver, "text", "Identificate");
		PO_HomeView.checkNoText(driver, "Perfil");
		
		
	}
	
	
}
