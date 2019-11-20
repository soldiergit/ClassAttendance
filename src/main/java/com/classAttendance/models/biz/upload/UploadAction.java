package com.classAttendance.models.biz.upload;

import com.classAttendance.common.config.ConfigApi;
import com.classAttendance.common.vo.R;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @ProjectName:ClassAttendance
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-18下午8:58
 * @Describe:
//  String filePath = ServletActionContext.getServletContext().getRealPath("/sysFile");
// :/usr/local/apache-tomcat-8.5.38/webapps/ClassAttendance/sysFile
 */
public class UploadAction extends ActionSupport {

    //单文件：  上传文件的控件名称;struts2用来封装页面文件域对应的文件内容  --控件名
    private File file;
    //单文件：  上传文件名称, 文件名称= 控件名+FileName;struts2用来封装该文件域对应的文件的文件名,xxxFileName,layui上传的文件域field默认值是file
    private String fileFileName;

    //日志
    private static Logger logger = Logger.getLogger(UploadAction.class);
    //返回集
    private R r = new R();

    /**
     * 上传附件
     */
    public String uploadAnnex() {
        //等于 /teacher
        String projectName = ServletActionContext.getServletContext().getContextPath();
        String UPLOAD_FILES_PATH = ConfigApi.UPLOAD_URL+projectName + "/annex/";

        // 获取原文件图片后缀，以最后的.作为截取(.excel)
        String extName = fileFileName.substring(fileFileName.lastIndexOf("."));
        //保存原始文件名
        String oldFileName = fileFileName;

        //  文件保存路径
        try {
            saveFile(UPLOAD_FILES_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }

        //返回前台路径，用于保存到数据库
        r = R.ok()
                .put("src", UPLOAD_FILES_PATH+fileFileName)
                .put("fileName", oldFileName)
                .put("fileType", extName);

        logger.info("上传附件："+r);

        return SUCCESS;
    }

    /**
     * 上传图片
     */
    public String uploadImage() {
        String projectName = ServletActionContext.getServletContext().getContextPath();
        String UPLOAD_FILES_PATH = ConfigApi.UPLOAD_URL+projectName + "/images/";

        //  文件保存路径
        try {
            saveFile(UPLOAD_FILES_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }

        //返回前台路径，用于保存到数据库
        r = R.ok().put("src", "/tomcat_annex"+projectName + "/images/"+fileFileName);

        logger.info("上传图片："+r);

        return SUCCESS;
    }

    private void saveFile(String path) throws IOException {

        // 获取原文件图片后缀，以最后的.作为截取(.jpg)
        String extName = fileFileName.substring(fileFileName.lastIndexOf("."));
        // 生成自定义的新文件名，这里以UUID.jpg|png|xxx作为格式（可选操作，也可以不自定义新文件名）
        String uuid = UUID.randomUUID().toString();

        //  文件保存路径
        File fileFolder = new File(path);
        if(!fileFolder.exists()) fileFolder.mkdirs();

        //修改文件名称
        fileFileName = uuid+extName;
        FileUtils.copyFile(file, new File(fileFolder, fileFileName));
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("");
        String filePath = file.getCanonicalPath();
        System.out.println(filePath);
    }
}
