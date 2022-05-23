package io.github.orioncraftmc.tellmeicheatnow.model

import io.github.orioncraftmc.tellmeicheatnow.model.reply.ReplyAction
import kotlin.reflect.KClass
import kotlin.reflect.full.companionObjectInstance

abstract class TmicnPacketManager<P : TmicnPacket, L : Any> {
    private val packetCompanionByClassMap: MutableMap<KClass<P>, TmicnPacketCompanion<P>> = mutableMapOf()
    private val mulitplexPacketCompanionByClassMap: MutableMap<KClass<P>, MutableMap<Enum<*>, TmicnPacketCompanion<P>>> =
        mutableMapOf()

    @PublishedApi
    internal val listeners: MutableList<L> = mutableListOf()

    @Suppress("UNCHECKED_CAST")
    fun <T : P> registerPacket(packetType: KClass<T>) {
        if (packetType.companionObjectInstance !is TmicnPacketCompanion<*>) {
            throw Exception("Packet of type $packetType's companion doesn't implement TMICPacketCompanion<${packetType}>")
        }
        packetCompanionByClassMap[packetType as KClass<P>] =
            packetType.companionObjectInstance as TmicnPacketCompanion<P>
    }

    fun <T : P> getPacketCompanionByKClass(packetType: KClass<T>): TmicnPacketCompanion<P>? {
        @Suppress("USELESS_CAST") // Reason: is not actually useless
        return packetCompanionByClassMap[packetType as KClass<*>] as? TmicnPacketCompanion<P>
    }


    fun <M : P, T : P, E : Enum<*>> registerMultiplexPacket(multiplexType: KClass<M>, packetType: KClass<T>, enum: E) {

        if (packetType.companionObjectInstance !is TmicnPacketCompanion<*>) {
            throw Exception("Packet of type $packetType's companion doesn't implement TMICPacketCompanion<${packetType}>")
        }

        mulitplexPacketCompanionByClassMap.getOrPut(multiplexType as KClass<P>) { mutableMapOf() }[enum] =
            packetType.companionObjectInstance as TmicnPacketCompanion<P>
    }

    fun <M : P, E : Enum<*>> getMultiplexPacketCompanionByEnum(multiplexType: KClass<M>, enum: E): TmicnPacketCompanion<P>? {
        @Suppress("USELESS_CAST") // Reason: is not actually useless
        return mulitplexPacketCompanionByClassMap[multiplexType as KClass<P>]?.get(enum)
    }

    fun registerListener(listener: L) {
        listeners.add(listener)
    }

    fun removeListener(listener: L) {
        listeners.remove(listener)
    }


    inline fun invokeListeners(crossinline invoker: L.() -> Unit) {
        listeners.forEach(invoker)
    }

    abstract fun handleRawIncomingPacket(payloadChannel: String, data: ByteArray): ReplyAction?

    abstract val server: TmicnSupportedServer

    abstract fun init()
}