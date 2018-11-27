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

package com.bandiago.cointrends.datamodel.http.service

import com.bandiago.cointrends.datamodel.http.dto.MarketPriceDto
import com.bandiago.cointrends.datamodel.http.dto.SymbolDto
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


interface MarketHttpService {

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("api/ticker/{amountAssetName}/{priceAssetName}")
    fun getMarketPrice(@Path("amountAssetName") amountAssetName: String, @Path("priceAssetName") priceAssetName: String): Observable<MarketPriceDto>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("api/symbols")
    fun getSymbols(): Deferred<Response<List<SymbolDto>>>
}
