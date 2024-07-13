package SeleniumTests;

import com.codeborne.selenide.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SelenideConfig {
    private static final Logger logger = LogManager.getLogger(SelenideConfig.class);

    public static void setup() {
        Configuration.browser = "chrome";
        logger.info("Selenide configuration is set up.");
    }
}