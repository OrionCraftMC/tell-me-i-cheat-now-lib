package io.github.orioncraftmc.tellmeicheatnow.landia.ac

enum class LandiaAntiCheatPacketType(val packetType: Int) {
    SCREENSHOT_REQUEST(1),
    GAME_HASH_REQUEST(2),
    CLIENT_DATA_REQUEST(3)
}