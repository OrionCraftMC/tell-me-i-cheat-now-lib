package io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.respose

import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaPacketCompanion
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacketType
import io.github.orioncraftmc.tellmeicheatnow.landia.constants.LandiaClientDataConstants
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.OutputStream

data class LandiaClientDataResponsePacket(
    val constants: LandiaClientDataConstants
) : LandiaAntiCheatPacket {
    override val antiCheatRequestType: LandiaAntiCheatPacketType = LandiaAntiCheatPacketType.CLIENT_DATA_RESPONSE

    companion object : LandiaPacketCompanion<LandiaClientDataResponsePacket> {
        override fun read(data: ByteArray): LandiaClientDataResponsePacket {
            DataInputStream(data.inputStream()).use {
                // Make sure we are reading the correct packet type
                assert(it.read() == LandiaAntiCheatPacketType.CLIENT_DATA_RESPONSE.id)

                return LandiaClientDataResponsePacket(
                    LandiaClientDataConstants(
                        /* Speed Modifier (?) */ it.readDouble(),
                        /* Unknown Constant 1 */ it.readDouble(),
                        /* Unknown Constant 2 */ it.readDouble(),
                        /* Unknown Constant 3 */ it.readDouble(),
                        /* Unknown Constant 4 */ it.readDouble(),
                        /* Unknown Constant 5 */ it.readDouble(),
                        /* Entity Hitbox Width */ it.readFloat(),
                        /* Entity Hitbox Height */ it.readFloat()
                    )
                )
            }
        }

        override fun write(packet: LandiaClientDataResponsePacket, data: OutputStream) {
            DataOutputStream(data).use {
                // Write the packet id
                it.write(LandiaAntiCheatPacketType.CLIENT_DATA_RESPONSE.id)

                // Write the constants
                it.writeDouble(packet.constants.speedModifier)
                it.writeDouble(packet.constants.unknownData1)
                it.writeDouble(packet.constants.unknownData2)
                it.writeDouble(packet.constants.unknownData3)
                it.writeDouble(packet.constants.unknownData4)
                it.writeDouble(packet.constants.unknownData5)
                it.writeFloat(packet.constants.entityHitboxWidth)
                it.writeFloat(packet.constants.entityHitboxHeight)
            }
        }

    }

}
