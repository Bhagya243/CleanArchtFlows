package com.example.cryptocurrencydemo.domain.repository

import com.example.cryptocurrencydemo.data.remote.dto.CoinDetailDto
import com.example.cryptocurrencydemo.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins():List<CoinDto>

    suspend fun getCoinById(coinId:String):CoinDetailDto
}