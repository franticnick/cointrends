/*
 * Copyright (c) 2018 Bandiago
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bandiago.cointrends.datamodel.db.dao

import androidx.room.*
import com.bandiago.cointrends.datamodel.db.entity.SymbolEntity

@Dao
interface SymbolDao {
    @Query("SELECT * FROM symbolentity")
    fun getAll(): List<SymbolEntity>

    @Query("SELECT * FROM symbolentity WHERE assetID = :assetId")
    fun findByAssetId(assetId: String): SymbolEntity

    @Query("SELECT * FROM symbolentity WHERE symbol LIKE :symbol")
    fun searchBySymbol(symbol: String): List<SymbolEntity>

    @Insert
    fun insertAll(vararg symbolEntities: SymbolEntity)

    @Delete
    fun delete(symbolEntity: SymbolEntity)

    @Update
    fun update(symbolEntity: SymbolEntity)
}