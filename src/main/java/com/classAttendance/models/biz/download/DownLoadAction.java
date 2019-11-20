package com.classAttendance.models.biz.download;

import com.opensymphony.xwork2.ActionSupport;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18下午8:58
 * @Describe:
 */
public class DownLoadAction extends ActionSupport {

    private String downloadPath;// 下载的路径
    private String contentType;// 下载文件的类型
    private String filename;// 文件名称

    public String downloadFile() throws Exception {
        //处理文件名称中文问题
        try {
            filename = (new String(URLDecoder.decode(filename,"UTF-8").getBytes("UTF-8"),
                    "ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException();
        }

        // 自动判断下载文件类型
        contentType = "multipart/form-data";
        return SUCCESS;
    }

    /**
     * 返回InputStream
     */
    public InputStream getInputStream() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(downloadPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    ///////////////////////////////////

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
