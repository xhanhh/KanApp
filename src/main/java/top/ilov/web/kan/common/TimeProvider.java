package top.ilov.web.kan.common;

import java.time.Instant;

public interface TimeProvider {

    default long getCurrentTime() {
        return Instant.now().getEpochSecond();
    }

}
