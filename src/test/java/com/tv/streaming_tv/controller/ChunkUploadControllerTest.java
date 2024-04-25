package com.tv.streaming_tv.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ChunkUploadController.class)
class ChunkUploadControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;
    @Test
    void chunkUploadPage() throws Exception {

        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();

        info.add("name", "ss");

        mockMvc.perform(get("/chunk")
                        .params(info))
                .andExpect(status().isOk());
    }

    @Test
    void chunkUpload() throws Exception {
        MultiValueMap<String, Integer> info = new LinkedMultiValueMap<>();
        MockMultipartFile multipartFile = new MockMultipartFile("images.jpeg", new FileInputStream(new File("video/images.jpeg")));


        info.add("chunkNumber", 2);
        info.add("totalChunks", 2);
        //when
        mockMvc.perform(
                        multipart("/chunk/upload").file(multipartFile)
                                        .param("chunkNumber", "1")
                                        .param("totalChunks", "2"))
                .andExpect(status().isOk());
    }
}