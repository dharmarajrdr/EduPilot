package com.edupilot.backend.controller;

import com.edupilot.backend.dto.request.CreateLectureRequestDto;
import com.edupilot.backend.dto.response.BaseResponseDto;
import com.edupilot.backend.dto.response.CreateLectureResponseDto;
import com.edupilot.backend.model.enums.ResponseStatus;
import com.edupilot.backend.service.interfaces.LectureService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/api/lecture")
public class LectureController {

    private final LectureService lectureService;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BaseResponseDto> createLecture(
            @RequestPart CreateLectureRequestDto createLectureRequestDto,
            @RequestPart("file") MultipartFile file,
            @RequestParam Long userId
    ) {

        CreateLectureResponseDto createLectureResponseDto = lectureService.addLecture(file, createLectureRequestDto, userId);
        BaseResponseDto baseResponseDto = BaseResponseDto.builder()
                .message("Lecture created successfully.")
                .status(ResponseStatus.SUCCESS)
                .data(createLectureResponseDto)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(baseResponseDto);
    }
}
