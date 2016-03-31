package informa.util;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteResult;

public class MongoDBTemplate {
	  private DB db;

	    /**
	     * Inserts the specified document into the specified collection
	     *
	     * @param collectionName        The collection to which to insert the document
	     * @param dbObjectBuilder       Builds the document to insert into the collection
	     */
	    public void insert( String collectionName, MongoDBDBObjectBuilder dbObjectBuilder )
	    {
	        // Obtain a reference to the collection
	        DBCollection collection = db.getCollection( collectionName );

	        // Build our object
	        BasicDBObjectBuilder basicDbObjectBuilder = BasicDBObjectBuilder.start();
	        dbObjectBuilder.build( basicDbObjectBuilder );

	        // Execute the insert operation
	        WriteResult result = collection.insert( basicDbObjectBuilder.get() );
	    }

	    /**
	     * Saves the specified document (insert or update, depending on the primary key)
	     * into the specified collection
	     *
	     * @param collectionName        The collection to which to insert the document
	     * @param dbObjectBuilder       Builds the document to insert into the collection
	     */
	    public void save( String collectionName, MongoDBDBObjectBuilder dbObjectBuilder )
	    {
	        // Obtain a reference to the collection
	        DBCollection collection = db.getCollection( collectionName );

	        // Build our object
	        BasicDBObjectBuilder basicDbObjectBuilder = BasicDBObjectBuilder.start();
	        dbObjectBuilder.build( basicDbObjectBuilder );

	        // Execute the insert operation
	        WriteResult result = collection.save( basicDbObjectBuilder.get() );
	    }

	    /**
	     * Executes a MongoDB find that contains search criteria
	     *
	     * @param <T>               The type of object being queried for
	     * @param collectionName    The name of the collection against which to execute the query
	     * @param criteriaBuilder   A builder used to add search criteria
	     * @param objectBuilder     A builder that converts a DBObject into a business object
	     *
	     * @return                  A list of business objects that match the search criteria
	     */
	    public <T> List<T> find( String collectionName,
	                             MongoDBQueryCriteriaBuilder criteriaBuilder,
	                             MongoDBObjectBuilder<T> objectBuilder )
	    {
	        // Obtain a reference to the collection
	        DBCollection collection = db.getCollection( collectionName );

	        // Build our search criteria
	        BasicDBObjectBuilder dbObjectBuilder = BasicDBObjectBuilder.start();
	        criteriaBuilder.build( dbObjectBuilder );

	        // Execute the query
	        DBCursor cursor = collection.find( dbObjectBuilder.get() );

	        // Construct the result list
	        List<T> results = new ArrayList<T>();
	        while( cursor.hasNext() )
	        {
	            // Delegate the conversion of a DBObject to a business object to the
	            // object builder - use that object builder to add a new object to our
	            // results
	            results.add( objectBuilder.getObject( cursor.next() ) );
	        }

	        // Return our results
	        return results;
	    }

	    /**
	     * Executes a MongoDB find operation that returns all objects in the specified collection
	     *
	     * @param <T>               The type of object being queried for
	     * @param collectionName    The name of the collection against which to execute the query
	     * @param objectBuilder     A builder that converts a DBObject into a business object
	     *
	     * @return                  A list of business objects that match the search criteria
	     */
	    public <T> List<T> findAll( String collectionName,
	                                MongoDBObjectBuilder<T> objectBuilder )
	    {
	        // Obtain a reference to the collection
	        DBCollection collection = db.getCollection( collectionName );

	        // Execute the query
	        DBCursor cursor = collection.find();

	        // Construct the result list
	        List<T> results = new ArrayList<T>();
	        while( cursor.hasNext() )
	        {
	            // Delegate the conversion of a DBObject to a business object to the
	            // object builder - use that object builder to add a new object to our
	            // results
	            results.add( objectBuilder.getObject( cursor.next() ) );
	        }

	        // Return our results
	        return results;
	    }

	    /**
	     * Removes the documents from the specified collection that match the specified criteria
	     * 
	     * @param collectionName        The name of the collection from which to remove the documents
	     * @param criteriaBuilder       The criteria that identifies which documents to remove
	     */
	    public void remove( String collectionName, MongoDBQueryCriteriaBuilder criteriaBuilder )
	    {
	        // Obtain a reference to the collection
	        DBCollection collection = db.getCollection( collectionName );

	        // Build our search criteria
	        BasicDBObjectBuilder dbObjectBuilder = BasicDBObjectBuilder.start();
	        criteriaBuilder.build( dbObjectBuilder );

	        // Remove all documents that match the specified criteria
	        collection.remove( dbObjectBuilder.get() );
	    }


	    /**
	     * Removes all documents from the specified collection
	     *
	     * @param collectionName        The name of the collection from which to remove all documents
	     */
	    public void removeAll( String collectionName )
	    {
	        // Obtain a reference to the collection
	        DBCollection collection = db.getCollection( collectionName );
	        collection.remove( new BasicDBObject() );
	    }

	    /**
	     * The DB against which to execute commands; should be injected in before using
	     * the template
	     *
	     * @param db        The DB against which to execute commands
	     */
	    public void setDB( DB db )
	    {
	        this.db = db;
	    }
}
