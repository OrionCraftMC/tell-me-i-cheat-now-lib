package io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request

import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaPacketCompanion
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacketType
import io.github.orioncraftmc.tellmeicheatnow.readBytesCompat
import java.io.ByteArrayInputStream
import java.io.OutputStream

data class LandiaGameHashRequestPacket(val responsePrefix: String) : LandiaAntiCheatPacket {
    override val antiCheatRequestType: LandiaAntiCheatPacketType = LandiaAntiCheatPacketType.GAME_HASH_REQUEST

    companion object : LandiaPacketCompanion<LandiaGameHashRequestPacket> {
        override fun read(data: ByteArray): LandiaGameHashRequestPacket {
            return ByteArrayInputStream(data).use {
                LandiaGameHashRequestPacket(it.readBytesCompat(it.read()).decodeToString())
            }
        }

        override fun write(packet: LandiaGameHashRequestPacket, data: OutputStream) {
            val bytes = packet.responsePrefix.toByteArray()
            data.write(bytes.size)
            data.write(bytes)
        }
    }
}