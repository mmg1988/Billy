package billy.resources.charges;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import com.fasterxml.jackson.databind.annotation.JsonNaming;

import billy.domain.Currency;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel
public class ApplyChargeRequest {

	private long eventId;
	private long userId;
	private BigDecimal amount;
	private Currency currency;
	private String eventType;
	private Date date;
	
	public ApplyChargeRequest() {
		
	}
	
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
	@ApiModelProperty(allowableValues = "CLASIFICADO,VENTA,PUBLICIDAD,ENVIO,CREDITO,MERCADOPAGO,MERCADOSHOP,FIDELIDAD")
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
