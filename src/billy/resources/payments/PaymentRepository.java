package billy.resources.payments;

import static com.mongodb.client.model.Filters.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.bson.Document;
import org.bson.types.Decimal128;

import com.mongodb.MongoClient;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;

import billy.models.PaymentModel;
import billy.mongo.MongoRepository;

public class PaymentRepository extends MongoRepository<PaymentModel> {

	@Inject
	public PaymentRepository(MongoClient client) {
		super(client, PaymentModel.class, "payments");
	}
	
	public List<PaymentModel> getByUserId(long userId) {
		List<PaymentModel> payments = new ArrayList<>();
		collection.find(eq("userId", userId)).into(payments);
		return payments;
	}
	
	public List<PaymentModel> getByCharges(long userId, List<UUID> charges) {
		List<PaymentModel> payments = new ArrayList<>();
		collection.find(and(
				eq("userId", userId),
				in("charges", charges))).into(payments);
		return payments;
	}
	
	public List<PaymentsSummary> getCountByPeriod(long userId) {  
		List<PaymentsSummary> groups = new ArrayList<>();
		collection.withDocumentClass(Document.class).aggregate(Arrays.asList(
					Aggregates.match(eq("userId", userId)),
					Aggregates.group("$period", Accumulators.sum("count", 1), Accumulators.sum("sum", "$amount"))
				)).map(d -> new PaymentsSummary(userId, (int)d.get("_id"), (int)d.get("count"), ((Decimal128)d.get("sum")).bigDecimalValue()))
				.into(groups);
		
		return groups;
	}
	
	public void add(PaymentModel payment) {
		collection.insertOne(payment);
	}
}
