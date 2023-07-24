package com.example.cryptocurrencydemo.presentation

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cryptocurrencydemo.common.Resourse
import com.example.cryptocurrencydemo.domain.model.Coin
import com.example.cryptocurrencydemo.domain.use_case.get_coin.GetCoinsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CoinListViewModelTest {

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var getCoinsUseCase: GetCoinsUseCase
    private lateinit var viewModel: CoinListViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel =
            Mockito.spy(
                CoinListViewModel(
                    getCoinsUseCase
                )
            )
    }

    @Test
    fun `when getcoins called then update viewmodel`() {
        // viewModel.getCoins()
        // invoke return >> Flow<Resourse<List<Coin>>>
        val channel = Channel<Resourse<List<Coin>>>()
        //every { mockedUseCase.someDataFlow } returns channel.consumeAsFlow()
        //Mockito.`when`(getCoinsUseCase.invoke()).thenReturn(0)

        //every { getCoinsUseCase.invoke() }returns channel.consumeAsFlow()

        // assertThat(viewModelUnderTest.uiState.value, `is`("a"))
        val list12: List<Coin> = listOf<Coin>(Coin("1", true, "coin", 1, "BTS"), Coin("1", true, "coin", 1, "BTS"))
       /* val m = mockk<Flow<Resourse<list12>>>()
        `when`(getCoinsUseCase.invoke()).then(Resourse<List<Coin>>)*/

        Assert.assertEquals(0, viewModel.coins.value?.size)
    }
}