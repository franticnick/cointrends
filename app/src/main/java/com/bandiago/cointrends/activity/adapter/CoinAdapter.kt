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

package com.bandiago.cointrends.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bandiago.cointrends.R
import com.bandiago.cointrends.activity.adapter.dto.CoinDto

class CoinAdapter(val context: Context) : RecyclerView.Adapter<CoinAdapter.ViewHolder>() {

    private var mItems: ArrayList<CoinDto> = ArrayList()

    class ViewHolder(itView: View) : RecyclerView.ViewHolder(itView) {
        val cardView: CardView = itView.findViewById(R.id.cardCoinView)

        val tvSymbol = itView.findViewById<TextView>(R.id.assetSymbol)
        val tvassetId = itView.findViewById<TextView>(R.id.assetId)
        val tvIssuer = itView.findViewById<TextView>(R.id.issuer)
        val tvName = itView.findViewById<TextView>(R.id.name)
        val tvIssueDate = itView.findViewById<TextView>(R.id.issueDate)
        val tvQuantity = itView.findViewById<TextView>(R.id.quantity)
        val tvReissuable = itView.findViewById<TextView>(R.id.reissuable)
        val tvDecimals = itView.findViewById<TextView>(R.id.decimals)
        val tvDescription = itView.findViewById<TextView>(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: CardView = LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false) as CardView

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemDto = mItems[position]

        with(itemDto) {
            holder.tvSymbol.text = symbol
            holder.tvassetId.text = assetId
            holder.tvIssuer.text = issuer
            holder.tvName.text = name
            holder.tvIssueDate.text = issueDate
            holder.tvQuantity.text = quantity
            when(reissuable){
                "true" -> holder.tvReissuable.text = context.getString(R.string.yes)
                "false" -> holder.tvReissuable.text = context.getString(R.string.no)
            }
            holder.tvDecimals.text = decimals.toString()
            holder.tvDescription.text = description
        }

    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun addAllSymbols(symbolDtos: List<CoinDto>) {
        mItems.clear()
        notifyDataSetChanged()

        symbolDtos.forEachIndexed { index, coinDto ->
            mItems.add(coinDto)
            notifyItemInserted(index)
        }

    }

    fun updateItem(coinDto: CoinDto) {
        val item = mItems.find { it.assetId == coinDto.assetId }
        if (item != null) {
            val index = mItems.indexOf(item)
            with(coinDto) {
                item.decimals = decimals
                item.name = name
                item.description = description
                item.issuer = issuer
                item.quantity = quantity
                item.reissuable = reissuable
                item.issueDate = issueDate
            }

            notifyItemChanged(index)
        }
    }

    fun replaceAllWith(coinDtos: List<CoinDto>?) {
        mItems.clear()
        mItems.addAll(coinDtos!!.asIterable())
        notifyDataSetChanged()
    }
}