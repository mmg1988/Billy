package billy.domain;

public enum ChargeType {
	CLASSIFIED("CLASIFICADO", "MARKETPLACE"),
	SALE("VENTA", "MARKETPLACE"),
	ADVERTISING("PUBLICIDAD", "SERVICIOS"),
	SHIPPING("ENVIO", "MARKETPLACE"),
	CREDIT("CREDITO", "SERVICIOS"),
	MERCADOPAGO("MERCADOPAGO", "EXTERNO"),
	MERCADOSHOP("MERCADOSHOP", "EXTERNO"),
	FIDELITY("FIDELIDAD", "SERVICIOS");

	private String type;
	private String category;
	
	ChargeType(String type, String category) {
		this.type = type;
		this.category = category;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getCategory() {
		return this.category;
	}
}
