package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import db.Person;
import db.PersonRepository;
import dto.InsertPersonRequestDTO;

public class InsertCommandTest {
	
	@AfterEach
	void cleanUpAfterEachTest(){
	    PersonRepository.getInstance().deleteAll();
	}

	
	@Test
    void insertCommandExecutedTest() {
		InsertPersonRequestDTO dto = new InsertPersonRequestDTO(1L, "dienes", "Kristian");
		Command c = new InsertCommand(dto);
		c.execute();
		Set<Person> persons = PersonRepository.getInstance().findAll();
		assertEquals(1, persons.size());
    }
	
	@Test
    void insertCommandCorrectDataInsertedTest() {
		InsertPersonRequestDTO dto = new InsertPersonRequestDTO(1L, "dienes", "Kristian");
		Command c = new InsertCommand(dto);
		c.execute();
		Set<Person> persons = PersonRepository.getInstance().findAll();
		assertEquals(1, persons.size());
		assertEquals("Kristian", persons.iterator().next().getName());
		assertEquals("dienes", persons.iterator().next().getUsername());
    }
	
	@Test
    void valueWithSameIdIsNotInsertedSecondTimeTest() {
		InsertPersonRequestDTO dto = new InsertPersonRequestDTO(1L, "dienes", "Kristian");
		Command c = new InsertCommand(dto);
		c.execute();
		InsertPersonRequestDTO dto2 = new InsertPersonRequestDTO(1L, "dienes", "Kristian");
		Command c2 = new InsertCommand(dto2);
		c2.execute();
		Set<Person> persons = PersonRepository.getInstance().findAll();
		assertEquals(1, persons.size());
    }

}
