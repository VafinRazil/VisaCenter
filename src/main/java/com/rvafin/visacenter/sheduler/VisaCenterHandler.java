package com.rvafin.visacenter.sheduler;

import com.rvafin.visacenter.configuration.VisaGenerateConfiguration;
import com.rvafin.visacenter.entity.EVisaEntity;
import com.rvafin.visacenter.entity.VisaApplicationFormEntity;
import com.rvafin.visacenter.enums.VisaStatus;
import com.rvafin.visacenter.mapper.EVisaMapper;
import com.rvafin.visacenter.mapper.EVisaMapperImpl;
import com.rvafin.visacenter.repository.EVisaEntityRepository;
import com.rvafin.visacenter.repository.VisaApplicationFormEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@ConditionalOnProperty(value = "visa.generate.enable", havingValue = "true", matchIfMissing = true)
@Service
public class VisaCenterHandler {

    private static Logger log = LoggerFactory.getLogger(VisaCenterHandler.class);

    private final VisaApplicationFormEntityRepository visaApplicationFormEntityRepository;
    private final EVisaEntityRepository eVisaEntityRepository;
    private final VisaGenerateConfiguration visaGenerateConfiguration;
    private final EVisaMapper eVisaMapper = new EVisaMapperImpl();

    @Autowired
    public VisaCenterHandler(
            VisaApplicationFormEntityRepository visaApplicationFormEntityRepository,
            EVisaEntityRepository eVisaEntityRepository,
            VisaGenerateConfiguration visaGenerateConfiguration
    ){
        this.visaApplicationFormEntityRepository = visaApplicationFormEntityRepository;
        this.eVisaEntityRepository = eVisaEntityRepository;
        this.visaGenerateConfiguration = visaGenerateConfiguration;
    }

    /**
     * Имитация логики оформления визы
     * */
    @Async
    @Scheduled(cron = "*/${visa.generate.period.second} * * * * *")
    @Transactional
    public void everyTenSeconds(){
        List<VisaApplicationFormEntity> visaApplicationFormEntities = visaApplicationFormEntityRepository.findVisaApplicationFormEntitiesByStatus(VisaStatus.SEND);
        for (VisaApplicationFormEntity visaApplicationForm: visaApplicationFormEntities){
            EVisaEntity eVisaEntity = eVisaMapper.toEVisaEntity(visaApplicationForm);
            LocalDate expireDate = formExpireVisaDate();
            eVisaEntity.setExpireDate(expireDate);
            eVisaEntityRepository.save(eVisaEntity);
        }
    }


    private LocalDate formExpireVisaDate(){
        return LocalDate.of(
                LocalDate.now().getYear(),
                LocalDate.now().getMonth().plus(visaGenerateConfiguration.getVisaIssuancePeriodInMonths()),
                LocalDate.now().getDayOfMonth()
        );
    }
}
