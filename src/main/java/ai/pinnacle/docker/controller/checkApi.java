package ai.pinnacle.docker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class checkApi {
    private static final Logger logger = LoggerFactory.getLogger(checkApi.class);

    @GetMapping("/hit")
    public void getHit() {
        logger.info("API endpoint /hit was hit.");
    }
}
