package io.github.orioncraftmc.tellmeicheatnow.landia

import io.github.orioncraftmc.tellmeicheatnow.model.TMICNPacket
import io.github.orioncraftmc.tellmeicheatnow.model.TMICNSupportedServer

interface LandiaPacket : TMICNPacket {
    val type: LandiaPacketType

    override val server
        get() = TMICNSupportedServer.CRAFTLANDIA
}

