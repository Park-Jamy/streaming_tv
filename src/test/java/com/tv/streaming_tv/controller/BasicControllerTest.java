package com.tv.streaming_tv.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@WebMvcTest(controllers = BasicController.class)
class BasicControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;
    @DisplayName("/basic 실행 여부")
    @Test
    void basicTest() throws Exception {

//        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
//
//        info.add("name", "");
//
//        mockMvc.perform(get("/basic")
//                        .params(info))    // 1, 2
//
//        //.andDo(print())
//                .andExpect(status().isOk());
    }

    @DisplayName("/basic post 처리")
    @Test
    void saveFileTest() throws Exception {
        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
        MockMultipartFile multipartFile = new MockMultipartFile("test.xlsx", new FileInputStream(new File("/home/admin/test.xlsx")));


        info.add("desc", "qwe");
        //when
        mockMvc.perform(
                        multipart("/basic").file(multipartFile)    // 1, 2
                        .params(info))
                .andExpect(status().isOk());
    }
}