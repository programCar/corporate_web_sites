package DButils;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbcp.DataSourceConnectionFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 功能:
 * 此类用于生成客户网站中的Mysql数据库数据源
 * 作用:
 * 用于取用数据源中的数据库连接接口
 */
public class DBCPUtils {

    private static DataSource dataSource = null;

    static {

        Properties properties = new Properties();

        try {
            properties.load((InputStream) new FileInputStream(new File("D:\\idea\\workspace\\corporate_web_sites\\src\\main\\resources\\DBCPConfig.properties")));
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * 用于调用公司数据库的连接接口，供以后对数据库的增删查改方法用
     * @return Connection
     */
    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 释放数据库JDBC资源
     * @param connection
     * @param statement
     * @param resultSet
     */
    public static void closeDB(Connection connection, Statement statement, ResultSet resultSet) {

        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
