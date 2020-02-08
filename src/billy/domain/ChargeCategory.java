package billy.domain;

public enum ChargeCategory {
	MARKETPLACE("MARKETPALCE"),
	SERVICES("SERVICIOS"),
	EXTERNAL("EXTERNO");

	private String value;
	
	ChargeCategory(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
