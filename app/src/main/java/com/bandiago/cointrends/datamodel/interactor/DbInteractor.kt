/*
 * Copyright (c) 2018 Bandiago
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bandiago.cointrends.datamodel.interactor

import com.bandiago.cointrends.datamodel.db.dao.SymbolDao
import com.bandiago.cointrends.datamodel.db.entity.SymbolEntity
import kotlinx.coroutines.*

class DbInteractor(var symbolDao: SymbolDao) {


    suspend fun getAllSymbols(): List<SymbolEntity>? {
        var result: List<SymbolEntity>? = null
        GlobalScope.async {
            result = symbolDao.getAll()
        }.await()

        return result
    }

    suspend fun searchBySymbol(query: String): List<SymbolEntity>? {
        var result: List<SymbolEntity>? = null
        GlobalScope.async {
            result = symbolDao.searchBySymbol(query)
        }.await()

        return result
    }

    suspend fun findById(assetId: String): SymbolEntity? {
        var result: SymbolEntity? = null
        GlobalScope.async {
            result = symbolDao.findByAssetId(assetId)
        }.await()

        return result
    }

    suspend fun insertSymbols(symbolEntities: List<SymbolEntity>?) {
        GlobalScope.async {
            symbolEntities?.forEach { symbolDao.insertAll(it) }
        }.await()
    }

    suspend fun updateSymbol(entity: SymbolEntity) {
        GlobalScope.async {
            symbolDao.update(entity)
        }.await()
    }
}