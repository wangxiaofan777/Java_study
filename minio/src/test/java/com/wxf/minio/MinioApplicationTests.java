package com.wxf.minio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@SpringBootTest
class MinioApplicationTests {

    @Autowired
    private MinioUtils minioUtils;

    /**
     * 测试上传到minio
     */
    @Test
    void testUploadToMinio() {
        try {
            File file = new File("C:\\Users\\39087\\Desktop\\微信图片_20210426180514.jpg");
            InputStream inputStream = new FileInputStream(file);
            minioUtils.uploadToMinio(inputStream, "20210426180514");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetAllBucket() throws Exception {
        minioUtils.downloadImageFromMinioToFile("20210426180514", "helloworld.jpg", "C:\\Users\\39087\\Desktop");
    }

}
