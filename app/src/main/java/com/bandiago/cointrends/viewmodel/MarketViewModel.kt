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

package com.bandiago.cointrends.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bandiago.cointrends.activity.adapter.dto.CoinDto
import com.bandiago.cointrends.datamodel.MarketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MarketViewModel : ViewModel() {

    lateinit var marketRepository: MarketRepository

    fun getSymbols(): LiveData<List<CoinDto>> {
        val result = MutableLiveData<List<CoinDto>>()

        GlobalScope.launch(Dispatchers.Main) {
            result.value = marketRepository.getSymbols()
        }

        return result
    }

    fun getDetails(assetId : String): LiveData<CoinDto> {
        val result = MutableLiveData<CoinDto>()

        GlobalScope.launch(Dispatchers.Main) {
            result.value = marketRepository.getDetails(assetId)
        }

        return result
    }

    fun searchBySymbol(query: String): LiveData<List<CoinDto>> {
        val result = MutableLiveData<List<CoinDto>>()

        GlobalScope.launch(Dispatchers.Main) {
            result.value = marketRepository.searchBySymbol("%$query%")
        }

        return result
    }

}
