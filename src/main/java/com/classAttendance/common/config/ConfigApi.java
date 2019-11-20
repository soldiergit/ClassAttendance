package com.classAttendance.common.config;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18下午8:58
 * @Describe:
 **/
public class ConfigApi {

    //    文件存储路径
    /**
     * 已在tomcat的conf的server.xml配置:
     *  <Context path="/tomcat_annex" docBase="/home/soldier/SOLDIER/tomcat_annex" reloadable="true"/>
     */

    /**
     * 1、阿里云服务器
     * 2、本机
     * 3、富川服务器
     */
//    public static String UPLOAD_URL = "/usr/local/tomcat_annex";
    public static String UPLOAD_URL = "/home/soldier/SOLDIER/tomcat_annex";
//    public static String UPLOAD_URL = "D:/tomcat_annex";
}
