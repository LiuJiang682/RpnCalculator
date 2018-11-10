package au.com.anz.rpncalculator.userenter.factory;

import java.util.Optional;

import au.com.anz.rpncalculator.userenter.UserEntry;
import au.com.anz.rpncalculator.userenter.enums.OperatorsEnum;
import au.com.anz.rpncalculator.userenter.operator.Addition;
import au.com.anz.rpncalculator.userenter.operator.Clear;
import au.com.anz.rpncalculator.userenter.operator.Division;
import au.com.anz.rpncalculator.userenter.operator.Multiplication;
import au.com.anz.rpncalculator.userenter.operator.SquareRoot;
import au.com.anz.rpncalculator.userenter.operator.Subtraction;
import au.com.anz.rpncalculator.userenter.operator.Undo;

public class OperatorFactory {

	public static Optional<UserEntry> getOperator(final String userEntered) {
		Optional<UserEntry> userEntry = Optional.empty();
		
		try {
			OperatorsEnum operator = OperatorsEnum.fromString(userEntered);
			switch (operator) {
				case ADDITION:
					userEntry = Optional.of(new Addition());
					break;
				case SUBTRACTION:
					userEntry = Optional.of(new Subtraction());
					break;
				case MULTIPLICATION:
					userEntry = Optional.of(new Multiplication());
					break;
				case DIVISION:
					userEntry = Optional.of(new Division());
					break;
				case SQUAREROOT:
					userEntry = Optional.of(new SquareRoot());
					break;
				case CLEAR:
					userEntry = Optional.of(new Clear());
					break;
				case UNDO:
					userEntry = Optional.of(new Undo());
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		return userEntry;
	}
}
