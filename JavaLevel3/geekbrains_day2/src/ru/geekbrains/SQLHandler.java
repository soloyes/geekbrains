package ru.geekbrains;

import java.sql.*;

class SQLHandler {

    private static Connection connection;

    private static Statement statement;

    private static PreparedStatement preparedStatement;

    static Connection getConnection() {
        return connection;
    }

    static Statement getStatement() {
        return statement;
    }

    static PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    static void setPreparedStatement(PreparedStatement preparedStatement) {
        SQLHandler.preparedStatement = preparedStatement;
    }

    SQLHandler() throws SQLException, ClassNotFoundException {
        connect();
    }

    static void connect() throws ClassNotFoundException, SQLException {

        System.setProperty("java.io.tmpdir", "/home/sol/");

        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:data.db");
        statement = connection.createStatement();
    }

    static void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
