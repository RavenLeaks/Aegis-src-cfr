/*
 * Decompiled with CFR <Could not determine version>.
 * 
 * Could not load the following classes:
 *  edu.umd.cs.findbugs.annotations.SuppressFBWarnings
 */
package net.md_5.bungee.entitymap;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.conf.Configuration;
import net.md_5.bungee.connection.InitialHandler;
import net.md_5.bungee.entitymap.EntityMap;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.ProtocolConstants;

class EntityMap_1_12
extends EntityMap {
    static final EntityMap_1_12 INSTANCE = new EntityMap_1_12();

    EntityMap_1_12() {
        this.addRewrite((int)0, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)1, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)3, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)4, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)5, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)6, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)8, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)27, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)false);
        this.addRewrite((int)37, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)38, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)39, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)40, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)47, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)50, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)53, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)56, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)59, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)60, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)false);
        this.addRewrite((int)61, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)62, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)66, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)74, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)75, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)77, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)78, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_CLIENT, (boolean)true);
        this.addRewrite((int)11, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_SERVER, (boolean)true);
        this.addRewrite((int)21, (ProtocolConstants.Direction)ProtocolConstants.Direction.TO_SERVER, (boolean)true);
    }

    @SuppressFBWarnings(value={"DLS_DEAD_LOCAL_STORE"})
    @Override
    public void rewriteClientbound(ByteBuf packet, int oldId, int newId) {
        super.rewriteClientbound((ByteBuf)packet, (int)oldId, (int)newId);
        int readerIndex = packet.readerIndex();
        int packetId = DefinedPacket.readVarInt((ByteBuf)packet);
        int packetIdLength = packet.readerIndex() - readerIndex;
        int jumpIndex = packet.readerIndex();
        switch (packetId) {
            case 60: {
                EntityMap_1_12.rewriteInt((ByteBuf)packet, (int)oldId, (int)newId, (int)(readerIndex + packetIdLength + 4));
                break;
            }
            case 74: {
                DefinedPacket.readVarInt((ByteBuf)packet);
                EntityMap_1_12.rewriteVarInt((ByteBuf)packet, (int)oldId, (int)newId, (int)packet.readerIndex());
                break;
            }
            case 66: {
                DefinedPacket.readVarInt((ByteBuf)packet);
                jumpIndex = packet.readerIndex();
            }
            case 49: {
                int count = DefinedPacket.readVarInt((ByteBuf)packet);
                int[] ids = new int[count];
                for (int i = 0; i < count; ++i) {
                    ids[i] = DefinedPacket.readVarInt((ByteBuf)packet);
                }
                packet.readerIndex((int)jumpIndex);
                packet.writerIndex((int)jumpIndex);
                DefinedPacket.writeVarInt((int)count, (ByteBuf)packet);
                for (int id : ids) {
                    if (id == oldId) {
                        id = newId;
                    } else if (id == newId) {
                        id = oldId;
                    }
                    DefinedPacket.writeVarInt((int)id, (ByteBuf)packet);
                }
                break;
            }
            case 0: {
                DefinedPacket.readVarInt((ByteBuf)packet);
                DefinedPacket.readUUID((ByteBuf)packet);
                short type = packet.readUnsignedByte();
                if (type != 60 && type != 90 && type != 91) break;
                if (type == 60 || type == 91) {
                    ++oldId;
                    ++newId;
                }
                packet.skipBytes((int)26);
                int position = packet.readerIndex();
                int readId = packet.readInt();
                if (readId == oldId) {
                    packet.setInt((int)position, (int)newId);
                    break;
                }
                if (readId != newId) break;
                packet.setInt((int)position, (int)oldId);
                break;
            }
            case 5: {
                DefinedPacket.readVarInt((ByteBuf)packet);
                int idLength = packet.readerIndex() - readerIndex - packetIdLength;
                UUID uuid = DefinedPacket.readUUID((ByteBuf)packet);
                UserConnection player = BungeeCord.getInstance().getPlayerByOfflineUUID((UUID)uuid);
                if (player == null) break;
                int previous = packet.writerIndex();
                packet.readerIndex((int)readerIndex);
                packet.writerIndex((int)(readerIndex + packetIdLength + idLength));
                DefinedPacket.writeUUID((UUID)player.getUniqueId(), (ByteBuf)packet);
                packet.writerIndex((int)previous);
                break;
            }
            case 44: {
                short event = packet.readUnsignedByte();
                if (event == 1) {
                    DefinedPacket.readVarInt((ByteBuf)packet);
                    EntityMap_1_12.rewriteInt((ByteBuf)packet, (int)oldId, (int)newId, (int)packet.readerIndex());
                    break;
                }
                if (event != 2) break;
                int position = packet.readerIndex();
                EntityMap_1_12.rewriteVarInt((ByteBuf)packet, (int)oldId, (int)newId, (int)packet.readerIndex());
                packet.readerIndex((int)position);
                DefinedPacket.readVarInt((ByteBuf)packet);
                EntityMap_1_12.rewriteInt((ByteBuf)packet, (int)oldId, (int)newId, (int)packet.readerIndex());
                break;
            }
            case 59: {
                DefinedPacket.readVarInt((ByteBuf)packet);
                EntityMap_1_12.rewriteMetaVarInt((ByteBuf)packet, (int)(oldId + 1), (int)(newId + 1), (int)6);
                EntityMap_1_12.rewriteMetaVarInt((ByteBuf)packet, (int)oldId, (int)newId, (int)7);
            }
        }
        packet.readerIndex((int)readerIndex);
    }

    @Override
    public void rewriteServerbound(ByteBuf packet, int oldId, int newId) {
        super.rewriteServerbound((ByteBuf)packet, (int)oldId, (int)newId);
        int readerIndex = packet.readerIndex();
        int packetId = DefinedPacket.readVarInt((ByteBuf)packet);
        int packetIdLength = packet.readerIndex() - readerIndex;
        if (packetId == 30 && !BungeeCord.getInstance().getConfig().isIpForward()) {
            UUID uuid = DefinedPacket.readUUID((ByteBuf)packet);
            ProxiedPlayer player = BungeeCord.getInstance().getPlayer((UUID)uuid);
            if (player != null) {
                int previous = packet.writerIndex();
                packet.readerIndex((int)readerIndex);
                packet.writerIndex((int)(readerIndex + packetIdLength));
                DefinedPacket.writeUUID((UUID)((UserConnection)player).getPendingConnection().getOfflineId(), (ByteBuf)packet);
                packet.writerIndex((int)previous);
            }
        }
        packet.readerIndex((int)readerIndex);
    }
}

