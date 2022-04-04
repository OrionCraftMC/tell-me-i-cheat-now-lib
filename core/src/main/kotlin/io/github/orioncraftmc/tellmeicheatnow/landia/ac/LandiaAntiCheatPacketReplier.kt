package io.github.orioncraftmc.tellmeicheatnow.landia.ac

import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaPacketManager
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacketReplier.handleClientDataRequestPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request.LandiaClientDataRequestPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request.LandiaGameHashRequestPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.respose.LandiaClientDataResponsePacket
import io.github.orioncraftmc.tellmeicheatnow.landia.reply.ChatReplyAction
import io.github.orioncraftmc.tellmeicheatnow.model.reply.PacketReplyAction
import io.github.orioncraftmc.tellmeicheatnow.model.reply.ReplyAction

internal object LandiaAntiCheatPacketReplier {

    internal fun LandiaPacketManager.handleGameHashRequestPacket(packet: LandiaGameHashRequestPacket): ReplyAction {
        invokeListeners { this.handleGameHashRequestPacket(packet) }
        return ChatReplyAction("${packet.responsePrefix}${constants.antiHackReplyId}/${constants.clientJarHash}")
    }

    internal fun LandiaPacketManager.handleClientDataRequestPacket(packet: LandiaClientDataRequestPacket): ReplyAction {
        invokeListeners { this.handleClientDataRequestPacket(packet) }
        return PacketReplyAction(LandiaClientDataResponsePacket(constants.clientData))
    }
}