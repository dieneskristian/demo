package command;

import org.apache.log4j.Logger;

import db.Person;
import db.PersonRepository;
import dto.InsertPersonRequestDTO;

public class InsertCommand extends Command {
	
	private final static Logger logger = Logger.getLogger(InsertCommand.class);
	
	private Long id;
	private String username;
	private String name;

	public InsertCommand(InsertPersonRequestDTO dto) {
		super(CommandType.INSERT);
		this.id = dto.getId();
		this.username = dto.getUsername();
		this.name = dto.getName();
	}

	@Override
	public void execute() {
		Person person = new Person(id, username, name);
		PersonRepository.getInstance().save(person);
		logger.info("Inserted PERSON with ID: " + id + " USERNAME: " + username +" NAME: "+name);
	}

}
