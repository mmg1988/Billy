package billy.events;

import java.util.Date;

public abstract class Event {
	
	private long aggregateId;
	private Date timestamp;
	private int version;
	
	public Event(long aggregateId, Date timestamp, int version) {
		this.aggregateId = aggregateId;
		this.timestamp = timestamp;
		this.version = version;
	}

	public long getAggregateId() {
		return aggregateId;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public int getVersion() {
		return version;
	}
}
