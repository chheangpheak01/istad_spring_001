package com.sopheak.restfulapi001.media;
import com.sopheak.restfulapi001.utils.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.time.Instant;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/file")
public class FileController {

    private final FileService fileService;

    @PostMapping("/uploads")
    public ResponseTemplate<Object> uploadSingleFile(@RequestParam(name = "file") MultipartFile multipartFile){
        return ResponseTemplate
                .builder()
                .staus(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Upload single file successfully")
                .data(fileService.uploadSingleFile(multipartFile))
                .build();
    }

}
