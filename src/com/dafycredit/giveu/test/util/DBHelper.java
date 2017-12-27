package com.dafycredit.giveu.test.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库操作类
 */
public class DBHelper {
    
    private static final Logger LOG = LoggerFactory.getLogger(DBHelper.class);
    
    private static final String USERNAME = ConfigUtil.getProperty("jdbc.username", Constants.CONFIG_JDBC);
    
    private static final String PASSWORD = ConfigUtil.getProperty("jdbc.password", Constants.CONFIG_JDBC);;
    
    private static final String DRIVER = ConfigUtil.getProperty("jdbc.driver", Constants.CONFIG_JDBC);;
    
    private static final String URL = ConfigUtil.getProperty("jdbc.url", Constants.CONFIG_JDBC);;
    
    private static Connection connection;
    
    public static void main(String[] args) throws Exception {
        DBHelper.connect();
        DBHelper.procedure("call prc_auto_daily(?)", "4428763");
        List<Map<String, Object>> results = DBHelper.query("select * from instalment where id_credit= 4428763");
        for(Map<String, Object> row : results){
            for(Map.Entry<String, Object> entry : row.entrySet()){
                LOG.info(entry.getKey() + "=" + entry.getValue());
            }
            LOG.info("---------------------------------------------");
        }
        DBHelper.close();
    }
 
    /**
     * 数据库插入
     * @param sql
     * @return
     */
    public static int insert(String sql){
        return executeUpdate(sql, OpType.INSERT);
    }
     
    /**
     * 数据库删除
     * @param sql
     * @return
     */
    public static int delete(String sql){
        return executeUpdate(sql, OpType.DELETE);
    }
    
    
    /**
     * 数据库修改
     * @param sql
     * @return
     */
    public static int update(String sql){
        return executeUpdate(sql, OpType.UPDATE);
    }
    
    /**
     * 数据库查询
     * @param sql
     * @return
     */
    public static List<Map<String, Object>> query(String sql){
        checkConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        LOG.info("Sql: " + sql);
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            while(rs.next()){
                int count = metaData.getColumnCount();
                Map<String, Object> rowMap = new HashMap<String, Object>();
                for(int i=1; i<=count; i++){    	
                    rowMap.put(metaData.getColumnLabel(i), rs.getObject(i));
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                }
                System.out.println("");
                results.add(rowMap);
            }
            LOG.info("Count: " + results.size());
        } catch (SQLException e) {
            LOG.error("查询失败", e.fillInStackTrace());
        } finally {
            close(ps, rs);
        }
        return results;
    }

	/**
     * 指定SQL语句,执行查询操作,并打印结果
     * @param sql
     * @return
     */
    public static void Query(String SQL) {  
	  	checkConnection();
	  	PreparedStatement ps = null;
        ResultSet rs = null;
	        try {
	              ps = connection.prepareStatement(SQL);
	              rs = ps.executeQuery();
	              int col = rs.getMetaData().getColumnCount(); 
	              System.out.println("============================");
	              while (rs.next()) {
	                   for (int i = 1; i <= col; i++) {
	                        System.out.print(rs.getString(i) + "\t");
	                      if ((i == 2) && (rs.getString(i).length() < 8)) {
	                             System.out.print("\t");
	                      }
	                   }
	                System.out.println("");
	              }
	                System.out.println("============================");
	                close();
	  	    }
	        catch (SQLException e) {
	        	System.out.println("查询失败");
	            e.printStackTrace();
	        }
	}

    /**
     * 执行数据库操作
     * @param sql
     * @param type
     * @return
     */
    private static int executeUpdate(String sql, OpType type){
        checkConnection();
        PreparedStatement ps = null;
        LOG.info("Sql: " + sql);
        try {
            ps = connection.prepareStatement(sql);
            int result = ps.executeUpdate();
            LOG.info("Result: " + result);
            return result;
        } catch (SQLException e) {
            LOG.error(type.desc()+"失败", e.fillInStackTrace());
        } finally {
            close(ps);
        }
        return -1;
    }
    
    /**
     * 执行存储过程,带参数
     * @param procedure
     * @param params
     * @return
     */
    public static void procedure(String procedure, Object... params) {
        checkConnection();
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall(procedure);
            if(params != null && params.length > 0){
                for(int i=0; i<params.length; i++){
                    //TODO 类型暂时只支持String
                    cs.setString(i+1, String.valueOf(params[i]));
                }
            }
            LOG.info("开始执行存储过程: "+ procedure); 
            cs.execute();
            LOG.info("存储过程执行成功 "); 
        } catch (SQLException e) {
            LOG.error("存储过程["+procedure+"]执行失败", e.fillInStackTrace());
        } finally {
            try {
                if(cs != null){ cs.close(); cs = null;}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    private static void checkConnection(){
        try {
            if(connection == null || connection.isClosed())
                connect();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e.fillInStackTrace());
        }
    }
    
    /**
     * 连接数据库
     * @throws Exception
     */
    public static void connect() throws Exception{
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            LOG.info("数据库连接成功");
        } catch (Exception e) {
            String message = "数据库连接失败";
            if(e instanceof ClassNotFoundException)
                message = "数据库驱动类未找到";
            throw new Exception(message, e.fillInStackTrace());
        } 
    }
    
    /**
     * 关闭资源
     * @param ps
     */
    public static void close(PreparedStatement ps){
        if(ps == null)
            return ;
        close(ps, null);
    }
    
    /**
     * 关闭资源
     * @param ps
     */
    public static void close(PreparedStatement ps, ResultSet rs){
        try {
            if(rs != null){
                rs.close();
                rs = null;
            }
            if(ps != null){
                ps.close();
                ps = null;
            }
        } catch (SQLException e) {
            LOG.error("数据库异常", e.fillInStackTrace());
        }
    }
    
    /**
     * 关闭数据库
     */
    public static void close(){
        try {
            if(connection != null && !connection.isClosed())
                connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库关闭失败");
        }
    }
    
    /**
     * 数据库操作枚举
     */
    enum OpType {
        
        INSERT("插入"), 
        UPDATE("更新"), 
        DELETE("删除"), 
        QUERY("查询");
        
        String desc;
        
        OpType(String desc){
            this.desc = desc;
        }
        
        String desc(){
            return desc;
        }
    } 

}
