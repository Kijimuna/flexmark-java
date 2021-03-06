package com.vladsch.flexmark.ext.admonition.internal;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.VisitHandler;
import com.vladsch.flexmark.util.ast.Visitor;
import com.vladsch.flexmark.ext.admonition.AdmonitionBlock;

import java.util.LinkedHashSet;
import java.util.Set;

@SuppressWarnings("WeakerAccess")
public class AdmonitionCollectingVisitor {
    private LinkedHashSet<String> qualifiers;
    private final NodeVisitor myVisitor;

    public AdmonitionCollectingVisitor() {
        myVisitor = new NodeVisitor(
                new VisitHandler<AdmonitionBlock>(AdmonitionBlock.class, new Visitor<AdmonitionBlock>() {
                    @Override
                    public void visit(AdmonitionBlock node) {
                        AdmonitionCollectingVisitor.this.visit(node);
                    }
                })
        );
    }

    public LinkedHashSet<String> getQualifiers() {
        return qualifiers;
    }

    public void collect(Node node) {
        qualifiers = new LinkedHashSet<String>();
        myVisitor.visit(node);
    }

    public Set<String> collectAndGetQualifiers(Node node) {
        collect(node);
        return qualifiers;
    }

    void visit(AdmonitionBlock node) {
        qualifiers.add(node.getInfo().toString());
    }

}
