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

package com.bandiago.cointrends.datamodel.db.entity

import androidx.room.Entity
import com.bandiago.cointrends.activity.adapter.dto.CoinDto
import com.bandiago.cointrends.util.formatDate
import java.util.*

@Entity(primaryKeys = ["assetID", "symbol"])
class SymbolEntity(
    var assetID: String,
    var symbol: String,
    var issuer: String = "",
    var issueDate: Long? = null,
    var name: String = "",
    var description: String = "",
    var decimals: Int? = null,
    var reissuable: Boolean? = null,
    var quantity: Long? = null
)

fun SymbolEntity.toCoinDto() = CoinDto(
    assetID, symbol, issuer,
    if (issueDate == null) "" else formatDate(Date(issueDate!!)),
    name,
    description,
    if (decimals == null) "" else decimals.toString(),
    if (reissuable == null) "" else reissuable.toString(),
    if (quantity == null) "" else quantity.toString()
)