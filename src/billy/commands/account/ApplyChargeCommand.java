package billy.commands.account;

import java.math.BigDecimal;
import java.util.Date;

import billy.commands.Command;
import billy.domain.ChargeType;
import billy.domain.Currency;

public class ApplyChargeCommand implements Command<Void> {

	private long eventId;
	private long userId;
	private BigDecimal amount;
	private Currency currency;
	private ChargeType type;
	private Date eventDate;
	
	public ApplyChargeCommand(long eventId, BigDecimal amount, Currency currency, long userId, ChargeType type,
			Date eventDate) {
		this.eventId = eventId;
		this.amount = amount;
		this.currency = currency;
		this.userId = userId;
		this.type = type;
		this.eventDate = eventDate;
	}

	public long getEventId() {
		return eventId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public long getUserId() {
		return userId;
	}

	public ChargeType getType() {
		return type;
	}

	public Date getEventDate() {
		return eventDate;
	}
	
}
