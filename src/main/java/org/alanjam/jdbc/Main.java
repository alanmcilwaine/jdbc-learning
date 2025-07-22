package org.alanjam.jdbc;

public class Main {
    static final String PATH = "./src/main/resources/db/";
    static final String URL = "jdbc:relique:csv:" + Main.PATH;
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
    }
}