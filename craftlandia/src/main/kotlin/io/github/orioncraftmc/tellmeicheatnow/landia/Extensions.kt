package io.github.orioncraftmc.tellmeicheatnow.landia

import io.github.orioncraftmc.tellmeicheatnow.TellMeICheatNow
import io.github.orioncraftmc.tellmeicheatnow.landia.constants.LandiaAntiCheatConstants


fun TellMeICheatNow.registerLandiaPacketManager(constants: LandiaAntiCheatConstants): LandiaPacketManager {
    return LandiaPacketManager(constants).also { this.registerPacketManager(it) }
}