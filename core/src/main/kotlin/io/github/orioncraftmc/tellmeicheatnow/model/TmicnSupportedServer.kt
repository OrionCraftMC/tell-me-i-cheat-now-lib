package io.github.orioncraftmc.tellmeicheatnow.model

enum class TmicnSupportedServer(val addressPattern: Regex) {
    CRAFTLANDIA(Regex(".+\\.craftlandia\\.com"))
}