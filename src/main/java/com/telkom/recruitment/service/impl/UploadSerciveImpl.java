package com.telkom.recruitment.service.impl;

import com.telkom.recruitment.domain.Upload;
import com.telkom.recruitment.repository.UploadRepository;
import com.telkom.recruitment.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UploadSerciveImpl implements UploadService {

    @Autowired
    private UploadRepository uploadRepository;

    @Override
    public Upload save(Upload upload) {
        return uploadRepository.save(upload);
    }

    @Override
    public Upload update(Upload upload) {
        return uploadRepository.save(upload);
    }

    @Override
    public void delete(String idUpload) {
        uploadRepository.delete(idUpload);
    }

    @Override
    public Optional<Upload> getUpload(String idUpload) {
        return uploadRepository.findByIdUpload(idUpload);
    }

    @Override
    public Iterable<Upload> getUploads() {
        return uploadRepository.findAll();
    }
}
