package informa.util;

import com.mongodb.BasicDBObjectBuilder;

	public interface MongoDBDBObjectBuilder
	{
	    /**
	     * Builds a DBObject
	     *
	     * @param builder       A pre-constructed BasicDBObjectBuilder to which to add
	     *                      your specific business object fields
	     */
	    public void build( BasicDBObjectBuilder builder );
	}

