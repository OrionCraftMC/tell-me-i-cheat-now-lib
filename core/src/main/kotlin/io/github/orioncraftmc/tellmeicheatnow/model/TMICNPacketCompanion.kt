package io.github.orioncraftmc.tellmeicheatnow.model

import java.io.OutputStream

interface TMICNPacketCompanion<P: TMICNPacket> {
    fun read(data: ByteArray): P

    fun write(packet: P, data: OutputStream)
}