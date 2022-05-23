package io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request

import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaAntiCheatPacketCompanion
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacketType
import io.github.orioncraftmc.tellmeicheatnow.landia.readLandiaString
import io.github.orioncraftmc.tellmeicheatnow.landia.writeLandiaString
import java.io.DataInputStream
import java.io.DataOutputStream

data class LandiaScreenshotRequestS2CPacket(val uploadKey: String) : LandiaAntiCheatPacket {

    companion object : LandiaAntiCheatPacketCompanion<LandiaScreenshotRequestS2CPacket>() {

        override val antiCheatRequestType: LandiaAntiCheatPacketType = LandiaAntiCheatPacketType.SCREENSHOT_REQUEST

        override fun read(data: DataInputStream): LandiaScreenshotRequestS2CPacket {
            return data.use {
                LandiaScreenshotRequestS2CPacket(it.readLandiaString())
            }
        }

        override fun write(packet: LandiaScreenshotRequestS2CPacket, output: DataOutputStream) {
            output.writeLandiaString(packet.uploadKey)
        }

    }
}
