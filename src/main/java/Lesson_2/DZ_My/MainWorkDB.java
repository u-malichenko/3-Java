package Lesson_2.DZ_My;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class MainWorkDB {

    //для коректной работы нужно указать верный путь к файлам с указаниями
    private static String FILEPATH = "D:\\temp\\";

    private static Connection connection;
    private static Statement stmt;

    public static void main(String[] args) {
        try {
            connect();
            System.out.println("Data Base connection");
            try {//для работы с файлами(содержат запросы к бд)
                readFile("dropTable.sql", "DROP TABLE %s;",1);//удалить старые таблицы, временный параметр
                readFile("createTable.sql", "CREATE TABLE %s(%s);",2);
                readFile("insert.sql", "INSERT INTO %s (%s) VALUES (%s);",3);
                readFile("delete.sql", "DELETE FROM %s WHERE %s",2);
                readFile("update.sql", "UPDATE %s SET %s WHERE %s",3);
                readFile("select.sql", "SELECT * FROM %s WHERE %s",2);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
            System.out.println("Data Base disconnection");
        }
    }

    public static void readFile(String fileName, String query, int limitSplit) throws FileNotFoundException, SQLException {
        FileInputStream fileInputStream = new FileInputStream(FILEPATH+fileName);
        Scanner scanner = new Scanner(fileInputStream);
        while (scanner.hasNext()) {
            String[] mass = scanner.nextLine().split("/", limitSplit);
            runQuery(query, mass);
        }
    }

    /**
     * Сделать методы для работы с БД (CREATE, UPDATE, DELETE, INSERT, SELECT)
     */
    public static void runQuery(String query, String[] data) {
        String sql = formatQuery(query,data);
        try {
            if(query.startsWith("SELECT")){
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println(query + " " + data[0]);
                formatResultSet(rs);
                rs.close();
            }else{
                stmt.executeUpdate(sql);
                System.out.println(query + " " + data[0]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void formatResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2));
        }
    }
//жаль что в prepareStatement нельзя вставить ? на место таблицы ...
// решил ограничить с помощью лимита элементов при сплите строки из файла
    public static String formatQuery(String query, String[] data){
        switch (data.length){
            case 3: return String.format(query, data[0], data[1], data[2]);
            case 2: return String.format(query, data[0], data[1]);
            default: return String.format(query, data[0]);
        }
    }

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:shopdb.db");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
