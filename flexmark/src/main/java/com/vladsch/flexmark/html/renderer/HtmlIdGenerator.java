package com.vladsch.flexmark.html.renderer;

import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface HtmlIdGenerator {
    HtmlIdGenerator NULL = new HtmlIdGenerator() {
        @Override
        public void generateIds(Document document) {

        }

        @Nullable
        @Override
        public String getId(@NotNull Node node) {
            return null;
        }

        @Nullable
        @Override
        public String getId(@NotNull CharSequence text) {
            return null;
        }
    };

    void generateIds(Document document);
    @Nullable String getId(@NotNull Node node);
    @Nullable String getId(@NotNull CharSequence text);
}
