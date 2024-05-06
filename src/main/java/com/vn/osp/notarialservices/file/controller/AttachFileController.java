package com.vn.osp.notarialservices.file.controller;

import com.vn.osp.notarialservices.file.domain.AttachFiles;
import com.vn.osp.notarialservices.file.service.AttachFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;

@Controller
@RequestMapping(value = "/attach-files")
public class AttachFileController {
    @Autowired
    private AttachFileService attachFileService;

    @RequestMapping(value = "/download/{id}/{code}/{type}", method = RequestMethod.GET)
    public void downloadFile(
            @PathVariable("id") Long id,
            @PathVariable("code") String code,
            @PathVariable("type") String type,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        InputStream inputStream = null;
        try {
            AttachFiles attachFile = attachFileService.getByCodeAndTypeAndId(id, type, code);
            if (attachFile == null) return;
            File file = new File(attachFile.getFile_path());
            if (!file.exists()) {
                String errorMessage = "Sorry. The file you are looking for does not exist";
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(errorMessage.getBytes());
                outputStream.close();
                return;
            }
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + attachFile.getFile_name() + "\""));
            response.setContentLength((int) file.length());
            inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(inputStream!=null) inputStream.close();
        }
    }
}
