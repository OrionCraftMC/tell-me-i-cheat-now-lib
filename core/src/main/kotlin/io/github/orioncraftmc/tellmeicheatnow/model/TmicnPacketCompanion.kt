package io.github.orioncraftmc.tellmeicheatnow.model

import java.io.OutputStream

interface TmicnPacketCompanion<P: TmicnPacket> {
    fun read(data: ByteArray): P

    fun write(packet: P, data: OutputStream)
}