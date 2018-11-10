package au.com.anz.rpncalculator.userenter.factory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import au.com.anz.rpncalculator.userenter.UserEntry;
import au.com.anz.rpncalculator.userenter.factory.OperatorFactory;
import au.com.anz.rpncalculator.userenter.operator.Addition;
import au.com.anz.rpncalculator.userenter.operator.Clear;
import au.com.anz.rpncalculator.userenter.operator.Division;
import au.com.anz.rpncalculator.userenter.operator.Multiplication;
import au.com.anz.rpncalculator.userenter.operator.SquareRoot;
import au.com.anz.rpncalculator.userenter.operator.Subtraction;
import au.com.anz.rpncalculator.userenter.operator.Undo;

public class OperatorFactoryTest {

	/**
	 * Given an addition string
	 * When the gerOperator method called
	 * Then the addition operator should return
	 */
	@Test
	public void whenAnAdditionStringProvidedThenTheAdditionOperatorShouldReturn() {
		//Given an addition string
		String userEntered = "+";
		//When the getOperator method called
		Optional<UserEntry> userEntry = OperatorFactory.getOperator(userEntered);
		//Then the addition operator return
		assertNotNull(userEntry);
		assertTrue(userEntry.isPresent());
		assertTrue(userEntry.get() instanceof Addition);
	}
	
	/**
	 * Given a subtract string
	 * When the gerOperator method called
	 * Then the subtract operator should return
	 */
	@Test
	public void whenASubtractStringProvidedThenTheSubtractionOperatorShouldReturn() {
		//Given a subtraction string
		String userEntered = "-";
		//When the getOperator method called
		Optional<UserEntry> userEntry = OperatorFactory.getOperator(userEntered);
		//Then the Subtraction operator return
		assertNotNull(userEntry);
		assertTrue(userEntry.isPresent());
		assertTrue(userEntry.get() instanceof Subtraction);
	}
	
	/**
	 * Given a multiplication string
	 * When the gerOperator method called
	 * Then the multiplication operator should return
	 */
	@Test
	public void whenAMultiplicationStringProvidedThenTheMultiplicationOperatorShouldReturn() {
		//Given a multiplication string
		String userEntered = "*";
		//When the getOperator method called
		Optional<UserEntry> userEntry = OperatorFactory.getOperator(userEntered);
		//Then the Multiplication operator return
		assertNotNull(userEntry);
		assertTrue(userEntry.isPresent());
		assertTrue(userEntry.get() instanceof Multiplication);
	}
	
	/**
	 * Given a division string
	 * When the gerOperator method called
	 * Then the division operator should return
	 */
	@Test
	public void whenADivisionStringProvidedThenTheDivisionOperatorShouldReturn() {
		//Given a division string
		String userEntered = "/";
		//When the getOperator method called
		Optional<UserEntry> userEntry = OperatorFactory.getOperator(userEntered);
		//Then the division operator return
		assertNotNull(userEntry);
		assertTrue(userEntry.isPresent());
		assertTrue(userEntry.get() instanceof Division);
	}
	
	/**
	 * Given a square root string
	 * When the gerOperator method called
	 * Then the square root operator should return
	 */
	@Test
	public void whenASqrtStringProvidedThenTheSquareRootOperatorShouldReturn() {
		//Given a sqrt string
		String userEntered = "sqrt";
		//When the getOperator method called
		Optional<UserEntry> userEntry = OperatorFactory.getOperator(userEntered);
		//Then the square root operator return
		assertNotNull(userEntry);
		assertTrue(userEntry.isPresent());
		assertTrue(userEntry.get() instanceof SquareRoot);
	}
	
	/**
	 * Given clear string
	 * When the gerOperator method called
	 * Then the clear operator should return
	 */
	@Test
	public void whenAClearStringProvidedThenTheClearOperatorShouldReturn() {
		//Given a clear string
		String userEntered = "clear";
		//When the getOperator method called
		Optional<UserEntry> userEntry = OperatorFactory.getOperator(userEntered);
		//Then the square root operator return
		assertNotNull(userEntry);
		assertTrue(userEntry.isPresent());
		assertTrue(userEntry.get() instanceof Clear);
	}
	
	/**
	 * Given undo string
	 * When the gerOperator method called
	 * Then the undo operator should return
	 */
	@Test
	public void whenAUndoStringProvidedThenTheUndoOperatorShouldReturn() {
		//Given a clear string
		String userEntered = "undo";
		//When the getOperator method called
		Optional<UserEntry> userEntry = OperatorFactory.getOperator(userEntered);
		//Then the square root operator return
		assertNotNull(userEntry);
		assertTrue(userEntry.isPresent());
		assertTrue(userEntry.get() instanceof Undo);
	}
	
	/**
	 * Given an invalid string
	 * When the gerOperator method called
	 * Then the empty optional object return
	 */
	@Test
	public void whenAnInvalidStringProvidedThenEmptyOptionalShouldReturn() {
		//Given an invalid string
		String userEntered = "abc";
		//When the getOperator method called
		Optional<UserEntry> userEntry = OperatorFactory.getOperator(userEntered);
		//Then the addition operator return
		assertNotNull(userEntry);
		assertFalse(userEntry.isPresent());
	}
	
	/**
	 * Given an empty string
	 * When the gerOperator method called
	 * Then the empty optional object return
	 */
	@Test
	public void whenEmptyStringProvidedThenEmptyOptionalObjectShouldReturn() {
		//Given an empty string
		String userEntered = "";
		//When the getOperator method called
		Optional<UserEntry> userEntry =
			OperatorFactory.getOperator(userEntered);
		//Then the empty optional object should return
		assertNotNull(userEntry);
		assertFalse(userEntry.isPresent());
	}
	
	/**
	 * Given a null string
	 * When the gerOperator method called
	 * Then the empty optional object should return
	 */
	@Test
	public void whenNullStringProvidedThenEmptyOptionalObjectShouldReturn() {
		//Given a null string
		String userEntered = null;
		//When the getOperator method called
		Optional<UserEntry> userEntry = OperatorFactory.getOperator(userEntered);
		//Then the empty optional object should return
		assertNotNull(userEntry);
		assertFalse(userEntry.isPresent());
	}
}
