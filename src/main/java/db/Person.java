package db;
import java.time.Instant;

import lombok.Getter;

@Getter
public class Person extends Entity {
	
	private String username;
	private String name;
	
	public Person(Long id) {
		super(id, Instant.now(), null);
	}

	public Person(Long id, String username, String name) {
		super(id, Instant.now(), null);
		this.username = username;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

}
