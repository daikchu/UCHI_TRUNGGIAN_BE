package com.vn.osp.notarialservices.citizenInformation.controller;
import com.vn.osp.notarialservices.citizenInformation.dto.CitizenInformationDTO;
import com.vn.osp.notarialservices.citizenInformation.service.CitizenInformationService;
import com.vn.osp.notarialservices.citizenVerification.dto.FileVerifyFaceId;
import com.vn.osp.notarialservices.identityAuthentication.dto.ResultTokenGenerate;
import com.vn.osp.notarialservices.paymentTransaction.domain.OrderMapVerification;
import com.vn.osp.notarialservices.utils.AuthorizationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/citizen-information")
public class CitizenInformationController {

    @Autowired
    private CitizenInformationService citizenInformationService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> add(@RequestHeader(value = "Authorization") String authorization, @RequestBody final CitizenInformationDTO citizenInformationDTO, HttpServletRequest request){
        // validate token
        if(AuthorizationUtil.validateAccessToken(authorization)) {
            CitizenInformationDTO result = citizenInformationService.saveOrUpdate(citizenInformationDTO).orElse(null);
            return ResponseEntity.ok(result);
        } else {
            ResultTokenGenerate result = new ResultTokenGenerate();
            result.setTimestamp(new Date().getTime());
            result.setStatus(401);
            result.setError("Unauthorized");
            result.setMessage("");
            result.setPath(request.getRequestURI());
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }
    }
    @RequestMapping(value = "generate-verify-id",method = RequestMethod.POST)
    public ResponseEntity<Object> generateVerifyId(@RequestHeader(value = "Authorization") String authorization, @RequestBody String notary_office_code, HttpServletRequest request){
        // validate token
        if(AuthorizationUtil.validateAccessToken(authorization)) {
            return new ResponseEntity<>(citizenInformationService.generateVerifyId(notary_office_code), HttpStatus.OK);
        }else {
            ResultTokenGenerate result = new ResultTokenGenerate();
            result.setTimestamp(new Date().getTime());
            result.setStatus(401);
            result.setError("Unauthorized");
            result.setMessage("");
            result.setPath(request.getRequestURI());
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "upload-verify-file",method = RequestMethod.POST)
    public Object uploadFileAndParams(@RequestHeader(value = "Authorization") String authorization, @RequestBody FileVerifyFaceId request, HttpServletRequest httpRequest) {
        Map<String, String> response = new HashMap<>();
        if(AuthorizationUtil.validateAccessToken(authorization)) {
            try {
                // Convert MultipartFile to File
                File convertedFile = convertMultiPartToFile(request.getFile());

                // Call the method to send the file and parameters
//            Map result = sendFileAuthenFaceId("YOUR_TOKEN", "YOUR_ACTION_URL", convertedFile, request.getVerifyId(), request.getNotaryOfficeId(), request.getCccdNumber());

                response.put("message", "File uploaded successfully");
//            response.put("result", result.toString());

                // Clean up - delete the temporary file
                convertedFile.delete();

            } catch (IOException e) {
                response.put("error", "File upload failed: " + e.getMessage());
            }
        }else {
            ResultTokenGenerate result = new ResultTokenGenerate();
            result.setTimestamp(new Date().getTime());
            result.setStatus(401);
            result.setError("Unauthorized");
            result.setMessage("");
            result.setPath(httpRequest.getRequestURI());
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }


        return response;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return convFile;
    }
}
