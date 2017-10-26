package com.telkom.recruitment.controller;

import com.telkom.recruitment.domain.Upload;
import com.telkom.recruitment.helper.HateoasResource;
import com.telkom.recruitment.helper.NotFoundRestHelper;
import com.telkom.recruitment.helper.ValidationIdHelper;
import com.telkom.recruitment.helper.storage.StorageFileNotFoundException;
import com.telkom.recruitment.helper.storage.StorageHelper;
import com.telkom.recruitment.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api")
public class UploadController implements ValidationIdHelper{

    @Autowired
    private UploadService uploadService;

    @Autowired
    private StorageHelper storageHelper;

    @GetMapping(value = "/upload")
    public ResponseEntity<?> getUploads(){
        HateoasResource hateoasResource = new HateoasResource(uploadService.getUploads());
        hateoasResource.add(linkTo(methodOn(UploadController.class).getUploads()).withSelfRel());
        return new ResponseEntity<>(hateoasResource, HttpStatus.OK);
    }

    @GetMapping(value = "/upload/{idUpload}")
    public Upload getUpload(@PathVariable("idUpload") String idUpload) {
        this.validateSelf(idUpload);
        return this.uploadService.getUpload(idUpload).orElse(null);
    }

    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<Resource> getFileUpload(@PathVariable String filename) {

        Resource file = storageHelper.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<?> save(@RequestBody Upload upload){
        String filename = storageHelper.store(upload.getFileUpload());

        Upload upload1 = uploadService.save(new Upload(
                upload.getNameUpload(),
                filename
        ));
        HateoasResource hateoasResource = new HateoasResource(upload1);
        hateoasResource.add(linkTo(methodOn(UploadController.class).getUpload(upload.getIdUpload())).withSelfRel());

        return new ResponseEntity<>(hateoasResource, HttpStatus.CREATED);
    }

    @PutMapping(value = "/upload/{idUpload}")
    public ResponseEntity<?> update(@PathVariable("idUpload") String idUpload,
                                    @RequestBody Upload upload){

        Upload upload1 = uploadService.save(new Upload(
                idUpload,
                upload.getNameUpload(),
                upload.getFileUpload()
        ));

        HateoasResource hateoasResource = new HateoasResource(upload1);
        hateoasResource.add(linkTo(methodOn(UploadController.class).getUpload(upload.getIdUpload())).withSelfRel());

        return new ResponseEntity<>(hateoasResource, HttpStatus.OK);
    }

    @DeleteMapping(value = "/upload/{idUpload}")
    public ResponseEntity<?> delete(@PathVariable("idUpload") String idUpload){
        this.validateSelf(idUpload);

        Upload upload = uploadService.getUpload(idUpload).orElseThrow(() -> new NotFoundRestHelper(idUpload, "Data Upload tidak tersedia"));
        storageHelper.delete(upload.getFileUpload());
        uploadService.delete(idUpload);
        HateoasResource hateoasResource = new HateoasResource(null);

        return new ResponseEntity<>(hateoasResource, HttpStatus.OK);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException storageFileNotFoundException) {
        return ResponseEntity.notFound().build();
    }

    @Override
    public void validateSelf(String id) {
        this.uploadService.getUpload(id).orElseThrow(() -> new NotFoundRestHelper(id, "Data Upload tidak tersedia"));
    }
}
