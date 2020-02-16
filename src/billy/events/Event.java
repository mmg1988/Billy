package billy.events;

import java.util.Date;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator
public abstract class Event {

	private long aggregateId;
	private Date timestamp;
	private int version;
	
	protected Event() {
	}
	
	public Event(long aggregateId, Date timestamp, int version) {
		this.aggregateId = aggregateId;
		this.timestamp = timestamp;
		this.version = version;
	}

	public long getAggregateId() {
		return aggregateId;
	}

	public void setAggregateId(long aggregateId) {
		this.aggregateId = aggregateId;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
