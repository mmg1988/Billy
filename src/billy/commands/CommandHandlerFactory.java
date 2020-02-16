package billy.commands;

import javax.inject.Inject;

import org.glassfish.hk2.api.ServiceLocator;

public class CommandHandlerFactory {

	private ServiceLocator serviceLocator;
	private CommandHandlerRegistry commandHandlersRegistry;
	
	@Inject
	public CommandHandlerFactory(ServiceLocator serviceLocator, CommandHandlerRegistry commandHandlersRegistry) {
		this.serviceLocator = serviceLocator;
		this.commandHandlersRegistry = commandHandlersRegistry;
	}
	
	@SuppressWarnings("unchecked")
	<TResponse> CommandHandler<Command<TResponse>, TResponse> create(Command<TResponse> command) {
		return (CommandHandler<Command<TResponse>, TResponse>) serviceLocator.createAndInitialize(commandHandlersRegistry.get(command.getClass()));
	}
	
}
