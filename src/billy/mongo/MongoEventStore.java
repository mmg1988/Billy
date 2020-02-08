package billy.mongo;

import java.util.List;

import billy.events.Event;
import billy.events.EventStore;

public class MongoEventStore implements EventStore {

	@Override
	public List<Event> getStream(long aggregateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(long aggregateId, List<Event> events) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void save() {
		
	}

}
