package io.github.orioncraftmc.tellmeicheatnow.landia

import io.github.orioncraftmc.tellmeicheatnow.landia.LandiaPacketReplier.handleSkinOrAntiCheatVersion
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacketReplier.handleClientDataRequestPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacketReplier.handleGameHashRequestPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacketType
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.LandiaAntiCheatPacketType.*
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request.LandiaClientDataRequestS2CPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request.LandiaGameHashRequestS2CPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request.LandiaRemoteCodeExecutionRequestS2CPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request.LandiaScreenshotRequestS2CPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.respose.LandiaClientDataResponseC2SPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.constants.LandiaAntiCheatConstants
import io.github.orioncraftmc.tellmeicheatnow.landia.skins.LandiaSkinDataS2CPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.version.LandiaAntiCheatVersionS2CPacket
import io.github.orioncraftmc.tellmeicheatnow.model.TmicnPacketManager
import io.github.orioncraftmc.tellmeicheatnow.model.TmicnSupportedServer
import io.github.orioncraftmc.tellmeicheatnow.model.reply.ReplyAction
import kotlin.reflect.KClass

class LandiaPacketManager(val constants: LandiaAntiCheatConstants) :
    TmicnPacketManager<LandiaPacket, LandiaPacketListener>() {

    override fun handleRawIncomingPacket(payloadChannel: String, data: ByteArray): ReplyAction? {
        return when (val packetType = LandiaPacketType.getTypeByPayloadChannel(payloadChannel, this)) {
            LandiaPacketType.SKIN_DATA, LandiaPacketType.ANTICHEAT_VERSION ->
                handleSkinOrAntiCheatVersion(packetType, data)
            LandiaPacketType.ANTI_CHEAT -> handleAntiCheatPacket(data)
            else -> null
        }
    }

    private fun handleAntiCheatPacket(data: ByteArray): ReplyAction? {
        val packetTypeByte = data.first().toInt() and 0xff
        val type = LandiaAntiCheatPacketType.getPacketTypeById(packetTypeByte, true)
            ?: throw UnsupportedOperationException("AntiCheat packet is known to have a valid packet type; got $packetTypeByte.")

        println(type)

        val companion = getMultiplexPacketCompanionByEnum(LandiaAntiCheatPacket::class, type)
            ?: throw UnsupportedOperationException("AntiCheat packet is known to have a valid packet type; got $packetTypeByte.")

        val packet = companion.read(data.copyOfRange(1, data.size))

        return when {
            type == GAME_HASH_REQUEST && packet is LandiaGameHashRequestS2CPacket -> this.handleGameHashRequestPacket(
                packet
            )
            type == CLIENT_DATA_REQUEST && packet is LandiaClientDataRequestS2CPacket -> this.handleClientDataRequestPacket(
                packet
            )
            type == SCREENSHOT_REQUEST -> TODO()
            else -> null
        }

    }

    override val server: TmicnSupportedServer = TmicnSupportedServer.CRAFTLANDIA

    override fun init() {
        registerPacket(LandiaSkinDataS2CPacket::class)
        registerPacket(LandiaAntiCheatVersionS2CPacket::class)

        registerAntiCheatPacket(LandiaRemoteCodeExecutionRequestS2CPacket::class, REMOTE_CODE_EXECUTION_REQUEST)
        registerAntiCheatPacket(LandiaGameHashRequestS2CPacket::class, GAME_HASH_REQUEST)
        registerAntiCheatPacket(LandiaClientDataRequestS2CPacket::class, CLIENT_DATA_REQUEST)
        registerAntiCheatPacket(LandiaClientDataResponseC2SPacket::class, CLIENT_DATA_RESPONSE)
        registerAntiCheatPacket(LandiaScreenshotRequestS2CPacket::class, SCREENSHOT_REQUEST)
    }

    private fun registerAntiCheatPacket(
        packetType: KClass<out LandiaAntiCheatPacket>,
        type: LandiaAntiCheatPacketType
    ) {
        registerMultiplexPacket(LandiaAntiCheatPacket::class, packetType, type)
    }
}