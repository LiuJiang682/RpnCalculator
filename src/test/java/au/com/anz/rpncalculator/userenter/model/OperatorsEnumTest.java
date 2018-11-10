package au.com.anz.rpncalculator.userenter.model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import au.com.anz.rpncalculator.userenter.enums.OperatorsEnum;

/**
 * In order to perform reverse polish notation calculation
 * As the RPN calculator operator, I like to
 * parse the user entered string into Operator Enum.
 */
public class OperatorsEnumTest {

	/**
	 * Given a null string
	 * when the fromString method called
	 * Then an IllegalArgumentException will raised
	 */
	@Test
	public void whenNullStringProvidedThenIllegalArgumentExceptionWillRaised() {
		//give a null string
		String userEntered = null;
		//When the fromString method called
		try {
			OperatorsEnum.fromString(userEntered);
			fail("Program reached unexpected point");
		}
		catch (Exception e) {
			//Then an IllegalArgumentException raised
			assertTrue(e instanceof IllegalArgumentException);
		}
	}
	
	/**
	 * Given an empty string
	 * when the fromString method called
	 * Then an IllegalArgumentException will raised
	 */
	@Test
	public void whenAnEmptyStringProvidedThenIllegalArgumentExceptionWillRaised() {
		//give an empty string
		String userEntered = "";
		//When the fromString method called
		try {
			OperatorsEnum.fromString(userEntered);
			fail("Program reached unexpected point");
		}
		catch (Exception e) {
			//Then an IllegalArgumentException raised
			assertTrue(e instanceof IllegalArgumentException);
		}
	}
	
	/**
	 * Given an invalid string
	 * when the fromString method called
	 * Then an IllegalArgumentException will raised
	 */
	@Test
	public void whenAnInvalidStringProvidedThenIllegalArgumentExceptionWillRaised() {
		//give an invalid string
		String userEntered = "abc";
		//When the fromString method called
		try {
			OperatorsEnum.fromString(userEntered);
			fail("Program reached unexpected point");
		}
		catch (Exception e) {
			//Then an IllegalArgumentException raised
			assertTrue(e instanceof IllegalArgumentException);
		}
	}
	
	/**
	 * Given an addition string
	 * when the fromString method called
	 * Then an addition operator will return
	 */
	@Test
	public void whenAnAdditionStringProvidedThenAnAdditionOperatorShouldReturn() {
		//give an addition string
		String userEntered = "+";
		//When the fromString method called
		OperatorsEnum operator =	OperatorsEnum.fromString(userEntered);
		//Then an addition operator will return
		assertTrue(OperatorsEnum.ADDITION == operator);
	}
	
	/**
	 * Given a subtraction string
	 * when the fromString method called
	 * Then a subtraction operator will return
	 */
	@Test
	public void whenAsubtractionStringProvidedThenASubtractionOperatorShouldReturn() {
		//give a subtraction string
		String userEntered = "-";
		//When the fromString method called
		OperatorsEnum operator =	OperatorsEnum.fromString(userEntered);
		//Then a subtraction operator will return
		assertTrue(OperatorsEnum.SUBTRACTION == operator);
	}
	
	/**
	 * Given a Multiplication string
	 * when the fromString method called
	 * Then a Multiplication operator will return
	 */
	@Test
	public void whenAMultiplicationStringProvidedThenAMultiplicationOperatorShouldReturn() {
		//give a Multiplication string
		String userEntered = "*";
		//When the fromString method called
		OperatorsEnum operator =	OperatorsEnum.fromString(userEntered);
		//Then a multiplication operator will return
		assertTrue(OperatorsEnum.MULTIPLICATION == operator);
	}
	
	/**
	 * Given a Division string
	 * when the fromString method called
	 * Then a Division operator will return
	 */
	@Test
	public void whenADivisionStringProvidedThenADivisionOperatorShouldReturn() {
		//give a Division string
		String userEntered = "/";
		//When the fromString method called
		OperatorsEnum operator =	OperatorsEnum.fromString(userEntered);
		//Then a division operator will return
		assertTrue(OperatorsEnum.DIVISION == operator);
	}
	
	/**
	 * Given a SquareRoot string
	 * when the fromString method called
	 * Then a SquareRoot operator will return
	 */
	@Test
	public void whenASquareRootStringProvidedThenASquareRootOperatorShouldReturn() {
		//give a square root string
		String userEntered = "sqrt";
		//When the fromString method called
		OperatorsEnum operator =	OperatorsEnum.fromString(userEntered);
		//Then a square root operator will return
		assertTrue(OperatorsEnum.SQUAREROOT == operator);
	}
	
	/**
	 * Given an undo string
	 * when the fromString method called
	 * Then an undo operator will return
	 */
	@Test
	public void whenAnUndoStringProvidedThenAnUndoOperatorShouldReturn() {
		//give a undo string
		String userEntered = "undo";
		//When the fromString method called
		OperatorsEnum operator =	OperatorsEnum.fromString(userEntered);
		//Then an undo operator will return
		assertTrue(OperatorsEnum.UNDO == operator);
	}
	
	/**
	 * Given a clear string
	 * when the fromString method called
	 * Then an clear operator will return
	 */
	@Test
	public void whenAClearStringProvidedThenAClearOperatorShouldReturn() {
		//give a clear string
		String userEntered = "clear";
		//When the fromString method called
		OperatorsEnum operator =	OperatorsEnum.fromString(userEntered);
		//Then a clear operator will return
		assertTrue(OperatorsEnum.CLEAR == operator);
	}
}
