package kr.idu.OInjo_Shop.service.File;

import java.io.IOException;

public interface FileService {

    String uploadFile(String uploadPath, String oriFileName, byte[] fileData) throws IOException;

    void deleteFile(String filePath);
}