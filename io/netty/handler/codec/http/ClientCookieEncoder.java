/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.handler.codec.http;

import io.netty.handler.codec.http.cookie.Cookie;

@Deprecated
public final class ClientCookieEncoder {
    @Deprecated
    public static String encode(String name, String value) {
        return io.netty.handler.codec.http.cookie.ClientCookieEncoder.LAX.encode((String)name, (String)value);
    }

    @Deprecated
    public static String encode(io.netty.handler.codec.http.Cookie cookie) {
        return io.netty.handler.codec.http.cookie.ClientCookieEncoder.LAX.encode((Cookie)cookie);
    }

    @Deprecated
    public static String encode(io.netty.handler.codec.http.Cookie ... cookies) {
        return io.netty.handler.codec.http.cookie.ClientCookieEncoder.LAX.encode((Cookie[])cookies);
    }

    @Deprecated
    public static String encode(Iterable<io.netty.handler.codec.http.Cookie> cookies) {
        return io.netty.handler.codec.http.cookie.ClientCookieEncoder.LAX.encode(cookies);
    }

    private ClientCookieEncoder() {
    }
}

