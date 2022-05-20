package io.github.orioncraftmc.tellmeicheatnow.landia

import io.github.orioncraftmc.tellmeicheatnow.landia.skins.LandiaSkinDataS2CPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.version.LandiaAntiCheatVersionS2CPacket
import io.github.orioncraftmc.tellmeicheatnow.model.reply.ReplyAction

internal object LandiaPacketReplier {

    internal fun LandiaPacketManager.handleSkinOrAntiCheatVersion(
        packetType: LandiaPacketType,
        data: ByteArray
    ): ReplyAction? {
        val clazz = packetType.kClass
            ?: throw UnsupportedOperationException("$packetType is known at compile time to have a Kotlin Class Reference")
        val companion = getPacketCompanionByKClass(clazz)
            ?: throw UnsupportedOperationException("$packetType is known at compile time to have a Packet Companion")

        val packet = companion.read(data)

        if (packetType == LandiaPacketType.SKIN_DATA) {
            invokeListeners { handleSkinPacket(packet as LandiaSkinDataS2CPacket) }
        } else if (packetType == LandiaPacketType.ANTICHEAT_VERSION) {
            invokeListeners { handleAntiCheatVersionPacket(packet as LandiaAntiCheatVersionS2CPacket) }
        }

        return null
    }

}