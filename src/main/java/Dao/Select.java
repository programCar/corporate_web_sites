package Dao;

import DButils.DBCPUtils;
import Perple.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.*;
import java.util.List;


/**
 * 查询客户信息
 */
public class Select {

    private final static String SELECT_ALL_SQL = "select username, password from user";

    private final static String SELECT_Person_SQL = "select username, password from user where username = ";

    private static QueryRunner queryRunner = null;

    static {
        queryRunner = new QueryRunner();
    }


    /**
     * 查询所有人的信息
     * @return
     */
    public static List<User> selectAll(){

        Connection connection = DBCPUtils.getConnection();

        try {

            List<User> usersInfo = queryRunner.query(connection, SELECT_ALL_SQL, new BeanListHandler<>(User.class));

            return usersInfo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }


    /**
     * 查询个人用户信息
     */

    public static User selectPerson(String username){

        String selectPersonSql = SELECT_Person_SQL + "'" + username + "'";

        Connection connection = DBCPUtils.getConnection();

        try {
            return queryRunner.query(connection, selectPersonSql, new BeanHandler<>(User.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
