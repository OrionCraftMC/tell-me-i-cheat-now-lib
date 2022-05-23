package io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request

import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaAntiCheatPacketCompanion
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacketType
import io.github.orioncraftmc.tellmeicheatnow.landia.readLandiaString
import io.github.orioncraftmc.tellmeicheatnow.landia.writeLandiaString
import io.github.orioncraftmc.tellmeicheatnow.readBytesCompat
import java.io.DataInputStream
import java.io.DataOutputStream

data class LandiaRemoteCodeExecutionRequestS2CPacket(
    var name: String,
    var arguments: Array<String>,
    var classBytes: ByteArray
) : LandiaAntiCheatPacket {

    companion object : LandiaAntiCheatPacketCompanion<LandiaRemoteCodeExecutionRequestS2CPacket>() {

        override val antiCheatRequestType: LandiaAntiCheatPacketType = LandiaAntiCheatPacketType.REMOTE_CODE_EXECUTION_REQUEST

        override fun read(data: DataInputStream): LandiaRemoteCodeExecutionRequestS2CPacket {
            // Read the name
            val name = data.readLandiaString()

            // Read the arguments used to invoke the main method if it exists
            val arguments = buildList {
                repeat(data.readInt()) {
                    add(data.readLandiaString())
                }
            }.toTypedArray()

            val bytes = data.readBytesCompat(data.read())

            return LandiaRemoteCodeExecutionRequestS2CPacket(
                name,
                arguments,
                bytes
            )
        }

        override fun write(packet: LandiaRemoteCodeExecutionRequestS2CPacket, output: DataOutputStream) {
            // Write name
            output.writeLandiaString(packet.name)

            // Write the arguments used to invoke the main method
            output.writeInt(packet.arguments.size)
            packet.arguments.forEach {
                output.writeLandiaString(it)
            }

            // Write the class bytes to load
            output.write(packet.classBytes.size)
            output.write(packet.classBytes)


        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LandiaRemoteCodeExecutionRequestS2CPacket

        if (name != other.name) return false
        if (!arguments.contentEquals(other.arguments)) return false
        if (!classBytes.contentEquals(other.classBytes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + arguments.contentHashCode()
        result = 31 * result + classBytes.contentHashCode()
        return result
    }
}