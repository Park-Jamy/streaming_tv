package com.tv.streaming_tv.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

@Slf4j
@Service
public class ChunkUploadService {
    public boolean chunkUpload(MultipartFile file, int chunkNumber, int totalChunks, String key) throws IOException {
        // 파일 업로드 위치
        String uploadDir = "video";
        String tempDir = "video/" + key;  // 파일 조각 저장 경로


        File dir = new File(tempDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 임시 저장 파일 이름
        String filename = file.getOriginalFilename() + ".part" + chunkNumber;

        Path filePath = Paths.get(tempDir, filename);
        // 임시 저장
        Files.write(filePath, file.getBytes());

        // 마지막 조각이 전송 됐을 경우
        if (chunkNumber == totalChunks-1) {
            String[] split = file.getOriginalFilename().split("\\.");
            String outputFilename = UUID.randomUUID() + "." + split[split.length-1];
            Path outputFile = Paths.get(uploadDir, outputFilename);
            Files.createFile(outputFile);

            // 임시 파일들을 하나로 합침
            for (int i = 0; i < totalChunks; i++) {
                Path chunkFile = Paths.get(tempDir, file.getOriginalFilename() + ".part" + i);
                Files.write(outputFile, Files.readAllBytes(chunkFile), StandardOpenOption.APPEND);
                // 합친 후 삭제
                Files.delete(chunkFile);
            }
            log.info("File uploaded successfully");
            return true;
        } else {
            return false;
        }
    }

    public int getLastChunkNumber(String key) {
        Path temp = Paths.get("video", key);
        String[] list = temp.toFile().list();
        return list == null ? 0 : Math.max(list.length-2, 0);
    }
}
