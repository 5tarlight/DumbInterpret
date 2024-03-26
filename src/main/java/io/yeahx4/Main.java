package io.yeahx4;

import io.yeahx4.args.parser.ArgsParser;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArgsParser.parse(args).forEach(System.out::println);
    }
}
