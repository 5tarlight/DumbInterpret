package io.yeahx4;

import io.yeahx4.args.parser.Args;
import io.yeahx4.exe.io.MainStarter;

public class Main {
    public static void main(String[] args) {
        Args argv = new Args(args);
        MainStarter.run(argv.readTargetContent());
    }
}
