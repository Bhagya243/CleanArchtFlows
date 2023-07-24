package com.example.cryptocurrencydemo.domain.use_case.get_coins

import com.example.cryptocurrencydemo.common.Resourse
import com.example.cryptocurrencydemo.data.remote.dto.toCoin
import com.example.cryptocurrencydemo.data.remote.dto.toCoinDetail
import com.example.cryptocurrencydemo.domain.model.Coin
import com.example.cryptocurrencydemo.domain.model.CoinDetail
import com.example.cryptocurrencydemo.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinID:String): Flow<Resourse<CoinDetail>> = flow {
        try {
            emit(Resourse.Loading<CoinDetail>())
            val coin = repository.getCoinById(coinID).toCoinDetail()
            emit(Resourse.Success<CoinDetail>(coin))
        } catch (e: HttpException) {
            emit(Resourse.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resourse.Error<CoinDetail>("Couldn't reach server. Check Internet connection"))
        }
    }
}