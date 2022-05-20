package io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request

import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaPacketCompanion
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacketType
import io.github.orioncraftmc.tellmeicheatnow.readBytesCompat
import java.io.ByteArrayInputStream
import java.io.OutputStream

data class LandiaGameHashRequestS2CPacket(val responsePrefix: String) : LandiaAntiCheatPacket {
    override val antiCheatRequestType: LandiaAntiCheatPacketType = LandiaAntiCheatPacketType.GAME_HASH_REQUEST

    companion object : LandiaPacketCompanion<LandiaGameHashRequestS2CPacket> {
        override fun read(data: ByteArray): LandiaGameHashRequestS2CPacket {
            return ByteArrayInputStream(data).use {
                LandiaGameHashRequestS2CPacket(it.readBytesCompat(it.read()).decodeToString())
            }
        }

        override fun write(packet: LandiaGameHashRequestS2CPacket, data: OutputStream) {
            val bytes = packet.responsePrefix.toByteArray()
            data.write(bytes.size)
            data.write(bytes)
        }
    }
}