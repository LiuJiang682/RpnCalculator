package au.com.anz.rpncalculator.userenter.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import au.com.anz.rpncalculator.history.record.OperationRecord;
import au.com.anz.rpncalculator.storage.Storage;
import au.com.anz.rpncalculator.userenter.UserEntry;

public class DigitalUserEntry implements UserEntry {

	private BigDecimal digits;
	
	public DigitalUserEntry(final String userEntered) {
		this.digits = new BigDecimal(userEntered);
	}
	
	@Override
	public void execute(Storage storage) {
		storage.pushDigit(digits);
		OperationRecord record = toOperationRecord().apply(digits);
		storage.pushOperationRecord(record);
	}

	static Function<BigDecimal, OperationRecord> toOperationRecord() {
		return d -> {List<BigDecimal> params = Arrays.asList(d);
			return new OperationRecord(params, null);
		};
	}

}
