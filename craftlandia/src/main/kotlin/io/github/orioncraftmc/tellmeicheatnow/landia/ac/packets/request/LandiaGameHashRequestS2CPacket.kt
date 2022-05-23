package io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request

import io.github.orioncraftmc.tellmeicheatnow.landia.AntiCheatLandiaPacketCompanion
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacketType
import io.github.orioncraftmc.tellmeicheatnow.landia.readLandiaString
import io.github.orioncraftmc.tellmeicheatnow.landia.writeLandiaString
import io.github.orioncraftmc.tellmeicheatnow.readBytesCompat
import java.io.DataInputStream
import java.io.DataOutputStream

data class LandiaGameHashRequestS2CPacket(val responsePrefix: String) : LandiaAntiCheatPacket {
    override val antiCheatRequestType: LandiaAntiCheatPacketType = LandiaAntiCheatPacketType.GAME_HASH_REQUEST

    companion object : AntiCheatLandiaPacketCompanion<LandiaGameHashRequestS2CPacket>() {
        override fun read(data: DataInputStream): LandiaGameHashRequestS2CPacket {
            return LandiaGameHashRequestS2CPacket(data.readLandiaString())
        }

        override fun write(packet: LandiaGameHashRequestS2CPacket, output: DataOutputStream) {
            output.writeLandiaString(packet.responsePrefix)
        }
    }
}