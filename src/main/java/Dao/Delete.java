package Dao;

import DButils.DBCPUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 用于删除客户的个人信息
 */
public class Delete {

    private static final String DELETE_PERSON_SQL = "delete from user where username = ";

    private static QueryRunner queryRunner = null;

    static {
         queryRunner = new QueryRunner();
    }


    public static boolean delete(String username){

        String deletePersonSql = DELETE_PERSON_SQL + "'" + username + "'";

        Connection connection = DBCPUtils.getConnection();

        try {
            if (queryRunner.update(connection, deletePersonSql) > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return false;

    }
}
