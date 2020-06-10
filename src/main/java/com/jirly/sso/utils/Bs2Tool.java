package com.jirly.sso.utils;

import com.jirly.sso.config.SsoConfiguration;
import com.yy.yycloud.bs2.auth.YCCredential;
import com.yy.yycloud.bs2.conf.ConfigAppInfo;
import com.yy.yycloud.bs2.event.ProgressEvent;
import com.yy.yycloud.bs2.event.ProgressListener;
import com.yy.yycloud.bs2.model.PutObjectRequest;
import com.yy.yycloud.bs2.transfer.PersistableTransfer;
import com.yy.yycloud.bs2.transfer.TransferManager;
import com.yy.yycloud.bs2.transfer.Upload;
import com.yy.yycloud.bs2.transfer.model.UploadResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author <a href = "mailto:zengzelin@yy.com">zeng zelin</a>
 * @version v1.0.0
 * @data 2018/11/21
 */
public class Bs2Tool {
    private static String bucketName = null;
    private static int appKey = 0;
    private static String appId = null;
    private static String secret = null;
    private static String deviceId = null;
    private static TransferManager tm = null;

    static {
        bucketName = Configuration.getProperty("bs2_bucket_name");
        appKey = Integer.valueOf(Configuration.getProperty("bs2_app_key"));
        appId = Configuration.getProperty("bs2_app_id");
        secret = Configuration.getProperty("bs2_secret");
        deviceId = Configuration.getProperty("bs2_device_id");
        tm = new TransferManager(new YCCredential(appKey, 1, secret));
    }

    public static List<String> upload(MultipartFile[] files) throws IOException, InterruptedException {
        List<String> filePaths = new ArrayList<String>();
        ConfigAppInfo.setAppId(appId);
        ConfigAppInfo.setAppVersion("1.0.0");
        ConfigAppInfo.setDeviceId(deviceId);
        ConfigAppInfo.setNetworkType(2);
        for (MultipartFile file: files) {
            PutObjectRequest request = new PutObjectRequest();
            String keyName = "KFcenter_"+DateUtils.convertDateToString(new Date(),DateUtils.DATETIME_PATTERN4) +"_"+UUID.randomUUID()+"_"+file.getOriginalFilename();
            request.withBucketName(bucketName)
                    .withKeyName(keyName)
                    .withInput(file.getInputStream())
                    .withSize(file.getBytes().length)
                    .withProgressListener(new ProgressListener() {
                        @Override
                        public void progressChanged (ProgressEvent progressEvent) {

                        }
                        @Override
                        public void onPersistableTransfer(PersistableTransfer persistableTransfer) {
                        }
                    });
            Upload upload = tm.upload(request);
            upload.waitForUploadResult();
            String filePath = "http://"+bucketName + ".bs2dl.yy.com/" + keyName;
            filePaths.add(filePath);
        }
        return filePaths;
    }

    public static String upload(File file) throws IOException, InterruptedException {
        ConfigAppInfo.setAppId(appId);
        ConfigAppInfo.setAppVersion("1.0.0");
        ConfigAppInfo.setDeviceId(deviceId);
        ConfigAppInfo.setNetworkType(2);
        PutObjectRequest request = new PutObjectRequest();
        String keyName = "KFcenter_"+DateUtils.convertDateToString(new Date(),DateUtils.DATETIME_PATTERN4) +"_"+UUID.randomUUID()+"_"+file.getName();
        InputStream fileInputStream = new FileInputStream(file);
        request.withBucketName(bucketName)
                .withKeyName(keyName)
                .withInput(fileInputStream)
                .withSize(fileInputStream.available())
                .withProgressListener(new ProgressListener() {
                    @Override
                    public void progressChanged (ProgressEvent progressEvent) {

                    }
                    @Override
                    public void onPersistableTransfer(PersistableTransfer persistableTransfer) {
                    }
                });
        Upload upload = tm.upload(request);
        UploadResult uploadResult = upload.waitForUploadResult();
        System.out.println(uploadResult.getDownloadUrl());
        String filePath = "http://"+bucketName + ".bs2dl.yy.com/" + keyName;
        return filePath;
    }
}

