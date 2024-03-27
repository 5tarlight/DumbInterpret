package io.yeahx4.args.parser;

import io.yeahx4.args.InvalidTargetNumberException;
import io.yeahx4.args.TargetFileIoException;
import io.yeahx4.args.data.ArgsData;
import io.yeahx4.args.data.ArgsPair;
import io.yeahx4.args.data.ArgsValue;
import io.yeahx4.args.NullArgsValueException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        validateArgs(list);
        return list;
    }

    public static void validateArgs(List<ArgsData> args) {
        int targetCnt = 0;

        for (ArgsData arg : args) {
            if (arg instanceof ArgsValue<?>) {
                targetCnt++;

                if (targetCnt > 1)
                    throw new InvalidTargetNumberException(false);
            }
        }

        if (targetCnt == 0)
            throw new InvalidTargetNumberException(true);
    }

    public static File getTargetFile(List<ArgsData> args) {
        for (ArgsData arg : args) {
            if (arg instanceof ArgsValue<?> value) {
                return new File(value.value().toString());
            }
        }

        throw new InvalidTargetNumberException(true);
    }

    public static List<String> readTargetFile(File target) {
        try {
            return Files.readAllLines(target.toPath());
        } catch (IOException io) {
            throw new TargetFileIoException(io);
        }
    }

    public static Map<String, String> getPair(List<ArgsData> args) {
        Map<String, String> map = new HashMap<>(Map.of());

        for (ArgsData arg : args) {
            if (arg instanceof ArgsPair<?, ?> pair) {
                map.put(pair.key().toString(), pair.value().toString());
            }
        }

        return map;
    }

    public static boolean hasKey(List<ArgsData> args, String key) {
        for (ArgsData arg : args) {
            if (arg instanceof ArgsPair<?, ?> pair) {
                if (pair.key().equals(key)) {
                    return true;
                }
            }
        }

        return false;
    }

    private ArgsParser() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}
