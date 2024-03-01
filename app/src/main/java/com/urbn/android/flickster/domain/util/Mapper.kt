package com.urbn.android.flickster.domain.util

interface Mapper<T, DomainModel> {

    fun mapToDomainModel(dto: T): DomainModel
}