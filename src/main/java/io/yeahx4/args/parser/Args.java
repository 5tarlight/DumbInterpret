package io.yeahx4.args.parser;

import io.yeahx4.args.InvalidTargetNumberException;
import io.yeahx4.args.NullArgsValueException;
import io.yeahx4.args.TargetFileIoException;
import io.yeahx4.args.data.ArgsData;
import io.yeahx4.args.data.ArgsPair;
import io.yeahx4.args.data.ArgsValue;
import io.yeahx4.util.Filenames;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public final class Args {
    List<ArgsData> args;

    public Args(String[] args) {
        this.args = new ArrayList<>(List.of());

        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                if (i + 1 >= args.length) {
                    throw new NullArgsValueException(args[i].substring(2));
                }

                this.args.add(new ArgsPair<>(args[i].substring(2), new ArgsValue<>(args[++i])));
            } else {
                this.args.add(new ArgsValue<>(args[i]));
            }
        }

        validateArgs();
    }

    private void validateArgs() {
        int targetCnt = 0;

        for (ArgsData arg : this.args) {
            if (arg instanceof ArgsValue<?>) {
                targetCnt++;

                if (targetCnt > 1)
                    throw new InvalidTargetNumberException(false);
            }
        }

        if (targetCnt == 0)
            throw new InvalidTargetNumberException(true);
    }

    public List<ArgsData> getArgs() {
        return this.args;
    }

    public String getTarget() {
        for (ArgsData arg : this.args) {
            if (arg instanceof ArgsValue<?> value) {
                return value.value().toString();
            }
        }

        throw new InvalidTargetNumberException(true);
    }

    public File getTargetFile() {
        return new File(getTarget());
    }

    public boolean targetExists() {
        return getTargetFile().exists();
    }

    @Deprecated
    private boolean isTargetValid(File target) {
        return target.exists() &&
                target.isFile() &&
                target.canRead() &&
                Filenames.getFileType(target) == Filenames.FileType.SOURCE;
    }

    private IOException validateTargetFile(File target) {
        if (!target.exists())
            return new IOException("Target file does not exist");
        if (!target.isFile())
            return new IOException("Target is not a file");
        if (!target.canRead())
            return new IOException("Target file is not readable");
        if (Filenames.getFileType(target) != Filenames.FileType.SOURCE)
            return new IOException("Target file is not a source file");

        return null;
    }

    public List<String> readTargetContent() {
        try {
            File target = getTargetFile();
            IOException e = validateTargetFile(target);

            if (e != null)
                throw e;

            return Files.readAllLines(target.toPath());
        } catch (IOException e) {
            throw new TargetFileIoException(e);
        }
    }

    public String getOption(String key) {
        for (ArgsData arg : this.args) {
            if (arg instanceof ArgsPair<?, ?> pair && pair.key().equals(key)) {
                return pair.value().toString();
            }
        }

        return null;
    }

    public boolean hasOption(String key) {
        for (ArgsData arg : this.args) {
            if (arg instanceof ArgsPair<?, ?> pair && pair.key().equals(key)) {
                return true;
            }
        }

        return false;
    }
}
