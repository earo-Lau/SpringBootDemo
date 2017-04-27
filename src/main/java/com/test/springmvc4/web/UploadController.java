package com.test.springmvc4.web;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by lauearo on 27/04/2017.
 */
@Controller
public class UploadController {
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String upload(MultipartFile fileUpload) {
        try {
            FileUtils.writeByteArrayToFile(new File("~/Documents/" + fileUpload.getOriginalFilename()), fileUpload.getBytes());

            return "File upload success";
        } catch (IOException io) {
            io.printStackTrace();
            return "File upload failed";
        }
    }
}
