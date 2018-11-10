package au.com.anz.rpncalculator;

import java.io.InputStream;
import java.util.EmptyStackException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import au.com.anz.rpncalculator.storage.DefaultStorage;
import au.com.anz.rpncalculator.storage.Storage;
import au.com.anz.rpncalculator.userenter.UserEnter;
import au.com.anz.rpncalculator.userenter.UserEntry;
import au.com.anz.rpncalculator.userenter.enums.OperatorsEnum;
import au.com.anz.rpncalculator.userenter.factory.DefaultUserEnterFactory;

public class RpnCalculator {

	private static final int ONE = 1;
	private UserEnter userEnter;
	private Storage storage;
	
	public RpnCalculator() {
		this(System.in);
	}
	
	public RpnCalculator(InputStream in) {
		if (null == in) {
			throw new IllegalArgumentException("InputStream cannot be null!");
		}
		
		this.userEnter = new DefaultUserEnterFactory(in);
		this.storage = new DefaultStorage();
	}
	
	public void run() {
		List<UserEntry> userEntries = null;
		AtomicInteger counter = new AtomicInteger(ONE);
		while( null != (userEntries = this.userEnter.getUserInput())) {		
			for(UserEntry e : userEntries) {
				try {
					e.execute(this.storage);
					counter.incrementAndGet();
				}
				catch (EmptyStackException ese) {
					System.err.println(formatErrorMessage(e, counter.get()));
					break;
				}
			}
			storage.printStack();
		}
	}

	protected String formatErrorMessage(UserEntry e, int counter) {
		StringBuilder buf = new StringBuilder("Operator: ");
		switch (e.getClass().getSimpleName()) {
			case "Addition" :
				buf.append(OperatorsEnum.ADDITION.getCode());
				break;
			case "Subtraction" :
				buf.append(OperatorsEnum.SUBTRACTION.getCode());
				break;
			case "Multiplication" :
				buf.append(OperatorsEnum.MULTIPLICATION.getCode());
				break;
			case "Division" :
				buf.append(OperatorsEnum.DIVISION.getCode());
				break;
			case "SquareRoot" :
				buf.append(OperatorsEnum.SQUAREROOT.getCode());
				break;
			case "Clear" :
				buf.append(OperatorsEnum.CLEAR.getCode());
				break;
			case "Undo" :
				buf.append(OperatorsEnum.UNDO.getCode());
				break;
			default:
				buf.append(e.getClass().getSimpleName());
		}
		buf.append(" (position: ");
		buf.append(counter);
		buf.append("): insufficient parameters");
		
		return buf.toString();
	}

	public Storage getStorage() {
		return storage;
	}

	public static void main(String[] argv) {
		new RpnCalculator().run();
	}
}
