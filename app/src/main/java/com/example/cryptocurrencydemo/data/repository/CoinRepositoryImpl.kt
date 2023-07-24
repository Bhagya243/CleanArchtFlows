package com.example.cryptocurrencydemo.data.repository

import javax.inject.Inject
import com.example.cryptocurrencydemo.data.remote.CoinPaprikaAPI
import com.example.cryptocurrencydemo.data.remote.dto.CoinDetailDto
import com.example.cryptocurrencydemo.data.remote.dto.CoinDto
import com.example.cryptocurrencydemo.domain.repository.CoinRepository

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaAPI
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }


}