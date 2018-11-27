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

package com.bandiago.cointrends.datamodel

import com.bandiago.cointrends.activity.adapter.dto.CoinDto
import com.bandiago.cointrends.datamodel.db.entity.SymbolEntity
import com.bandiago.cointrends.datamodel.db.entity.toCoinDto
import com.bandiago.cointrends.datamodel.http.dto.DetailsDto
import com.bandiago.cointrends.datamodel.http.dto.toSymbolEntity
import com.bandiago.cointrends.datamodel.interactor.DbInteractor
import com.bandiago.cointrends.datamodel.interactor.HttpInteractor

class MarketRepository(var dbInteractor: DbInteractor, var httpInteractor: HttpInteractor) {


    suspend fun getSymbols(): List<CoinDto> {
        val result: List<CoinDto>

        val entities = dbInteractor.getAllSymbols()
        result = if (entities != null && !entities.isEmpty()) {
            entities.map { it.toCoinDto() }
        } else {
            val symbolDtos = httpInteractor.getSymbols()
            dbInteractor.insertSymbols(symbolDtos?.map { SymbolEntity(it.assetID, it.symbol) })
            symbolDtos!!.map { CoinDto(it.assetID, it.symbol) }
        }

        return result
    }

    suspend fun getDetails(assetId: String): CoinDto? {
        val result: CoinDto

        var entity = dbInteractor.findById(assetId)

        //nothing in db, get data from web
        if (entity != null && entity.decimals == null) {
            val detailsDto = httpInteractor.getDetails(assetId)
            //update db
            entity = updateDb(detailsDto, entity)
        }

        result = entity!!.toCoinDto()

        return result
    }

    suspend fun searchBySymbol(query: String): List<CoinDto> {
        var result: List<CoinDto> = emptyList()
        val entities: List<SymbolEntity>? = if (query.isEmpty()) {
            dbInteractor.getAllSymbols()
        } else {
            dbInteractor.searchBySymbol(query)
        }
        if (entities != null) {
            result = entities.map { it.toCoinDto() }
        }

        return result
    }

    private suspend fun updateDb(detailsDto: DetailsDto?, symbolEntity: SymbolEntity): SymbolEntity {
        var entity = symbolEntity
        if (detailsDto != null) {
            entity = detailsDto.toSymbolEntity(entity.symbol)
        }
        dbInteractor.updateSymbol(entity)
        return entity
    }

}

