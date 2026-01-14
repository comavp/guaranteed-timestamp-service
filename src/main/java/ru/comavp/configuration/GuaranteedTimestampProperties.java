package ru.comavp.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "guaranteed-timestamp")
@Getter
@Setter
public class GuaranteedTimestampProperties {

    private boolean needClearDb = false;
}
