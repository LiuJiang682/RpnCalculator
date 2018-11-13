package au.com.anz.rpncalculator.userenter.operator;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.EmptyStackException;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;

import au.com.anz.rpncalculator.fixture.RpnCalculatorTestFixture;
import au.com.anz.rpncalculator.history.record.OperationRecord;
import au.com.anz.rpncalculator.storage.Storage;

public class ClearTest {

	private Clear testInstance;
	
	@Before
	public void setUp() {
		this.testInstance = new Clear();
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
	public void when2ElementsProvidedThenStorageShouldClear() {
		//Given 2 elements in the storage
		Storage mockStorage = PowerMockito.mock(Storage.class);
		final Boolean[] flags = {true, true};
		PowerMockito.when(mockStorage.popDigit()).thenAnswer(new Answer<BigDecimal>() {

			@Override
			public BigDecimal answer(InvocationOnMock invocation) throws Throwable {
				Boolean flag0 = flags[0];
				Boolean flag1 = flags[1];
				if (flag0) {
					flags[0] = false;
					return BigDecimal.TEN;
				}
				if (flag1) {
					flags[1] = false;
					return BigDecimal.ONE;
				}
				throw new EmptyStackException();
			}
			
		});
		
		//When the execute method called
		testInstance.execute(mockStorage);
		//Then the storage is updated
		verify(mockStorage, times(3)).popDigit();
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
		Optional<OperationRecord> recordOptional = this.testInstance.getOperationRecord(RpnCalculatorTestFixture.get2OperationParameters());
		//Then an OperationRecord object should return
		assertThat(recordOptional, is(notNullValue()));
		assertTrue(recordOptional.isPresent());
		OperationRecord record = recordOptional.get();
		assertThat(record.getParameters().size(), is(equalTo(2)));
		assertThat(this.testInstance, is(equalTo(record.getOperator())));
		assertThat(record.getParameters().get(0), is(equalTo(new BigDecimal(2))));
		assertThat(record.getParameters().get(1), is(equalTo(new BigDecimal(6))));
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
		assertThat(message, is(equalTo("Operator: clear (position: 1): insufficient parameters")));
	}
}
