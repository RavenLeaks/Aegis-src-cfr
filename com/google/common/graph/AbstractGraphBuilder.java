/*
 * Decompiled with CFR <Could not determine version>.
 */
package com.google.common.graph;

import com.google.common.base.Optional;
import com.google.common.graph.ElementOrder;

abstract class AbstractGraphBuilder<N> {
    final boolean directed;
    boolean allowsSelfLoops = false;
    ElementOrder<N> nodeOrder = ElementOrder.insertion();
    Optional<Integer> expectedNodeCount = Optional.absent();

    AbstractGraphBuilder(boolean directed) {
        this.directed = directed;
    }
}

