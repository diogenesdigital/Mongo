package informa.util;

import com.mongodb.BasicDBObjectBuilder;

public interface MongoDBQueryCriteriaBuilder
{
    /**
     * Given a DBObject, adds criteria that should be passed to the MongoDB find() method
     * 
     * @param builder       A pre-constructed BasicDBObjectBuilder to which to add search criteria
     */
    public void build( BasicDBObjectBuilder builder );
}
