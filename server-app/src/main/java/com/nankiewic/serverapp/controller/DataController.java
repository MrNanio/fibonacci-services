package com.nankiewic.serverapp.controller;

import com.nankiewic.serverapp.service.DataService;
import com.nankiewic.serverapp.dto.KeyValueDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calc")
@CrossOrigin(origins = "http://localhost:4200")
public class DataController {

    private final DataService dataService;

    @PostMapping
    public void sendValue(@RequestBody KeyValueDTO keyValueDTO) {
        dataService.saveValueToCalc(keyValueDTO);
    }

    @GetMapping("/all-data")
    public List<KeyValueDTO> getAll() {
        return dataService.getTenLastData();
    }

    @GetMapping("/all-index")
    public List<KeyValueDTO> getAllIndex() {
        return dataService.getAllIndex();
    }
}
