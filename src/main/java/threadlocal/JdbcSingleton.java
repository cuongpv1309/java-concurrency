package main.java.threadlocal;

public class JdbcSingleton {

    private static JdbcSingleton jdbc;
    private String connection;

    private JdbcSingleton(String connection) {
        System.out.println(Thread.currentThread().getName() + " is owner - " + " with connection " + connection);
        this.connection = connection;
    }



    public static JdbcSingleton getInstance(String connection) {
        if(jdbc == null) {
            jdbc = new JdbcSingleton(connection);
        }
        return jdbc;
    }
}
