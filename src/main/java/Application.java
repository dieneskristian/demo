import java.sql.Connection;
import java.sql.Statement;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import command.Command;
import command.CommandReader;
import command.DeleteAllCommand;
import command.InsertCommand;
import command.PrintCommand;
import db.DatabaseConnectionProvider;
import dto.InsertPersonRequestDTO;

public class Application {
	
	private final static Logger logger = Logger.getLogger(Application.class);
	
	public static void main(String[] args) {
		try {
			//init app
			logger.info("App init started");
			Connection connection = DatabaseConnectionProvider.getConnection();
			Statement stmt = connection.createStatement();
	        String dropTableSql = "drop table PERSON";
	        stmt.executeUpdate(dropTableSql);
	        logger.info("Dropped table PERSON");
	        String createTableSql = "create table PERSON " +
	                   "(ID integer not null, " +
	                   " USERNAME VARCHAR(255), " + 
	                   " NAME VARCHAR(255), " + 
	                   " CREATED_AT DATETIME, " + 
	                   " UPDATED_AT DATETIME, " + 
	                   " PRIMARY KEY ( id ))"; 
	        stmt.executeUpdate(createTableSql);
	        logger.info("Created PERSON DB table");
	        stmt.close();
	        logger.info("App init succefull");
	        
	        //demo data
		    InsertPersonRequestDTO insertFirstPersonDTO = new InsertPersonRequestDTO(1L, "a1", "Dusan");
		    InsertPersonRequestDTO insertSecondPersonDTO = new InsertPersonRequestDTO(2L, "a2", "Fero");
		    Command insertFirstPersonCommand = new InsertCommand(insertFirstPersonDTO);
		    Command insertSecondPersonCommand = new InsertCommand(insertSecondPersonDTO);
		    Command printAllCommand = new PrintCommand();
		    Command deleteAllCommand = new DeleteAllCommand();    
		    BlockingQueue<Command> queue = new LinkedBlockingQueue<>();
		    queue.add(insertFirstPersonCommand);
		    queue.add(insertSecondPersonCommand);
		    queue.add(printAllCommand);
		    queue.add(deleteAllCommand);
		    Thread thread = new Thread(new CommandReader(queue));
		    thread.start();
		    //put thread to sleep to demonstrate a new command execution
		    Thread.sleep(2000);
		    logger.info("Waiting 2000ms to add another print command");
		    queue.add(printAllCommand);
		} catch (Exception e) {
			logger.error("App init failed", e);
		}
	    
	}

}
