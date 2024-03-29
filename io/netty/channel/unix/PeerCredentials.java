/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.channel.unix;

import io.netty.util.internal.EmptyArrays;

public final class PeerCredentials {
    private final int pid;
    private final int uid;
    private final int[] gids;

    PeerCredentials(int p, int u, int ... gids) {
        this.pid = p;
        this.uid = u;
        this.gids = gids == null ? EmptyArrays.EMPTY_INTS : gids;
    }

    public int pid() {
        return this.pid;
    }

    public int uid() {
        return this.uid;
    }

    public int[] gids() {
        return (int[])this.gids.clone();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((int)128);
        sb.append((String)"UserCredentials[pid=").append((int)this.pid).append((String)"; uid=").append((int)this.uid).append((String)"; gids=[");
        if (this.gids.length > 0) {
            sb.append((int)this.gids[0]);
            for (int i = 1; i < this.gids.length; ++i) {
                sb.append((String)", ").append((int)this.gids[i]);
            }
        }
        sb.append((char)']');
        return sb.toString();
    }
}

