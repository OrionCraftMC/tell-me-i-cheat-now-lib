package io.github.orioncraftmc.tellmeicheatnow.landia.ac

import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaPacketType

interface LandiaAntiCheatPacket : LandiaPacket {
    override val type: LandiaPacketType
        get() = LandiaPacketType.ANTI_CHEAT

    val antiCheatRequestType: LandiaAntiCheatPacketType
}