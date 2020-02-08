package billy.aggregates;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import billy.events.Event;

public abstract class Aggregate {
	
	private long id;
	private int version;
	private List<Event> pendingEvents;
	
	public Aggregate(long id) {
		this(id, Collections.emptyList());
	}
	
	public Aggregate(long id, List<Event> events) {
		this.id = id;
		events.forEach(e -> {
			apply(e);
			this.version = e.getVersion();
		});
		this.pendingEvents = new ArrayList<>();
	}
	
	protected void addEvent(Event event) {
		apply(event);
		pendingEvents.add(event);
	}

    private void apply(Event event) {
    	try {
	        Method method = this.getClass().getDeclaredMethod("apply", event.getClass());
	        method.setAccessible(true);
	        method.invoke(this, event);
    	} catch (Exception e) {
    		throw new UnsupportedOperationException();
    	}
    }
	
	public long getId() {
		return id;
	}
	
	public List<Event> getPendingEvents() {
		return pendingEvents;
	}
	
	public int getVersion() {
		return version;
	}
}
