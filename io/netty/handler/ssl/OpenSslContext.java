/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.handler.ssl;

import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.ssl.ApplicationProtocolConfig;
import io.netty.handler.ssl.CipherSuiteFilter;
import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.OpenSsl;
import io.netty.handler.ssl.OpenSslApplicationProtocolNegotiator;
import io.netty.handler.ssl.OpenSslEngine;
import io.netty.handler.ssl.ReferenceCountedOpenSslContext;
import io.netty.util.ReferenceCounted;
import java.security.cert.Certificate;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;

public abstract class OpenSslContext
extends ReferenceCountedOpenSslContext {
    OpenSslContext(Iterable<String> ciphers, CipherSuiteFilter cipherFilter, ApplicationProtocolConfig apnCfg, long sessionCacheSize, long sessionTimeout, int mode, Certificate[] keyCertChain, ClientAuth clientAuth, String[] protocols, boolean startTls, boolean enableOcsp) throws SSLException {
        super(ciphers, (CipherSuiteFilter)cipherFilter, (ApplicationProtocolConfig)apnCfg, (long)sessionCacheSize, (long)sessionTimeout, (int)mode, (Certificate[])keyCertChain, (ClientAuth)clientAuth, (String[])protocols, (boolean)startTls, (boolean)enableOcsp, (boolean)false);
    }

    OpenSslContext(Iterable<String> ciphers, CipherSuiteFilter cipherFilter, OpenSslApplicationProtocolNegotiator apn, long sessionCacheSize, long sessionTimeout, int mode, Certificate[] keyCertChain, ClientAuth clientAuth, String[] protocols, boolean startTls, boolean enableOcsp) throws SSLException {
        super(ciphers, (CipherSuiteFilter)cipherFilter, (OpenSslApplicationProtocolNegotiator)apn, (long)sessionCacheSize, (long)sessionTimeout, (int)mode, (Certificate[])keyCertChain, (ClientAuth)clientAuth, (String[])protocols, (boolean)startTls, (boolean)enableOcsp, (boolean)false);
    }

    @Override
    final SSLEngine newEngine0(ByteBufAllocator alloc, String peerHost, int peerPort, boolean jdkCompatibilityMode) {
        return new OpenSslEngine((OpenSslContext)this, (ByteBufAllocator)alloc, (String)peerHost, (int)peerPort, (boolean)jdkCompatibilityMode);
    }

    protected final void finalize() throws Throwable {
        Object.super.finalize();
        OpenSsl.releaseIfNeeded((ReferenceCounted)this);
    }
}

