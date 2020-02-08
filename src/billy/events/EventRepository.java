package billy.events;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;

import billy.aggregates.Aggregate;

public class EventRepository {

	@Inject
	private EventStore eventStore;
	
	@Inject
	private EventBus eventBus;
	
	private List<Aggregate> changedAggregates = new ArrayList<>();
	
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
		changedAggregates.add(aggregate);
	}
	
	public <T> void commit() {
		List<Event> pendingEvents = new ArrayList<>();
		changedAggregates.forEach(a -> {
			List<Event> events = a.getPendingEvents();
			pendingEvents.addAll(events);
			eventStore.add(a.getId(), events);
		});
		eventStore.save();
		pendingEvents.forEach(event -> eventBus.post(event));
	}
}
