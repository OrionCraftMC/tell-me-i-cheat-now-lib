package io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request

import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaPacketCompanion
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacketType
import io.github.orioncraftmc.tellmeicheatnow.readBytesCompat
import java.io.ByteArrayInputStream
import java.io.OutputStream

data class LandiaScreenshotRequestS2CPacket(val uploadKey: String) : LandiaAntiCheatPacket {
    override val antiCheatRequestType: LandiaAntiCheatPacketType = LandiaAntiCheatPacketType.SCREENSHOT_REQUEST

    companion object : LandiaPacketCompanion<LandiaScreenshotRequestS2CPacket> {
        override fun read(data: ByteArray): LandiaScreenshotRequestS2CPacket {
            return ByteArrayInputStream(data).use {
                LandiaScreenshotRequestS2CPacket(it.readBytesCompat(it.read()).decodeToString())
            }
        }

        override fun write(packet: LandiaScreenshotRequestS2CPacket, data: OutputStream) {
            val bytes = packet.uploadKey.toByteArray()
            data.write(bytes.size)
            data.write(bytes)
        }

    }
}
