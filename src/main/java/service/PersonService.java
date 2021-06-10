package service;
import java.util.Set;

import dto.PersonDTO;

public interface PersonService {
	Set<PersonDTO> getPersonsForPrint();
}
