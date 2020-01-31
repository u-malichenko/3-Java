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
    private static PreparedStatement pstmt;

    public static void main(String[] args) {
        try { //для подключения - отключения БД
            connect();
            System.out.println("Data Base connection");

//            stmt.executeUpdate("INSERT INTO students (name, score) values('Bob1', 10)");
//            Savepoint sp = connection.setSavepoint();
//            stmt.executeUpdate("INSERT INTO students (name, score) values('Bob2', 20)");
//            connection.rollback(sp);
//            connection.setAutoCommit(true);
//            stmt.executeUpdate("INSERT INTO students (name, score) values('Bob3', 30)");

            try {//для работы с файлами(содержат запросы к бд)
                readFile("createTable.sql", "createTable");
                readFile("dropTable.sql", "dropTable");
                readFile("insert.sql", "insertDB");
                readFile("delete.sql", "deleteDB");
                readFile("update.sql", "updateDB");
                readFile("select.sql", "selectDB");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

//            long t = System.currentTimeMillis();
//            connection.setAutoCommit(false);
//            pstmt = connection.prepareStatement("insert into students (name, score) values(?, ?)");
//            for (int i = 0; i < 1000; i++) {
//                pstmt.setString(1, "Bob" + (i + 1));
//                pstmt.setInt(2, 20 * (i + 1));
//                pstmt.addBatch();
//            }
//
//            pstmt.executeBatch();
//            connection.setAutoCommit(true);
//            System.out.println(System.currentTimeMillis() - t);
//
//            connection.setAutoCommit(false);
//            long t = System.currentTimeMillis();
//            for (int i = 0; i < 1000; i++) {
//                int a = i * 10;
//                stmt.executeUpdate("insert into students (name, score) values ('unknow', " + a +")");
////                if (i%100 == 0) {
////                    connection.commit();
////                }
//            }
//            //connection.commit();
//            connection.setAutoCommit(true);
//            System.out.println(System.currentTimeMillis() - t);

//            ResultSetMetaData rsmd = rs.getMetaData();
//
//            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//                System.out.println(rsmd.getColumnName(i) + " " + rsmd.getColumnType(i) + " " + rsmd.getTableName(i));
//            }
//
//            while (rs.next()) {
//                System.out.println(rs.getInt(1) + " " + rs.getString("name"));
//            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
            System.out.println("Data Base disconnection");
        }
    }

    public static void readFile(String fileName, String type) throws FileNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(FILEPATH+fileName);
        Scanner scanner = new Scanner(fileInputStream);
        switch (type) {
            case "createTable": {
                while (scanner.hasNext()) {
                    String[] mass = scanner.nextLine().split("/", 2);
                    createTable(mass[0], mass[1]);
                }
                break;
            }
            case "insertDB": {
                while (scanner.hasNext()) {
                    String[] mass = scanner.nextLine().split("/", 3);
                    insertDB(mass[0], mass[1], mass[2]);
                }
                break;
            }
            case "dropTable": {
                while (scanner.hasNext()) {
                    String mass = scanner.nextLine();
                    dropTable(mass);
                }
                break;
            }
            case "updateDB": {
                while (scanner.hasNext()) {
                    String[] mass = scanner.nextLine().split("/", 3);
                    updateDB(mass[0], mass[1], mass[2]);
                }
                break;
            }
            case "deleteDB": {
                while (scanner.hasNext()) {
                    String[] mass = scanner.nextLine().split("/", 2);
                    deleteDB(mass[0], mass[1]);
                }
                break;
            }
            case "selectDB": {
                while (scanner.hasNext()) {
                    String[] mass = scanner.nextLine().split("/", 3);
                    selectDB(mass[0], mass[1], mass[2]);
                    System.out.println("Select from table ");
                }
                break;
            }
        }
    }

    /**
     * Сделать методы для работы с БД (CREATE, UPDATE, DELETE, INSERT, SELECT)
     */
    public static void dropTable(String tab) {
        String sql = String.format("DROP TABLE %s;", tab);
        try {
            stmt.executeUpdate(sql);
            System.out.println("Deleted table " + tab);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable(String tab, String query) {
        String sql = String.format("CREATE TABLE %s(,%s);", tab, query);

        try {
            stmt.executeUpdate(sql);
            System.out.println("Created table " + tab);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertDB(String tab, String columns, String values) {
        String sql = String.format("INSERT INTO %s (%s) VALUES (%s);", tab, columns, values);
        try {
            stmt.executeUpdate(sql);
            System.out.println("Inserted table " + tab + " values: " + values);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateDB(String tab, String values, String were) {
        String sql = String.format("UPDATE %s SET %s WHERE %s", tab, values, were);
        try {
            stmt.executeUpdate(sql);
            System.out.println("Update row in table " + tab + " were: " + were);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectDB(String tab, String values, String were) {
        String sql = String.format("SELECT %s FROM %s WHERE %s", values, tab, were);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            /**
             * TODO РАзобрать результать запроса
             */
            rs.getString(2);
            System.out.println("Select from table " + rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDB(String tab, String were) {
        String sql = String.format("DELETE FROM %s WHERE %s", tab, were);
        try {
            stmt.executeUpdate(sql);
            System.out.println("Deleted row in table " + tab + " were: " + were);
        } catch (SQLException e) {
            e.printStackTrace();
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
