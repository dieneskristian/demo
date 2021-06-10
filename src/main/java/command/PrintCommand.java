package command;

import java.util.Set;

import org.apache.log4j.Logger;

import dto.PersonDTO;
import service.PersonServiceImpl;

public class PrintCommand extends Command {
	
	private final static Logger logger = Logger.getLogger(PrintCommand.class);

	public PrintCommand() {
		super(CommandType.PRINT);
	}

	@Override
	public void execute() {
		logger.info("Executing Print Command");
		Set<PersonDTO> persons = PersonServiceImpl.getInstance().getPersonsForPrint();
		if (persons != null && !persons.isEmpty()) {
			StringBuilder outputBuilder = new StringBuilder();
			persons.forEach(person -> outputBuilder.append(getOutputLine(person)));
			System.out.println(outputBuilder.toString());
		} else {
			System.out.print("Database is empty");
		}
	}
	
	private String getOutputLine(PersonDTO dto) {
		return dto.getUsername() + ", " + dto.getName() + "\n";
	}

}
