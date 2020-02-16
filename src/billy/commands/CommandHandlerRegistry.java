package billy.commands;

import java.util.Map;

public class CommandHandlerRegistry {

	private Map<Class<?>, Class<?>> commandHandlers;
	
	public CommandHandlerRegistry(Map<Class<?>, Class<?>> commandHandlers) {
		this.commandHandlers = commandHandlers;
	}
	
	public Class<?> get(Class<?> commandClass) {
		return this.commandHandlers.get(commandClass);
	}
}
