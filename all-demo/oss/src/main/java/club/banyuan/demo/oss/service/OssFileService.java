package club.banyuan.demo.oss.service;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Repository
public interface OssFileService{
    String getObjectName(@RequestParam("file") MultipartFile file);

    String save(String objectName, InputStream stream, String contentType) throws IOException;
}
