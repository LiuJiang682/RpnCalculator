package au.com.anz.rpncalculator.userenter.operator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.apache.commons.collections4.CollectionUtils;

import au.com.anz.rpncalculator.history.record.OperationRecord;
import au.com.anz.rpncalculator.storage.Storage;
import au.com.anz.rpncalculator.userenter.UserEntry;
import au.com.anz.rpncalculator.userenter.enums.OperatorsEnum;

public class Clear implements UserEntry {

	@Override
	public void execute(Storage storage) {
		List<BigDecimal> elements = new ArrayList<>();
		BigDecimal digit;
		
		try {
			while (null != (digit = storage.popDigit())) {
				elements.add(digit);
			}
		}
		catch (EmptyStackException e) {
			//Reached the end of stack
			Consumer<OperationRecord> consumer = record -> storage.pushOperationRecord(record);
			Optional<OperationRecord> record = this.getOperationRecord(elements);
			record.ifPresent(consumer);
		}
	}

	protected Optional<OperationRecord> getOperationRecord(List<BigDecimal> elements) {
		if (CollectionUtils.isNotEmpty(elements)) {
			Collections.reverse(elements);
			return Optional.of(new OperationRecord(elements, this));
		}
		return Optional.empty();
	}

	@Override
	public String getEmptyStackErrorMessage(int counter) {
		StringBuilder buf = new StringBuilder("Operator: ");
		
		buf.append(OperatorsEnum.CLEAR.getCode());
				
		buf.append(" (position: ");
		buf.append(counter);
		buf.append("): insufficient parameters");
		
		return buf.toString();
	}

}
