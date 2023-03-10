package comp3350.quickkitchen.application;
public class Main {

    private static String dbName="QK"; //name of the db

    public static void main(String[] args)
    {
    }

    public static void setDBPathName(final String name) {
        /*
        mutator method set db location
        * */
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dbName = name;
    }

    //accessor
    //get the path location
    public static String getDBPathName() {
        return dbName;
    }

}
