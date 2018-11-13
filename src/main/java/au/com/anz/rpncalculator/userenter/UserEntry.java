package au.com.anz.rpncalculator.userenter;

import au.com.anz.rpncalculator.storage.Storage;

public interface UserEntry {

	public int DECIMAL_PLACES = 15;

	void execute(Storage storage);
	
	String getEmptyStackErrorMessage(final int counter);
}
