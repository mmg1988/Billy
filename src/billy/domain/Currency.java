package billy.domain;

public enum Currency {
	USD("USD"),
	ARS("ARS");

	private String currency;
	
	Currency(String currency) {
		this.currency = currency;
	}
	
	public String getCurrency() {
		return this.currency;
	}
}
