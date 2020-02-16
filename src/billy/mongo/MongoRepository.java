package billy.mongo;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoRepository<T> {

	protected MongoCollection<T> collection;
	
	public MongoRepository(MongoClient client, Class<T> type, String collectionName) {
		//ClassModel<Event> eventModel = ClassModel.builder(Event.class).enableDiscriminator(true).build();
		//ClassModel<ChargeAppliedEvent> chargeEventModel = ClassModel.builder(ChargeAppliedEvent.class).enableDiscriminator(true).build();

		CodecRegistry codecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		
		MongoDatabase db = client.getDatabase("billy").withCodecRegistry(codecRegistry);
		this.collection = db.getCollection(collectionName, type);
	}
}
