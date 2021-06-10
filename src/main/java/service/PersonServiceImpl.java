package service;
import java.util.Set;
import java.util.stream.Collectors;

import db.Person;
import db.PersonRepository;
import dto.PersonDTO;

public class PersonServiceImpl implements PersonService {
	
	private static PersonServiceImpl instance = null;
	
    public static PersonServiceImpl getInstance()
    {
        if (instance == null) {
        	instance = new PersonServiceImpl();
        }
        return instance;
    }
	
	public Set<PersonDTO> getPersonsForPrint() {
		Set<Person> persons = PersonRepository.getInstance().findAll();
		return assembleDTOs(persons);
	}

	private Set<PersonDTO> assembleDTOs(Set<Person> persons) {
		 return persons
		 			.stream()
		 			.map(person -> new PersonDTO(person.getUsername(), person.getName()))
		 			.collect(Collectors.toSet());
	}
}
