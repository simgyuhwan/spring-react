package com.example.spa.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

@Slf4j
public class UploadFileUtils {

    public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception{
        UUID uid = UUID.randomUUID();

        String savedName = uid.toString() + "_" + originalName;

        // 현재 날짜로 된 디렉터티를 생성하기 위한 메서드
        String savedPath = calcPath(uploadPath);

        // 기본 경로 + 현재 날짜 디렉터리, 파일 이름(UUID 를 포함한 이름)
        File target = new File(uploadPath + savedPath, savedName);

        FileCopyUtils.copy(fileData, target);


        String uploadedFileName = makeUploadFileName(uploadPath, savedPath, savedName);

        return uploadedFileName;
    }

    private static String makeUploadFileName(String uploadPath, String path, String fileName )throws Exception{
        // File.separator 를 사용하는 이유는 OS 마다 '/' or '\' 와 같이 구분자가 다르기 때문에 해당 OS 맞는 구분자를 호출하기 위해서 사용합니다.
        String uploadedFileName = uploadPath + path + File.separator + fileName;
        return uploadedFileName.substring(uploadPath.length()).replace(File.separatorChar, '/');
    }

    private static String calcPath(String uploadPath){
        Calendar cal = Calendar.getInstance();

        String yearPath = File.separator + cal.get(Calendar.YEAR);
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

        makeDir(uploadPath, yearPath, monthPath, datePath);

        log.info(datePath);

        return datePath;
    }

    private static void makeDir(String uploadPath, String... paths){
        if(new File(paths[paths.length -1]).exists()){
            return;
        }

        for(String path : paths){
            File dirPath = new File(uploadPath + path);

            if(!dirPath.exists()){
                dirPath.mkdir();
            }
        }
    }
}
