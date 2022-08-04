package com.example.pulkovotest.service;

import com.example.pulkovotest.DTO.ServerStatisticDTO;
import com.example.pulkovotest.model.enums.Status;

public interface ServerStatisticService {
    ServerStatisticDTO getServerStatisticDTO(Status status, boolean adult);
}
