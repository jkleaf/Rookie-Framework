package tk.leaflame.framework.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 封装数据库相关操作
 *
 * @author leaflame
 * @date 2020/4/2 23:45
 */
public class DataBaseHelper {

    private static final Logger logger = LoggerFactory.getLogger(DataBaseHelper.class);

    /**
     * Defines a ThreadLocal to store SQL connection of each thread
     */
    private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<>();

    public static Connection getConnection() {
        Connection conn;
        try {
            //Gets connection from ThreadLocal
            conn = CONNECTION_HOLDER.get();
            if (conn == null) {
                //if not exists,gets from DataSource
                assert getDataSource() != null;
                conn = getDataSource().getConnection();
                //Puts the connection into ThreadLocal
                if (conn != null) {
                    CONNECTION_HOLDER.set(conn);
                }
            }
        } catch (SQLException e) {
            logger.error("Error in getting SQL Connection!", e);
            throw new RuntimeException(e);
        }
        return conn;
    }


    public static DataSource getDataSource() { //TODO
        return null;
    }

    //TODO...

    /**
     * 开启事务
     */
    public static void beginTransaction() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                logger.error("Error in opening transaction！", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_HOLDER.set(conn);
            }
        }
    }

    /**
     * 提交事务
     */
    public static void commitTransaction() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.commit(); //commit
                conn.close(); //close
            } catch (Exception e) {
                logger.error("Error in committing transaction!", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_HOLDER.remove();
            }
        }

    }

    /**
     * 回滚事务
     */
    public static void rollbackTransaction() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.rollback(); //rollback
                conn.close(); //close
            } catch (SQLException e) {
                logger.error("Error in rollback transaction!", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }
}
