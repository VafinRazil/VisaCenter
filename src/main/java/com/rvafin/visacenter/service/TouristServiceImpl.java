package com.rvafin.visacenter.service;

import com.rvafin.visacenter.dto.request.TouristRequestDTO;
import com.rvafin.visacenter.dto.response.TouristResponseDTO;
import com.rvafin.visacenter.entity.TouristEntity;
import com.rvafin.visacenter.mapper.TouristMapper;
import com.rvafin.visacenter.repository.TouristEntityRepository;
import com.rvafin.visacenter.service.interfaces.TouristService;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TouristServiceImpl implements TouristService {

    private static Logger log = LoggerFactory.getLogger(TouristServiceImpl.class);

    private final TouristEntityRepository touristEntityRepository;
    private final TouristMapper touristMapper = Mappers.getMapper(TouristMapper.class);

    @Autowired
    public TouristServiceImpl(
            TouristEntityRepository touristEntityRepository
    ) {
        this.touristEntityRepository = touristEntityRepository;
    }

    @Transactional
    @Override
    public boolean createNewTourist(TouristRequestDTO touristRequestDTO) {
        TouristEntity tourist = touristMapper.toTouristEntity(touristRequestDTO);
        touristEntityRepository.save(tourist);
        return true;
    }

    @Transactional
    @Override
    public boolean editTouristInfo(TouristRequestDTO touristRequestDTO, Long id) {
        TouristEntity touristOdDB = touristEntityRepository.findById(id).orElseThrow();
        TouristEntity changedTourist = touristMapper.updateTouristEntity(touristOdDB, touristRequestDTO);
        touristEntityRepository.save(changedTourist);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public TouristResponseDTO getTouristInfoById(Long id) {
        TouristEntity tourist = touristEntityRepository.findById(id).orElseThrow();
        return touristMapper.toTouristResponseDTO(tourist);
    }
}
