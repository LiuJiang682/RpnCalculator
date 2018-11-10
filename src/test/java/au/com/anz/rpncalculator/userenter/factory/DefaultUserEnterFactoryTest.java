package au.com.anz.rpncalculator.userenter.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

import au.com.anz.rpncalculator.fixture.RpnCalculatorTestFixture;
import au.com.anz.rpncalculator.userenter.UserEntry;
import au.com.anz.rpncalculator.userenter.factory.DefaultUserEnterFactory;
import au.com.anz.rpncalculator.userenter.operator.Addition;



/**
 * In order to perform reverse polish notation calculation 
 * As the calculator
 * operator, I like to able to accept the user enter
 */
public class DefaultUserEnterFactoryTest {

	private DefaultUserEnterFactory testInstance;
	private Scanner mockScanner;

	@Before
	public void setUp() {
		this.testInstance = new DefaultUserEnterFactory();
	}

	@After
	public void tearDown() {
		this.testInstance = null;
		this.mockScanner = null;
	}

	/**
	 * Given the user enter null string 
	 * When the constructUserEntry method
	 * called 
	 * Then a Optional object of empty 
	 * should return
	 */
	@Test
	public void whenUserEnterNullThanOptionalOfEmptyUserEntryShouldReturn() {
		// Given user entered null
		String userEntered = null;
		// When the constructUserEntry called
		Optional<UserEntry> userEntry = testInstance.constructUserEntry(userEntered);
		// Then the a DoNothing command object should return
		assertNotNull(userEntry);
		assertFalse(userEntry.isPresent());
	}
	
	/**
	 * Given the user enter an empty string
	 * When the constructUserEntry method called
	 * Then a Optional object of empty 
	 * should return
	 */
	@Test
	public void whenUserEnteredEmptyStringThenAnEmptyOptionalObjectShouldReturn() {
		//Given user entered an empty string 
		String userEntered = "";
		//When the constructUserEntry called
		Optional<UserEntry> userEntry = testInstance.constructUserEntry(userEntered);
		//Then the an empty optional object should return
		assertNotNull(userEntry);
		assertFalse(userEntry.isPresent());
	}
	
	/**
	 * Given the user enter an invalid string
	 * When the constructUserEntry method called
	 * Then a Optional object of empty 
	 * should return
	 */
	@Test
	public void whenUserEnteredInvalidStringThenAnEmptyOptionalObjectShouldReturn() {
		//Given user entered an invalid command string 
		String userEntered = "abc";
		//When the constructUserEntry called
		Optional<UserEntry> userEntry = testInstance.constructUserEntry(userEntered);
		//Then the an empty optional object should return
		assertNotNull(userEntry);
		assertFalse(userEntry.isPresent());
	}
	
	/**
	 * Given the user enter a valid digits string
	 * When the constructUserEntry method called
	 * Then a Optional UserEntry object  
	 * should return
	 */
	@Test
	public void whenUserEnteredValidStringThenAnDigitalUserEntryOptionalObjectShouldReturn() {
		//Given user entered an invalid command string 
		String userEntered = "123";
		//When the constructUserEntry called
		Optional<UserEntry> userEntry = testInstance.constructUserEntry(userEntered);
		//Then the an valid optional object should return
		assertNotNull(userEntry);
		assertTrue(userEntry.isPresent());
	}
	
	/**
	 * Given the user enter a valid negative digits string
	 * When the constructUserEntry method called
	 * Then a Optional UserEntry object  
	 * should return
	 */
	@Test
	public void whenUserEnteredValidNegativeStringThenAnDigitalUserEntryOptionalObjectShouldReturn() {
		//Given user entered an invalid command string 
		String userEntered = "-123";
		//When the constructUserEntry called
		Optional<UserEntry> userEntry = testInstance.constructUserEntry(userEntered);
		//Then the an valid optional object should return
		assertNotNull(userEntry);
		assertTrue(userEntry.isPresent());
		BigDecimal retrieve = Whitebox.getInternalState(userEntry.get(), "digits");
		assertEquals(new BigDecimal(-123), retrieve);
	}
	
	/**
	 * Given the user enter a "+" string
	 * When the constructUserEntry method called
	 * Then a Optional UserEntry object  
	 * should return
	 */
	@Test
	public void whenUserEnteredAnAdditionStringThenAnDigitalUserEntryOptionalObjectShouldReturn() {
		//Given user entered an addition string 
		String userEntered = "+";
		//When the constructUserEntry called
		Optional<UserEntry> userEntry = testInstance.constructUserEntry(userEntered);
		//Then the an valid optional object with addition
		//operator should return
		assertNotNull(userEntry);
		assertTrue(userEntry.isPresent());
		UserEntry userEntryObject = userEntry.get();
		assertTrue(userEntryObject instanceof Addition);
	}
	
	/**
	 * Given the user enter an valid string
	 * When the getDigitalUserEntry method called
	 * Then a populate Optional object 
	 * should return
	 */
	@Test
	public void whenUserEnteredValidStringThenAnDigitalOptionalObjectShouldReturn() {
		//Given user entered an valid string 
		String userEntered = "123";
		//When the constructUserEntry called
		Optional<UserEntry> userEntry = testInstance.getDigitalUserEntry(userEntered);
		//Then the an empty optional object should return
		assertNotNull(userEntry);
		assertTrue(userEntry.isPresent());
	}
	
