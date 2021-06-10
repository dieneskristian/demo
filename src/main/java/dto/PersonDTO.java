package dto;

public class PersonDTO {
	private String username;
	private String name;
	
	public PersonDTO(String username, String name) {
		this.username = username;
		this.name = name;
	}

	public String getUsername() {
		return this.username;
	}
	
	public String getName() {
		return this.name;
	}
}
