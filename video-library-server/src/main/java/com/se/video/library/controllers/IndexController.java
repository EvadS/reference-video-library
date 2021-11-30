package com.se.video.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RestController("/upload")
public class IndexController {
    @PostMapping()
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

          int a=0;

          return file.getOriginalFilename();
    }
}
