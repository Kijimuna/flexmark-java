package com.vladsch.flexmark.util.collection.iteration;

import org.jetbrains.annotations.NotNull;

import java.util.BitSet;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class BitSetIterator implements ReversibleIterator<Integer> {
    private final @NotNull BitSet myBitSet;
    private final boolean myIsReversed;
    private int myNext;
    private int myLast;

    public BitSetIterator(@NotNull BitSet bitSet) {
        this(bitSet, false);
    }

    public BitSetIterator(@NotNull BitSet bitSet, boolean reversed) {
        myBitSet = bitSet;
        myIsReversed = reversed;
        myNext = reversed ? bitSet.previousSetBit(bitSet.length()) : bitSet.nextSetBit(0);
        myLast = -1;
    }

    @Override
    public boolean isReversed() {
        return myIsReversed;
    }

    @Override
    public boolean hasNext() {
        return myNext != -1;
    }

    @Override
    public Integer next() {
        if (myNext == -1) {
            throw new NoSuchElementException();
        }

        myLast = myNext;
        myNext = myIsReversed ? (myNext == 0 ? -1 : myBitSet.previousSetBit(myNext - 1)) : myBitSet.nextSetBit(myNext + 1);
        return myLast;
    }

    @Override
    public void remove() {
        if (myLast == -1) {
            throw new NoSuchElementException();
        }

        myBitSet.clear(myLast);
    }

    public void forEachRemaining(@NotNull Consumer<? super Integer> consumer) {
        while (hasNext()) {
            consumer.accept(next());
        }
    }
}
