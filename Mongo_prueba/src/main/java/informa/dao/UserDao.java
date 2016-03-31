package informa.dao;

import java.util.List;

import informa.model.User;

public interface UserDao {
	  	public void addUser( User user );

	    public void updateUser( User user );

	    public void removeAll();
	    public void removeUser( User user );
	    public void removeUserByLastName( String lastName );

	    public List<User> findAll();
	    public List<User> findByLastName( String lastName );
	    public List<User> findByFirstNameAndLastName( String firstName, String lastName );
	    public List<User> findByAgeLessThan( int age );
	    public List<User> findByAgeGreaterThan( int age );
}
