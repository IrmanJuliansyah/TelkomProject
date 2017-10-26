/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telkom.recruitment.helper.storage;

import com.telkom.recruitment.helper.StringManipulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Irman Juliansyah <irmanjuliansyah@gmail.com>
 */
@Service
public class FileSystemStorageService implements StorageHelper {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void init() {
        try {
            if (!Files.isDirectory(rootLocation)) {
                Files.createDirectory(rootLocation);
            }
        } catch (IOException e) {
            throw new StorageFileNotFoundException("Could not initialize storage", e);
        }
    }

    @Override
    public String store(String filename) {
        try {
            if (filename.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            String file = StringManipulation.createNewFileName();
            Files.write(this.rootLocation.resolve(file), StringManipulation.base64ToByte(filename));
            return file;
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path path = rootLocation.resolve(filename);
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void delete(String filename) {
        try {
            Path path = rootLocation.resolve(filename);
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new StorageException("Failed to delete file " + filename, e);
        }
    }
}
