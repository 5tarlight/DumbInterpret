package io.yeahx4.log;

public class StdLogger extends AbstractLogger {
    public StdLogger(Class<?> target) {
        super(target);
    }

    @Override
    public void debug(String message) {
        if (level.getLevel() >= LogLevel.DEBUG.getLevel())
            System.out.println(super.getDebugMessage(message, true));
    }

    @Override
    public void error(String message) {
        System.err.println(super.getErrorMessage(message, true));
    }

    @Override
    public void warn(String message) {
        if (level.getLevel() >= LogLevel.WARN.getLevel())
            System.out.println(super.getWarnMessage(message, true));
    }

    @Override
    public void info(String message) {
        if (level.getLevel() >= LogLevel.INFO.getLevel())
            System.out.println(super.getInfoMessage(message, true));
    }
}
