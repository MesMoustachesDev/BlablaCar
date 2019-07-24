package dev.blablacar.data.common

interface CacheStrategy<T> {
    fun isCacheValid(t: T): Boolean
    fun newCacheSet(t: T)
}