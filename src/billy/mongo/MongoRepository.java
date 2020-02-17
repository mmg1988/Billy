package billy.mongo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoRepository<T> {

	protected MongoCollection<T> collection;
	
	public MongoRepository(MongoDatabase db, Class<T> type, String collectionName) {
		this.collection = db.getCollection(collectionName, type);
	}
}
