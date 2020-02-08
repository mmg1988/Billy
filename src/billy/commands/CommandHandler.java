package billy.commands;

public interface CommandHandler<TCommand extends Command<TResponse>, TResponse> {
	
	TResponse handle(TCommand command);
	
}
