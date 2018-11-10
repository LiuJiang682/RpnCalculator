package au.com.anz.rpncalculator.userenter.operator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;

import au.com.anz.rpncalculator.history.record.OperationRecord;
import au.com.anz.rpncalculator.storage.Storage;
import au.com.anz.rpncalculator.userenter.UserEntry;
import au.com.anz.rpncalculator.userenter.operator.SquareRoot;

public class SquareRootTest {

	private SquareRoot testInstance;
	
	@Before
	public void setUp() {
		this.testInstance = new SquareRoot();
	}
	
	@After
	public void tearDown() {
		this.testInstance = null;
	}
	
	/**
	 * Given 1 element in the storage
	 * When the execute method called
	 * The storage will update the result
	 */
	@Test
	public void whenElementProvidedThenStorageUpdateWithOneResult() {
		//Given an element in the storage
		Storage mockStorage = PowerMockito.mock(Storage.class);
		PowerMockito.when(mockStorage.popDigit()).thenReturn(new BigDecimal(9));
		
		//When the execute method called
		testInstance.execute(mockStorage);
		//Then the storage is updated
		verify(mockStorage).pushDigit(Matchers.eq(new BigDecimal(3).setScale(UserEntry.DECIMAL_PLACES)));
		verify(mockStorage, times(1)).popDigit();
	}
	
	/**
	 * Given 1 elements in the storage
	 * When the execute method called
	 * The storage will update the result
	 */
	@Test
	public void whenNegativeElementProvidedThenStorageUpdateWithOneResult() {
		//Given a negative element in the storage
		Storage mockStorage = PowerMockito.mock(Storage.class);
		PowerMockito.when(mockStorage.popDigit()).thenReturn(new BigDecimal(-9));
		
		//When the execute method called
		testInstance.execute(mockStorage);
		//Then the storage is updated
		verify(mockStorage, times(1)).pushDigit(Matchers.eq(new BigDecimal(-9)));
		verify(mockStorage, times(1)).popDigit();
	}
	
	
	/**
	 * Given 1 element
	 * When the getOperationRecord method called
	 * Then an OperationRecord object should return
	 */
	@Test
	public void when1ElementProvidedThenAnOperationRecordShouldReturn() {
		//Given 1 element in the storage
		//When the getOperaitonRecord method called
		OperationRecord record = this.testInstance.getOperationRecord(new BigDecimal(6));
		//Then an OperationRecord object should return
		assertNotNull(record);
		assertTrue(1 ==  record.getParameters().size());
		assertEquals(this.testInstance, record.getOperator());
		assertEquals(new BigDecimal(6), record.getParameters().get(0));
	}
}
