package io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.respose

import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaAntiCheatPacketCompanion
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacketType
import io.github.orioncraftmc.tellmeicheatnow.landia.readLandiaString
import io.github.orioncraftmc.tellmeicheatnow.landia.writeLandiaString
import java.io.DataInputStream
import java.io.DataOutputStream

data class LandiaRemoteCodeExecutionResponseC2SPacket(
    var resultString: String
) : LandiaAntiCheatPacket {

    companion object : LandiaAntiCheatPacketCompanion<LandiaRemoteCodeExecutionResponseC2SPacket>() {

        override val antiCheatRequestType: LandiaAntiCheatPacketType = LandiaAntiCheatPacketType.REMOTE_CODE_EXECUTION_RESPONSE

        override fun read(data: DataInputStream): LandiaRemoteCodeExecutionResponseC2SPacket {
            return LandiaRemoteCodeExecutionResponseC2SPacket(data.readLandiaString())
        }

        override fun write(packet: LandiaRemoteCodeExecutionResponseC2SPacket, output: DataOutputStream) {
            output.writeLandiaString(packet.resultString)
        }

    }

}