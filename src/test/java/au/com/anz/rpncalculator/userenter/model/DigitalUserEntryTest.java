package au.com.anz.rpncalculator.userenter.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

import au.com.anz.rpncalculator.RpnCalculator;
import au.com.anz.rpncalculator.history.record.OperationRecord;
import au.com.anz.rpncalculator.storage.Storage;
import au.com.anz.rpncalculator.userenter.model.DigitalUserEntry;

/**
 * In order to perform reverse polish notation calculation
 * As a RPN calculator operator, I like to
 * able to construct the DigitalUserEntry object.
 */
public class DigitalUserEntryTest {

	/**
	 * Given a digital string when the constructor called Then the object should
	 * return
	 */
	@Test
	public void whenADigitalStringProvidedThanADigitalUserEntryObjectShouldConstruct() {
		// Given a digital string
		String userEntered = "123";
		// when the constructor called
		DigitalUserEntry userEntry = new DigitalUserEntry(userEntered);
		// Then the object should return
		assertNotNull(userEntry);
	}

	// Given an invalid string
	// when the constructor called
	// Then a NumberFormateException raised
	@Test
	public void whenAnInvalidStringprovidedThenAnExceptionRaised() {
		// Given an invalid string
		String userEntered = "abc";
		// when the constructor called
		try {
			new DigitalUserEntry(userEntered);
			fail("Program reached unexpected point!");
		} catch (Exception e) {
			// Then a NumberFormateException raised
			assertTrue(e instanceof NumberFormatException);
		}
	}

	// Given an empty string
	// when the constructor called
	// Then a NumberFormateException raised
	@Test
	public void whenAnEmptyStringprovidedThenAnExceptionRaised() {
		// Given an empty string
		String userEntered = "";
		// when the constructor called
		try {
			new DigitalUserEntry(userEntered);
			fail("Program reached unexpected point!");
		} catch (Exception e) {
			// Then a NumberFormateException raised
			assertTrue(e instanceof NumberFormatException);
		}
	}

	// Given a null string
	// when the constructor called
	// Then a NullPointerException raised
	@Test
	public void whenANullStringprovidedThenAnExceptionRaised() {
		// Given a null string
		String userEntered = null;
		// when the constructor called
		try {
			new DigitalUserEntry(userEntered);
			fail("Program reached unexpected point!");
		} catch (Exception e) {
			// Then a NullPointerException raised
			assertTrue(e instanceof NullPointerException);
		}
	}
	
	//Given a mock calculator
	//When the execute method called
	//Then the push method of stack called
	@Test
	public void whenMockCalculatorProvidedThenThePushMethodOfStackCalled() throws Exception {
		//Given mock calculator
		RpnCalculator mockCalculator = PowerMockito.mock(RpnCalculator.class);
		Storage mockStorage = PowerMockito.mock(Storage.class);
		Whitebox.setInternalState(mockCalculator, "storage", mockStorage);
	
		DigitalUserEntry testInstance = new DigitalUserEntry("6");
		
		//When the execute method called
		testInstance.execute(mockStorage);
		//Then the push method of stack called
		ArgumentCaptor<BigDecimal> digitCaptor = ArgumentCaptor.forClass(BigDecimal.class);
		verify(mockStorage);
		mockStorage.pushDigit(digitCaptor.capture());
		List<BigDecimal> allValues = digitCaptor.getAllValues();
		assertNotNull(allValues);
		assertTrue(1 == allValues.size());
		BigDecimal retrieve = allValues.get(0);
		assertNotNull(retrieve);
		assertEquals(new BigDecimal(6), retrieve);
	}
	
	/**
	 * Given the BigDecimal
	 * When the toOperaitonRecord function called
	 * Then an OperationRecord object should return
	 */
	@Test
	public void whenBigDecimalProvidedThenAnOperationRecordShouldReturn() {
		//Given the BigDecimal
		BigDecimal  digit = BigDecimal.ONE;
		//When the toOperationRecord function called
		OperationRecord record = DigitalUserEntry.toOperationRecord().apply(digit);
		//Then an OperationRecord object should return
		assertNotNull(record);
		assertEquals(digit, record.getParameters().get(0));
		assertNull(record.getOperator());
	}
}
