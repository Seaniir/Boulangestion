package controller;

import java.util.List;

public interface IDao<T> {
	//CRUD:
	public void create(T object);
	public List<T> read();
	public void update(T object, int idObject);
	public void delete(int idToDelete);
}
