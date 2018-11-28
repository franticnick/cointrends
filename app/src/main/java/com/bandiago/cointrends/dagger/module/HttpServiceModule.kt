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

import com.bandiago.cointrends.datamodel.http.service.ExchangeHttpService
import com.bandiago.cointrends.datamodel.http.service.MarketHttpService
import com.bandiago.cointrends.datamodel.http.service.NodeHttpService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
class HttpServiceModule {

    companion object {
        const val DEFAULT_NODE = "https://nodes.wavesplatform.com" //main node
        const val HTTPS_MARKETDATA = "https://marketdata.wavesplatform.com/"
        const val HTTPS_EXCHANGE = "https://api.wavesplatform.com/"
    }

    @Provides
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .build()

    @Provides
    @Named("retrofitNode")
    fun providesRetrofit(httpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(DEFAULT_NODE)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(httpClient)
        .build()

    @Provides
    @Named("retrofitMarket")
    fun providesRetrofitMarketData(httpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(HTTPS_MARKETDATA)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(httpClient)
        .build()

    @Provides
    @Named("retrofitExchange")
    fun provdesRetrofitExchange(httpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(HTTPS_EXCHANGE)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(httpClient)
        .build()

    @Provides
    fun providesNodeHttpService(@Named("retrofitNode") retrofit: Retrofit): NodeHttpService =
        retrofit.create(NodeHttpService::class.java)


    @Provides
    fun providesMarketHttpService(@Named("retrofitMarket") retrofitMarket: Retrofit): MarketHttpService =
        retrofitMarket.create(MarketHttpService::class.java)

    @Provides
    fun providesExchangeHttpService(@Named("retrofitExchange") retrofitExchange: Retrofit): ExchangeHttpService =
        retrofitExchange.create(ExchangeHttpService::class.java)

}