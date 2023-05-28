package kz.arb.ecoplaning.controllers;

import kz.arb.ecoplaning.models.Image;
import kz.arb.ecoplaning.services.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestHeader("Authorization") String token) {


        if (token != null && token.startsWith("Bearer_")) {
            token = token.substring(7);
        }

        this.fileService.uploadFile(file, token);

//        return ResponseEntity.ok("Файл успешно сохранен");
        return new ResponseEntity<>("успех", HttpStatus.OK);
    }

    @GetMapping("/download")
    public void download(@RequestParam Long fileId, HttpServletResponse response) throws IOException {
        Image image = this.fileService.loadFile(fileId);
        response.setHeader("Content-Disposition", "attachment; filename = " + image.getName());
        response.setHeader("Content-type", image.getContentType());
        response.getOutputStream().write(image.getBytes());
        response.flushBuffer();
    }




}
