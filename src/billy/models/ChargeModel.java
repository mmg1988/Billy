package billy.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.bson.codecs.pojo.annotations.BsonIgnore;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import billy.domain.ChargeType;
import billy.domain.Currency;
import billy.domain.InvoiceUtils;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ChargeModel {

	private UUID id;
	private int period;
	private long userId;
	private long eventId;
	private BigDecimal amount;
	private Currency currency;
	private ChargeType type;
	private Date timestamp;
	private List<PaymentModel> payments = new ArrayList<>();
	
	protected ChargeModel() { }
	
	public ChargeModel(UUID id, long userId, long eventId, BigDecimal amount, Currency currency, ChargeType type, Date timestamp) {
		this.id = id;
		this.userId = userId;
		this.eventId = eventId;
		this.amount = amount;
		this.currency = currency;
		this.type = type;
		this.timestamp = timestamp;
		this.period = InvoiceUtils.getPeriod(timestamp);
	}
	
	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
	public long getEventId() {
		return eventId;
	}
	
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public Currency getCurrency() {
		return currency;
	}
	
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	public ChargeType getType() {
		return type;
	}
	
	public void setType(ChargeType type) {
		this.type = type;
	}
	
	@JsonProperty("type")
	@BsonIgnore
	public String getTypeName() {
		return type.getType();
	}
	
	public String getCategory() {
		return type.getCategory();
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public List<PaymentModel> getPayments() {
		return payments;
	}
	
	public void setPayments(List<PaymentModel> payments) {
		this.payments = payments;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}
