package io.github.orioncraftmc.tellmeicheatnow.landia.ac

import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaPacket

interface LandiaAntiCheatPacket : LandiaPacket {
    val antiCheatRequestType: LandiaAntiCheatPacketType
}