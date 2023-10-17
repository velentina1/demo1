package bookmanager.util;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

public class JDBCPool implements DataSource {
    public static LinkedList<Connection> list = new LinkedList<>();
    static {
        try {
            InputStream is =
                   JDBCPool.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(is);
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            String driver = properties.getProperty("driver");

            Class.forName(driver);
            for (int i = 0; i < 10; i++) {
                Connection conn = DriverManager.getConnection(url,username,password);
                list.add(conn);

            }

            properties.load(is);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
//        System.out.println("当前的池子中，还有：" + list.size() + "个conn对象");
        Connection conn = list.removeFirst();
//        System.out.println("conn对象被取走一个，当前的池子中，还有：" + list.size() + "个conn对象1");
        return (Connection) Proxy.newProxyInstance(JDBCPool.class.getClassLoader(),
                conn.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (!method.getName().equals("close")){
                            return method.invoke(conn,args);
                        }list.add(conn);
//                          System.out.println("池子中归还了一个conn对象，现在大小是：" + list.size());

                        return null;
                    }
                });

    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}