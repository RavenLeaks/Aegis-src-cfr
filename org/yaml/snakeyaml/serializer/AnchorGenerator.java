/*
 * Decompiled with CFR <Could not determine version>.
 */
package org.yaml.snakeyaml.serializer;

import org.yaml.snakeyaml.nodes.Node;

public interface AnchorGenerator {
    public String nextAnchor(Node var1);
}

