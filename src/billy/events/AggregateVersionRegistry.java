package billy.events;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class AggregateVersionRegistry {

    private final Map<Long, Integer> versions = new ConcurrentHashMap<>();
    
    public void merge(long aggregateId, int version, Consumer<Void> callback) {
    	versions.compute(aggregateId, (k, oldVersion) -> {
			if (oldVersion != null && version <= oldVersion)
				throw new OptimisticLockingException();
			
			callback.accept(null);
			return version;
    	});
    }
}
