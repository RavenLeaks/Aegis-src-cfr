/*
 * Decompiled with CFR <Could not determine version>.
 */
package com.mysql.jdbc;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Properties;

public interface SocketFactory {
    public Socket afterHandshake() throws SocketException, IOException;

    public Socket beforeHandshake() throws SocketException, IOException;

    public Socket connect(String var1, int var2, Properties var3) throws SocketException, IOException;
}

