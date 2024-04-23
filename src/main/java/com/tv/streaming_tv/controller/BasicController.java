package com.tv.streaming_tv.controller;

import com.tv.streaming_tv.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BasicController {

    @Autowired(required = false)
    private BasicService basicService;

    // view
    @GetMapping("/basic")
    public String basic() {
        return "basic";
    }

    // 파일 업로드
    @ResponseBody
    @PostMapping("/basic")
    public String saveFile(@RequestParam("file") MultipartFile file,
                           @RequestParam("desc") String description) {

        basicService.saveFile(file);
        return "업로드 성공!! - 파일 이름: " + file.getOriginalFilename() + ", 파일 설명: " + description;
    }
}
