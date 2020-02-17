package billy.resources.accounts;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import java.math.BigDecimal;

import javax.inject.Inject;

import com.mongodb.client.MongoDatabase;

import billy.models.AccountModel;
import billy.mongo.MongoRepository;
import billy.resources.UserNotFoundException;

public class AccountRepository extends MongoRepository<AccountModel> {
	
	@Inject
	public AccountRepository(MongoDatabase db) {
		super(db, AccountModel.class, "accounts");
	}

	public AccountModel get(long id) {
		AccountModel account = collection.find(eq("userId", id)).first();
		if (account == null)
			throw new UserNotFoundException();
		
		return account;
	}
	
	public void add(AccountModel account) {
		collection.insertOne(account);
	}
	
	public void updateBalance(long id, BigDecimal balance) {
		collection.updateOne(eq("userId", id), set("balance", balance));
	}
}
