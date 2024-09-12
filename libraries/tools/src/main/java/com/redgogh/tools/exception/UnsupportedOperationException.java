package com.redgogh.tools.exception;

import org.jetbrains.annotations.NotNull;

public class UnsupportedOperationException extends KeepLiveRuntimeException {

    public UnsupportedOperationException() {
    }

    public UnsupportedOperationException(@NotNull Throwable e) {
        super(e);
    }

    public UnsupportedOperationException(@NotNull String vfmt, @NotNull Object... args) {
        super(vfmt, args);
    }

    public UnsupportedOperationException(@NotNull String vfmt, @NotNull Throwable e, @NotNull Object... args) {
        super(vfmt, e, args);
    }

}
