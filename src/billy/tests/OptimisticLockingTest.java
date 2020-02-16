package billy.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;

import billy.events.AggregateVersionRegistry;
import billy.events.OptimisticLockingException;

public class OptimisticLockingTest {

	private final AggregateVersionRegistry versionRegistry = new AggregateVersionRegistry();
	
	@Test
	void testOptimisticLock() throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(5);

	    List<Future<Void>> futures = new ArrayList<>();
	    futures.add(
			executor.submit(() -> {
				versionRegistry.merge(1, 1, x -> { });
				return null;
			})
		);
	    futures.add(
			executor.submit(() -> {
				versionRegistry.merge(1, 1, x -> { });
				return null;
			})
		);
	    for (Future<Void> future : futures) {
	        try {
	        	future.get();
	        } catch (InterruptedException | ExecutionException ex) {
	        	assertEquals(OptimisticLockingException.class, ex.getCause().getClass());
	        }
	    }
	}
	
	
}
