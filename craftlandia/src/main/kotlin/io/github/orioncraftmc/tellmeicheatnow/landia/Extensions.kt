package io.github.orioncraftmc.tellmeicheatnow.landia

import io.github.orioncraftmc.tellmeicheatnow.TellMeICheatNow
import io.github.orioncraftmc.tellmeicheatnow.landia.constants.LandiaAntiCheatConstants
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException


fun TellMeICheatNow.registerLandiaPacketManager(constants: LandiaAntiCheatConstants): LandiaPacketManager {
    return LandiaPacketManager(constants).also { this.registerPacketManager(it) }
}

fun DataInputStream.readLandiaString(): String {
    val length = this.readShort()
    return buildString {
        repeat(length.toInt()) {
            append(readChar())
        }
    }
}

fun DataOutputStream.writeLandiaString(string: String) {
    if (string.length > Short.MAX_VALUE) {
        throw IOException("String too big")
    } else {
        writeShort(string.length)
        writeChars(string)
    }
}