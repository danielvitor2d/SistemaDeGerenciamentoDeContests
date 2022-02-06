package gemp.github;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@Log4j2
@SpringBootApplication
public class ContestManagerApplication {
    public static void main(String[] args) {
        log.info("ContestManagerAPI started successfully at {}", LocalDateTime.now());
    }
}
