/*
 * Decompiled with CFR <Could not determine version>.
 */
package com.flowpowered.nbt;

import com.flowpowered.nbt.Tag;
import com.flowpowered.nbt.TagType;

public final class StringTag
extends Tag<String> {
    private final String value;

    public StringTag(String name, String value) {
        super((TagType)TagType.TAG_STRING, (String)name);
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public String toString() {
        String name = this.getName();
        String append = "";
        if (name == null) return "TAG_String" + append + ": " + this.value;
        if (name.equals((Object)"")) return "TAG_String" + append + ": " + this.value;
        append = "(\"" + this.getName() + "\")";
        return "TAG_String" + append + ": " + this.value;
    }

    @Override
    public StringTag clone() {
        return new StringTag((String)this.getName(), (String)this.value);
    }
}

