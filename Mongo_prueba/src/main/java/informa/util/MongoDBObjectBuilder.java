package informa.util;

import com.mongodb.DBObject;

public interface MongoDBObjectBuilder<T> {
    /**
     * Builds a business object from the specified DBObject
     * @param <T>
     * 
     * @param obj       The DBObject from which to build the business object
     * @return          The business object
     */
    public T getObject( DBObject obj );
}
