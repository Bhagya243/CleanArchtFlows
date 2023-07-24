package com.example.cryptocurrencydemo

import com.example.cryptocurrencydemo.domain.model.Coin
import com.example.cryptocurrencydemo.presentation.CoinListState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CoinListStateTest {

    @Test
    fun testLoading() {
        val coinListState = CoinListState()
        val myViewStateCopy = coinListState.copy(isLoading = true)
        assertEquals(true, myViewStateCopy.isLoading)
    }

    @Test
    fun testError() {
        val coinListState = CoinListState()
        val myViewStateCopy = coinListState.copy(error = "Network Error")
        assertEquals("Network Error", myViewStateCopy.error)
    }

    @Test
    fun testCoinList() {
        val coinListState = CoinListState()
        val coins: List<Coin> = listOf(Coin("1",true,"BTS",1,"BTS"),Coin("2",true,"BTS",1,"BTS"))
        val myViewStateCopy = coinListState.copy(coins = coins)
        assertNotEquals(0, myViewStateCopy.coins.size)
    }

    @Test
    fun testEmptyCoinList() {
        val coinListState = CoinListState()
        val coins: List<Coin> = emptyList()
        val myViewStateCopy = coinListState.copy(coins = coins)
        assertEquals(0, myViewStateCopy.coins.size)
    }
}