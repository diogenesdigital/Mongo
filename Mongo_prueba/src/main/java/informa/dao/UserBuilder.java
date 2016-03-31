package informa.dao;

import informa.model.User;
import informa.util.MongoDBObjectBuilder;

import org.bson.types.ObjectId;

import com.mongodb.DBObject;

@SuppressWarnings("rawtypes")
public class UserBuilder implements MongoDBObjectBuilder
{
    /**
     * Converts a DBObject into a User
     *
     * @param obj       The DBObject to convert to a user
     *
     * @return          The converted User
     */
    @Override
    public User getObject( DBObject obj )
    {
        // Create a new user
        User user = new User();

        // Initialize its fields
        user.setId( ( ObjectId )obj.get( "_id" ) );
        user.setFirstName( ( String )obj.get( "firstName" ) );
        user.setLastName( ( String )obj.get( "lastName" ) );
        user.setAge( ( Integer )obj.get( "age" ) );

        // Return the newly created user
        return user;
    }
}