	/**
	 * Given the user enter an invalid string
	 * When the getDigitalUserEntry method called
	 * Then a Optional object of empty 
	 * should return
	 */
	@Test
	public void whenUserEnteredInvalidStringThenAnEmptyDigitalOptionalObjectShouldReturn() {
		//Given user entered an invalid string 
		String userEntered = "abc";
		//When the constructUserEntry called
		Optional<UserEntry> userEntry = testInstance.getDigitalUserEntry(userEntered);
		//Then the an empty optional object should return
		assertNotNull(userEntry);
		assertFalse(userEntry.isPresent());
	}
	
	/**
	 * Given the user enter an valid string
	 * When the getOperatorUserEntry method called
	 * Then a populated Optional object 
	 * should return
	 */
	@Test
	public void whenUserEnteredValidStringThenAnOperatorOptionalObjectShouldReturn() {
		//Given user entered an valid string 
		String userEntered = "+";
		//When the constructUserEntry called
		Optional<UserEntry> userEntry = testInstance.getOperatorUserEntry(userEntered);
		//Then the an empty optional object should return
		assertNotNull(userEntry);
		assertTrue(userEntry.isPresent());
	}
	
	/**
	 * Given the user enter an invalid string
	 * When the getOperatorUserEntry method called
	 * Then a Optional object of empty 
	 * should return
	 */
	@Test
	public void whenUserEnteredInvalidStringThenAnEmptyOperatorOptionalObjectShouldReturn() {
		//Given user entered an invalid string 
		String userEntered = "abc";
		//When the constructUserEntry called
		Optional<UserEntry> userEntry = testInstance.getOperatorUserEntry(userEntered);
		//Then the an empty optional object should return
		assertNotNull(userEntry);
		assertFalse(userEntry.isPresent());
	}
	
	/**
	 * Give user entered an invalid string
	 * When the getUserInput method called
	 * Then NoSuchElementException will raised
	 * @throws Exception -- when unexpected happen.
	 */
	@Test
	public void whenUserEnteredInvalidStringThenEmptyListShouldReturn() throws Exception {
		//Given a invalid user entered string
		String userEntered = "abc";
		DefaultUserEnterFactory partialMockFactory = givenPartialMockFactory(userEntered);
		PowerMockito.doCallRealMethod().when(partialMockFactory, "constructUserEntry", Matchers.anyString());
		PowerMockito.doCallRealMethod().when(partialMockFactory, "getOperatorUserEntry", Matchers.anyString());
		
		assertNotNull(partialMockFactory.getScanner());
		
		//When the getUserInput method called
		List<UserEntry> userEntries = partialMockFactory.getUserInput();
		//Then the userEntries is empty
		assertNotNull(userEntries);
		assertTrue(userEntries.isEmpty());
	}
	
	/**
	 * Give user entered an empty string
	 * When the getUserInput method called
	 * Then NoSuchElementException will raised
	 * @throws Exception -- when unexpected happen.
	 */
	@Test
	public void whenUserEnteredEmptyStringThenExceptionShouldRaised() throws Exception {
		//Given an empty user entered string
		String userEntered = "";
		DefaultUserEnterFactory partialMockFactory = givenPartialMockFactory(userEntered);
		
		assertNotNull(partialMockFactory.getScanner());
		
		//When the getUserInput method called
		try {
			partialMockFactory.getUserInput();
			fail("Program reached unexpected point!");
		}
		catch (NoSuchElementException e) {
			//Then no line element found
			accessNoLineFoundException(e);
		}
	}
	
	/**
	 * Given the user entered a Ctrl-C
	 * When UserEntryFactory received the input 
	 * Then the userEntryFactory exit
	 * @throws Exception 
	 */
	@Test
	public void whenUserEnteredCtrlCThenExist() throws Exception {
		//Given the DefaultUserEnterFactory and mock scanner 
		String userEntered = "\u0003";
		DefaultUserEnterFactory partialMockFactory = givenPartialMockFactory(userEntered);
		
		assertNotNull(partialMockFactory.getScanner());
		
		//When the getUserInput method called
		try {
			partialMockFactory.getUserInput();
			fail("Program reached unexpected point!");
		}
		catch (NoSuchElementException e) {
			//Then no line element found
			accessNoLineFoundException(e);
		}
		
	}

	private DefaultUserEnterFactory givenPartialMockFactory(String userEntered) throws Exception {
		DefaultUserEnterFactory partialMockFactory = PowerMockito.mock(DefaultUserEnterFactory.class);
		givenMockByteArrayInputStreamScanner(userEntered);
		Whitebox.setInternalState(partialMockFactory, "scanner", mockScanner);
		PowerMockito.doCallRealMethod().when(partialMockFactory, "getUserInput");
		PowerMockito.doCallRealMethod().when(partialMockFactory, "getScanner");
		return partialMockFactory;
	}
	
	private void accessNoLineFoundException(NoSuchElementException e) {
		String errorMessage = e.getMessage();
		assertNotNull(errorMessage);
		assertEquals("No line found", errorMessage);
	}
	
	private void givenMockByteArrayInputStreamScanner(String input) {
		InputStream in = RpnCalculatorTestFixture.givenByteArrayInputStream(input);
	    System.setIn(in);
	    mockScanner = new Scanner(System.in);
	}
}
