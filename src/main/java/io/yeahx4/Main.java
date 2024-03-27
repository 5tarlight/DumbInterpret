package io.yeahx4;

import io.yeahx4.args.data.ArgsData;
import io.yeahx4.args.parser.ArgsParser;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<ArgsData> argv = ArgsParser.parse(args);
        File target = ArgsParser.getTargetFile(argv);
        List<String> lines = ArgsParser.readTargetFile(target);

        lines.forEach(System.out::println);
    }
}
