/*
 * Decompiled with CFR <Could not determine version>.
 */
package com.mysql.jdbc;

import com.mysql.jdbc.ConnectionImpl;
import com.mysql.jdbc.Messages;
import com.mysql.jdbc.SocketFactory;
import com.mysql.jdbc.SocketMetadata;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class StandardSocketFactory
implements SocketFactory,
SocketMetadata {
    public static final String TCP_NO_DELAY_PROPERTY_NAME = "tcpNoDelay";
    public static final String TCP_KEEP_ALIVE_DEFAULT_VALUE = "true";
    public static final String TCP_KEEP_ALIVE_PROPERTY_NAME = "tcpKeepAlive";
    public static final String TCP_RCV_BUF_PROPERTY_NAME = "tcpRcvBuf";
    public static final String TCP_SND_BUF_PROPERTY_NAME = "tcpSndBuf";
    public static final String TCP_TRAFFIC_CLASS_PROPERTY_NAME = "tcpTrafficClass";
    public static final String TCP_RCV_BUF_DEFAULT_VALUE = "0";
    public static final String TCP_SND_BUF_DEFAULT_VALUE = "0";
    public static final String TCP_TRAFFIC_CLASS_DEFAULT_VALUE = "0";
    public static final String TCP_NO_DELAY_DEFAULT_VALUE = "true";
    protected String host = null;
    protected int port = 3306;
    protected Socket rawSocket = null;
    protected int loginTimeoutCountdown = DriverManager.getLoginTimeout() * 1000;
    protected long loginTimeoutCheckTimestamp = System.currentTimeMillis();
    protected int socketTimeoutBackup = 0;

    @Override
    public Socket afterHandshake() throws SocketException, IOException {
        this.resetLoginTimeCountdown();
        this.rawSocket.setSoTimeout((int)this.socketTimeoutBackup);
        return this.rawSocket;
    }

    @Override
    public Socket beforeHandshake() throws SocketException, IOException {
        this.resetLoginTimeCountdown();
        this.socketTimeoutBackup = this.rawSocket.getSoTimeout();
        this.rawSocket.setSoTimeout((int)this.getRealTimeout((int)this.socketTimeoutBackup));
        return this.rawSocket;
    }

    protected Socket createSocket(Properties props) {
        return new Socket();
    }

    private void configureSocket(Socket sock, Properties props) throws SocketException, IOException {
        int receiveBufferSize;
        int trafficClass;
        int sendBufferSize;
        sock.setTcpNoDelay((boolean)Boolean.valueOf((String)props.getProperty((String)TCP_NO_DELAY_PROPERTY_NAME, (String)"true")).booleanValue());
        String keepAlive = props.getProperty((String)TCP_KEEP_ALIVE_PROPERTY_NAME, (String)"true");
        if (keepAlive != null && keepAlive.length() > 0) {
            sock.setKeepAlive((boolean)Boolean.valueOf((String)keepAlive).booleanValue());
        }
        if ((receiveBufferSize = Integer.parseInt((String)props.getProperty((String)TCP_RCV_BUF_PROPERTY_NAME, (String)"0"))) > 0) {
            sock.setReceiveBufferSize((int)receiveBufferSize);
        }
        if ((sendBufferSize = Integer.parseInt((String)props.getProperty((String)TCP_SND_BUF_PROPERTY_NAME, (String)"0"))) > 0) {
            sock.setSendBufferSize((int)sendBufferSize);
        }
        if ((trafficClass = Integer.parseInt((String)props.getProperty((String)TCP_TRAFFIC_CLASS_PROPERTY_NAME, (String)"0"))) <= 0) return;
        sock.setTrafficClass((int)trafficClass);
    }

    @Override
    public Socket connect(String hostname, int portNumber, Properties props) throws SocketException, IOException {
        if (props == null) throw new SocketException((String)"Unable to create socket");
        this.host = hostname;
        this.port = portNumber;
        String localSocketHostname = props.getProperty((String)"localSocketAddress");
        InetSocketAddress localSockAddr = null;
        if (localSocketHostname != null && localSocketHostname.length() > 0) {
            localSockAddr = new InetSocketAddress((InetAddress)InetAddress.getByName((String)localSocketHostname), (int)0);
        }
        String connectTimeoutStr = props.getProperty((String)"connectTimeout");
        int connectTimeout = 0;
        if (connectTimeoutStr != null) {
            try {
                connectTimeout = Integer.parseInt((String)connectTimeoutStr);
            }
            catch (NumberFormatException nfe) {
                throw new SocketException((String)("Illegal value '" + connectTimeoutStr + "' for connectTimeout"));
            }
        }
        if (this.host == null) throw new SocketException((String)"Unable to create socket");
        InetAddress[] possibleAddresses = InetAddress.getAllByName((String)this.host);
        if (possibleAddresses.length == 0) {
            throw new SocketException((String)"No addresses for host");
        }
        SocketException lastException = null;
        for (int i = 0; i < possibleAddresses.length; ++i) {
            try {
                this.rawSocket = this.createSocket((Properties)props);
                this.configureSocket((Socket)this.rawSocket, (Properties)props);
                InetSocketAddress sockAddr = new InetSocketAddress((InetAddress)possibleAddresses[i], (int)this.port);
                if (localSockAddr != null) {
                    this.rawSocket.bind((SocketAddress)localSockAddr);
                }
                this.rawSocket.connect((SocketAddress)sockAddr, (int)this.getRealTimeout((int)connectTimeout));
                break;
            }
            catch (SocketException ex) {
                lastException = ex;
                this.resetLoginTimeCountdown();
                this.rawSocket = null;
                continue;
            }
        }
        if (this.rawSocket == null && lastException != null) {
            throw lastException;
        }
        this.resetLoginTimeCountdown();
        return this.rawSocket;
    }

    @Override
    public boolean isLocallyConnected(ConnectionImpl conn) throws SQLException {
        return SocketMetadata.Helper.isLocallyConnected((ConnectionImpl)conn);
    }

    protected void resetLoginTimeCountdown() throws SocketException {
        if (this.loginTimeoutCountdown <= 0) return;
        long now = System.currentTimeMillis();
        this.loginTimeoutCountdown = (int)((long)this.loginTimeoutCountdown - (now - this.loginTimeoutCheckTimestamp));
        if (this.loginTimeoutCountdown <= 0) {
            throw new SocketException((String)Messages.getString((String)"Connection.LoginTimeout"));
        }
        this.loginTimeoutCheckTimestamp = now;
    }

    protected int getRealTimeout(int expectedTimeout) {
        if (this.loginTimeoutCountdown <= 0) return expectedTimeout;
        if (expectedTimeout == 0) return this.loginTimeoutCountdown;
        if (expectedTimeout <= this.loginTimeoutCountdown) return expectedTimeout;
        return this.loginTimeoutCountdown;
    }
}

