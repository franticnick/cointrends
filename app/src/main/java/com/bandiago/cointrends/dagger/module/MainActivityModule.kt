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

package com.bandiago.cointrends.dagger.module

import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.bandiago.cointrends.datamodel.MarketRepository
import com.bandiago.cointrends.activity.MainActivity
import com.bandiago.cointrends.activity.adapter.CoinAdapter
import com.bandiago.cointrends.datamodel.db.AppDatabase
import com.bandiago.cointrends.viewmodel.MarketViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainActivityModule(val activity: MainActivity) {

    @Provides
    fun providesAppDatabase(): AppDatabase = Room.databaseBuilder(
        activity.applicationContext, AppDatabase::class.java, "market-db"
    ).build()


    @Singleton
    @Provides
    fun providesCoinAdapter(): CoinAdapter {
        return CoinAdapter(activity)
    }


    @Singleton
    @Provides
    fun providesMarketViewModel(marketRepository: MarketRepository) : MarketViewModel  {
        val marketViewModel =  ViewModelProviders.of(activity).get(MarketViewModel::class.java)
        marketViewModel.marketRepository = marketRepository
        return marketViewModel
    }
}