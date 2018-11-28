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

package com.bandiago.cointrends.dagger.component

import com.bandiago.cointrends.dagger.module.HttpServiceModule
import com.bandiago.cointrends.activity.MainActivity
import com.bandiago.cointrends.dagger.module.MainActivityModule
import com.bandiago.cointrends.dagger.module.RepositoryModule
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = [MainActivityModule::class, RepositoryModule::class, HttpServiceModule::class])
interface MainActivityComponent {
    fun inject(activity: MainActivity)
}