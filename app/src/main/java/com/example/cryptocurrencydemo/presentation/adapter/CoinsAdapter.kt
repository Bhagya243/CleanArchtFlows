package com.example.cryptocurrencydemo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencydemo.databinding.CoinItemBinding
import com.example.cryptocurrencydemo.domain.model.Coin

class CoinsAdapter(private val coins: List<Coin>) :
    RecyclerView.Adapter<CoinsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CoinItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CoinItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(coins[position]) {

                binding.tvCoinName.text = this.name
                binding.tvCoinRank.text = this.rank.toString()
                if (this.isActive) {
                    binding.tvCoinStatus.text = "Active"
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return coins.size
    }

}