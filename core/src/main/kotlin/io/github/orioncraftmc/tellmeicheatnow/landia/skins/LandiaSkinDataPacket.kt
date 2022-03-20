package io.github.orioncraftmc.tellmeicheatnow.landia.skins

import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaPacketCompanion
import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaPacketType
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.OutputStream

data class LandiaSkinDataPacket(
    val profileName: String,
    val skinUrl: String?,
    val cloakUrl: String?,
    val upsideDown: Boolean,
    val scale: Float
) : LandiaPacket {

    override val type: LandiaPacketType = LandiaPacketType.SKIN_DATA

    companion object : LandiaPacketCompanion<LandiaSkinDataPacket> {

        override fun read(data: ByteArray): LandiaSkinDataPacket {
            return DataInputStream(data.inputStream()).use { stream ->
                val name = stream.readUTF()
                val cloakUrl = stream.readUTF().takeIf { it.isNotEmpty() }
                val skinUrl = stream.readUTF().takeIf { it.isNotEmpty() }
                val upsideDown = stream.readBoolean()
                val scale = stream.readFloat()

                LandiaSkinDataPacket(name, skinUrl, cloakUrl, upsideDown, scale)
            }
        }

        override fun write(packet: LandiaSkinDataPacket, data: OutputStream) {
            DataOutputStream(data).use { stream ->
                stream.writeUTF(packet.profileName)
                stream.writeUTF(packet.cloakUrl ?: "")
                stream.writeUTF(packet.skinUrl ?: "")
                stream.writeBoolean(packet.upsideDown)
                stream.writeFloat(packet.scale)
            }
        }
    }
}