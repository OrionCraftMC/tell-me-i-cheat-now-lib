package io.github.orioncraftmc.tellmeicheatnow.landia.ac

enum class LandiaAntiCheatPacketType(val id: Int, val isIncoming: Boolean = true) {
    REMOTE_CODE_EXECUTION_REQUEST(99),
    REMOTE_CODE_EXECUTION_RESPONSE(100, false),
    CLIENT_DATA_REQUEST(101),
    CLIENT_DATA_RESPONSE(102, false),
    SCREENSHOT_REQUEST(103),
    SCREENSHOT_UPLOADED_RESPONSE(104, false),
    GAME_HASH_REQUEST(105);

    companion object {
        private val cachedValues = values()

        fun getPacketTypeById(id: Int, incoming: Boolean): LandiaAntiCheatPacketType? {
            return cachedValues.firstOrNull { it.id == id && it.isIncoming == incoming }
        }
    }
}