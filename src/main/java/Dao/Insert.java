package Dao;

import DButils.DBCPUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 插入客户信息
 */
public class Insert {

    //添加用户个人信息SQL
    private static final String INSERT_USERINFO_SQL = "insert into userInfo(username,password) values(";

    //添加用户个人的其他信息SQL
    private static final String INSERT_USEROTHERINFO_SQL = "insert into user_otherInfo(username,idCard,YourName,birthday) values(";

    private static QueryRunner queryRunner = null;

    //单例模式
    static {
        queryRunner = new QueryRunner();
    }

    /**
     * 添加用户信息
     * @param username
     * @param password
     * @param idCard
     * @param yourName
     * @return boolean
     */
    public static boolean insert(String username, String password, String idCard, String yourName){

        //添加用户个人信息SQL
        String insert_UserInfo_Sql = INSERT_USERINFO_SQL + "'" + username + "','"  + password + "')";

        //通过身份证捕获个人生日日期
        String birthday = idCard.substring(6,10) + idCard.substring(10,12) + idCard.substring(12,14);

        //添加用户个人的其他信息SQL
        String insert_UserOtherInfo_Sql = INSERT_USEROTHERINFO_SQL + "'" + username +"','" + idCard + "','" + yourName + "','" + birthday + "')";

        Connection connection = DBCPUtils.getConnection();

        try {

            if (queryRunner.update(connection, insert_UserInfo_Sql) > 0){

                if (queryRunner.update(connection, insert_UserOtherInfo_Sql) > 0) {
                    return true;
                }

            }

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        //如果用户信息添加失败，则回滚事务
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return false;
    }
}
