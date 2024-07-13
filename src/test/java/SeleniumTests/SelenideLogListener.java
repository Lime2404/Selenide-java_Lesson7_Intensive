package SeleniumTests;

import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// 7. Output all actions to the console using the Log4j library
public class SelenideLogListener implements LogEventListener {
    private static final Logger logger = LogManager.getLogger(SelenideLogListener.class);

    public void onEvent(LogEvent event) {
        switch (event.getStatus()) {
            case PASS:
                logger.info("PASS: {} in {} ms", event.getSubject(), event.getDuration());
                break;
            case FAIL:
                logger.error("FAIL: {} in {} ms", event.getSubject(), event.getDuration());
                break;
            default:
                logger.info("Event: {} in {} ms", event.getSubject(), event.getDuration());
                break;
        }
    }

    @Override
    public void afterEvent(LogEvent event) {
        // можно оставить пустым, если не требуется дополнительная обработка после события
    }

    @Override
    public void beforeEvent(LogEvent logEvent) {
//        logger.info("Before event processing for: {}", event.getSubject());
    }
}
