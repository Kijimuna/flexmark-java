package com.vladsch.flexmark.ext.zzzzzz;

import com.vladsch.flexmark.util.ast.VisitHandler;
import com.vladsch.flexmark.util.ast.Visitor;

public class ZzzzzzVisitorExt {
    public static <V extends ZzzzzzVisitor> VisitHandler<?>[] VISIT_HANDLERS(final V visitor) {
        return new VisitHandler<?>[] {
// @formatter:off
                new VisitHandler<Zzzzzz>(Zzzzzz.class, new Visitor<Zzzzzz>() { @Override public void visit(Zzzzzz node) { visitor.visit(node); } }),// zzzoptionszzz(CUSTOM_NODE)
                new VisitHandler<ZzzzzzBlock>(ZzzzzzBlock.class, new Visitor<ZzzzzzBlock>() { @Override public void visit(ZzzzzzBlock node) { visitor.visit(node); } }),// zzzoptionszzz(CUSTOM_BLOCK_NODE)
 // @formatter:on
        };
    }
}
