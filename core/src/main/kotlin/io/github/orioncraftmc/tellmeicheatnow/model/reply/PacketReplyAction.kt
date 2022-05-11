package io.github.orioncraftmc.tellmeicheatnow.model.reply

import io.github.orioncraftmc.tellmeicheatnow.model.TmicnPacket

data class PacketReplyAction<T : TmicnPacket>(val packet: T) : ReplyAction