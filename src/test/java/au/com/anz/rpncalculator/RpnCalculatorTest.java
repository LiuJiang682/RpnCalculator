package au.com.anz.rpncalculator;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

import au.com.anz.rpncalculator.RpnCalculator;
import au.com.anz.rpncalculator.history.record.OperationRecord;
import au.com.anz.rpncalculator.storage.Storage;
import au.com.anz.rpncalculator.userenter.UserEnter;
import au.com.anz.rpncalculator.userenter.UserEntry;
import au.com.anz.rpncalculator.userenter.model.DigitalUserEntry;
import au.com.anz.rpncalculator.userenter.operator.Addition;
import au.com.anz.rpncalculator.userenter.operator.Clear;
import au.com.anz.rpncalculator.userenter.operator.Division;
import au.com.anz.rpncalculator.userenter.operator.Multiplication;
import au.com.anz.rpncalculator.userenter.operator.SquareRoot;
import au.com.anz.rpncalculator.userenter.operator.Subtraction;
import au.com.anz.rpncalculator.userenter.operator.Undo;

public class RpnCalculatorTest {

	private static final String TEST_MESSAGE = "+ (position: 1 \"): insufficient parameters";
	private RpnCalculator testInstance;
	
	@Before
	public void setUp() {
		this.testInstance = new RpnCalculator();
	}
	
	@After
	public void tearDown() {
		this.testInstance = null;
	}
	
