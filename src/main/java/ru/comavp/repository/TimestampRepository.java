package ru.comavp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.comavp.model.entity.TimestampEntity;

public interface TimestampRepository extends JpaRepository<TimestampEntity, Long> {
}
