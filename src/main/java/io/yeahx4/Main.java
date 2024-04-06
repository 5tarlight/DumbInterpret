package io.yeahx4;

import io.yeahx4.args.parser.Args;
import io.yeahx4.exe.io.MainStarter;
import io.yeahx4.log.AbstractLogger;
import io.yeahx4.log.Logger;
import io.yeahx4.log.StdLogger;

import java.util.Objects;

public class Main {
    private static final Logger logger = new StdLogger(Main.class);

    public static void main(String[] args) {
        Args argv = new Args(args);

        // TODO : Move this to args package
        if (argv.hasOption("log")) {
            int logLevel = Integer.parseInt(Objects.requireNonNull(argv.getOption("log")));
            AbstractLogger.level = AbstractLogger.LogLevel.values()[logLevel];
            System.out.println("Log level set to " + AbstractLogger.level.name() + " (" + logLevel + ")");
        }

        logger.info("Hello World!");
        logger.info("Runtime initiated");
        logger.debug("Arguments: " + argv.getArgs());

        MainStarter.run(argv.readTargetContent());
    }
}
