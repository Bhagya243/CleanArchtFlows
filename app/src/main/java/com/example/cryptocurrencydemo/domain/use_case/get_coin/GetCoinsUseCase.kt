package com.example.cryptocurrencydemo.domain.use_case.get_coin

import com.example.cryptocurrencydemo.common.Resourse
import com.example.cryptocurrencydemo.data.remote.dto.toCoin
import com.example.cryptocurrencydemo.domain.model.Coin
import com.example.cryptocurrencydemo.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resourse<List<Coin>>> = flow {
        try {
            emit(Resourse.Loading<List<Coin>>())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resourse.Success<List<Coin>>(coins))
        } catch (e: HttpException) {
            emit(Resourse.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resourse.Error<List<Coin>>("Couldn't reach server. Check Internet connection"))
        }
    }

}