package club.banyuan.demo.oss.controller;

import club.banyuan.demo.oss.service.OssFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    OssFileService ossFileService;

    @RequestMapping(value = "/image/upload",method = RequestMethod.POST)
    public String upload(@RequestParam("file")MultipartFile file){
        String objectName = ossFileService.getObjectName(file);
        try {
           return ossFileService.save(objectName,file.getInputStream(),file.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "failed";
    }


    @RequestMapping(value = "/image/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public String delet(@RequestParam("objectName")String ObjectName){
        return "success";
    }
}
