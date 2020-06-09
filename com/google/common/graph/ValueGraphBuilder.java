/*
 * Decompiled with CFR <Could not determine version>.
 */
package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.graph.AbstractGraphBuilder;
import com.google.common.graph.ConfigurableMutableValueGraph;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.Graph;
import com.google.common.graph.Graphs;
import com.google.common.graph.MutableValueGraph;

@Beta
public final class ValueGraphBuilder<N, V>
extends AbstractGraphBuilder<N> {
    private ValueGraphBuilder(boolean directed) {
        super((boolean)directed);
    }

    public static ValueGraphBuilder<Object, Object> directed() {
        return new ValueGraphBuilder<Object, Object>((boolean)true);
    }

    public static ValueGraphBuilder<Object, Object> undirected() {
        return new ValueGraphBuilder<Object, Object>((boolean)false);
    }

    public static <N> ValueGraphBuilder<N, Object> from(Graph<N> graph) {
        return new ValueGraphBuilder<N, V>((boolean)graph.isDirected()).allowsSelfLoops((boolean)graph.allowsSelfLoops()).nodeOrder(graph.nodeOrder());
    }

    public ValueGraphBuilder<N, V> allowsSelfLoops(boolean allowsSelfLoops) {
        this.allowsSelfLoops = allowsSelfLoops;
        return this;
    }

    public ValueGraphBuilder<N, V> expectedNodeCount(int expectedNodeCount) {
        this.expectedNodeCount = Optional.of(Integer.valueOf((int)Graphs.checkNonNegative((int)expectedNodeCount)));
        return this;
    }

    public <N1 extends N> ValueGraphBuilder<N1, V> nodeOrder(ElementOrder<N1> nodeOrder) {
        ValueGraphBuilder<N1, V1> newBuilder = this.cast();
        newBuilder.nodeOrder = Preconditions.checkNotNull(nodeOrder);
        return newBuilder;
    }

    public <N1 extends N, V1 extends V> MutableValueGraph<N1, V1> build() {
        return new ConfigurableMutableValueGraph<N, V>(this);
    }

    private <N1 extends N, V1 extends V> ValueGraphBuilder<N1, V1> cast() {
        return this;
    }
}

