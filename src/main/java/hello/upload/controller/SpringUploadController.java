package hello.upload.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/spring/v3")
public class SpringUploadController {
    // application.properties의 속성을 그대로 가져온다.
    @Value("${file.dir}")
    private String fileDir;


    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFile(@RequestParam String itemName,
                           @RequestParam MultipartFile file,
                           HttpServletRequest request) throws IOException {

        log.info("request = {}", request);
        log.info("itemName = {}", itemName);
        log.info("multipart file = {}", file);

        if (!file.isEmpty()) {
            String fullPath = fileDir + file.getOriginalFilename();
            log.info("파일 저장 = {}", fullPath);
            file.transferTo(new File(fullPath));
        }

        return "upload-form";
    }
}
