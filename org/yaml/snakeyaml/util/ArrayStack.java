/*
 * Decompiled with CFR <Could not determine version>.
 */
package org.yaml.snakeyaml.util;

import java.util.ArrayList;

public class ArrayStack<T> {
    private ArrayList<T> stack;

    public ArrayStack(int initSize) {
        this.stack = new ArrayList<E>((int)initSize);
    }

    public void push(T obj) {
        this.stack.add(obj);
    }

    public T pop() {
        return (T)this.stack.remove((int)(this.stack.size() - 1));
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    public void clear() {
        this.stack.clear();
    }
}

