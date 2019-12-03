package db;

import sample.User;
import urlParser.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DbManager {
    /**
     * @param user
     * @return Возвращает true в случае создания и записи нового пользователя в бд,
     * Возвращает false если пользователь с таким userName уже есть
     */

    public static boolean addUser(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DbConnection.Connection_to_my_db();
            stmt = conn.prepareStatement("insert into users values(default,?,?,?,?,?);");

            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getCountry());
            stmt.setString(5, user.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {

            }

        }
        return true;
    }

    /**
     * true если пользователь успешно вошел в систему
     * false если пароль/логин введены неверно
     *
     * @return
     */
    public static boolean chekUser(String login, String password) {
        boolean flag = false;
        Connection conn = null;
        ResultSet res = null;
        PreparedStatement stmt = null;
        try {
            conn = DbConnection.Connection_to_my_db();
            stmt = conn.prepareStatement("select*from chekLogin('" + login + "','" + password + "');");
            res = stmt.executeQuery();
            while (res.next()) {
                if (res.getInt("chekLogin") == 1) {
                    flag = true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (res != null)
                    res.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {

            }
            return flag;
        }


    }

    /**
     * Заполнение таблицы datavalue данными из файла
     * @param br
     * @param userName
     */
    public static void insertData(BufferedReader br, String userName) {
        Connection conn = null;
        ResultSet res = null;
        PreparedStatement stmt = null;
        String line = "";
        String cvsSplitBy = ";";
        String date = "";
        try {
            conn = DbConnection.Connection_to_my_db();
            deleteRows(conn, stmt,userName);
            stmt = conn.prepareStatement("insert into dataValue values(default,?,?,?,?,?,?,?,?);");
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] d = line.split(cvsSplitBy);
                date = d[2].substring(0, 4) + "-" + d[2].substring(4, 6) + "-" + d[2].substring(6, 8);
                Date sqlDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime());
                stmt.setString(1, userName);
                stmt.setString(2, d[0]);
                stmt.setString(3, d[1]);
                stmt.setDate(4, sqlDate);
                stmt.setFloat(5, Float.valueOf(d[4]));
                stmt.setFloat(6, Float.valueOf(d[5]));
                stmt.setFloat(7, Float.valueOf(d[6]));
                stmt.setFloat(8, Float.valueOf(d[7]));
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e);
        } catch (IOException e) {

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (res != null)
                    res.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {

            }

        }

    }

    public static ArrayList<Data> getData(String username) {
        ArrayList<Data> dataList = new ArrayList<>();
        Connection conn = null;
        ResultSet res = null;
        PreparedStatement stmt = null;
        Data data;
        try {
            conn = DbConnection.Connection_to_my_db();
            stmt = conn.prepareStatement("select*from datavalue where username="+"'"+username+"'");
            res = stmt.executeQuery();
            while (res.next()) {
                data = new Data(res.getString("ticker"), res.getString("per"), res.getString("currentdate"), res.getFloat("openvalue"), res.getFloat("lowvalue"), res.getFloat("highvalue"), res.getFloat("closevalue"));
                dataList.add(data);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (res != null)
                    res.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {

            }
        }
        return dataList;
    }

    private static void deleteRows(Connection conn, PreparedStatement stmt,String userName) {
        try {
            stmt = conn.prepareStatement("delete from datavalue where username="+"'"+userName+"'");
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

