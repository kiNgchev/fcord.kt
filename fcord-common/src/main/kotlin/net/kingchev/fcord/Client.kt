package net.kingchev.fcord

import kotlinx.coroutines.*
import net.kingchev.fcord.entity.BotStats
import net.kingchev.fcord.http.HttpClient
import net.kingchev.fcord.http.JsonAdapter
import net.kingchev.fcord.http.STATUS_UPDATE

/**
 * The core of Fcord.kt. Fcord API client gives access to all parts of the API.
 *
 * @author kiNgchev
 */
public class Client internal constructor(private val token: String, private val botId: String) {
    init {
        if (token.isBlank() && token.isEmpty()) throw IllegalArgumentException("Token cannot be empty")
    }

    private val http: HttpClient = HttpClient(token)

    /**
     * Update bot statistics to fcord API
     *
     * @param stats: [BotStats] - Bot statistic, that needs to be updated
     * @return [Unit]
     * @throws [Exception]
     * @author kiNgchev
     */
    public suspend fun updateStatistics(stats: BotStats) {
        val route = STATUS_UPDATE
        val responce = http.request(route) {
            body = JsonAdapter.stringify<BotStats>(stats)
            params("bot.user.id" to botId)
        }
        if (responce.status.value != 200) throw Exception("${responce.status.value}: ${responce.status.description}")
    }
}

/**
 * Is the function, that you need to create [Client] object
 *
 * @param token: [String] - A fcord token, that you need to
 * @param botId: [String] - A bot identifier on monitoring
 * @return [Deferred]<[Client]>
 * @author kiNgchev
 */
@OptIn(DelicateCoroutinesApi::class)
public fun fcord(token: String, botId: String): Deferred<Client> = fcord(token, botId, GlobalScope)

/**
 * Is overloading function [fcord]
 *
 * @param token: [String] - A fcord token, that you need to
 * @param botId: [String] - A bot identifier on monitoring
 * @param scope: [CoroutineScope] - A coroutine scope, in which the coroutine will be executed
 * @return [Deferred]<[Client]>
 * @author kiNgchev
 */
public fun fcord(token: String, botId: String, scope: CoroutineScope): Deferred<Client> = scope.async { Client(token, botId) }

/**
 * Is overloading function [fcord]
 *
 * @param token: [String] - A fcord token, that you need to
 * @param botId: [String] - A bot identifier on monitoring
 * @param block - Lambda that will execute and return result
 * @return [Deferred]<[T]>
 * @author kiNgchev
 */
@OptIn(DelicateCoroutinesApi::class)
public fun <T> fcord(
    token: String,
    botId: String,
    block: suspend Client.() -> T
): Deferred<T> = fcord(token, botId, GlobalScope, block)

/**
 * Is overloading function [fcord]
 *
 * @param token: [String] - A fcord token, that you need to
 * @param botId: [String] - A bot identifier on monitoring
 * @param scope: [CoroutineScope] - A coroutine scope, in which the coroutine will be executed
 * @param block - Lambda that will execute and return result
 * @return [Deferred]<[T]>
 * @author kiNgchev
 */
public fun <T> fcord(
    token: String,
    botId: String,
    scope: CoroutineScope,
    block: suspend Client.() -> T
): Deferred<T> = scope.async { Client(token, botId).block() }
