package au.com.anz.rpncalculator.history.record;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import au.com.anz.rpncalculator.fixture.RpnCalculatorTestFixture;
import au.com.anz.rpncalculator.history.record.OperationRecord;
import au.com.anz.rpncalculator.userenter.UserEntry;
import au.com.anz.rpncalculator.userenter.operator.SquareRoot;

public class OperationRecordTest {

	/**
	 * Given the parameters
	 * When the constructor called
	 * Then the OperationRecord object should return
	 */
	@Test
	public void whenContructorCalledThenObjectShouldReturn() {
		//Given the parameters
		List<BigDecimal> params = RpnCalculatorTestFixture.getOperationParameters();
		UserEntry operator = new SquareRoot();
		//When the constructor called
		OperationRecord record = new OperationRecord(params, operator);
		//Then the object should return
		assertNotNull(record);
		assertEquals(params, record.getParameters());
		assertEquals(operator, record.getOperator());
	}

	/**
	 * Given the parameters but no operator
	 * When the constructor called
	 * Then the OperationRecord object should return
	 */
	@Test
	public void whenContructorCalledWithoutOperatorThenObjectShouldReturn() {
		//Given the parameters
		List<BigDecimal> params = RpnCalculatorTestFixture.getOperationParameters();
		UserEntry operator = null;
		//When the constructor called
		OperationRecord record = new OperationRecord(params, operator);
		//Then the object should return
		assertNotNull(record);
		assertEquals(params, record.getParameters());
		assertNull(record.getOperator());
	}
	
	/**
	 * Given the null parameters
	 * When the constructor called
	 * Then the OperationRecord object should return
	 */
	@Test
	public void whenContructorCalledWithNullParamsThenExceptionRaised() {
		//Given the  null parameters
		List<BigDecimal> params = null;
		UserEntry operator = null;
		//When the constructor called
		try {
			new OperationRecord(params, operator);
			fail("Program reached unexpecte point!");
		}
		catch (IllegalArgumentException e) {
			//Then the exception raise
			String message = e.getMessage();
			assertNotNull(message);
			assertEquals("parameters cannot be null", message);
		}
	}
}
