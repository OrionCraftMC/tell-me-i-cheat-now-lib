package io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request

import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaPacketCompanion
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacketType
import java.io.OutputStream

class LandiaClientDataRequestPacket : LandiaAntiCheatPacket {
    override val antiCheatRequestType: LandiaAntiCheatPacketType = LandiaAntiCheatPacketType.CLIENT_DATA_REQUEST

    companion object : LandiaPacketCompanion<LandiaClientDataRequestPacket> {
        override fun read(data: ByteArray): LandiaClientDataRequestPacket {
            return LandiaClientDataRequestPacket()
        }

        override fun write(packet: LandiaClientDataRequestPacket, data: OutputStream) {
        }
    }
}