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

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bandiago.cointrends.datamodel.dto.DetailsDto
import com.bandiago.cointrends.datamodel.dto.SymbolDto
import com.bandiago.cointrends.datamodel.http.service.ExchangeHttpService
import com.bandiago.cointrends.datamodel.http.service.MarketHttpService
import com.bandiago.cointrends.datamodel.http.service.NodeHttpService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class HttpInteractor(var exchangeHttpService: ExchangeHttpService, var marketHttpService: MarketHttpService, var nodeHttpService: NodeHttpService) {

    suspend fun getSymbols(): List<SymbolDto>? {
        var result: List<SymbolDto>? = null

        try {
            val response = marketHttpService.getSymbols().await()
            if (response.isSuccessful) {
                result = response.body()
            } else {
                Log.e("error", response.errorBody().toString())
            }
        } catch (e: Exception) {
            Log.e("exception occurred ", e.message)
        }

        return result
    }

    suspend fun getDetails(assetId: String): DetailsDto? {
        var result: DetailsDto? = null

        try {
            val response = nodeHttpService.getDetailsForAsset(assetId).await()
            if (response.isSuccessful) {
                result = response.body()
            } else {
                Log.e("error", response.errorBody().toString())
            }
        } catch (e: Exception) {
            Log.e("exception occurred ", e.message)
        }

        return result
    }

}