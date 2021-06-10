package db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * Methods in this class gracefully handle exceptions, so the command execution flow won't fail
 */
public class PersonRepository extends Repository {
	
	private static PersonRepository instance = null;
	
	private final static Logger logger = Logger.getLogger(PersonRepository.class);
	
    public static PersonRepository getInstance()
    {
        if (instance == null) {
        	instance = new PersonRepository();
        }
        return instance;
    }

	@Override
	public Set<Person> findAll() {
		Set<Person> persons = new HashSet<>();
		String selectPersonSql = "select * from PERSON";
		try (Connection connection = DatabaseConnectionProvider.getConnection();
	            Statement statement = connection.createStatement()) {
			ResultSet result = statement.executeQuery(selectPersonSql);
			while(result.next()) {
				Person person = new Person(result.getLong("ID")); 
				person.setUsername(result.getString("USERNAME"));
				person.setName(result.getString("NAME"));
				persons.add(person);
			} 
			statement.close();
		} catch (Exception e) {
			logger.error(e);
		}
		return persons;
	}

	@Override
	public void save(Entity entity) {
		String insertPersonSql = "insert into PERSON (ID, USERNAME, NAME, CREATED_AT) values (?,?,?,?)";
		try (Connection connection = DatabaseConnectionProvider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql)) {
			Person person = (Person) entity;
            preparedStatement.setLong(1, person.getId());
            preparedStatement.setString(2, person.getUsername());
            preparedStatement.setString(3, person.getName());
            preparedStatement.setTime(4, Time.valueOf(LocalTime.now()));
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
        	logger.error(e);
        }
	}
	
	@Override
	public void deleteAll() {
		String deleteAllFromPersonSql = "delete from PERSON";
		try (Connection connection = DatabaseConnectionProvider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteAllFromPersonSql)) {
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
        	logger.error(e);
        }
	}
}
