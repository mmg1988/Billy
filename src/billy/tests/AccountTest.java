package billy.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import billy.aggregates.account.Account;
import billy.aggregates.account.Charge;
import billy.aggregates.account.Payment;
import billy.domain.ChargeType;
import billy.domain.Currency;

public class AccountTest {

	@Test
	void testAccountBalanceStartWithZero() {
		Account account = new Account(1, Collections.emptyList());
		assertEquals(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP), account.getBalance());
	}
	
	@Test
	void testAccountBalanceAfterCharge() {
		Account account = new Account(1, Collections.emptyList());
		account.addCharge(new Charge(UUID.randomUUID(), 1, new BigDecimal(10), Currency.ARS, ChargeType.MERCADOPAGO, new Date()));
		
		assertEquals(new BigDecimal(-10).setScale(2, BigDecimal.ROUND_HALF_UP), account.getBalance());
	}
	
	@Test
	void testAccountBalanceAfterChargeAndPayment() {
		Account account = new Account(1, Collections.emptyList());
		account.addCharge(new Charge(UUID.randomUUID(), 1, new BigDecimal(10), Currency.ARS, ChargeType.MERCADOPAGO, new Date()));
		account.addPayment(new Payment(new BigDecimal(5), Currency.ARS));
		
		assertEquals(new BigDecimal(-5).setScale(2, BigDecimal.ROUND_HALF_UP), account.getBalance());
	}
	
	@Test
	void testAccountBalanceAtZeroAfterChargeAndPayment() {
		Account account = new Account(1, Collections.emptyList());
		account.addCharge(new Charge(UUID.randomUUID(), 1, new BigDecimal(10), Currency.ARS, ChargeType.MERCADOPAGO, new Date()));
		account.addPayment(new Payment(new BigDecimal(10), Currency.ARS));
		
		assertEquals(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP), account.getBalance());
	}
	
	@Test
	void testPositiveBalanceUnsupportedException() {
		Account account = new Account(1, Collections.emptyList());
		account.addCharge(new Charge(UUID.randomUUID(), 1, new BigDecimal(10), Currency.ARS, ChargeType.MERCADOPAGO, new Date()));
		
		assertThrows(UnsupportedOperationException.class, () -> {
			account.addPayment(new Payment(new BigDecimal(10.5), Currency.ARS));
	    });
	}
	
	@Test
	void testMultipleChargesAndPayments() {
		Account account = new Account(1, Collections.emptyList());
		account.addCharge(new Charge(UUID.randomUUID(), 1, new BigDecimal(10), Currency.ARS, ChargeType.MERCADOPAGO, new Date()));
		account.addCharge(new Charge(UUID.randomUUID(), 2, new BigDecimal(25.56), Currency.ARS, ChargeType.SALE, new Date()));
		account.addPayment(new Payment(new BigDecimal(30), Currency.ARS));
		account.addCharge(new Charge(UUID.randomUUID(), 3, new BigDecimal(9.12), Currency.ARS, ChargeType.CLASSIFIED, new Date()));
		account.addPayment(new Payment(new BigDecimal(5), Currency.ARS));
		
		assertEquals(new BigDecimal(-9.68).setScale(2, BigDecimal.ROUND_HALF_UP), account.getBalance());
	}
	
}
