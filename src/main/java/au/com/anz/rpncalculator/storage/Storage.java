package au.com.anz.rpncalculator.storage;

import java.math.BigDecimal;

import au.com.anz.rpncalculator.history.record.OperationRecord;

public interface Storage {

	public void pushDigit(BigDecimal userEntry);
	public BigDecimal popDigit();
	public void printStack();
	
	public void pushOperationRecord(OperationRecord record);
	public OperationRecord popOperationRecord();
	public int getDigitsStackSize();
}
