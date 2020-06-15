package club.banyuan.mall.integration.oss.service.impl;

import club.banyuan.mall.integration.oss.service.OssFileService;
import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MiniioOssFileServiceImpl implements OssFileService {
    @Value("${minio.endpoint}")
    private String ENDPOINT;

    @Value("${minio.bucketName}")
    private String BUCKET_NAME;

    @Value("${minio.accessKey}")
    private String ACCESS_KEY;

    @Value("${minio.secretKey}")
    private String SECRET_KEY;


    @Override
    public String getObjectName(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(new Date())+"/"+filename;
    }

    @Override
    public String save(String objectName, InputStream stream, String contentType) throws IOException {
        try {
            MinioClient minioClient = new MinioClient(ENDPOINT,ACCESS_KEY,SECRET_KEY);
            if(!minioClient.bucketExists(BUCKET_NAME)){
                minioClient.setBucketPolicy(BUCKET_NAME,"*.*", PolicyType.READ_WRITE);
            }

            minioClient.putObject(BUCKET_NAME,objectName,stream,contentType);
            return ENDPOINT+"/"+BUCKET_NAME+"/"+objectName;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
