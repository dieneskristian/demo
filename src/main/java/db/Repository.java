package db;
import java.util.Set;

public abstract class Repository {
	abstract public Set<? extends Entity> findAll();
	abstract public void save(Entity entity);
	abstract public void deleteAll();
}
