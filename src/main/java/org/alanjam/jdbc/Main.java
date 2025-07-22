package org.alanjam.jdbc;

import java.sql.*;

public class Main {
    static final String PATH = "./src/main/resources/db/";
    public static void main(String[] args) {
        String url = "jdbc:relique:csv:" + Main.PATH;
        FetchStudents fetch =  new FetchStudents(url);
        fetch.fetchByDegreeAndMajor("BSc", "CS");
    }
}

record FetchStudents (String url){
    public FetchStudents{
        assert url != null;
    }
    public void fetchAll() {
        assert url != null;
        try (Connection conn = DriverManager.getConnection(url)){
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next()) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    sb.append(rsmd.getColumnName(i + 1)).append(": ").append(rs.getString(rsmd.getColumnName(i + 1))).append(" ");
                }
                System.out.println(sb);
            }

        } catch(Exception e){
            System.out.println(e);
        }
    }
    public void fetchById(int id) {
        assert id > 0;
        assert url != null;
        try(Connection conn = DriverManager.getConnection(url)){
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM students WHERE ID = \'" + id + "\'");
            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next()) {
                String out = "";
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    out += rsmd.getColumnName(i + 1) + ": " + rs.getString(rsmd.getColumnName(i + 1)) + " ";
                }
                System.out.println(out);
            }
        } catch (Exception e){
            System.out.println(e);
        }

    }
    public void fetchByDegreeAndMajor(String degree, String major) {
        assert url != null;
        assert degree != null;
        assert major != null;
        try(Connection conn = DriverManager.getConnection(url)){
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM students WHERE DEGREE = \'"  + degree + "\' AND major = \'" + major + "\'");
            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next()) {
                String out = "";
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    out += rsmd.getColumnName(i + 1) + ": " + rs.getString(rsmd.getColumnName(i + 1)) + " ";
                }
                System.out.println(out);
            }
        } catch (Exception e){
            System.out.println(e);
        }

    }
}