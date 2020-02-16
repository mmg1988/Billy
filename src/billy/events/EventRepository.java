package billy.events;

import java.util.List;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;

import billy.aggregates.Aggregate;

public class EventRepository {

	@Inject
	private EventStore eventStore;
	
	@Inject
	private EventBus eventBus;
	
	public <T extends Aggregate> T get(Class<T> type, long id) {
		List<Event> events = eventStore.getStream(id);
		try {
			T aggregate = type.getDeclaredConstructor(long.class, List.class).newInstance(id, events);
			return aggregate;
		} catch(Exception e) {
	        return null;
	    }
	}
	
	public <T> void save(Aggregate aggregate) {
		List<Event> events = aggregate.getPendingEvents();
		eventStore.store(aggregate.getId(), events);
		events.forEach(event -> eventBus.post(event));
	}
}
