package com.uniovi.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.OffersRepository;
import com.uniovi.repositories.UsersRepository;
import com.uniovi.services.OffersService;
import com.uniovi.services.RolesService;
import com.uniovi.services.UsersService;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
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
	private OffersService offersService;
	@Autowired
	private RolesService rolesService;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private OffersRepository offerRepository;

	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):
//	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
//	static String Geckdriver024 = "C:\\Users\\Eric\\Desktop\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

	 static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	 static String Geckdriver024 =
	 "C:\\Users\\aleex\\Desktop\\UniTercero2\\SDI\\Material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

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
		offerRepository.deleteAll();
		usersRepository.deleteAll();
		// Usuario 1
		User user1 = new User("user", "Pedro", "Díaz");
		user1.setPassword("user");
		user1.setSaldo(100.0);
		user1.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user1);
		// Ofertas usuario 1
		Offer of1 = new Offer("Triciclo", "En buen estado", LocalDate.now(), 50, user1);
		offersService.addOffer(of1);
		Offer of2 = new Offer("Boligrafo", "De regalo entrada para la final de la champions", LocalDate.now(), 110,
				user1);
		offersService.addOffer(of2);
		Offer of3 = new Offer("Movil", "Alcatel", LocalDate.now(), 30, user1);
		offersService.addOffer(of3);

		// Usuario 2
		User user2 = new User("Juan@gmail.com", "Juan", "Díaz");
		user2.setPassword("123456");
		user2.setSaldo(200.0);
		user2.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user2);
		// Ofertas usuario 2
		Offer of4 = new Offer("Opel corsa", "Lo vendo", LocalDate.now(), 80, user2);
		offersService.addOffer(of4);
		Offer of5 = new Offer("PC", "Con intel core 7", LocalDate.now(), 70, user2);
		offersService.addOffer(of5);
		Offer of6 = new Offer("Piedra", "Durisima", LocalDate.now(), 30, user2);
		offersService.addOffer(of6);

		// Usuario 3
		User user3 = new User("pepe@hotmail.com", "Pepe", "Perez");
		user3.setPassword("123456");
		user3.setSaldo(100.0);
		user3.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user3);
		// Ofertas usuario3
		Offer of7 = new Offer("Gorra de alonso ", "Firmada por el propio Alonso", LocalDate.now(), 999, user3);
		offersService.addOffer(of7);
		Offer of8 = new Offer("Portatil", "De marca HP", LocalDate.now(), 90, user3);
		offersService.addOffer(of8);
		Offer of9 = new Offer("Hoja de papel", "Es blanca ", LocalDate.now(), 10, user3);
		offersService.addOffer(of9);

		// Usuario 4
		User user4 = new User("alex@hotmail.com", "Alex", "Caso");
		user4.setPassword("123456");
		user4.setSaldo(500.0);
		user4.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user4);

		// Ofertas usuario4
		Offer of10 = new Offer("Camara digital ", "Hace muy buenas fotos", LocalDate.now(), 220, user4);
		offersService.addOffer(of10);
		Offer of11 = new Offer("Tablet", "De marca Samsung", LocalDate.now(), 80, user4);
		offersService.addOffer(of11);
		Offer of12 = new Offer("Nintendo", "Esta rota ", LocalDate.now(), 40, user4);
		offersService.addOffer(of12);

		// Usuario 5
		User user5 = new User("eric@hotmail.com", "Eric", "Almeda");
		user5.setPassword("123456");
		user5.setSaldo(500.0);
		user5.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user5);
		// Ofertas usuario 5
		Offer of13 = new Offer("Ford fiesta ", "65 caballos", LocalDate.now(), 500, user5);
		Offer of14 = new Offer("Playeros", "De marca nike", LocalDate.now(), 80, user5);
		Offer of15 = new Offer("Pelota de baloncesto", "Esta desinchada ", LocalDate.now(), 5, user5);
		offersService.addOffer(of13);
		offersService.addOffer(of14);
		offersService.addOffer(of15);

		// Usuario 6
		User user6 = new User("edward@hotmail.com", "Edward", "Rolando");
		user6.setPassword("123456");
		user6.setSaldo(500.0);
		user6.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user6);
		// Ofertas usuario6
		Offer of16 = new Offer("Lampara", "Grande para el salón", LocalDate.now(), 50, user6);
		Offer of17 = new Offer("Aifon", "Es el 7", LocalDate.now(), 10, user6);
		Offer of18 = new Offer("Dinero", "Vendo dinero", LocalDate.now(), 2, user6);

		offersService.addOffer(of16);
		offersService.addOffer(of17);
		offersService.addOffer(of18);

		// Compras de ofertas
		offersService.buyOffer(of5.getId(), user1);
		offersService.buyOffer(of6.getId(), user1);

		offersService.buyOffer(of1.getId(), user2);
		offersService.buyOffer(of2.getId(), user2);

		offersService.buyOffer(of3.getId(), user3);
		offersService.buyOffer(of4.getId(), user3);

		offersService.buyOffer(of14.getId(), user4);
		offersService.buyOffer(of18.getId(), user4);

		offersService.buyOffer(of16.getId(), user5);
		offersService.buyOffer(of17.getId(), user5);

		offersService.buyOffer(of12.getId(), user6);
		offersService.buyOffer(of13.getId(), user6);

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
		PO_LoginView.checkKey(driver, "Error.signup.email.duplicate", PO_Properties.getSPANISH());

	}

	@Test
	public void test5() { // Login con admin
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		PO_LoginView.fillForm(driver, "admin@email.es", "admin");

		PO_LoginView.checkElement(driver, "text", "Listado de usuarios"); // Podemos aceder al listado de usuarios
																			// porque
																			// somos admin
		PO_LoginView.checkNoText(driver, "Mis ofertas");
	}

	@Test
	public void test6() { // Login con usuario normal
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		PO_LoginView.fillForm(driver, "pepe@hotmail.com", "123456");
		PO_LoginView.checkElement(driver, "text", "Mis Ofertas"); // Podemos aceder a mis ofertas porque somos un
																	// usuario
																	// normal
		PO_LoginView.checkNoText(driver, "Listado de usuarios");

	}

	@Test
	public void test7() { // Login con campos vacios
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Email vacio
		PO_LoginView.fillForm(driver, "", "user");
		PO_LoginView.checkElement(driver, "text", "Identificate");

		// Contraseña vacia
		PO_LoginView.fillForm(driver, "pepe@hotmail.com", "");
		PO_LoginView.checkElement(driver, "text", "Identificate");

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
		PO_LoginView.fillForm(driver, "pepe@hotmail.com", "123456");

		PO_HomeView.clickProfile(driver);
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_View.checkElement(driver, "text", "Identificate");

	}

	@Test
	public void test11() { // Logout no sale
		PO_HomeView.checkElement(driver, "text", "Identificate");
		PO_HomeView.checkNoText(driver, "Perfil");

	}

	@Test
	public void test12() { // Listado de todos los usuarios
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.es", "admin");

		PO_View.checkElement(driver, "text", "Listado de usuarios");
		PO_HomeView.clickOption(driver, "user/list", "text", "Listado de usuarios");

		List<User> users = usersService.getNormalUsers();

		for (User u : users) {
			PO_View.checkElement(driver, "text", u.getEmail());
		}

	}

	@Test
	public void test13() { // Borrar el primer usuario
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.es", "admin");

		PO_View.checkElement(driver, "text", "Listado de usuarios");
		PO_HomeView.clickOption(driver, "user/list", "text", "Listado de usuarios");

	
		List<WebElement> checkBoxes=PO_View.checkElement(driver, "class", "checkbox");
		int size1= checkBoxes.size();
		checkBoxes.get(0).click();
		PO_View.checkElement(driver, "id", "btnEliminar").get(0).click();
		 checkBoxes=PO_View.checkElement(driver, "class", "checkbox");
		int size2= checkBoxes.size();
		assertTrue(size1!=size2);;
	}
	
	@Test
	public void test14() { // Borrar el ultimo usuario
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.es", "admin");

		PO_View.checkElement(driver, "text", "Listado de usuarios");
		PO_HomeView.clickOption(driver, "user/list", "text", "Listado de usuarios");

		
		List<WebElement> checkBoxes=PO_View.checkElement(driver, "class", "checkbox");
		int size1= checkBoxes.size();
		checkBoxes.get(size1-1).click();
		PO_View.checkElement(driver, "id", "btnEliminar").get(0).click();
		 checkBoxes=PO_View.checkElement(driver, "class", "checkbox");
		int size2= checkBoxes.size();
		assertTrue(size1!=size2);;
	}
	
	@Test
	public void test15() { // Borrar varios
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.es", "admin");

		PO_View.checkElement(driver, "text", "Listado de usuarios");
		PO_HomeView.clickOption(driver, "user/list", "text", "Listado de usuarios");

		List<User> users = usersService.getNormalUsers();
		User u1= users.get(0);
		User u2= users.get(1);
		User u3 = users.get(2);
		
		PO_View.checkElement(driver, "id", String.valueOf(u1.getId())).get(0).click();
		PO_View.checkElement(driver, "id", String.valueOf(u2.getId())).get(0).click();
		PO_View.checkElement(driver, "id", String.valueOf(u3.getId())).get(0).click();
		PO_View.checkElement(driver, "id", "btnEliminar").get(0).click();
		
		PO_View.checkNoText(driver, u1.getEmail());
		PO_View.checkNoText(driver, u2.getEmail());
		PO_View.checkNoText(driver, u3.getEmail());
	}

	@Test
	public void test16() { // Añadir una oferta a la lista
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user", "user");

		PO_HomeView.clickMisOfertas(driver);

		PO_HomeView.clickOption(driver, "offer/add", "text");

		PO_PrivateView.fillFormAddOffer(driver, "Pelota Vasca", "Esta que flipas de nueva", "8");

		PO_View.checkElement(driver, "text", "Pelota Vasca");

	}

	@Test
	public void test17() { // Añadir un usuarios inválidos
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user", "user");

		PO_HomeView.clickMisOfertas(driver);

		PO_HomeView.clickOption(driver, "offer/add", "text");

		PO_PrivateView.fillFormAddOffer(driver, "", "Esta que flipas de nueva", "8");

		PO_PrivateView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());

	}

	@Test
	public void test18() { // Listado de todas mis ofertas
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user", "user");

		PO_HomeView.clickMisOfertas(driver);

		PO_HomeView.clickOption(driver, "offer/list", "text");

		User user = usersService.getUserByEmail("user");
		List<Offer> offers = offersService.getMyOffers(user);
		for (Offer o : offers) {
			PO_View.checkElement(driver, "text", o.getTitle());
		}

	}

	@Test
	public void test19() { // Eliminar mi primera oferta
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user", "user");

		PO_HomeView.clickMisOfertas(driver);

		PO_HomeView.clickOption(driver, "offer/list", "text");

		User user = usersService.getUserByEmail("user");
		List<Offer> offers = offersService.getMyOffers(user);
		int pre = offers.size();

		PO_PrivateView.deleteOfferFromList(driver, "Triciclo");

		offers = offersService.getMyOffers(user);
		assertEquals(pre - 1, offers.size());
	}

	@Test
	public void test20() { // Eliminar mi ultima oferta
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user", "user");

		PO_HomeView.clickMisOfertas(driver);

		PO_HomeView.clickOption(driver, "offer/list", "text");

		User user = usersService.getUserByEmail("user");
		List<Offer> offers = offersService.getMyOffers(user);
		int pre = offers.size();

		PO_PrivateView.deleteOfferFromList(driver, "Movil");

		offers = offersService.getMyOffers(user);
		assertEquals(pre - 1, offers.size());
	}

	@Test
	public void test21() { // Busqueda vacía mismos elementos
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user", "user");

		PO_HomeView.clickOtrasOfertas(driver);

		PO_HomeView.clickOption(driver, "offer/buyList", "text");

		int pre = PO_PrivateView.count(driver, "othersOffersList");
		PO_PrivateView.search(driver, "searchText", "");
		int post = PO_PrivateView.count(driver, "otherOffersList");
		assertEquals(pre, post);
	}

	@Test
	public void test22() { // Busqueda elemento no existe
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user", "user");

		PO_HomeView.clickOtrasOfertas(driver);

		PO_HomeView.clickOption(driver, "offer/buyList", "text");

		PO_PrivateView.search(driver, "searchText", "adasdsadasd");
		int post = PO_PrivateView.count(driver, "otherOffersList");

		assertEquals(0, post);
	}

	@Test
	public void test26() { // Comprobar ofertas compradas
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user", "user");

		PO_HomeView.clickOtrasOfertas(driver);

		PO_HomeView.clickOption(driver, "offer/boughtList", "text");

		User user = usersService.getUserByEmail("user");
		List<Offer> offers = offersService.getOffersBought(user);
		for (Offer o : offers) {
			PO_View.checkElement(driver, "text", o.getTitle());
		}
	}

	@Test
	public void test27() { // Test Internacionalizacion
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user", "user");
		
		//check Welcome
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());

		PO_HomeView.clickIdioma(driver, PO_Properties.getSPANISH());
		PO_HomeView.clickCambiaIdiomaEsEn(driver);
		
		PO_HomeView.checkWelcome(driver, PO_Properties.getENGLISH());
		
		//check profile
		PO_HomeView.clickIdioma(driver, PO_Properties.getENGLISH());
		PO_HomeView.clickCambiaIdiomaEnEs(driver);
		
		PO_HomeView.checkProfile(driver, PO_Properties.getSPANISH());
		
		PO_HomeView.clickIdioma(driver, PO_Properties.getSPANISH());
		PO_HomeView.clickCambiaIdiomaEsEn(driver);
		
		PO_HomeView.checkProfile(driver, PO_Properties.getENGLISH());	
		PO_HomeView.clickProfile(driver, PO_Properties.getENGLISH());
		
		PO_HomeView.checkDisconnect(driver, PO_Properties.getENGLISH());	
		
		PO_HomeView.clickIdioma(driver, PO_Properties.getENGLISH());
		PO_HomeView.clickCambiaIdiomaEnEs(driver);

		PO_HomeView.clickProfile(driver, PO_Properties.getSPANISH());
		
		PO_HomeView.checkDisconnect(driver, PO_Properties.getSPANISH());	
		
		PO_HomeView.clickDisconnect(driver, PO_Properties.getSPANISH());
		
		//check User List
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.es", "admin");
		
		PO_HomeView.checkUserList(driver, PO_Properties.getSPANISH());	
		
		PO_HomeView.clickIdioma(driver, PO_Properties.getSPANISH());
		PO_HomeView.clickCambiaIdiomaEsEn(driver);
		
		PO_HomeView.checkUserList(driver, PO_Properties.getENGLISH());
		PO_HomeView.clickIdioma(driver, PO_Properties.getENGLISH());
		PO_HomeView.clickCambiaIdiomaEnEs(driver);
		
		PO_HomeView.clickUserList(driver, PO_Properties.getSPANISH());
		
		PO_HomeView.checkUserText(driver, PO_Properties.getSPANISH());	
		
		PO_HomeView.clickIdioma(driver, PO_Properties.getSPANISH());
		PO_HomeView.clickCambiaIdiomaEsEn(driver);
		
		PO_HomeView.checkUserText(driver, PO_Properties.getENGLISH());	
		
		PO_HomeView.clickIdioma(driver, PO_Properties.getENGLISH());
		PO_HomeView.clickCambiaIdiomaEnEs(driver);
		PO_HomeView.checkUserText(driver, PO_Properties.getSPANISH());
		
		PO_HomeView.clickProfile(driver, PO_Properties.getSPANISH());
		
		PO_HomeView.checkDisconnect(driver, PO_Properties.getSPANISH());	
		
		PO_HomeView.clickDisconnect(driver, PO_Properties.getSPANISH());
		
		//check add offer
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user", "user");
		
		PO_HomeView.clickMisOfertas(driver, PO_Properties.getSPANISH());
		PO_HomeView.clickAddOferta(driver, PO_Properties.getSPANISH());
		
		PO_HomeView.checkAddOfertaTitulo(driver, PO_Properties.getSPANISH());	
		PO_HomeView.checkAddOfertaDescripcion(driver, PO_Properties.getSPANISH());	
		
		PO_HomeView.clickIdioma(driver, PO_Properties.getSPANISH());
		PO_HomeView.clickCambiaIdiomaEsEn(driver);
		
		PO_HomeView.checkAddOfertaTitulo(driver, PO_Properties.getENGLISH());	
		PO_HomeView.checkAddOfertaDescripcion(driver, PO_Properties.getENGLISH());	
		
		PO_HomeView.clickIdioma(driver, PO_Properties.getENGLISH());
		PO_HomeView.clickCambiaIdiomaEnEs(driver);
		
		PO_HomeView.checkAddOfertaTitulo(driver, PO_Properties.getSPANISH());	
		PO_HomeView.checkAddOfertaDescripcion(driver, PO_Properties.getSPANISH());	
	}
}
