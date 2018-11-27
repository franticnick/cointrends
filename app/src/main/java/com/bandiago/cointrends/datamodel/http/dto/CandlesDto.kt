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


data class CandlesDto(
        var timeStart: Long,
        var timeEnd: Long,
        var candles: Array<Candles>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CandlesDto

        if (timeStart != other.timeStart) return false
        if (timeEnd != other.timeEnd) return false
        if (!candles.contentEquals(other.candles)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timeStart.hashCode()
        result = 31 * result + timeEnd.hashCode()
        result = 31 * result + candles.contentHashCode()
        return result
    }
}

data class Candles(
        var time: Long,
        var txsCount: Int,
        var volume: Double,
        var high: Float,
        var low: Float,
        var close: Float,
        var open: Float)

