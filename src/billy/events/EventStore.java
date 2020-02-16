package billy.events;

import java.util.List;

public interface EventStore {
	
	List<Event> getStream(long aggregateId);
	void store(long aggreagteId, List<Event> events);
	
}
