package com.rminfo.util;

import com.rminfo.module.DeviceInfo;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


public class JdbcUtil {
//    private static String URL = "jdbc:mysql://192.168.3.221:3307/wufeng?user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
    private static String URL = "jdbc:mysql://119.23.145.226:3306/wufeng?user=root&password=rminfo@168&useUnicode=true&characterEncoding=UTF8";
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void insertBody(String body) throws Exception {
        Connection conn = null;
        String sql;
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            conn = DriverManager.getConnection(URL);
//            Statement stmt = conn.createStatement();
//            sql = "INSERT INTO log(body)  VALUES ('"+body+"')";
//            int result = stmt.executeUpdate(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
//            if (result != -1) {
//                System.out.println("成功插入数据库");
//            }
            DeviceInfo deviceInfo = TransferUtil.tranferRequestCode(body);
            String newsql = "INSERT INTO device_info(" +
                    "length,"+
                    "cus_id,"+
                    "card_id,"+
                    "version,"+
                    "command_code,"+
                    "command_id,"+
                    "reserved,"+
                    "longitude,"+
                    "latitude,"+
                    "speed,"+
                    "direction,"+
                    "height,"+
                    "status_bits,"+
                    "date,"+
                    "time,"+
                    "gsm,"+
                    "gps,"+
                    "run_time,"+
                    "run_mile,"+
                    "total_mile,"+
                    "run_speed,"+
                    "power,"+
                    "fuel,"+
                    "total_fuel,"+
                    "malfunction,"+
                    "warn_type,"+
                    "on_off,"+
                    "content,"+
                    "body"+
                    ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst=conn.prepareStatement(newsql);
            pst.setString( 1, deviceInfo.getLength());
            pst.setString(2, deviceInfo.getCusID());
            pst.setString(3, deviceInfo.getCardID());
            pst.setString(4, deviceInfo.getVersion());
            pst.setString(5, deviceInfo.getCommandCode());
            pst.setString(6, deviceInfo.getCommandId());
            pst.setString(7, deviceInfo.getReserved());
            pst.setString(8, deviceInfo.getLongitude());
            pst.setString(9, deviceInfo.getLatitude());
            pst.setString(10, deviceInfo.getSpeed());
            pst.setString(11, deviceInfo.getDirection());
            pst.setString(12, deviceInfo.getHeight());
            pst.setString(13, deviceInfo.getStatusBits());
            pst.setString(14, deviceInfo.getDate());
            pst.setString(15, deviceInfo.getTime());
            pst.setString(16, deviceInfo.getGsm());
            pst.setString(17, deviceInfo.getGps());
            pst.setString(18, deviceInfo.getRunTime());
            pst.setString(19, deviceInfo.getRunMile());
            pst.setString(20, deviceInfo.getTotalMile());
            pst.setString(21, deviceInfo.getRunSpeed());
            pst.setString(22, deviceInfo.getPower());
            pst.setString(23, deviceInfo.getFuel());
            pst.setString(24, deviceInfo.getTotalFuel());
            pst.setString(25, deviceInfo.getMalfunction());
            pst.setString(26, deviceInfo.getWarnType());
            pst.setString(27, deviceInfo.getOnOff());
            pst.setString(28, null);
            pst.setString(29, body);

            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }


    public static boolean selectSim(String sim) throws Exception {
        Connection conn = null;
        boolean flag = false;
        String sql;
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            conn = DriverManager.getConnection(URL);
            Statement stmt = conn.createStatement();
            sql = "SELECT count(*) from r5rmcommunicate where HDSIM = '"+sim.substring(2)+"'";
            ResultSet rs = stmt.executeQuery(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
            if (rs.next())
            {
                int count = rs.getInt(1);
                if (count>0) {
                    flag=true;
                }
            }
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return flag;
    }

    public static   void selectDate(String sim){
        Connection conn = null;
        boolean flag = false;
        String sql;
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            conn = DriverManager.getConnection(URL);/*
            String dateSql = "select max(create_time) from device_info where card_id = "+sim;
            PreparedStatement psDate = conn.prepareStatement(dateSql);
            ResultSet rsDate =  psDate.executeQuery(dateSql);
            if(rsDate.next()){
                System.out.println("此sim卡数据库中最大时间为"+rsDate.getTimestamp(1));
            }*/
            sql = "select * from device_info where card_id = "+sim+" and create_time> '2017-07-17 14:24:00' ORDER BY create_time desc";
            System.out.println(sql);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
                System.out.println("id 长度 公司号 卡号 版本 命令号 命令id 预留字段(FFFF为下发) 时间");
            while(rs.next()){
                StringBuilder sb = new StringBuilder();
                for(int i =1;i<9;i++) {
                    sb.append(rs.getString(i)).append(" ");
                }
                System.out.println(sb.toString()+rs.getTimestamp("create_time"));
            }
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
 
}