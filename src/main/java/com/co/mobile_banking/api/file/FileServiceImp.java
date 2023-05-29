package com.co.mobile_banking.api.file;

import com.co.mobile_banking.api.file.web.FileDto;
import com.co.mobile_banking.util.FileUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.System.out;

@Service
public class FileServiceImp implements FileService {
    private FileUtil fileUtil;
    @Value("${file.server-path}")
    private String fileServerPath;
    @Value("${file.base-url}")
    private String baseUrl;
    @Value("${file.download-url}")
    private String fileDownloadUrl;
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    public void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public FileDto uploadSingle(MultipartFile file) {
        return fileUtil.upload(file);
    }

    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> files) {
        List<FileDto> fileDtoList = new ArrayList<>();
        for (MultipartFile file : files) {
            fileDtoList.add(fileUtil.upload(file));
        }
        return fileDtoList;
    }

    @Override
    public List<FileDto> findAll() {
        File file = new File(fileServerPath);
        if (Objects.requireNonNull(file.list()).length > 0) {
            List<File> fileList = new ArrayList<>(List.of(Objects.requireNonNull(file.listFiles())));
            List<FileDto> resultList = new ArrayList<>();
            for (File f : fileList) {
                resultList.add(
                        FileDto.builder()
                                .name(f.getName())
                                .url(baseUrl + f.getName())
                                .downloadUrl(fileDownloadUrl + f.getName())
                                .extension(fileUtil.getExtensionFile(f))
                                .size(f.length())
                                .build()
                );
            }
            return resultList;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No file in server"
        );
    }

    @Override
    public FileDto findByName(String fileName) {
        Resource resource = resourceLoader.getResource("file:" + fileServerPath + fileName);
        if (resource.exists()) {
            try {
                return FileDto.builder()
                        .name(resource.getFilename())
                        .url(baseUrl + resource.getFilename())
                        .downloadUrl(fileDownloadUrl + resource.getFilename())
                        .extension(fileUtil.getExtensionFile(resource.getFile()))
                        .size(resource.contentLength())
                        .build();
            } catch (IOException e) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "the resource cannot be resolved as absolute file path"
                );
            }
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("file with name %s search not found", fileName)
        );
    }

    @Override
    public String deleteByName(String filename) {
        Resource resource = resourceLoader.getResource("file:" + fileServerPath + filename);
        if (resource.exists()) {
            try {
                // check make sure delete success
                boolean delete = resource.getFile().delete();
                if (delete) {
                    return resource.getFilename();
                } else {
                    throw new ResponseStatusException(
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            "delete not success please !!"
                    );
                }
            } catch (IOException e) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "the resource cannot be resolved as absolute file path"
                );
            }
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("file with name %s search not found", filename)
        );
    }

    @Override
    public boolean removeAllFiles() {
        Resource resource = resourceLoader.getResource("file:" + fileServerPath);
        try {
            File folder = ResourceUtils.getFile(resource.getURL());
            out.println(folder);
            FileUtils.cleanDirectory(folder);
            return true;
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "delete all files fail ... ! because directory doesn't exist or rescource not available"
            );
        }
    }

    @Override
    public FileDto downloadFileByFileName(String filename, HttpServletResponse response) {
        Path path = Paths.get(fileServerPath + filename);
        File file = path.toFile();
        if (file.exists()) {
            byte[] imageData = fileUtil.getImageData(file.getName());
            String extension = fileUtil.getExtensionFile(file);
            response.setContentType(fileUtil.getContentType(extension));
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            try {
                response.getOutputStream().write(imageData);
                return FileDto.builder()
                        .name(file.getName())
                        .url(baseUrl + file.getName())
                        .downloadUrl(fileDownloadUrl + file.getName())
                        .extension(fileUtil.getExtensionFile(file))
                        .size(file.length())
                        .build();
            } catch (IOException e) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "writing file error please !!"
                );
            }
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("file name %s not found !!", filename)
        );
    }
}
