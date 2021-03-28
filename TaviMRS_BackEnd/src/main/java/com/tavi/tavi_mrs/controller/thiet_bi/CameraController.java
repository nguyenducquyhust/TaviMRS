package com.tavi.tavi_mrs.controller.thiet_bi;

import com.tavi.tavi_mrs.entities.giay_phep_khai_thac.GiayPhepKhaiThac;
import com.tavi.tavi_mrs.entities.json.JsonResult;
import com.tavi.tavi_mrs.entities.json.PageJson;
import com.tavi.tavi_mrs.entities.thiet_bi.Camera;
import com.tavi.tavi_mrs.service.thiet_bi.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin/camera")
public class CameraController {

    @Autowired
    private CameraService cameraService;

    @GetMapping("/find-by-id")
    public ResponseEntity<JsonResult> findById(@RequestParam("camera-id") int idCamera) {
        return cameraService.findByIdCameraAndXoa(idCamera, false)
                .map(JsonResult::found)
                .orElse(JsonResult.idNotFound());
    }


    @GetMapping("/find-all")
    public ResponseEntity<JsonResult> findAll(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                              @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Camera> cameraPage = cameraService.findAllByXoa(false,pageable);

        return Optional.ofNullable(cameraPage)
                .map(cameras -> cameras.getTotalElements() != 0 ? JsonResult.found(PageJson.build(cameras)) : JsonResult.notFound("Camera"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-by-thiet-bi")
    public ResponseEntity<JsonResult> findByThietBiIdAndXoa(@RequestParam("thiet-bi-id") int idThietBi,
                                             @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                              @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Camera> cameraPage = cameraService.findByThietBiIdAndXoa(idThietBi,false,pageable);

        return Optional.ofNullable(cameraPage)
                .map(cameras -> cameras.getTotalElements() != 0 ? JsonResult.found(PageJson.build(cameras)) : JsonResult.notFound("Camera"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-by-doanh-nghiep")
    public ResponseEntity<JsonResult> findByDoanhNghiep(@RequestParam(value = "doanh-nghiep-id", defaultValue = "0", required = false) int idDoanhNghiep,
                                                            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                            @RequestParam(name = "size", defaultValue = "4", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Camera> cameraPage = cameraService.findByDoanhNghiep(idDoanhNghiep, pageable);

        return Optional.ofNullable(cameraPage)
                .map(cameras -> cameras.getTotalElements() != 0 ? JsonResult.found(PageJson.build(cameras)) : JsonResult.notFound("Camera/DoanhNghiep"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @GetMapping("/find-by-mo")
    public ResponseEntity<JsonResult> findByMo(@RequestParam(value = "mo-id", defaultValue = "0", required = false) int idMo,
                                                        @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                        @RequestParam(name = "size", defaultValue = "16", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Camera> cameraPage = cameraService.findByMo(idMo, pageable);

        return Optional.ofNullable(cameraPage)
                .map(cameras -> cameras.getTotalElements() != 0 ? JsonResult.found(PageJson.build(cameras)) : JsonResult.notFound("Camera/Mo"))
                .orElse(JsonResult.serverError("Internal Server error"));
    }

    @PutMapping("/update")
    public ResponseEntity<JsonResult> update(@Valid @RequestBody Camera camera) {
        return cameraService.save(camera)
                .map(JsonResult::updated)
                .orElse(JsonResult.saveError("Camera "));
    }


}
