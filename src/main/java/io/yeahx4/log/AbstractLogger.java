package io.yeahx4.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collector;
import java.util.stream.Stream;

public abstract class AbstractLogger implements Logger {
    protected Class<?> target;

    public AbstractLogger(Class<?> target) {
        this.target = target;
    }

    protected String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
    }

    protected String getClassPrefix() {
        String fullName = target.getName();
        String className = fullName.substring(fullName.lastIndexOf(".") + 1);
        String packageName = fullName.substring(0, fullName.length() - className.length() - 1);

        if (packageName.length() > 10) {
            // Get the first character of each package name when the package name is too long
            packageName = Stream.of(fullName.split("\\."))
                    .map(it -> it.charAt(0))
                    .collect(
                            Collector.of(
                                    StringBuilder::new,
                                    StringBuilder::append,
                                    StringBuilder::append,
                                    StringBuilder::toString
                            )
                    );
        }

        return String.format("[%s.%s]", packageName, className);
    }

    protected String getPrefix() {
        return String.format("%s %s", getTimeStamp(), getClassPrefix());
    }

    protected String getErrorMessage(String message) {
        return String.format("%s %s %s", getPrefix(), "[ERROR]", message);
    }

    protected String getWarnMessage(String message) {
        return String.format("%s %s %s", getPrefix(), "[WARN]", message);
    }

    protected String getInfoMessage(String message) {
        return String.format("%s %s %s", getPrefix(), "[INFO]", message);
    }

    protected String getDebugMessage(String message) {
        return String.format("%s %s %s", getPrefix(), "[DEBUG]", message);
    }
}
