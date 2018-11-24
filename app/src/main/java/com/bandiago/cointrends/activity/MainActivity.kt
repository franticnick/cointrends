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

package com.bandiago.cointrends.activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bandiago.cointrends.R
import com.bandiago.cointrends.app
import com.bandiago.cointrends.dagger.module.MainActivityModule
import com.bandiago.cointrends.activity.adapter.CoinAdapter
import com.bandiago.cointrends.activity.adapter.dto.CoinDto
import com.bandiago.cointrends.activity.search.SearchSuggestionProvider
import com.bandiago.cointrends.viewmodel.MarketViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.contentView
import javax.inject.Inject
import android.widget.EditText
import android.R.id.closeButton


class MainActivity : AppCompatActivity() {
    private val component by lazy {
        app.component
            .add(MainActivityModule(this))
    }

    private lateinit var pullToRefresh: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: LinearLayout
    private lateinit var mSnackbar: Snackbar


    @Inject
    lateinit var mCoinAdapter: CoinAdapter

    @Inject
    lateinit var mMarketViewModel: MarketViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        component.inject(this)

        //enable type-to-search functionality
        setDefaultKeyMode(DEFAULT_KEYS_SEARCH_LOCAL)

        recyclerView = contentView!!.findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mCoinAdapter

        progressBar = findViewById(R.id.layoutProgressBar)

        mSnackbar = Snackbar.make(
            findViewById<RelativeLayout>(R.id.activity_main),
            R.string.error, Snackbar.LENGTH_LONG
        )

        pullToRefresh = findViewById(R.id.pullToRefresh)
        pullToRefresh.setOnRefreshListener {
            getAllSymbols()
            pullToRefresh.isRefreshing = false
        }

        getAllSymbols()
    }

    public override fun onRestart() {  // After a pause OR at startup
        super.onRestart()
        //Refresh your stuff here
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        //reset search on exit from search widget
        searchView.setOnCloseListener {
            getAllSymbols()
            true
        }

        searchView.setOnQueryTextFocusChangeListener{ v, b ->
            if(!b) {
                getAllSymbols()
            }
        }

        return true
    }

    override fun onNewIntent(intent: Intent) {
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                SearchRecentSuggestions(this, SearchSuggestionProvider.AUTHORITY, SearchSuggestionProvider.MODE)
                    .saveRecentQuery(query, null)

                mMarketViewModel.searchBySymbol(query).observe(this, Observer<List<CoinDto>> { coinDtos ->
                    if (coinDtos != null) {
                        mCoinAdapter.replaceAllWith(coinDtos)
                    } else {
                        mSnackbar.show()
                    }
                    progressBar.visibility = View.GONE
                })
            }
        }
    }


    private fun getAllSymbols() {
        progressBar.visibility = View.VISIBLE
        mMarketViewModel.getSymbols().observe(this, Observer<List<CoinDto>> { coinDtos ->
            if (coinDtos != null) {
                mCoinAdapter.addAllSymbols(coinDtos)
                coinDtos.forEach {
                    if (it.decimals.isEmpty()) {
                        mMarketViewModel.getDetails(it.assetId).observe(this, Observer<CoinDto> { coinDto ->
                            if (coinDto != null) {
                                mCoinAdapter.updateItem(coinDto)
                            } else {
                                mSnackbar.show()
                            }
                        })
                    }
                }

            } else {
                mSnackbar.show()
            }
            progressBar.visibility = View.GONE
        })
    }
}
