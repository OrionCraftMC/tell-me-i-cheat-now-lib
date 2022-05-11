package io.github.orioncraftmc.tellmeicheatnow.landia.version

import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaPacketCompanion
import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaPacketType
import java.io.ByteArrayInputStream
import java.io.OutputStream

data class LandiaAntiCheatVersionPacket(val version: String?) : LandiaPacket {
    companion object : LandiaPacketCompanion<LandiaAntiCheatVersionPacket> {
        override fun read(data: ByteArray): LandiaAntiCheatVersionPacket {
            return LandiaAntiCheatVersionPacket(ByteArrayInputStream(data).use {
                // If we have a version
                if (it.read() == 1) {
                    it.readNBytes(it.read()).decodeToString()
                } else {
                    null
                }
            })
        }

        override fun write(packet: LandiaAntiCheatVersionPacket, data: OutputStream) {
            data.write(if (packet.version != null) 1 else 0)
            if (packet.version != null) {
                val versionBytes = packet.version.encodeToByteArray()
                data.write(versionBytes.size)
                data.write(versionBytes)
            }
        }
    }

    override val type: LandiaPacketType = LandiaPacketType.ANTICHEAT_VERSION

}