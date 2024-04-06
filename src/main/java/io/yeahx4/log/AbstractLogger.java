package io.yeahx4.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collector;
import java.util.stream.Stream;

public abstract class AbstractLogger implements Logger {
    protected class ColorScheme {
        public static final String RESET = "\u001B[0m";
        public static final String BLACK = "\u001B[30m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String BLUE = "\u001B[34m";
        public static final String PURPLE = "\u001B[35m";
        public static final String CYAN = "\u001B[36m";
        public static final String WHITE = "\u001B[37m";
        public static final String GRAY = "\u001B[90m";
        public static final String BOLD = "\u001B[1m";
        public static final String UNDERLINE = "\u001B[4m";

        public static String colorize(String message, String color) {
            return color + message + RESET;
        }
    }

    protected Class<?> target;

    public AbstractLogger(Class<?> target) {
        this.target = target;
    }

    protected String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
    }

    protected String getTimeStamp(boolean color) {
        return color ? ColorScheme.colorize(getTimeStamp(), ColorScheme.PURPLE) : getTimeStamp();
    }

    protected String getClassPrefix(boolean color) {
        String fullName = target.getName();
        String className = fullName.substring(fullName.lastIndexOf(".") + 1);
        String packageName = fullName.substring(0, fullName.length() - className.length() - 1);

        if (packageName.length() > 10) {
            // Get the first character of each package name when the package name is too long
            packageName = Stream.of(fullName.split("\\."))
//                    .map(it -> it.charAt(0) + '.')
                    .map(it -> new String(new char[] { it.charAt(0), '.' }))
                    .collect(
                            Collector.of(
                                    StringBuilder::new,
                                    StringBuilder::append,
                                    StringBuilder::append,
                                    StringBuilder::toString
                            )
                    );
        }

        if (color) {
            return String.format("[%s.%s]", ColorScheme.colorize(packageName, ColorScheme.CYAN), ColorScheme.colorize(className, ColorScheme.BOLD));
        } else {
            return String.format("[%s.%s]", packageName, className);
        }
    }

    protected String getLogType(String type, boolean color) {
        if (color) {
            return switch (type) {
                case "ERROR" -> ColorScheme.colorize(type, ColorScheme.RED + ColorScheme.BOLD + ColorScheme.UNDERLINE);
                case "WARN" -> ColorScheme.colorize(type, ColorScheme.YELLOW + ColorScheme.BOLD + ColorScheme.UNDERLINE);
                case "INFO" -> ColorScheme.colorize(type, ColorScheme.GREEN + ColorScheme.BOLD);
                case "DEBUG" -> ColorScheme.colorize(type, ColorScheme.GRAY + ColorScheme.BOLD);
                default -> type;
            };
        } else {
            return type;
        }
    }

    protected String getClassPrefix() {
        return getClassPrefix(false);
    }

    protected String getPrefix(boolean color) {
        return String.format("%s %s", getTimeStamp(color), getClassPrefix(color));
    }

    protected String getPrefix() {
        return getPrefix(false);
    }

    protected String getErrorMessage(String message, boolean color) {
        return String.format("%s %s %s", getPrefix(color), getLogType("ERROR", color), message);
    }

    protected String getErrorMessage(String message) {
        return getErrorMessage(message, false);
    }

    protected String getWarnMessage(String message, boolean color) {
        return String.format("%s %s %s", getPrefix(color), getLogType("WARN", color), message);
    }

    protected String getWarnMessage(String message) {
        return getWarnMessage(message, false);
    }

    protected String getInfoMessage(String message, boolean color) {
        return String.format("%s %s %s", getPrefix(color), getLogType("INFO", color), message);
    }

    protected String getInfoMessage(String message) {
        return getInfoMessage(message, false);
    }

    protected String getDebugMessage(String message, boolean color) {
        return String.format("%s %s %s", getPrefix(color), getLogType("DEBUG", color), message);
    }

    protected String getDebugMessage(String message) {
        return getDebugMessage(message, false);
    }
}
