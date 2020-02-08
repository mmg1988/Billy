package billy.events;

import java.util.List;

public interface EventStore {
	
	List<Event> getStream(long aggregateId);
	void add(long aggreagteId, List<Event> events);
	void save();
	
}
