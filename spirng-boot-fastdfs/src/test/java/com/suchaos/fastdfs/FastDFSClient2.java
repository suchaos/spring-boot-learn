package com.suchaos.fastdfs;

import lombok.extern.slf4j.Slf4j;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.net.URLDecoder;

/**
 * FastDFSClient2
 *
 * @author suchao
 * @date 2018/11/29
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FastDFSClient2 {

    private static TrackerClient trackerClient = null;
    private static TrackerServer trackerServer = null;
    private static StorageServer storageServer = null;
    private static StorageClient1 client = null;
    // fdfsclient的配置文件的路径
    // private static String CONF_NAME = "/fdfs/fdfs_client.conf";
    private static String CONF_NAME = "classpath:fdfs_client2.conf";

    @Before
    public void setUp() {
        try {
            // 配置文件必须指定全路径
            //String confName = FastDFSClient.class.getResource(CONF_NAME).getPath();
            // 配置文件全路径中如果有中文，需要进行utf8转码
            //confName = URLDecoder.decode(confName, "utf8");
            File file = ResourceUtils.getFile("classpath:fdfs_client2.conf");
            String confName = file.getAbsolutePath();
            ClientGlobal.init(confName);
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = null;
            client = new StorageClient1(trackerServer, storageServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件方法
     * <p>
     * Title: uploadFile
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param fileName
     *            文件全路径
     * @param extName
     *            文件扩展名，不包含（.）
     * @param metas
     *            文件扩展信息
     * @return
     * @throws Exception
     */
    public static String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
        String result = client.upload_file1(fileName, extName, metas);
        return result;
    }

    public static String uploadFile(String fileName) throws Exception {
        return uploadFile(fileName, null, null);
    }

    public static String uploadFile(String fileName, String extName) throws Exception {
        return uploadFile(fileName, extName, null);
    }

    /**
     * 上传文件方法
     * <p>
     * Title: uploadFile
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param fileContent
     *            文件的内容，字节数组
     * @param extName
     *            文件扩展名
     * @param metas
     *            文件扩展信息
     * @return
     * @throws Exception
     */
    public static String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {

        String result = client.upload_file1(fileContent, extName, metas);
        return result;
    }

    public static String uploadFile(byte[] fileContent) throws Exception {
        return uploadFile(fileContent, null, null);
    }

    public static String uploadFile(byte[] fileContent, String extName) throws Exception {
        return uploadFile(fileContent, extName, null);
    }

    public static String uploadFile2(String filePath) throws Exception {
        String fileId = "";
        String fileExtName = "";
        if (filePath.contains(".")) {
            fileExtName = filePath.substring(filePath.lastIndexOf(".") + 1);
        } else {
            log.warn("Fail to upload file, because the format of filename is illegal.");
            return fileId;
        }
        // 建立连接
        /* ....... */
        // 上传文件
        try {
            fileId = client.upload_file1(filePath, fileExtName, null);
        } catch (Exception e) {
            log.warn("Upload file \"" + filePath + "\"fails");
        } finally {
            trackerServer.close();
        }
        return fileId;
    }

    public static String uploadSlaveFile(String masterFileId, String prefixName, String slaveFilePath)
            throws Exception {
        String slaveFileId = "";
        String slaveFileExtName = "";
        if (slaveFilePath.contains(".")) {
            slaveFileExtName = slaveFilePath.substring(slaveFilePath.lastIndexOf(".") + 1);
        } else {
            log.warn("Fail to upload file, because the format of filename is illegal.");
            return slaveFileId;
        }
        // 建立连接
        /* ....... */
        // 上传文件
        try {
            slaveFileId = client.upload_file1(masterFileId, prefixName, slaveFilePath, slaveFileExtName, null);
        } catch (Exception e) {
            log.warn("Upload file \"" + slaveFilePath + "\"fails");
        } finally {
            trackerServer.close();
        }
        return slaveFileId;
    }

    public static int download(String fileId, String localFile) throws Exception {
        int result = 0;
        // 建立连接
        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getConnection();
        StorageServer storageServer = null;
        StorageClient1 client = new StorageClient1(trackerServer, storageServer);
        // 上传文件
        try {
            result = client.download_file1(fileId, localFile);
        } catch (Exception e) {
            log.warn("Download file \"" + localFile + "\"fails");
        } finally {
            trackerServer.close();
        }
        return result;
    }

    @Test
    public void testUpload() {
        try {
            /*String masterFileId = uploadFile("D:/testpic/20180822151840.jpg");
            System.out.println("主文件:" + masterFileId);*/
            String masterFileId = "group1/M00/00/00/wKgf0Fv_h9mAOW8iABvB-go06G4527.jpg";
            download(masterFileId, "D:/testpic/master1.jpg");
            /*String slaveFileId = uploadSlaveFile(masterFileId, "_120x120", "D:/testpic/20180822151840.jpg");
            System.out.println("从文件:" + slaveFileId);
            download(slaveFileId, "D:/testpic/slave.jpg");*/
        } catch (Exception e) {
            log.error("upload file to FastDFS failed.", e);
        }
    }
}
