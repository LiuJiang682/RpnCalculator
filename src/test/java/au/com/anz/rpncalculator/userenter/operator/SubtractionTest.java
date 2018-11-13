package au.com.anz.rpncalculator.userenter.operator;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import au.com.anz.rpncalculator.fixture.RpnCalculatorTestFixture;
import au.com.anz.rpncalculator.history.record.OperationRecord;
import au.com.anz.rpncalculator.storage.Storage;
import au.com.anz.rpncalculator.userenter.operator.Subtraction;

public class SubtractionTest {

	private Subtraction testInstance;
	
	@Before
	public void setUp() {
		this.testInstance = new Subtraction();
	}

	@After
	public void tearDown() {
		this.testInstance = null;
	}
	
	/**
	 * Given 2 elements in the storage
	 * When the execute method called
	 * The storage will update the result
	 */
	@Test
	public void when2ElementsProvidedThenStorageUpdateWithOneResult() {
		//Given 2 elements in the storage
		Storage mockStorage = RpnCalculatorTestFixture.givenMockStorage();
		
		//When the execute method called
		testInstance.execute(mockStorage);
		//Then the storage is updated
		verify(mockStorage).pushDigit(Matchers.eq(new BigDecimal(4)));
		verify(mockStorage, times(2)).popDigit();
		verify(mockStorage).pushOperationRecord(Matchers.any(OperationRecord.class));
	}
	
	/**
	 * Given 2 elements
	 * When the getOperationRecord method called
	 * Then an OperationRecord object should return
	 */
	@Test
	public void when2ElementsProvidedThenAnOperationRecordShouldReturn() {
		//Given 2 elements in the storage
		//When the getOperaitonRecord method called
		OperationRecord record = this.testInstance.getOperationRecord(new BigDecimal(2), new BigDecimal(6));
		//Then an OperationRecord object should return
		assertThat(record, is(notNullValue()));
		assertThat(record.getParameters().size(), is(equalTo(2)));
		assertThat(this.testInstance, is(equalTo(record.getOperator())));
		assertThat(record.getParameters().get(0), is(equalTo(new BigDecimal(6))));
		assertThat(record.getParameters().get(1), is(equalTo(new BigDecimal(2))));
	}
	
	/**
	 * Given the testInstance
	 * When the getEmptyStackErrorMessage method called
	 * Then the correct error message should return
	 */
	@Test
	public void shouldReturnCorrectErrorMessage() {
		//Given
		int counter = 1;
		//When
		String message = this.testInstance.getEmptyStackErrorMessage(counter);
		//Then
		assertThat(message, is(notNullValue()));
		assertThat(message, is(equalTo("Operator: - (position: 1): insufficient parameters")));
	}
	
}
