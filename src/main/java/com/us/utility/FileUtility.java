package com.us.utility;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtility {

    private static final Logger logger = LogManager.getLogger(FileUtility.class);

    @Autowired
    private Environment environment;

    public String saveEncodedFile(String fileByte, String fileName) {
        String finalName = null;
        try {
            int index = fileName.lastIndexOf('.');
            String path = environment.getRequiredProperty("file.report.path");
            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();

            finalName = fileName.substring(0, index) + System.currentTimeMillis() + fileName.substring(index);

            StringBuffer sb = new StringBuffer();
            sb.append(path);
            sb.append(finalName);

            int ind = fileByte.indexOf(",");
            byte[] data = Base64.getDecoder().decode(fileByte.substring(ind + 1));
            try (OutputStream stream = new FileOutputStream(sb.toString())) {
                stream.write(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return finalName;
    }

    public String saveFile(MultipartFile files) throws Exception {
        String finalName = null;
        try {
            String fileName = files.getOriginalFilename().trim();
            if (fileName != null && fileName.length() != 0 && files.getBytes().length != 0) {
                byte[] bytes = files.getBytes();
                int index = fileName.lastIndexOf('.');
                String path = environment.getRequiredProperty("file.report.path");
                File dir = new File(path);
                if (!dir.exists())
                    dir.mkdirs();

                finalName = fileName.substring(0, index) + System.currentTimeMillis() + fileName.substring(index);

                StringBuffer sb = new StringBuffer();
                sb.append(path);
                sb.append(finalName);
                BufferedOutputStream buffStream = new BufferedOutputStream(
                        new FileOutputStream(new File(sb.toString())));
                buffStream.write(bytes);
                buffStream.close();
            }
        } catch (Exception e) {
            logger.error("Error while saving multipart file : ", e);
            throw e;
        }
        return finalName;
    }

    public void saveEncodedImageWithSameName(String imageByte, String fileName) {
        try {
            String path = environment.getRequiredProperty("file.gallery.path");
            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();

            StringBuffer sb = new StringBuffer();
            sb.append(path);
            sb.append(fileName);

            int ind = imageByte.indexOf(",");
            byte[] data = Base64.getDecoder().decode(imageByte.substring(ind + 1));
            try (OutputStream stream = new FileOutputStream(sb.toString())) {
                stream.write(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
