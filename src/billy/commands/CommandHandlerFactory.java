package billy.commands;

public class CommandHandlerFactory {
	<TResponse> CommandHandler<Command<TResponse>, TResponse> create(Command<TResponse> command) {
		//return new CommandHandler<Command<?>, ?>();
		return null;
	}
}
