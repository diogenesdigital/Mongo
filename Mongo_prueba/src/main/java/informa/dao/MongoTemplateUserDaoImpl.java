package informa.dao;

import informa.model.User;
import informa.util.MongoDBDBObjectBuilder;
import informa.util.MongoDBQueryCriteriaBuilder;
import informa.util.MongoDBTemplate;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;

public class MongoTemplateUserDaoImpl implements UserDao {

	  /**
     * The MongoDBTemplate that we'll be using to execute our queries
     */
    private MongoDBTemplate template;

    /**
     * Object that can convert MongoDB DBObjects to User objects
     */
    private UserBuilder userBuilder = new UserBuilder();

    @Override
    public void addUser( final User user )
    {
        // Delegate the insertion operation to the MongoDBTemplate
        template.insert( "users", new MongoDBDBObjectBuilder() {
            @Override
            public void build( BasicDBObjectBuilder builder ) {
                builder.add( "firstName", user.getFirstName() );
                builder.add( "lastName", user.getLastName() );
                builder.add( "age", user.getAge() );
            }
        });
    }

    @Override
    public void updateUser( final User user )
    {
        // Delegate the update operation to the MongoDBTemplate
        template.save( "users", new MongoDBDBObjectBuilder() {
            @Override
            public void build( BasicDBObjectBuilder builder ) {
                builder.add( "firstName", user.getFirstName() );
                builder.add( "lastName", user.getLastName() );
                builder.add( "age", user.getAge() );
            }
        });
    }

    @Override
    public void removeAll()
    {
        template.removeAll( "users" );
    }


    @Override
    public void removeUser( User user )
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeUserByLastName( final String lastName )
    {
        template.remove( "users",
            new MongoDBQueryCriteriaBuilder() {
                @Override
                public void build( BasicDBObjectBuilder builder ) {
                    // Add the last name criteria
                    builder.add( "lastName", lastName );
                }
            } );
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<User> findAll()
    {
        return template.findAll( "users", userBuilder );
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<User> findByLastName( final String lastName )
    {
        return template.find( "users",
            new MongoDBQueryCriteriaBuilder() {
                @Override
                public void build( BasicDBObjectBuilder builder ) {
                    // Add the last name criteria
                    builder.add( "lastName", lastName );
                }
            },
            userBuilder
        );
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<User> findByFirstNameAndLastName( final String firstName, final String lastName )
    {
        return template.find( "users",
            new MongoDBQueryCriteriaBuilder() {
                @Override
                public void build( BasicDBObjectBuilder builder ) {
                    // Add the first and last name criteria
                    builder.add( "firstName", firstName );
                    builder.add( "lastName", lastName );
                }
            },
            userBuilder
        );
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<User> findByAgeLessThan( final int age )
    {
        return template.find( "users",
            new MongoDBQueryCriteriaBuilder() {
                @Override
                public void build( BasicDBObjectBuilder builder ) {
                    // Add the age criteria
                    builder.add( "age", new BasicDBObject( "$lt", age ) );
                }
            },
            userBuilder
        );
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<User> findByAgeGreaterThan( final int age )
    {
        return template.find( "users",
            new MongoDBQueryCriteriaBuilder() {
                @Override
                public void build( BasicDBObjectBuilder builder ) {
                    // Add the age criteria
                    builder.add( "age", new BasicDBObject( "$gt", age ) );
                }
            },
            userBuilder
        );
    }

    /**
     * Method to inject the MongoDBTemplate into the DAO
     *
     * @param template      The MongoDBTemplate that this DAO should delegate to
     *                      for its MongoDB operations
     */
    public void setTemplate( MongoDBTemplate template )
    {
        this.template = template;
    }
}
