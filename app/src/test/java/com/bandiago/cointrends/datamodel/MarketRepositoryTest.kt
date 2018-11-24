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

package com.bandiago.cointrends.datamodel

import com.bandiago.cointrends.datamodel.db.dao.SymbolDao
import com.bandiago.cointrends.datamodel.db.entity.SymbolEntity
import com.bandiago.cointrends.datamodel.http.service.ExchangeHttpService
import com.bandiago.cointrends.datamodel.http.service.MarketHttpService
import com.bandiago.cointrends.datamodel.http.service.NodeHttpService
import com.bandiago.cointrends.datamodel.interactor.DbInteractor
import com.bandiago.cointrends.datamodel.interactor.HttpInteractor
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MarketRepositoryTest {

    @Mock
    lateinit var symbolDao: SymbolDao

    @InjectMocks
    private lateinit var dbInteractor: DbInteractor

    @Mock
    lateinit var nodeHttpService: NodeHttpService

    @Mock
    private lateinit var exchangeHttpService: ExchangeHttpService

    @Mock
    private lateinit var marketHttpService: MarketHttpService

    @InjectMocks
    private lateinit var httpInteractor: HttpInteractor

    private lateinit var marketRepository: MarketRepository

    @Before
    fun init() {
        val symbolEntity1 = SymbolEntity("000", "TST", decimals = 2)
        val symbolEntity2 = SymbolEntity("001", "SRCH_TST", decimals = 2)
        `when`(symbolDao.findByAssetId("000")).thenReturn(symbolEntity1)
        `when`(symbolDao.searchBySymbol("SRCH_TST")).thenReturn(listOf(symbolEntity2))
        `when`(symbolDao.getAll()).thenReturn(listOf(symbolEntity1, symbolEntity2))

        marketRepository = MarketRepository(dbInteractor, httpInteractor)
    }

    @Test
    fun getSymbols() = runBlocking {
        val list = marketRepository.getSymbols()
        Assert.assertEquals(2, list.size)
    }

    @Test
    fun getDetails() = runBlocking {
        val coinDtoDb = marketRepository.getDetails("000")
        Assert.assertEquals("TST", coinDtoDb!!.symbol)
    }

    @Test
    fun searchBySymbol() = runBlocking {
        val result = marketRepository.searchBySymbol("SRCH_TST")
        Assert.assertEquals(1, result.size)
    }

    @Test
    fun getDbInteractor() {
        Assert.assertEquals(dbInteractor, marketRepository.dbInteractor)
    }

    @Test
    fun getHttpInteractor() {
        Assert.assertEquals(httpInteractor, marketRepository.httpInteractor)
    }
}