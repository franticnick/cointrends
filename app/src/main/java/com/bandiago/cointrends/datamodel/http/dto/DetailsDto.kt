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

package com.bandiago.cointrends.datamodel.http.dto

import com.bandiago.cointrends.datamodel.db.entity.SymbolEntity

data class DetailsDto(
    val assetId: String,
    val issueHeight: Int,
    val issueTimestamp: Long,
    val issuer: String,
    val name: String,
    val description: String,
    val decimals: Int,
    val reissuable: Boolean,
    val quantity: Long,
    val script: String?,
    val scriptText: String?,
    val complexity: Int,
    val extraFee: Int,
    val minSponsoredAssetFee: Long?
)

fun DetailsDto.toSymbolEntity(symbol: String) = SymbolEntity(
    assetId,
    symbol,
    issuer,
    issueTimestamp,
    name,
    description,
    decimals,
    reissuable,
    quantity
)

