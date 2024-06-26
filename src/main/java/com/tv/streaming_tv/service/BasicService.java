package com.tv.streaming_tv.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.Internal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class BasicService {

    public void saveFile(MultipartFile file) {
        if (!file.isEmpty()) {
            // 파일을 저장할 path
            // project root path 밑에 video 디렉토리에 저장
            Path filepath = Paths.get("video", file.getOriginalFilename());

            // 해당 path 에 파일의 스트림 데이터를 저장
            try (OutputStream os = Files.newOutputStream(filepath)) {
                os.write(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
