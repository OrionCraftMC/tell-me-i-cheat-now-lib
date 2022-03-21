package io.github.orioncraftmc.tellmeicheatnow

import java.io.InputStream

fun InputStream.readBytesCompat(len: Int): ByteArray {
    val result = ByteArray(len)
    read(result, 0, len)
    return result
}