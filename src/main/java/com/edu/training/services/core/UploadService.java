package com.edu.training.services.core;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

/**
 * Upload Service
 * @param <T> Object type to save.
 */
public interface UploadService<T> {

    @ResponseBody Map<String, Object> uploadCSV(@RequestParam("file") MultipartFile file, @RequestParam("token") String token) throws ServletException, IOException;

}
