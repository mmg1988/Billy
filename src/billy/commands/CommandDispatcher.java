package billy.commands;

public class CommandDispatcher {

	private CommandHandlerFactory factory;
	
	public <TResponse> TResponse dispatch(Command<TResponse> command) {
		return factory.create(command).handle(command);
	}
}
