package io.github.orioncraftmc.tellmeicheatnow

import io.github.orioncraftmc.tellmeicheatnow.model.TmicnPacketManager
import io.github.orioncraftmc.tellmeicheatnow.model.TmicnSupportedServer

object TellMeICheatNow {
    private val packetManagers: MutableMap<TmicnSupportedServer, TmicnPacketManager<*, *>> = mutableMapOf()

    fun registerPacketManager(manager: TmicnPacketManager<*, *>) {
        packetManagers[manager.server] = manager.also { it.init() }
    }

}