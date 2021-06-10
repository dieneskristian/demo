package dto;


public class InsertPersonRequestDTO {
	private Long id;
	private String username;
	private String name;
	
	public InsertPersonRequestDTO(Long id, String username, String name) {
		this.id = id;
		this.username = username;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getName() {
		return name;
	}
}
