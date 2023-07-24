package com.example.cryptocurrencydemo

import com.example.cryptocurrencydemo.domain.model.Coin
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TestCoin {

    @Test
    fun testCoinData() {
        val actualCoin = Coin(id = "2", isActive = true, name = "Augur", rank = 2, symbol = "AUG")
        val expectedCoin =
            Coin(id = "2", isActive = false, name = "Bitcoin", rank = 6, symbol = "BTS")
        assertEquals(actualCoin.id, expectedCoin.id)
    }

    /* assertNotEquals(actualCoin.isActive, false)
     assertNotEquals(actualCoin,expectedCoin)*/
}