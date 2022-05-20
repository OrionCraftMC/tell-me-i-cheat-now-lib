package io.github.orioncraftmc.tellmeicheatnow.landia

import io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request.LandiaClientDataRequestS2CPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request.LandiaGameHashRequestS2CPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.skins.LandiaSkinDataS2CPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.version.LandiaAntiCheatVersionS2CPacket

interface LandiaPacketListener {

    fun handleSkinPacket(packet: LandiaSkinDataS2CPacket) {}

    fun handleAntiCheatVersionPacket(packet: LandiaAntiCheatVersionS2CPacket) {}

    fun handleGameHashRequestPacket(packet: LandiaGameHashRequestS2CPacket) {}
    
    fun handleClientDataRequestPacket(packet: LandiaClientDataRequestS2CPacket) {}

}