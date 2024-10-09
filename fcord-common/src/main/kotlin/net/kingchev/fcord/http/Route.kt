package net.kingchev.fcord.http

import io.ktor.http.*
import net.kingchev.fcord.entity.BotStats
import kotlin.reflect.KClass

internal const val BASE_URL = "https://fcord.mivian.ru/api"

internal data class Route(val endpoint: String, val method: HttpMethod, val clazz: KClass<*>)

internal val STATUS_UPDATE = Route("/monitoring/bots/{bot.user.id}/stats-update", HttpMethod.Post, BotStats::class)