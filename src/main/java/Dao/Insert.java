package Dao;

import DButils.DBCPUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 插入客户信息
 */
public class Insert {

    private static final String INSERT_SQL = "insert into user values(";

    private static QueryRunner queryRunner = null;

    static {
        queryRunner = new QueryRunner();
    }

    public static boolean insert(String username, String password){

        String insertSql = INSERT_SQL + "'" + username + "','"  + password + "')";

        Connection connection = DBCPUtils.getConnection();

        try {
            if (queryRunner.update(connection, insertSql) > 0){
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
