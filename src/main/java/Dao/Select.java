package Dao;

import DButils.DBCPUtils;
import Perple.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import java.sql.*;
import java.util.List;


/**
 * 查询客户信息
 */
public class Select {

    //查询全部用户的个人信息SQL
    public final static String SELECT_ALL_USER_SQL = "select username, password from userInfo";

    //查询全部用户个人全部信息SQL
    public final static String SELECT_ALL_SQL = "select userInfo.username,userInfo.password,user_otherInfo.idCard," +
            "user_otherInfo.YourName,user_otherInfo.birthday from userInfo join user_otherInfo";

    //查询用户个人信息SQL
    public final static String SELECT_Person_SQL = "select username, password from userInfo where username = ";

    //查询用户个人全部信息SQL
    public final static String SELECT_PersonALL_SQL = "select userInfo.username,userInfo.password,user_otherInfo.idCard," +
            "user_otherInfo.YourName,user_otherInfo.birthday from userInfo join user_otherInfo where userInfo.username = ";

    //查询用户个人的其他信息SQL
    public final static String SELECT_PersonOtherInfo_SQL = "select username,idCard,YourName,birthday from user_otherInfo where username = ";

    public static QueryRunner queryRunner = null;

    //单例模式
    static {
        queryRunner = new QueryRunner();
    }


    /**
     * 查询全部用户的个人信息或查询全部用户的个人全部信息
     * @param select_Type
     * @return List<User>
     */
    public static List<User> selectAll(String select_Type){

        Connection connection = DBCPUtils.getConnection();

        try {

            List<User> usersInfo = null;

            //判断是要查询查询全部用户的个人信息还是查询全部用户的个人全部信息
            if (select_Type.equals(SELECT_ALL_USER_SQL)){
                usersInfo = queryRunner.query(connection, SELECT_ALL_USER_SQL, new BeanListHandler<>(User.class));
            }else if (select_Type.equals(SELECT_ALL_SQL)){
                usersInfo = queryRunner.query(connection, SELECT_ALL_SQL, new BeanListHandler<>(User.class));
            }else {
                return null;
            }

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
     * 查询个人用户信息或个人用户全部信息
     * @param username
     * @param select_Type
     * @return User
     */
    public static User selectPerson(String username, String select_Type){

        Connection connection = DBCPUtils.getConnection();

        if (username == null){
            return null;
        }

        try {

            //判断是要查询个人用户信息还是个人用户全部信息
            if (select_Type.equals(SELECT_Person_SQL)){
                String selectPersonSql = SELECT_Person_SQL + "'" + username + "'";
                return queryRunner.query(connection, selectPersonSql, new BeanHandler<User>(User.class));
            }else if (select_Type.equals(SELECT_PersonALL_SQL)){
                String selectPersonAllSql = SELECT_PersonALL_SQL + "'" + username + "' and user_otherInfo = '" + username + "'";
                return queryRunner.query(connection, selectPersonAllSql, new BeanHandler<User>(User.class));
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
        return null;
    }


    /**
     * 查询数据库身份证
     * @return List<String>
     */
    public static List<String> select_idCard(){

        String select_idCard_Sql = "select idCard from user_otherInfo";

        try {
            return queryRunner.query( DBCPUtils.getConnection(),select_idCard_Sql, new ColumnListHandler<String>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
