package io.github.orioncraftmc.tellmeicheatnow.landia

enum class LandiaPacketType(private val payloadChannel: String? = null) {
    SKIN_DATA("CL|Skins"),
    ANTICHEAT_VERSION("CL|AHVersion"),
    ANTI_CHEAT;

    fun getPayloadChannel(landiaPacketManager: LandiaPacketManager): String {
        if (payloadChannel != null) return payloadChannel

        if (this == ANTI_CHEAT) {
            return landiaPacketManager.constants.antiHackPayloadChannel
        }

        throw Exception("Unable to find Payload Channel for packet type $name")
    }
}