package io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request

import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaAntiCheatPacketCompanion
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacketType
import java.io.DataInputStream
import java.io.DataOutputStream

class LandiaClientDataRequestS2CPacket : LandiaAntiCheatPacket {

    companion object : LandiaAntiCheatPacketCompanion<LandiaClientDataRequestS2CPacket>() {
        override val antiCheatRequestType: LandiaAntiCheatPacketType = LandiaAntiCheatPacketType.CLIENT_DATA_REQUEST

        override fun read(data: DataInputStream): LandiaClientDataRequestS2CPacket {
            return LandiaClientDataRequestS2CPacket()
        }

        override fun write(packet: LandiaClientDataRequestS2CPacket, output: DataOutputStream) {
        }
    }
}