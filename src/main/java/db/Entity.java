package db;
import java.time.Instant;

import lombok.Getter;

@Getter
public class Entity {
	private final Long id;
	private final Instant createdAt;
	private final Instant updatedAt;

	public Entity(Long id, Instant createdAt, Instant updatedAt) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
