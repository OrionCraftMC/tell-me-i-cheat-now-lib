package io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.respose

import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaAntiCheatPacketCompanion
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacketType
import io.github.orioncraftmc.tellmeicheatnow.landia.constants.LandiaClientDataConstants
import java.io.DataInputStream
import java.io.DataOutputStream

data class LandiaClientDataResponseC2SPacket(
    val constants: LandiaClientDataConstants
) : LandiaAntiCheatPacket {

    companion object : LandiaAntiCheatPacketCompanion<LandiaClientDataResponseC2SPacket>() {

        override val antiCheatRequestType: LandiaAntiCheatPacketType = LandiaAntiCheatPacketType.CLIENT_DATA_RESPONSE

        override fun read(data: DataInputStream): LandiaClientDataResponseC2SPacket {
            data.use {
                return LandiaClientDataResponseC2SPacket(
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

        override fun write(packet: LandiaClientDataResponseC2SPacket, output: DataOutputStream) {
            output.use {
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
