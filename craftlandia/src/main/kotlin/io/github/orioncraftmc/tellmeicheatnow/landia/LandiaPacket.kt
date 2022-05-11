package io.github.orioncraftmc.tellmeicheatnow.landia

import io.github.orioncraftmc.tellmeicheatnow.model.TmicnPacket
import io.github.orioncraftmc.tellmeicheatnow.model.TmicnSupportedServer

interface LandiaPacket : TmicnPacket {
    val type: LandiaPacketType

    override val server
        get() = TmicnSupportedServer.CRAFTLANDIA
}

