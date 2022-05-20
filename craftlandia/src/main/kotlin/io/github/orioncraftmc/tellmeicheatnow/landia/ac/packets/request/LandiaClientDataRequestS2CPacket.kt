package io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request

import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaPacketCompanion
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacketType
import java.io.OutputStream

class LandiaClientDataRequestS2CPacket : LandiaAntiCheatPacket {
    override val antiCheatRequestType: LandiaAntiCheatPacketType = LandiaAntiCheatPacketType.CLIENT_DATA_REQUEST

    companion object : LandiaPacketCompanion<LandiaClientDataRequestS2CPacket> {
        override fun read(data: ByteArray): LandiaClientDataRequestS2CPacket {
            return LandiaClientDataRequestS2CPacket()
        }

        override fun write(packet: LandiaClientDataRequestS2CPacket, data: OutputStream) {
        }
    }
}