	/**
	 * Given a null input stream
	 * When the real constructor called
	 * An IllegalArgumentException raised
	 */
	@Test
	public void whenNullInputStreamProvidedThenAnIllegalArgumentExceptionRaised() {
		//Given a null input stream
		InputStream in = null;
		//When the real constructor called
		try {
			new RpnCalculator(in);
		}
		catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertNotNull(message);
			assertEquals("InputStream cannot be null!", message);
		}
	}
	
	/**
	 * Given an input stream
	 * When the real constructor called
	 * A RpnCalculator object should return
	 * @throws Throwable 
	 */
	@Test
	public void whenAnInputStreamProvidedThenARpnCalculatorObjectShouldReturn() throws Throwable {
		//Given a null input stream
		InputStream in = new FileInputStream("src/test/resources/log4j.properties");
		//When the real constructor called
		RpnCalculator rpnCalculator = new RpnCalculator(in);
		//Then a RpnCalculator object should return
		assertNotNull(rpnCalculator);
	}
	
	/**
	 * Given user can access to RpnCalculator class
	 * When the default constructor called
	 * A RpnCalculator object should return
	 * @throws Throwable 
	 */
	@Test
	public void whenNoInputStreamProvidedThenARpnCalculatorObjectShouldReturn() throws Throwable {
		//Given user can access to RpnCalculator
		//When the real constructor called
		RpnCalculator rpnCalculator = new RpnCalculator();
		//Then a RpnCalculator object should return
		assertNotNull(rpnCalculator);
	}
	
	/**
	 * Given an Addition class UserEntry object
	 * When the formatErrorMessage method called
	 * Then the "+" should return
	 */
	@Test
	public void whenAdditionUserEntryProvidedThenPlusSignShouldReturn() {
		//Given an Addition class UserEntry object
		Addition e = new Addition();
		int counter = 1;
		//When the formatErrorMessage method called
		String message = testInstance.formatErrorMessage(e, counter);
		//Then the message should contains "+"
		assertNotNull(message);
		assertEquals("Operator: + (position: 1): insufficient parameters", message);
	}
	
	/**
	 * Given an Subtraction class UserEntry object
	 * When the formatErrorMessage method called
	 * Then the "-" should return
	 */
	@Test
	public void whenSubtractionUserEntryProvidedThenPlusSignShouldReturn() {
		//Given an Subtraction class UserEntry object
		Subtraction e = new Subtraction();
		int counter = 1;
		//When the formatErrorMessage method called
		String message = testInstance.formatErrorMessage(e, counter);
		//Then the message should contains "+"
		assertNotNull(message);
		assertEquals("Operator: - (position: 1): insufficient parameters", message);
	}
	
	/**
	 * Given an Multiplication class UserEntry object
	 * When the formatErrorMessage method called
	 * Then the "*" should return
	 */
	@Test
	public void whenMultiplicationUserEntryProvidedThenPlusSignShouldReturn() {
		//Given an Multiplication class UserEntry object
		Multiplication e = new Multiplication();
		int counter = 1;
		//When the formatErrorMessage method called
		String message = testInstance.formatErrorMessage(e, counter);
		//Then the message should contains "+"
		assertNotNull(message);
		assertEquals("Operator: * (position: 1): insufficient parameters", message);
	}
	
	/**
	 * Given an Division class UserEntry object
	 * When the formatErrorMessage method called
	 * Then the "/" should return
	 */
	@Test
	public void whenDivisionUserEntryProvidedThenPlusSignShouldReturn() {
		//Given an Division class UserEntry object
		Division e = new Division();
		int counter = 1;
		//When the formatErrorMessage method called
		String message = testInstance.formatErrorMessage(e, counter);
		//Then the message should contains "+"
		assertNotNull(message);
		assertEquals("Operator: / (position: 1): insufficient parameters", message);
	}
	
	/**
	 * Given an SquareRoot class UserEntry object
	 * When the formatErrorMessage method called
	 * Then the "sqrt" should return
	 */
	@Test
	public void whenSquareRootUserEntryProvidedThenPlusSignShouldReturn() {
		//Given an SquareRoot class UserEntry object
		SquareRoot e = new SquareRoot();
		int counter = 1;
		//When the formatErrorMessage method called
		String message = testInstance.formatErrorMessage(e, counter);
		//Then the message should contains "+"
		assertNotNull(message);
		assertEquals("Operator: sqrt (position: 1): insufficient parameters", message);
	}
	
	/**
	 * Given an Clear class UserEntry object
	 * When the formatErrorMessage method called
	 * Then the "clear" should return
	 */
	@Test
	public void whenClearUserEntryProvidedThenPlusSignShouldReturn() {
		//Given an Clear class UserEntry object
		Clear e = new Clear();
		int counter = 1;
		//When the formatErrorMessage method called
		String message = testInstance.formatErrorMessage(e, counter);
		//Then the message should contains "+"
		assertNotNull(message);
		assertEquals("Operator: clear (position: 1): insufficient parameters", message);
	}
	
	/**
	 * Given an Undo class UserEntry object
	 * When the formatErrorMessage method called
	 * Then the "clear" should return
	 */
	@Test
	public void whenUndoUserEntryProvidedThenPlusSignShouldReturn() {
		//Given an Undo class UserEntry object
		Undo e = new Undo();
		int counter = 1;
		//When the formatErrorMessage method called
		String message = testInstance.formatErrorMessage(e, counter);
		//Then the message should contains "+"
		assertNotNull(message);
		assertEquals("Operator: undo (position: 1): insufficient parameters", message);
	}
	
	/**
	 * Given an UserEntry class UserEntry object
	 * When the formatErrorMessage method called
	 * Then the "object" should return
	 */
	@Test
	public void whenUserEntryUserEntryProvidedThenPlusSignShouldReturn() {
		//Given an Object class UserEntry object
		UserEntry mockUserEntry = PowerMockito.mock(UserEntry.class);
		when(mockUserEntry.getEmptyStackErrorMessage(eq(1))).thenReturn(TEST_MESSAGE);
		int counter = 1;
		//When the formatErrorMessage method called
		String message = testInstance.formatErrorMessage(mockUserEntry, counter);
		//Then the message should contains "+"
		assertNotNull(message);
		assertThat(message, equalTo(TEST_MESSAGE));
	}
	
	/**
	 * Given the mock storage and user input
	 * When the run method called
	 * Then the mock storage should be updated
	 * @throws Throwable 
	 */
	@Test
	public void whenMockStorageAndInputProvidedThenStorageShouldBeUpdated() throws Throwable {
		//Given the mock storage and user input
		RpnCalculator partialMockCalculator = PowerMockito.mock(RpnCalculator.class);
		UserEnter mockUserEnter = PowerMockito.mock(UserEnter.class);
		List<UserEntry> entries = new ArrayList<>();
		entries.add(new DigitalUserEntry("6"));
		PowerMockito.when(mockUserEnter.getUserInput()).thenReturn(entries, null);
		Whitebox.setInternalState(partialMockCalculator, "userEnter", mockUserEnter);
		Storage mockStorage = PowerMockito.mock(Storage.class);
		Whitebox.setInternalState(partialMockCalculator, "storage", mockStorage);
		PowerMockito.doCallRealMethod().when(partialMockCalculator, "run");
		
		//When the run method called
		partialMockCalculator.run();
		//Then the storage updated
		verify(mockStorage).pushDigit(Matchers.eq(new BigDecimal(6)));
		verify(mockStorage).pushOperationRecord(Matchers.any(OperationRecord.class));
	}
	
	/**
	 * Given the mock storage and user input
	 * When the run method called
	 * Then the mock storage should be updated
	 * @throws Throwable 
	 */
	@Test
	public void whenMockStorageAndInputProvidedWithExceptionThenWarningDisplay() throws Throwable {
		//Given the mock storage and user input
		RpnCalculator partialMockCalculator = PowerMockito.mock(RpnCalculator.class);
		Storage mockStorage = PowerMockito.mock(Storage.class);
		Whitebox.setInternalState(partialMockCalculator, "storage", mockStorage);
		UserEnter mockUserEnter = PowerMockito.mock(UserEnter.class);
		List<UserEntry> entries = new ArrayList<>();
		UserEntry mockEntry = PowerMockito.mock(UserEntry.class);
		PowerMockito.doThrow(new EmptyStackException()).when(mockEntry, "execute", mockStorage);
		entries.add(mockEntry);
		PowerMockito.when(mockUserEnter.getUserInput()).thenReturn(entries, null);
		Whitebox.setInternalState(partialMockCalculator, "userEnter", mockUserEnter);
		
		PowerMockito.doCallRealMethod().when(partialMockCalculator, "run");
		
		//When the run method called
		partialMockCalculator.run();
		//Then the warning display
		verify(mockStorage).printStack();
	}
	
	/**
	 * Given the RpnCalculator object
	 * When the getStorage method called
	 * Then an empty storage should return
	 */
	@Test
	public void whenRpnCalculatorObjectProvidedThenAnEmptyStorageShouldReturn() {
		//Given the RpnCalculator object
		//When the getStorage method called
		Storage storage = testInstance.getStorage();
		//Then an empty storage should return
		assertNotNull(storage);
	}

}
