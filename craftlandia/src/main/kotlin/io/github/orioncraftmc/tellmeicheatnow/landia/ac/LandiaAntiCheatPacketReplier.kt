package io.github.orioncraftmc.tellmeicheatnow.landia.ac

import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaPacketManager
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request.LandiaClientDataRequestS2CPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request.LandiaGameHashRequestS2CPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.respose.LandiaClientDataResponseC2SPacket
import io.github.orioncraftmc.tellmeicheatnow.model.reply.ChatReplyAction
import io.github.orioncraftmc.tellmeicheatnow.model.reply.PacketReplyAction
import io.github.orioncraftmc.tellmeicheatnow.model.reply.ReplyAction

internal object LandiaAntiCheatPacketReplier {

    internal fun LandiaPacketManager.handleGameHashRequestPacket(packet: LandiaGameHashRequestS2CPacket): ReplyAction {
        invokeListeners { this.handleGameHashRequestPacket(packet) }
        return ChatReplyAction("${packet.responsePrefix}${constants.antiHackReplyId}/${constants.clientJarHash}")
    }

    internal fun LandiaPacketManager.handleClientDataRequestPacket(packet: LandiaClientDataRequestS2CPacket): ReplyAction {
        invokeListeners { this.handleClientDataRequestPacket(packet) }
        return PacketReplyAction(LandiaClientDataResponseC2SPacket(constants.clientData))
    }
}