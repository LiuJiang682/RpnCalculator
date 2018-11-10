package au.com.anz.rpncalculator.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

import au.com.anz.rpncalculator.fixture.RpnCalculatorTestFixture;
import au.com.anz.rpncalculator.history.record.OperationRecord;
import au.com.anz.rpncalculator.storage.DefaultStorage;
import au.com.anz.rpncalculator.storage.Storage;
import au.com.anz.rpncalculator.userenter.UserEntry;

/**
 * In order to perform reverse polish notation calculation As the calculator
 * operator, I like to able to storage the user enter and operation history.
 */
public class DefaultStorageTest {

	private Storage testInstance;
	
	@Before
	public void setUp() {
		this.testInstance = new DefaultStorage();
	}
	
	@After
	public void tearDown() {
		this.testInstance = null;
	}
	/**
	 * Given the user can access the DefaultStorage class 
	 * When the constructor called 
	 * Then a DefaultStorage object return
	 */
	@Test
	public void whenConstructorCalledThenAObjectShouldReturn() {
		// Given the user can access the DefaultStorage class
		// When the constructor called
		Storage storage = new DefaultStorage();
		// Then the object should return
		assertNotNull(storage);
		assertEmptyStack(storage);
	}

	/**
	 * Given the DefaultStorage object 
	 * When the push method called Then the
	 * object push into the storage
	 */
	@Test
	public void whenStorageProvidedThenPushNPopShouldWork() {
		// Given the DefaultStorage object
		assertEmptyStack(this.testInstance);
		
		BigDecimal digit = BigDecimal.ZERO;
		
		//When the push method called
		testInstance.pushDigit(digit);
		//Then the object should store in storage
		BigDecimal retrieve = testInstance.popDigit();
		assertNotNull(retrieve);
		assertEquals(digit, retrieve);
		
		assertEmptyStack(testInstance);
	}
	
	/**
	 * Given the OperationRecord
	 * When the pushOperationRecord method called
	 * Then a record is pushed into history
	 */
	@Test
	public void whenOperationRecordProvidedThenHistoryShouldHasTheRecord() {
		//Given an OperationRecord
		OperationRecord record = new OperationRecord(RpnCalculatorTestFixture.getOperationParameters(), null);
		this.assertEmptyHistory(testInstance);
		//When the pushOperationRecord method called
		testInstance.pushOperationRecord(record);
		//Then the object should store in storage
		OperationRecord retrieve = testInstance.popOperationRecord();
		assertNotNull(retrieve);
		assertEquals(record, retrieve);
				
		assertEmptyStack(testInstance);
	}
	
	/**
	 * Given the mock storage
	 * When the printStack method called
	 * Then the output to console
	 * @throws Exception 
	 */
	@Test
	public void whenMockStorageProvidedThenOputToConsole() throws Exception {
		//Given the mock storage
		DefaultStorage mockStorage = givenMockStorage();
		PowerMockito.doCallRealMethod().when(mockStorage, "printStack");
		//When the printStack method called
		mockStorage.printStack();
		//Then the output to console.
	}
	
	/**
	 * Given the mock storage
	 * When getDigitsStackSize method called
	 * Then the size should return
	 * @throws Exception 
	 */
	@Test
	public void whenMockStorageProvidedThen2ShouldReturn() throws Exception {
		//Given the mock storage
		DefaultStorage mockStorage = givenMockStorage();
		PowerMockito.doCallRealMethod().when(mockStorage, "getDigitsStackSize");
		//When getDigitsStackSize method called
		int size = mockStorage.getDigitsStackSize();
		//Then 2 should return
		assertTrue(2 == size);
	}
	
	/**
	 * Given a BigDecimal object
	 * When the format10Digits method called
	 * Then a 10 digits string should return
	 */
	@Test
	public void whenABigDecimalProvidedThen10DigitsShouldReturn() {
		//Given a BigDecimal object
		BigDecimal first = new BigDecimal(2);
		BigDecimal digit = new BigDecimal(Math.sqrt(first.doubleValue())).setScale(UserEntry.DECIMAL_PLACES, BigDecimal.ROUND_DOWN);
		//When the format10Digits method called
		String result = ((DefaultStorage)testInstance).format10Digits(digit);
		//Then 10 digits string should return
		assertNotNull(result);
		assertEquals("1.4142135382", result);
	}
	
	/**
	 * Given a BigDecimal object
	 * When the format10Digits method called
	 * Then a 10 digits string should return
	 */
	@Test
	public void whenABigDecimalProvidedThen2DigitsShouldReturn() {
		//Given a BigDecimal object
		BigDecimal digit = new BigDecimal(0.25);
		//When the format10Digits method called
		String result = ((DefaultStorage)testInstance).format10Digits(digit);
		//Then 10 digits string should return
		assertNotNull(result);
		assertEquals("0.25", result);
	}
	
	private DefaultStorage givenMockStorage() {
		DefaultStorage mockStorage = PowerMockito.mock(DefaultStorage.class);
		Stack<BigDecimal> list = new Stack<>();
		list.addAll(RpnCalculatorTestFixture.get2OperationParameters());
		Whitebox.setInternalState(mockStorage, "digitStack", list);
		return mockStorage;
	}
	
	private void assertEmptyHistory(Storage storage) {
		try {
			storage.popOperationRecord();
			fail("Program reached unexpected point!");
		} catch (Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}
	}
	
	private void assertEmptyStack(Storage storage) {
		try {
			storage.popDigit();
			fail("Program reached unexpected point!");
		} catch (Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}
	}
}
