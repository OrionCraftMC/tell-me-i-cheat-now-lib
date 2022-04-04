package io.github.orioncraftmc.tellmeicheatnow.model.reply

import io.github.orioncraftmc.tellmeicheatnow.model.TMICNPacket

data class PacketReplyAction<T : TMICNPacket>(val packet: T) : ReplyAction