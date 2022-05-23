package io.github.orioncraftmc.tellmeicheatnow.landia

import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacketType
import java.io.ByteArrayInputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.OutputStream
import java.util.*

abstract class AntiCheatLandiaPacketCompanion<T : LandiaPacket>(private val expectedPacketType: Int? = null) :
    LandiaPacketCompanion<T> {
    abstract fun read(data: DataInputStream): T
    abstract fun write(packet: T, output: DataOutputStream)

    final override fun read(data: ByteArray): T {
        DataInputStream(ByteArrayInputStream(data)).use {
            // Make sure we are reading the correct packet type if requested
            if (expectedPacketType != null) {
                val type = it.read()
                if (type != expectedPacketType)
                    throw Exception("Expected packet type to be $expectedPacketType, got $type instead")
            }

            it.readLandiaString() // Packet UUID
            return read(it)
        }
    }

    final override fun write(packet: T, data: OutputStream) {
        DataOutputStream(data).use {
            // Write the packet type if requested
            if (expectedPacketType != null) {
                it.write(expectedPacketType)
            }

            it.writeLandiaString(UUID.randomUUID().toString())
            write(packet, it)
        }
    }
}