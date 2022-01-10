package com.gob.museumapp.db;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DBHelper {
    private String driver_class = "com.mysql.jdbc.Driver";
    private String driver_url = "jdbc:mysql://119.3.165.231:3306/museum?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private String database_usr = "root";
    private String database_password = "123456";
    private String sql; // 要传入的sql语句
    private List sqlValues; // sql语句的参数
    public Connection conn; // 连接对象

    public DBHelper(){ // 构造函数
        this.conn = getConnection();
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public void setSqlValues(List sqlValues) {
        this.sqlValues = sqlValues;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    /**
     * 获取数据库连接
     * @return
     */
    public Connection getConnection(){
        try {
            Class.forName(driver_class);
            conn = DriverManager.getConnection(driver_url, database_usr, database_password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭所有连接
     * @param con
     * @param st
     * @param rst
     */
    private void closeAll(Connection con, Statement st, ResultSet rst){
        if(rst != null){
            try{
                rst.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        if(st != null){
            try{
                st.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        if(conn != null){
            try{
                conn.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询
     * @return
     */
    public List executeQuery(){
        ResultSet rst = null;
        Statement st = null;
        List result = null;
        try{
            st = conn.createStatement();
            rst = st.executeQuery(sql);
            result = ResultTransform(rst);
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            this.closeAll(conn, st, rst);
        }
        return result;
    }

    /**
     * 增删改
     * @return
     */
    public int executeUpdate(){
        int result = -1;
        PreparedStatement pst = null;
        try{
            pst = conn.prepareStatement(sql);
            if(sqlValues != null && sqlValues.size() > 0){
                setSqlValues(pst, sqlValues);
            }
            result = pst.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            this.closeAll(conn, pst, null);
        }
        return result;
    }

    /**
     * 给sql语句中的占位符赋值
     * @param pst
     * @param sqlValues
     */
    private void setSqlValues(PreparedStatement pst,List sqlValues){
        for(int i=0;i<sqlValues.size();i++){
            try {
                pst.setObject(i+1,sqlValues.get(i));
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private List<Map<String,Object>> ResultTransform(ResultSet rst){
        List<Map<String,Object>>list=new ArrayList<Map<String,Object>>();
        try {
            ResultSetMetaData md = rst.getMetaData();
            int columnCount = md.getColumnCount();
            while(rst.next()){
                Map<String,Object> rowData = new HashMap<String,Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rst.getObject(i));
                }
                list.add(rowData);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}