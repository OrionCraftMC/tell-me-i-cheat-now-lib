package io.github.orioncraftmc.tellmeicheatnow.landia.ac

enum class LandiaAntiCheatPacketType(val id: Int, val isIncoming: Boolean = true) {
    SCREENSHOT_REQUEST(1),
    GAME_HASH_REQUEST(2),
    CLIENT_DATA_REQUEST(3),
    CLIENT_DATA_RESPONSE(3, false);

    companion object {
        private val cachedValues = values()

        fun getPacketTypeById(id: Int, incoming: Boolean): LandiaAntiCheatPacketType? {
            return cachedValues.firstOrNull { it.id == id && it.isIncoming == incoming }
        }
    }
}