package org.alanjam.jdbc;

import java.sql.*;

record FetchStudents() {
    public static String fetchAll(String url) {
        assert url != null;
        String out = "";
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                String results = "";
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    results += rsmd.getColumnName(i + 1) + ": " + rs.getString(rsmd.getColumnName(i + 1)) + " ";
                }
                out += results + "\n";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return out;
    }

    public static String fetchById(String url, String id) {
        assert id != null;
        assert url != null;
        String out = "";
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM students WHERE ID = \'" + id + "\'");
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                String results = "";
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    results += rsmd.getColumnName(i + 1) + ": " + rs.getString(rsmd.getColumnName(i + 1)) + " ";
                }
                out += results + "\n";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return out;
    }

    public static String fetchByDegreeAndMajor(String url, String degree, String major) {
        assert url != null;
        assert degree != null;
        assert major != null;
        String out = "";
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM students WHERE DEGREE = \'" + degree + "\' AND major = \'" + major + "\'");
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                String results = "";
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    results += rsmd.getColumnName(i + 1) + ": " + rs.getString(rsmd.getColumnName(i + 1)) + " ";
                }
                out += results + "\n";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return out;
    }
}
