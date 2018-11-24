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

import com.bandiago.cointrends.datamodel.http.service.ExchangeHttpService
import com.bandiago.cointrends.datamodel.http.service.MarketHttpService
import com.bandiago.cointrends.datamodel.http.service.NodeHttpService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HttpInteractorTest {

    @Mock
    lateinit var nodeHttpService: NodeHttpService

    @Mock
    private lateinit var exchangeHttpService: ExchangeHttpService

    @Mock
    private lateinit var marketHttpService: MarketHttpService

    @InjectMocks
    private lateinit var httpInteractor: HttpInteractor

    @Test
    fun getSymbols() {
    }

    @Test
    fun getDetails() {
        runBlocking {
            httpInteractor.getDetails("000")

            Mockito.verify(nodeHttpService, times(1)).getDetailsForAsset("000")
        }
    }

    @Test
    fun getExchangeHttpService() {
        Assert.assertEquals(exchangeHttpService, httpInteractor.exchangeHttpService)
    }

    @Test
    fun getMarketHttpService() {
        Assert.assertEquals(marketHttpService, httpInteractor.marketHttpService)
    }


    @Test
    fun getNodeHttpService() {
        Assert.assertEquals(nodeHttpService, httpInteractor.nodeHttpService)
    }

}