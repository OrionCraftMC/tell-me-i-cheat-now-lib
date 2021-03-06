package io.github.orioncraftmc.tellmeicheatnow.landia

import io.github.orioncraftmc.tellmeicheatnow.landia.skins.LandiaSkinDataS2CPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.version.LandiaAntiCheatVersionS2CPacket
import kotlin.reflect.KClass

enum class LandiaPacketType(private val payloadChannel: String? = null, val kClass: KClass<out LandiaPacket>? = null) {
    SKIN_DATA("CL|Skins", LandiaSkinDataS2CPacket::class),
    ANTICHEAT_VERSION("CL|AHVersion", LandiaAntiCheatVersionS2CPacket::class),
    ANTI_CHEAT;

    companion object {

        private val valuesCached = values()
        @JvmStatic
        fun getTypeByPayloadChannel(payloadChannel: String, landiaPacketManager: LandiaPacketManager) : LandiaPacketType? {
            return valuesCached.firstOrNull { it.getPayloadChannel(landiaPacketManager) == payloadChannel }
        }

    }

    fun getPayloadChannel(landiaPacketManager: LandiaPacketManager): String {
        if (payloadChannel != null) return payloadChannel

        if (this == ANTI_CHEAT) {
            return landiaPacketManager.constants.antiHackPayloadChannel
        }

        throw Exception("Unable to find Payload Channel for packet type $name")
    }
}