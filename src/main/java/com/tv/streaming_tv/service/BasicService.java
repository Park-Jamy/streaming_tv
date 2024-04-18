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
public interface BasicService {

    public BasicService() throws Exception;;

    public default void saveFile(MultipartFile file) {
    }
}
