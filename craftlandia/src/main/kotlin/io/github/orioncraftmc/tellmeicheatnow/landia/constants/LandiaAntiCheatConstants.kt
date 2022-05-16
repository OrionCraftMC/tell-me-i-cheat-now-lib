package io.github.orioncraftmc.tellmeicheatnow.landia.constants

data class LandiaAntiCheatConstants(
    val antiHackVersion: String /* AntiHackVersion#version */,

    val antiHackPayloadChannel: String /* VersionHandler#versionChannelName */,
    val clientJarHash: String,
    val antiHackReplyId: String /* VersionHandler#versionId */,

    val macrosModJarHash: String /* MacroModHashCallable#getMacroMd5 */,

    val clientData: LandiaClientDataConstants
)
