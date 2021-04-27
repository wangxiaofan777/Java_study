package com.wxf.minio;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
public class MinioUtils {
    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.port}")
    private Integer port;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;
    @Value("${minio.bucketName}")
    private String bucketName;


    private static MinioClient minioClient;

    public MinioClient getInstance() {
        if (minioClient == null) {
            minioClient = MinioClient.builder().endpoint(endpoint, port, false).credentials(accessKey, secretKey).build();
        }
        return minioClient;
    }

    /**
     * @return java.util.List<io.minio.messages.Bucket>
     * @Description 获取minio所有的桶
     **/
    public List<Bucket> getAllBucket() throws Exception {
        // 获取minio中所以的bucket
        List<Bucket> buckets = getInstance().listBuckets();
        for (Bucket bucket : buckets) {
            log.info("bucket 名称:  {}      bucket 创建时间: {}", bucket.name(), bucket.creationDate());
        }
        return buckets;
    }


    /**
     * @param inputStream: 输入流
     * @param objectName:  对象名称
     * @return void
     * @Description 将图片上传到minio服务器
     **/
    public void uploadToMinio(InputStream inputStream, String objectName) {
        try {
            long size = inputStream.available();
            MinioClient minioClient = getInstance();
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(inputStream, size, -1)
                    .build();
            // 上传到minio
            minioClient.putObject(putObjectArgs);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param objectName: 对象的名称
     * @return java.lang.String
     * @Description 根据指定的objectName获取下载链接，需要bucket设置可下载的策略
     **/
    public String getUrlByObjectName(String objectName) {
        String objectUrl = null;
        try {
//            objectUrl = getInstance().getObjectUrl(bucketName, objectName);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return objectUrl;
    }


    /**
     * @param objectName: objectName
     * @param fileName:   文件名称
     * @param dir:        文件目录
     * @return void
     * @Description 根据objectName从minio中下载文件到指定的目录
     **/
    public void downloadImageFromMinioToFile(String objectName, String fileName, String dir) throws Exception {
        GetObjectArgs objectArgs = GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build();
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        InputStream inputStream = getInstance().getObject(objectArgs);
        FileOutputStream outputStream = new FileOutputStream(new File(dir, fileName.substring(fileName.lastIndexOf("/") + 1)));
        int length;
        byte[] buffer = new byte[1024];
        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }
        outputStream.close();
        inputStream.close();
    }

}
