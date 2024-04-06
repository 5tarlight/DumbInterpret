package io.yeahx4.log;

public interface Logger {
    void error(String message);
    void warn(String message);
    void info(String message);
    void debug(String message);
}
