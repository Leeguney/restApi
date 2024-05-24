package com.example.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.String;
import com.example.service.StringService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "", description = "")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/")
public class StringController {

    private final StringService stringService;
    
    @Operation(summary = "", description = "")
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public void post(@RequestBody String string){
        stringService.fileDownload(string);
    }
}
