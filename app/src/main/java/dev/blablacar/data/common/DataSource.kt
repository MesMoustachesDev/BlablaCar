package dev.blablacar.data.common

import androidx.lifecycle.LiveData

interface DataSource<T> {
    fun add(item: T)
    fun add(items: Iterable<T>)
    fun remove(specification: Specification)
    fun queryId(specification: String): LiveData<T>
    fun queryList(specification: Specification): LiveData<List<T>>

    class Spec : Specification {
        data class ByRef(val id: String) : Specification
        class All : Specification
    }
}

interface Specification
