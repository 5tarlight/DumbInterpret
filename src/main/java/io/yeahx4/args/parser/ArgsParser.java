package io.yeahx4.args.parser;

import io.yeahx4.args.data.ArgsData;
import io.yeahx4.args.data.ArgsPair;
import io.yeahx4.args.data.ArgsValue;
import io.yeahx4.args.NullArgsValueException;

import java.util.ArrayList;
import java.util.List;

public final class ArgsParser {
    public static List<ArgsData> parse(String[] args) {
        List<ArgsData> list = new ArrayList<>(List.of());

        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                if (i + 1 >= args.length) {
                    throw new NullArgsValueException(args[i].substring(2));
                }

                list.add(new ArgsPair<>(args[i].substring(2), new ArgsValue<>(args[++i])));
            } else {
                list.add(new ArgsValue<>(args[i]));
            }
        }

        return list;
    }

    private ArgsParser() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}
