package comp3350.quickkitchen.application;

import comp3350.quickkitchen.persistence.RecipePersistence;
import comp3350.quickkitchen.persistence.hsqldb.RecipePersistenceHSQLDB;

public class Services {
    /*
    connect to database
    * */

    private static RecipePersistence recipePersistence = null;
    // accessor method.
    //set the path to db
    public static synchronized RecipePersistence getRecipePersistence() {
        if (recipePersistence == null) {
            recipePersistence = new RecipePersistenceHSQLDB(Main.getDBPathName());
        }
        return recipePersistence;
    }
}
