package com.rolnik.remik.mappers

interface Mapper<T, S> {
    fun mapFrom(toMap: S): T
    fun mapTo(mapFrom: T): S
    fun mapListFrom(objects: MutableList<S>): MutableList<T> = objects.map { mapFrom(it) }.toMutableList()
    fun mapListTo(objects: MutableList<T>): MutableList<S> = objects.map { mapTo(it) }.toMutableList()
}