package com.example.spa.controller;

import com.example.spa.common.security.domain.CustomUser;
import com.example.spa.domain.UserItem;
import com.example.spa.prop.ShopProperties;
import com.example.spa.service.UserItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/useritems")
public class UserItemController {
    private final UserItemService service;
    private final ShopProperties shopProperties;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserItem>> list(@AuthenticationPrincipal CustomUser customUser)throws Exception{
        long userNo = customUser.getUserNo();
        log.info("read : userNo " + userNo);
        return new ResponseEntity<>(service.list(userNo), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MEMBER')")
    @GetMapping("/{userItemNo}")
    public ResponseEntity<UserItem> read(@PathVariable("userItemNo") Long userItemNo) throws Exception{
        UserItem userItem = service.read(userItemNo);
        return new ResponseEntity<>(userItem, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MEMBER')")
    @GetMapping("/download/{userItemNo}")
    public ResponseEntity<byte[]> download(@PathVariable("userItemNo")Long userItemNo) throws Exception{
        // ????????? ????????????.
        UserItem userItem = service.read(userItemNo);

        // ?????? ????????? ????????????. url ??????
        String fullName = userItem.getPictureUrl();

        // ????????? ?????? ??????
        ResponseEntity<byte[]> entity = null;

        try{
            // ResponseEntity ??? ????????? Header ?????? ??????
            HttpHeaders headers = new HttpHeaders();

            // ????????? ??????????????? ?????? File ??????
            File file = new File(shopProperties.getUploadPath() + File.separator + fullName);

            // Url ?????? ???????????? ????????????.
            String fileName = fullName.substring(fullName.indexOf("_") + 1 );

            // header ??? ContentType ??? ??????
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            // header ??? ????????? ????????? ???????????? ?????? ????????? ???????????????.
            headers.add("Content-Disposition", "attachment; filename=\"" +
                    new String(fileName.getBytes(StandardCharsets.UTF_8),"ISO-8859-1")+"\"");

            entity = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),headers, HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();;
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

}
