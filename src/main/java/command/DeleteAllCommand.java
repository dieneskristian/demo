package command;

import org.apache.log4j.Logger;

import db.PersonRepository;

public class DeleteAllCommand extends Command {
	
	private final static Logger logger = Logger.getLogger(DeleteAllCommand.class);

	public DeleteAllCommand() {
		super(CommandType.DELETE_ALL);
	}

	@Override
	public void execute() {
		PersonRepository.getInstance().deleteAll();
		logger.info("Wiped PERSON table");
	}

}
