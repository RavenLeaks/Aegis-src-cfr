/*
 * Decompiled with CFR <Could not determine version>.
 */
package net.md_5.bungee;

import net.md_5.bungee.protocol.packet.ClientStatus;
import net.md_5.bungee.protocol.packet.PluginMessage;
import net.md_5.bungee.protocol.packet.Respawn;

public class PacketConstants {
    public static final Respawn DIM1_SWITCH = new Respawn((int)1, (short)0, (long)0L, (short)0, (String)"default");
    public static final Respawn DIM2_SWITCH = new Respawn((int)-1, (short)0, (long)0L, (short)0, (String)"default");
    public static final ClientStatus CLIENT_LOGIN = new ClientStatus((byte)0);
    public static final PluginMessage FORGE_MOD_REQUEST = new PluginMessage((String)"FML", (byte[])new byte[]{0, 0, 0, 0, 0, 2}, (boolean)false);
}

