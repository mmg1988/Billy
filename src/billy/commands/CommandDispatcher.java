package billy.commands;

import javax.inject.Inject;

import billy.events.OptimisticLockingException;

public class CommandDispatcher {

	private CommandHandlerFactory factory;

	@Inject
	public CommandDispatcher(CommandHandlerFactory factory) {
		this.factory = factory;
	}
	
	public <TResponse> TResponse dispatch(Command<TResponse> command) {
		CommandHandler<Command<TResponse>, TResponse> commandHandler = factory.create(command);
		for (int attempt = 1; ; ++attempt) {
			try {
				commandHandler.handle(command);	
			} catch(OptimisticLockingException ex) {
				if (attempt == 3)
					throw ex;
			}
		}
	}
}
