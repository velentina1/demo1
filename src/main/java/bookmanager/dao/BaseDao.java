package bookmanager.dao;

import bookmanager.bean.*;
import bookmanager.util.JDBCUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {
    public boolean update(String sql,Object...params)  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
        connection = JDBCUtil.getConnection();
        preparedStatement = JDBCUtil.getPreparedStatement(sql,connection);
        JDBCUtil.bindPstmt(preparedStatement,params);
        int i = preparedStatement.executeUpdate();
        return i > 0 ? true :false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null,preparedStatement,connection);
        }

        return false;
    }

    public <T> T QueryOne(Class<T> tClass,String sql,Object...params){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        T bean = null;
        try{
            conn = JDBCUtil.getConnection();
            pstmt = JDBCUtil.getPreparedStatement(sql,conn);
            JDBCUtil.bindPstmt(pstmt,params);
            rs = pstmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
//            for (int i = 0; i < metaData.getColumnCount(); i++) {
//                System.out.println(metaData.getColumnLabel(i+1));
//            }


            while (rs.next()){
                bean = tClass.newInstance();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    BeanUtils.setProperty(bean,
                            metaData.getColumnLabel(i+1),
                            rs.getObject(i+1));
//                    System.out.println(rs.getObject(i+1));

                }

            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(rs,pstmt,conn);
        }

       return bean;

    }

    public <T> List<T> QueryAll (Class<T> tClass, String sql,Object...params){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List <T> list= null;
        try{
            conn = JDBCUtil.getConnection();
            pstmt = JDBCUtil.getPreparedStatement(sql,conn);
//            params[0] = "%" + params[0] + "%"; //模糊
            JDBCUtil.bindPstmt(pstmt,params);
            rs = pstmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
        T bean = null;
        list = new ArrayList<>();
        while (rs.next()){
            bean = tClass.newInstance();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                BeanUtils.setProperty(bean,
                        metaData.getColumnLabel(i+1),
                        rs.getObject(i+1));
            }
            list.add(bean);
        }
        } catch (SQLException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs,pstmt,conn);
        }

        return list;
    }


}
