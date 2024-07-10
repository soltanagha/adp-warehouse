package ua.task.adp.warehouse.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionLogHandler extends AbstractExceptionHandler {
  private static final Logger logger = LoggerFactory.getLogger(ExceptionLogHandler.class);

  @Override
  public void handleException(Exception exception) {
    logger.error("Handled exception: {}", exception.getMessage(), exception);
  }
}
