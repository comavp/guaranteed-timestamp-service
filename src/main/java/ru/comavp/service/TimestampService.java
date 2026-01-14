package ru.comavp.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.comavp.configuration.GuaranteedTimestampProperties;
import ru.comavp.model.dto.TimestampDto;
import ru.comavp.model.entity.TimestampEntity;
import ru.comavp.model.mappers.TimestampMapper;
import ru.comavp.repository.TimestampRepository;

import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimestampService {

    private final TimestampRepository timestampRepository;
    private final InMemoryBufferService inMemoryBufferService;
    private final TimestampMapper timestampMapper;
    private final GuaranteedTimestampProperties properties;

    @PostConstruct
    public void init() {
        if (properties.isNeedClearDb()) {
            log.info("Previous timestamps will be cleared");
            clearTimestampList();
        }
    }

    public List<TimestampDto> findAll() {
        var result = timestampRepository.findAll();
        log.info("Found {} entities in database", result.size());
        return timestampMapper.toTimestampDtoList(result);
    }

    @Scheduled(fixedRate = 1_000)
    public void saveTimestamp() {
        inMemoryBufferService.writeToBuffer(TimestampEntity.builder()
                .timestamp(OffsetDateTime.now())
                .build());
        log.debug("Current timestamp was successfully saved to buffer");
    }

    public void clearTimestampList() {
        timestampRepository.deleteAll();
    }
}
