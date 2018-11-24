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

import com.bandiago.cointrends.datamodel.dto.CandlesDto
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface ExchangeHttpService {


    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("/candles/{assetId1}/{assetId2}")
    fun getTradeCandles(@Path("assetId1") assetId1: String,
                        @Path("assetId2") assetId2: String,
                        @Query("timeStart") timeStart: Long,
                        @Query("timeEnd") timeEnd: Long,
                        @Query("interval") interval: String
    ): Observable<CandlesDto>

}
