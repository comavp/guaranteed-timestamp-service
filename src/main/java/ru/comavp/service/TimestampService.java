package ru.comavp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
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

    final TimestampRepository timestampRepository;
    final TimestampMapper timestampMapper;

    public List<TimestampDto> findAll() {
        var result = timestampRepository.findAll();
        log.info("Found {} entities in database", result.size());
        return timestampMapper.toTimestampDtoList(result);
    }

    @Scheduled(fixedRate = 1000)
    public void saveTimestamp() {
        timestampRepository.save(TimestampEntity.builder()
                .timestamp(OffsetDateTime.now())
                .build());
        log.info("Current timestamp was successfully saved");
    }
}
