package ru.geekbrains;

import java.sql.ResultSet;
import java.sql.SQLException;

class Commands {

    static void price(String item) {
        try {
            ResultSet set = SQLHandler
                    .getStatement()
                    .executeQuery(String.format("SELECT * FROM products WHERE title LIKE '%s'", item));
            while (set.next()) {
                System.out.println(set.getDouble(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void change(String item, String price) {
        try {
            double dprice = Double.parseDouble(price);
            int r = SQLHandler
                    .getStatement()
                    .executeUpdate(String.format("UPDATE products SET cost='%f' WHERE title='%s'", dprice, item));
            System.out.println("Changed " + r + " values");
        } catch (SQLException | NumberFormatException e) {
            System.out.println("No update");
        }
    }

    static void all(String start, String stop) {
        try {
            double dstart = Double.parseDouble(start);
            double dstop = Double.parseDouble(stop);

            ResultSet set = SQLHandler
                    .getStatement()
                    .executeQuery(String.format("SELECT * FROM products WHERE cost > '%f' AND cost < '%f'", dstart, dstop));
            while (set.next()) {
                System.out.println(set.getDouble(4));
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
