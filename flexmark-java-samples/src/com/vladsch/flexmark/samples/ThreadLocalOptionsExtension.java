package com.vladsch.flexmark.samples;

import com.vladsch.flexmark.html.RendererBuilder;
import com.vladsch.flexmark.html.RendererExtension;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.builder.Extension;
import com.vladsch.flexmark.util.options.DataSet;
import com.vladsch.flexmark.util.options.MutableDataHolder;

public class ThreadLocalOptionsExtension implements
        Parser.ParserExtension,
        RendererExtension
{
    private static final ThreadLocal<MutableDataHolder> threadOptions = new ThreadLocal<>();

    private ThreadLocalOptionsExtension() {
    }

    /**
     * Set the per thread options for flexmark-java.
     *
     * Must be called from the thread to set options to be set for
     * all flexmark-java code which uses this extension.
     *
     * CAUTION: Do not try to make mutable data values shared.
     * setAll() copies values of keys so immutable data valued
     * keys: Boolean, String, Integer, enums, etc., are good to
     * go as is.
     *
     * For mutable values you will need to add extra code to address
     * these or you will be hunting down overwrites of data from
     * another thread.
     *
     * @param perThreadOptions data set of the per thread options
     */
    public static void setThreadOptions(DataSet perThreadOptions) {
        threadOptions.get().setAll(perThreadOptions);
    }

    public static Extension create() {
        return new ThreadLocalOptionsExtension();
    }

    @Override
    public void extend(Parser.Builder parserBuilder) {
    }

    @Override
    public void parserOptions(final MutableDataHolder options) {
        // copy thread local options to builder
        options.setAll(threadOptions.get());
    }

    @Override
    public void rendererOptions(final MutableDataHolder options) {
        // copy thread local options to builder
        options.setAll(threadOptions.get());
    }

    @Override
    public void extend(final RendererBuilder rendererBuilder, final String rendererType) {

    }
}
