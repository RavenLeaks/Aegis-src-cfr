/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.resolver;

import io.netty.resolver.DefaultHostsFileEntriesResolver;
import io.netty.resolver.ResolvedAddressTypes;
import java.net.InetAddress;

public interface HostsFileEntriesResolver {
    public static final HostsFileEntriesResolver DEFAULT = new DefaultHostsFileEntriesResolver();

    public InetAddress address(String var1, ResolvedAddressTypes var2);
}

