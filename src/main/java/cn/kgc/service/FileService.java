package cn.kgc.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public String fileUpload(MultipartFile file, String path);
}
