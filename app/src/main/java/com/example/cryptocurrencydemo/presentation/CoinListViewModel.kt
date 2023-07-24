package com.example.cryptocurrencydemo.presentation


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencydemo.common.Resourse
import com.example.cryptocurrencydemo.domain.model.Coin
import com.example.cryptocurrencydemo.domain.use_case.get_coin.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {
    private val TAG = "CoinListViewModel"
    val coins = MutableLiveData<List<Coin>>()
    val error = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    val _state = mutableStateOf(CoinListState())

    val state: State<CoinListState> = _state


    init {
        getCoins()
    }

    fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resourse.Success -> {
                    Log.d(TAG, "getCoins: ${result.data?.size}")
                    coins.value = result.data
                    _state.value = CoinListState(coins = result.data ?: emptyList())

                }
                is Resourse.Error -> {
                    error.value = result.message ?: "An unexpected erroe occured"
                    _state.value =
                        CoinListState(error = result.message ?: "An unexpected erroe occured")
                }
                is Resourse.Loading -> {
                    isLoading.value = true
                    _state.value = CoinListState(isLoading = true)

                }
            }
        }.launchIn(viewModelScope)
    }
}
