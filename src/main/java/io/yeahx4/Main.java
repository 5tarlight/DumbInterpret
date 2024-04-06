package io.yeahx4;

import io.yeahx4.args.parser.Args;
import io.yeahx4.exe.io.MainStarter;
import io.yeahx4.log.Logger;
import io.yeahx4.log.StdLogger;

public class Main {
    private static final Logger logger = new StdLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Hello World!");
        logger.info("Runtime initiated");

        Args argv = new Args(args);
        MainStarter.run(argv.readTargetContent());
    }
}
