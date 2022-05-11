package io.github.orioncraftmc.tellmeicheatnow

import io.github.orioncraftmc.tellmeicheatnow.model.TMICNPacketManager
import io.github.orioncraftmc.tellmeicheatnow.model.TMICNSupportedServer

object TellMeICheatNow {
    private val packetManagers: MutableMap<TMICNSupportedServer, TMICNPacketManager<*, *>> = mutableMapOf()

    fun registerPacketManager(manager: TMICNPacketManager<*, *>) {
        packetManagers[manager.server] = manager.also { it.init() }
    }

}