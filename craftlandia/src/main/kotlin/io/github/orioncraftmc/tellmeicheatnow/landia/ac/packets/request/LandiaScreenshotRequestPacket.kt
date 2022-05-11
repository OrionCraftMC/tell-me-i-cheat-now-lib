package io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request

import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaPacketCompanion
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacketType
import io.github.orioncraftmc.tellmeicheatnow.readBytesCompat
import java.io.ByteArrayInputStream
import java.io.OutputStream

data class LandiaScreenshotRequestPacket(val uploadKey: String) : LandiaAntiCheatPacket {
    override val antiCheatRequestType: LandiaAntiCheatPacketType = LandiaAntiCheatPacketType.SCREENSHOT_REQUEST

    companion object : LandiaPacketCompanion<LandiaScreenshotRequestPacket> {
        override fun read(data: ByteArray): LandiaScreenshotRequestPacket {
            return ByteArrayInputStream(data).use {
                LandiaScreenshotRequestPacket(it.readBytesCompat(it.read()).decodeToString())
            }
        }

        override fun write(packet: LandiaScreenshotRequestPacket, data: OutputStream) {
            val bytes = packet.uploadKey.toByteArray()
            data.write(bytes.size)
            data.write(bytes)
        }

    }
}
