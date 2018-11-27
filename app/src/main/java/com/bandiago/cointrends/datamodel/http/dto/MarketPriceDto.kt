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

package com.bandiago.cointrends.datamodel.http.dto

import com.google.gson.annotations.SerializedName

data class MarketPriceDto(
        var symbol: String,
        var amountAssetID: String,
        var amountAssetName: String,
        var amountAssetDecimals: Int,
        var amountAssetTotalSupply: String,
        var amountAssetMaxSupply: String,
        var amountAssetCirculatingSupply: String,
        var priceAssetID: String,
        var priceAssetName: String,
        var priceAssetDecimals: Int,

        var priceAssetTotalSupply: String,
        var priceAssetMaxSupply: String,
        var priceAssetCirculatingSupply: String,
        @SerializedName("24h_open") val open24h: String,
        @SerializedName("24h_high") val high24h: String,
        @SerializedName("24h_low") val low24h: String,
        @SerializedName("24h_close") val close24h: String,
        @SerializedName("24h_vwap") val vwap24h: String,
        @SerializedName("24h_volume") val volume24h: String,
        @SerializedName("24h_priceVolume") val priceVolume24h: String,
        val timestamp: Long) {

    override fun hashCode(): Int {
        return 31 + amountAssetID.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MarketPriceDto

        if (amountAssetID != other.amountAssetID) return false

        return true
    }
}
