package billy.resources.charges;

import static com.mongodb.client.model.Filters.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.bson.Document;
import org.bson.types.Decimal128;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;

import billy.models.ChargeModel;
import billy.mongo.MongoRepository;

public class ChargeRepository extends MongoRepository<ChargeModel> {

	@Inject
	public ChargeRepository(MongoDatabase db) {
		super(db, ChargeModel.class, "charges");
	}

	public List<ChargeModel> getByUserId(long userId) {
		List<ChargeModel> charges = new ArrayList<>();
		collection.find(eq("userId", userId)).into(charges);
		return charges;
	}
	
	public List<ChargeModel> getByPeriod(long userId, int period) {
		List<ChargeModel> charges = new ArrayList<>();
		collection.find(and(
				eq("userId", userId),
				eq("period", period))).into(charges);
		return charges;
	}
	
	public List<ChargesSummary> getCountByPeriod(long userId) {  
		List<ChargesSummary> groups = new ArrayList<>();
		collection.withDocumentClass(Document.class).aggregate(Arrays.asList(
					Aggregates.match(eq("userId", userId)),
					Aggregates.group("$period", Accumulators.sum("count", 1), Accumulators.sum("sum", "$amount"))
				)).map(d -> {
					return new ChargesSummary(userId, (int)d.get("_id"), (int)d.get("count"), ((Decimal128)d.get("sum")).bigDecimalValue());
				})
				.into(groups);
		
		return groups;
	}
	
	public void add(ChargeModel charge) {
		collection.insertOne(charge);
	}
}
