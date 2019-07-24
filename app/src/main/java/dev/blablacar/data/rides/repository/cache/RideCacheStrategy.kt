package dev.blablacar.data.rides.repository.cache

import dev.blablacar.data.common.CacheStrategy
import java.util.*

class RideCacheStrategy: CacheStrategy<RideCacheStrategy.Params> {
    private var time: Long = 0
    private var start: String = ""
    private var stop: String = ""

    override fun isCacheValid(t: Params): Boolean {
        return ((Date().time - time) < 1 * 60 * 1000 && t.start == start && t.stop == stop)
    }

    override fun newCacheSet(t: Params) {
        time = Date().time
        start = t.start
        stop = t.stop
    }

    data class Params(val start: String, val stop: String)
}