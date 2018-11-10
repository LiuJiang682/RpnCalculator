package au.com.anz.rpncalculator.userenter.operator;

import java.math.BigDecimal;

import au.com.anz.rpncalculator.history.record.OperationRecord;
import au.com.anz.rpncalculator.storage.Storage;

public class Subtraction extends BiOperator {

	@Override
	protected void performDetailOperation(Storage storage) {
		BigDecimal first = storage.popDigit();
		BigDecimal second = storage.popDigit();
		BigDecimal result = second.subtract(first);
		storage.pushDigit(result);
		OperationRecord record = this.getOperationRecord(first, second);
		storage.pushOperationRecord(record);
	}

}
