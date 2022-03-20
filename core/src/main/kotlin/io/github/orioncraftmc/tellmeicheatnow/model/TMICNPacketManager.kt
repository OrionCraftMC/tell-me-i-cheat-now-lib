package io.github.orioncraftmc.tellmeicheatnow.model

import kotlin.reflect.KClass

abstract class TMICNPacketManager<P : TMICNPacket> {
    private val packetMap: MutableMap<KClass<P>, TMICNPacketCompanion<P>> = mutableMapOf()

    @Suppress("UNCHECKED_CAST")
    fun <T : P> registerPacket(packetType: KClass<T>) {
        assert(packetType.objectInstance is TMICNPacketCompanion<*>) { "Packet of type $packetType's companion doesn't implement TMICPacketCompanion<${packetType}>" }
        packetMap[packetType as KClass<P>] = packetType.objectInstance as TMICNPacketCompanion<P>
    }

    abstract val server: TMICNSupportedServer

    abstract fun init()
}