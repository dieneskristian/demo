package command;

public abstract class Command {
	
	private final CommandType type;
	
	public Command(CommandType type) {
		this.type = type;
	}
	
	public abstract void execute();
}
