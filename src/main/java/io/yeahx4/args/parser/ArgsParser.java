package io.yeahx4.args.parser;

import io.yeahx4.args.ArgsData;
import io.yeahx4.args.ArgsPair;
import io.yeahx4.args.ArgsValue;
import io.yeahx4.args.NullArgsException;

import java.util.List;

public final class ArgsParser {
    public static List<ArgsData> parse(String[] args) {
        List<ArgsData> list = new java.util.ArrayList<>(List.of());

        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                if (i + 1 >= args.length) {
                    throw new NullArgsException(args[i].substring(2));
                }

                list.add(new ArgsPair<>(args[i].substring(2), new ArgsValue<>(args[++i])));
            } else {
                list.add(new ArgsValue<>(args[i]));
            }
        }

        return list;
    }
}
