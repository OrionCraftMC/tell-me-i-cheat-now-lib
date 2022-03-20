package io.github.orioncraftmc.tellmeicheatnow.landia

import io.github.orioncraftmc.tellmeicheatnow.landia.constants.LandiaAntiCheatConstants
import io.github.orioncraftmc.tellmeicheatnow.landia.skins.LandiaSkinDataPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.version.LandiaAntiCheatVersionPacket
import io.github.orioncraftmc.tellmeicheatnow.model.TMICNPacketManager
import io.github.orioncraftmc.tellmeicheatnow.model.TMICNSupportedServer

class LandiaPacketManager(val constants: LandiaAntiCheatConstants) : TMICNPacketManager<LandiaPacket>() {

    override val server: TMICNSupportedServer = TMICNSupportedServer.CRAFTLANDIA

    override fun init() {
        registerPacket(LandiaSkinDataPacket::class)
        registerPacket(LandiaAntiCheatVersionPacket::class)
    }
}