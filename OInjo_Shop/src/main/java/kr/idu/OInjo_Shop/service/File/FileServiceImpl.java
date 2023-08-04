package kr.idu.OInjo_Shop.service.File;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService{

    @Override
    public String uploadFile(String uploadPath, String oriFileName, byte[] fileData) throws IOException {

        UUID uuid = UUID.randomUUID(); // UUID 고유한 식별자
        String extension = oriFileName.substring(oriFileName.lastIndexOf(".")); // 원본 파일에서 확장자 추출
        String savedFileName = uuid.toString() + extension; // 식별자와 확장자 결합 후 저장
        String fileUploadFullUrl = uploadPath + "/" + savedFileName; // 전체 파일 경로
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl); // 파일 경로를 사용하여 초기화
        fos.write(fileData); // 내용 기록
        fos.close();
        return savedFileName;
    }

    @Override
    public void deleteFile(String filePath) {
        File deleteFile = new File(filePath);

        if(deleteFile.exists()) {
            deleteFile.delete();
            log.info("파일을 삭제했습니다.");
        } else {
            log.info("파일을 존재하지 않습니다.");
        }
    }
}
