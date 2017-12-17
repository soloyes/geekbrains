package ru.geekbrains;

import java.sql.SQLException;

class DBInit {

    DBInit() {
        try {
            new SQLHandler();
            SQLHandler.getStatement().executeUpdate("DROP TABLE IF EXISTS products");
            SQLHandler.getStatement().execute("CREATE TABLE products (\n" +
                    "    id     INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                    "                   NOT NULL\n" +
                    "                   UNIQUE,\n" +
                    "    prodid INTEGER UNIQUE\n" +
                    "                   NOT NULL,\n" +
                    "    title  STRING  NOT NULL,\n" +
                    "    cost   DOUBLE  NOT NULL\n" +
                    "                   DEFAULT (0) \n" +
                    ");\n");

            SQLHandler.setPreparedStatement(SQLHandler.getConnection().prepareStatement(
                    "INSERT INTO products(prodid, title, cost) VALUES (?, ?, ?)"));

            SQLHandler.getConnection().setAutoCommit(false);

            for (int i = 0; i <= 10000; i++) {
                SQLHandler.getPreparedStatement().setInt(1, i);
                SQLHandler.getPreparedStatement().setString(2, "item" + i);
                SQLHandler.getPreparedStatement().setDouble(3, i);
                SQLHandler.getPreparedStatement().addBatch();
            }
            SQLHandler.getPreparedStatement().executeBatch();
            SQLHandler.getConnection().setAutoCommit(true);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLHandler.disconnect();
        }
    }
}
