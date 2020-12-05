package cn.kgc.service.impl;

import cn.kgc.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service("fileService")
public class FileServiceImpl implements FileService {
    public String fileUpload(MultipartFile file,String path){
        //获取文件名
        String originalFileName = file.getOriginalFilename();
        //获取文件后缀
        String suffixName =  originalFileName.substring(originalFileName.lastIndexOf('.')+1,originalFileName.length());
        //TODO 这里可以根据业务需求来判断相应的后缀名

        if (! suffixName.equals("jpg")){
            return "文件后缀不符合标准";
        }
        //给图片一个新的名字
        String newName = UUID.randomUUID().toString()+"."+suffixName;
        //准备上传
        File uploadFilePath=new File(path);
        //验证上传地址是否存在
        if (! uploadFilePath.exists()){
            //在任何系统中获取最高写入权限
            uploadFilePath.setWritable(true);
            //
            uploadFilePath.mkdir();
        }
        File upload = new File(path,newName);
        try {
            //将文件上传到tomcat服务器
            file.transferTo(upload);
            // TODO 获取文件服务器，将文件上传
            // TODO 获取是否成功
            // todo
        } catch (IOException e) {
            e.printStackTrace();
        }
        return upload.getPath();
    }
}
