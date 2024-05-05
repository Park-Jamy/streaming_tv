package com.tv.streaming_tv.controller;

import com.tv.streaming_tv.service.ChunkUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ChunkUploadController {

    private final ChunkUploadService chunkUploadService;

    @GetMapping("/chunk")
    public String chunkUploadPage() {
        return "chunk";
    }

//    @ResponseBody
//    @PostMapping("/chunk/upload")
//    public ResponseEntity<String> chunkUpload(@RequestParam(name = "chunk", required = false) MultipartFile file,
//                                              @RequestParam("chunkNumber") int chunkNumber,
//                                              @RequestParam("totalChunks") int totalChunks) throws IOException {
//        boolean isDone = chunkUploadService.chunkUpload(file, chunkNumber, totalChunks);
//
//        return isDone ?
//                ResponseEntity.ok("File uploaded successfully") :
//                ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).build();
//    }
    @ResponseBody
    @GetMapping("/chunk/upload/{key}")
    public ResponseEntity<?> getLastChunkNumber(@PathVariable String key) {
        return ResponseEntity.ok(chunkUploadService.getLastChunkNumber(key));
    }

    @ResponseBody
    @PostMapping("/chunk/upload/{key}")
    public ResponseEntity<String> chunkUpload(@RequestParam("chunk") MultipartFile file,
                                              @RequestParam("chunkNumber") int chunkNumber,
                                              @RequestParam("totalChunks") int totalChunks,
                                              @PathVariable String key) throws IOException {
        boolean isDone = chunkUploadService.chunkUpload(file, chunkNumber, totalChunks, key);

        return isDone ?
                ResponseEntity.ok("File uploaded successfully") :
                ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).build();
    }
}
