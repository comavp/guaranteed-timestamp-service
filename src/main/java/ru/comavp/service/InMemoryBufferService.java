package ru.comavp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.comavp.model.entity.TimestampEntity;
import ru.comavp.repository.TimestampRepository;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Service
@RequiredArgsConstructor
@Slf4j
public class InMemoryBufferService {

    private final TimestampRepository timestampRepository;

    private final BlockingQueue<TimestampEntity> inMemoryBuffer = new ArrayBlockingQueue<>(1000);

    public void writeToBuffer(TimestampEntity timestampEntity) {
        inMemoryBuffer.add(timestampEntity);
    }

    @Scheduled(fixedRate = 1_000)
    private void readFromBuffer() {
        TimestampEntity timestampEntity = inMemoryBuffer.peek();
        if (timestampEntity != null) {
            try {
                timestampRepository.save(timestampEntity);
                inMemoryBuffer.poll();
                log.debug("Current timestamp was successfully saved to DB");
            } catch (Exception e) {
                log.warn("DB connection is unavailable");
            }
        } else {
            log.debug("Buffer is empty");
        }
    }
}
