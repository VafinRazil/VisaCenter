package com.rvafin.visacenter.service.interfaces;

import com.rvafin.visacenter.dto.request.TouristRequestDTO;
import com.rvafin.visacenter.dto.response.TouristResponseDTO;

public interface TouristService {

    boolean createNewTourist(TouristRequestDTO touristRequestDTO);

    boolean editTouristInfo(TouristRequestDTO touristRequestDTO, Long id);

    TouristResponseDTO getTouristInfoById(Long id);
}
