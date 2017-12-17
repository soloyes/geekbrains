package ru.geekbrains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        new DBInit();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Input query (price, change, all)  or end:");
            String line;
            try {
                SQLHandler.connect();
                while (!(line = reader.readLine()).equals("end")) {
                    if (!(line.startsWith("price") || line.startsWith("change") || line.startsWith("all"))) {
                        System.out.println("Wrong command");
                        continue;
                    }
                    if (line.startsWith("price")) {
                        line = line.replaceFirst("price ", "");
                        Commands.price(line);
                    }

                    if (line.startsWith("change")) {
                        line = line.replaceFirst("change ", "");
                        String[] s = line.split(" ", 2);
                        Commands.change(s[0], s[1]);
                    }
                    if (line.startsWith("all")) {
                        line = line.replaceFirst("all ", "");
                        String[] s = line.split(" ", 2);
                        Commands.all(s[0], s[1]);
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
            finally {
                SQLHandler.disconnect();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
