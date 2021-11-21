package dongyang.ac.kr.greennae.controller.upload;

import dongyang.ac.kr.greennae.domain.Image;
import dongyang.ac.kr.greennae.dto.Example;
import dongyang.ac.kr.greennae.dto.UsersDto;
import dongyang.ac.kr.greennae.dto.uploadResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class UploadController {



    @Value("C:\\upload")
    private String uploadPath;

    @PostMapping("/uploadAjax")
    public ResponseEntity<?> uploadFile(MultipartFile[] uploadFiles){

        List<uploadResultDto> resultDtoList=new ArrayList<>();

        for (MultipartFile uploadFile:uploadFiles){

            if(uploadFile.getContentType().startsWith("image")==false){
                log.warn("this is not image type file");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            String originalName=uploadFile.getOriginalFilename();
            String fileName=originalName.substring(originalName.lastIndexOf("\\")+1);

            log.info("filename ={}",fileName);

            String folderPath=makeFolder();

            String uuid= UUID.randomUUID().toString();

            String saveName=uploadPath+ File.separator+folderPath+File.separator+uuid+"_"+fileName;

            Path savePath = Paths.get(saveName);

            try{
                uploadFile.transferTo(savePath);
                String thumbnailSaveName=uploadPath+File.separator+folderPath+File.separator+
                        "s_"+uuid+"_"+fileName;
                File thumbnailFile=new File(thumbnailSaveName);
                Thumbnailator.createThumbnail(savePath.toFile(),thumbnailFile,100,100);
                resultDtoList.add(new uploadResultDto(fileName,uuid,folderPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(resultDtoList,HttpStatus.OK);
    }

    private String makeFolder(){
        String str=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath=str.replace("/",File.separator);

        File uploadPathFolder =new File(uploadPath,folderPath);

        if (uploadPathFolder.exists()==false){
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName){
        ResponseEntity<byte[]> result=null;

        try {
            String srcFileName= URLDecoder.decode(fileName,"UTF-8");
            log.info("filename={}",fileName);
            File file=new File(uploadPath+File.separator+srcFileName);
            log.info("file={}",file);

            HttpHeaders header=new HttpHeaders();

            header.add("Content-Type", Files.probeContentType(file.toPath()));
            result=new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
}
