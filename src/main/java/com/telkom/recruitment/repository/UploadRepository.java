package com.telkom.recruitment.repository;

import com.telkom.recruitment.domain.Upload;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UploadRepository extends MongoRepository<Upload, String> {
    Optional<Upload> findByIdUpload(String idUpload);
}
