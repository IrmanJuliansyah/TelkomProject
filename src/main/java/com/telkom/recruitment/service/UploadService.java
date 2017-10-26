package com.telkom.recruitment.service;

import com.telkom.recruitment.domain.Upload;

import java.util.Optional;

public interface UploadService {

    Upload save(Upload upload);

    Upload update(Upload upload);

    public void delete(String idUpload);

    Optional<Upload> getUpload(String idUpload);

    Iterable<Upload> getUploads();
}
