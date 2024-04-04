package io.yeahx4;

import io.yeahx4.args.parser.Args;

public class Main {
    public static void main(String[] args) {
        Args argv = new Args(args);
        argv.readTargetContent().forEach(System.out::println);
    }
}
