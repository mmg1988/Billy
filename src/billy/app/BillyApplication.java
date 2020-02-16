package billy.app;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.mongodb.MongoClient;

import billy.commands.CommandDispatcher;
import billy.commands.CommandHandlerFactory;
import billy.commands.CommandHandlerRegistry;
import billy.commands.account.ApplyChargeCommand;
import billy.commands.account.ApplyChargeCommandHandler;
import billy.commands.account.ApplyPaymentCommand;
import billy.commands.account.ApplyPaymentCommandHandler;
import billy.eventhandlers.account.AccountCreatedEventHandler;
import billy.eventhandlers.account.ChargeAppliedEventHandler;
import billy.eventhandlers.account.PaymentAppliedEventHandler;
import billy.events.AggregateVersionRegistry;
import billy.events.EventRepository;
import billy.events.EventStore;
import billy.mongo.MongoEventStore;
import billy.resources.OptimisticLockingExceptionMapper;
import billy.resources.UnsupportedOperationExceptionMapper;
import billy.resources.accounts.AccountRepository;
import billy.resources.accounts.AccountsResource;
import billy.resources.charges.ChargeRepository;
import billy.resources.charges.ChargesResource;
import billy.resources.invoices.InvoiceQueries;
import billy.resources.invoices.InvoicesResource;
import billy.resources.payments.PaymentRepository;
import billy.resources.payments.PaymentsResource;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

@ApplicationPath("")
public class BillyApplication extends ResourceConfig {

    ServiceLocator serviceLocator;
    
	@SuppressWarnings("serial")
	public BillyApplication() {
		MongoClient mongoClient = new MongoClient();
		EventBus eventBus = new AsyncEventBus(newSingleThreadExecutor());
        Map<Class<?>, Class<?>> commandHandlers = new HashMap<Class<?>, Class<?>>() {
        	{
        		put(ApplyChargeCommand.class, ApplyChargeCommandHandler.class);
        		put(ApplyPaymentCommand.class, ApplyPaymentCommandHandler.class);
        	}
        };

        register(new ContainerLifecycleListener()
        {
            public void onStartup(Container container)
            {
                serviceLocator = container.getApplicationHandler().getServiceLocator();

                eventBus.register(new AccountCreatedEventHandler(serviceLocator));
                eventBus.register(new ChargeAppliedEventHandler(serviceLocator));
                eventBus.register(new PaymentAppliedEventHandler(serviceLocator));
            }

            public void onReload(Container container) {/*...*/}
            public void onShutdown(Container container) {
            	mongoClient.close();
        	}
        });
        
        // exceptions
        register(OptimisticLockingExceptionMapper.class);
        register(UnsupportedOperationExceptionMapper.class);
        
        // resources
		register(ChargesResource.class);
		register(PaymentsResource.class);
		register(InvoicesResource.class);
		register(AccountsResource.class);
		
		register(new AbstractBinder() {
            @Override
            protected void configure() {
            	// commands
                bindAsContract(CommandDispatcher.class).in(Singleton.class);
                commandHandlers.values().forEach(x -> {
                	bindAsContract(x).in(RequestScoped.class);;
                });
                bind(new CommandHandlerRegistry(commandHandlers)).to(CommandHandlerRegistry.class);
                bindAsContract(CommandHandlerFactory.class).in(Singleton.class);
                
                // events
                bindAsContract(AggregateVersionRegistry.class).in(Singleton.class);
                bindAsContract(EventRepository.class).in(RequestScoped.class);
                bind(MongoEventStore.class).to(EventStore.class).in(RequestScoped.class);
                bind(eventBus).to(EventBus.class);
                
                // repositories
                bind(mongoClient).to(MongoClient.class);
                bindAsContract(ChargeRepository.class).in(RequestScoped.class);
                bindAsContract(PaymentRepository.class).in(RequestScoped.class);
                bindAsContract(AccountRepository.class).in(RequestScoped.class);

                bindAsContract(InvoiceQueries.class).in(RequestScoped.class);
            }
        });
	}
}
