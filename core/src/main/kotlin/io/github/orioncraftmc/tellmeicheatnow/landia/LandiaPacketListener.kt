package io.github.orioncraftmc.tellmeicheatnow.landia

import io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request.LandiaClientDataRequestPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.ac.packets.request.LandiaGameHashRequestPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.skins.LandiaSkinDataPacket
import io.github.orioncraftmc.tellmeicheatnow.landia.version.LandiaAntiCheatVersionPacket

interface LandiaPacketListener {

    fun handleSkinPacket(packet: LandiaSkinDataPacket) {}

    fun handleAntiCheatVersionPacket(packet: LandiaAntiCheatVersionPacket) {}

    fun handleGameHashRequestPacket(packet: LandiaGameHashRequestPacket) {}
    
    fun handleClientDataRequestPacket(packet: LandiaClientDataRequestPacket) {}

}