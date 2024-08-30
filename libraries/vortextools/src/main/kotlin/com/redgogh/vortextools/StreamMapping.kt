package com.redgogh.vortextools

interface StreamMapping<T, R> {
    fun map(any: T): R
}