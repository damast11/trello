package com.gmail.dkylish.service;

import org.springframework.web.multipart.MultipartFile;


public interface FilesStorageService {
    public void init();

    public void save(MultipartFile file);

}
