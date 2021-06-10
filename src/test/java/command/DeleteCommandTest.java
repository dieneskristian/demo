package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

import db.Person;
import db.PersonRepository;
import dto.InsertPersonRequestDTO;

public class DeleteCommandTest {
	
	@Test
    void deleteCommandExecutedTest() {
		InsertPersonRequestDTO dto = new InsertPersonRequestDTO(1L, "dienes", "Kristian");
		Command c = new InsertCommand(dto);
		c.execute();
		Set<Person> persons = PersonRepository.getInstance().findAll();
		assertEquals(1, persons.size());
		PersonRepository.getInstance().deleteAll();
		persons = PersonRepository.getInstance().findAll();
		assertEquals(0, persons.size());
    }

}
