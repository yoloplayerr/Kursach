package db;

import sample.User;

import java.sql.*;
import java.util.ArrayList;

public class DbManager {
   private static Connection conn = null;
   private static ResultSet res = null;
   private static PreparedStatement stmt = null;

    /**
     *
     * @param user
     * @return Возвращает true в случае создания и записи нового пользователя в бд,
     * Возвращает false если пользователь с таким userName уже есть
     *
     */

    public static boolean addUser(User user) {
        try {
            conn = DbConnection.Connection_to_my_db_Max();
            stmt = conn.prepareStatement("insert into users values(?,?,?,?,?)");

            stmt.setString(1,user.getUserName());
            stmt.setString(2,user.getFirstName());
            stmt.setString(3,user.getLastName());
            stmt.setString(4,user.getCountry());
            stmt.setString(5,user.getPassword());
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
     *
     * @return Возвращает список всех пользователей из бд
     */
    public static ArrayList<User> getUsers() {
        ArrayList<User> usersList = new ArrayList<>();
        try {
            conn = DbConnection.Connection_to_my_db_Max();
            stmt = conn.prepareStatement("select*from users");
            res = stmt.executeQuery();
            while (res.next()) {
                usersList.add(new User(res.getString("userName"),
                        res.getString("firstName"),
                        res.getString("lastName"),
                        res.getString("country"),
                        res.getString("password")));
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

            return usersList;
        }


    }
}
