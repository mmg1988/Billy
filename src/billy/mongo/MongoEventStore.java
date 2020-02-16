package billy.mongo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.mongodb.MongoClient;

import static com.mongodb.client.model.Filters.*;

import billy.events.AggregateVersionRegistry;
import billy.events.Event;
import billy.events.EventStore;

public class MongoEventStore extends MongoRepository<Event> implements EventStore {
	
	@Inject
	private AggregateVersionRegistry versionRegistry;
	
	@Inject
	public MongoEventStore(MongoClient client) {
		super(client, Event.class, "events");
	}

	@Override
	public List<Event> getStream(long aggregateId) {
		List<Event> stream = new ArrayList<>();
		collection.find(eq("aggregateId", aggregateId)).into(stream);
		return stream;
	}

	@Override
	public void store(long aggregateId, List<Event> events) {
		versionRegistry.merge(aggregateId, events.get(events.size() - 1).getVersion(), x -> {
			collection.insertMany(events);
		});
	}

}
