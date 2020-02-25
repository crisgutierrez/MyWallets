package com.example.firstbitpandachallenge

import com.example.firstbitpandachallenge.model.CryptocoinWallet
import com.example.firstbitpandachallenge.model.FiatWallet
import com.example.firstbitpandachallenge.model.MetalWallet
import com.example.firstbitpandachallenge.utils.filterByType
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CollectionsTests {

    @Test
    fun `When removing all deleted item in a list full of deleted items, Then nothing is returned`() {

        // Given
        val cut = arrayListOf(
            FiatWallet(
                id = "1",
                name = "EUR Wallet",
                fiatId = "6",
                balance = 400.0,
                isDefault = false,
                deleted = true
            ),
            FiatWallet(
                id = "2",
                name = "CHF Wallet",
                fiatId = "7",
                balance = 0.0,
                isDefault = false,
                deleted = true
            )
        )

        // When
        cut.removeAll { it.deleted }

        // Then
        assertThat(cut).isEmpty()
    }

    @Test
    fun `When removing all deleted item in a list with 1 item deleted and 1 that is not deleted, Then 1 is returned`() {

        // Given
        val cut = arrayListOf(
            FiatWallet(
                id = "1",
                name = "EUR Wallet",
                fiatId = "6",
                balance = 400.0,
                isDefault = false,
                deleted = false
            ),
            FiatWallet(
                id = "2",
                name = "CHF Wallet",
                fiatId = "7",
                balance = 0.0,
                isDefault = false,
                deleted = true
            )
        )

        // When
        cut.removeAll { it.deleted }

        // Then
        assertThat(cut.size).isEqualTo(1)
    }

    @Test
    fun `When removing all deleted item in a list without deleted items, Then the return the full list`() {

        // Given
        val cut = arrayListOf(
            FiatWallet(
                id = "1",
                name = "EUR Wallet",
                fiatId = "6",
                balance = 400.0,
                isDefault = false,
                deleted = false
            ),
            FiatWallet(
                id = "2",
                name = "CHF Wallet",
                fiatId = "7",
                balance = 0.0,
                isDefault = false,
                deleted = false
            )
        )
        val listSizeBeforeRemove = cut.size

        // When
        cut.removeAll { it.deleted }

        // Then
        assertThat(cut.size).isEqualTo(listSizeBeforeRemove)
    }

    @Test
    fun `When filtering the list by Fiat type, Then the returned a list with only Fiat items`() {

        // Given
        val cut = arrayListOf(
            FiatWallet(
                id = "1",
                name = "EUR Wallet",
                fiatId = "6",
                balance = 400.0,
                isDefault = false,
                deleted = false
            ),
            CryptocoinWallet(
                id = "2",
                name = "Test BTC Wallet 2",
                balance = 0.0,
                isDefault = false,
                cryptocoinId = "1",
                deleted = true
            ),
            MetalWallet(
                id = "3",
                name = "Test Palladium Wallet",
                balance = 200.0,
                isDefault = false,
                metalId = "5",
                deleted = false
            )
        )


        cut.removeAll {it.id == "2" || it.id == "3"}

        val filterType = 1

        // When
        val filteredList = cut.filterByType(filterType) { it.type == filterType }

        // Then
        assertThat(filteredList).isEqualTo(cut)
    }


    @Test
    fun `When filtering the list by Cryptocoin type, Then the returned a list with only Cryptocoin items`() {

        // Given
        val cut = arrayListOf(
            FiatWallet(
                id = "1",
                name = "EUR Wallet",
                fiatId = "6",
                balance = 400.0,
                isDefault = false,
                deleted = false
            ),
            CryptocoinWallet(
                id = "2",
                name = "Test BTC Wallet 2",
                balance = 0.0,
                isDefault = false,
                cryptocoinId = "1",
                deleted = true
            ),
            MetalWallet(
                id = "3",
                name = "Test Palladium Wallet",
                balance = 200.0,
                isDefault = false,
                metalId = "5",
                deleted = false
            )
        )

        cut.removeAll {it.id == "1" || it.id == "3"}

        val filterType = 2

        // When
        val filteredList = cut.filterByType(filterType) { it.type == filterType }

        // Then
        assertThat(filteredList).isEqualTo(cut)
    }

    @Test
    fun `When filtering the list by Metal type, Then the returned a list with only Metal items`() {

        // Given
        val cut = arrayListOf(
            FiatWallet(
                id = "1",
                name = "EUR Wallet",
                fiatId = "6",
                balance = 400.0,
                isDefault = false,
                deleted = false
            ),
            CryptocoinWallet(
                id = "2",
                name = "Test BTC Wallet 2",
                balance = 0.0,
                isDefault = false,
                cryptocoinId = "1",
                deleted = true
            ),
            MetalWallet(
                id = "3",
                name = "Test Palladium Wallet",
                balance = 200.0,
                isDefault = false,
                metalId = "5",
                deleted = false
            )
        )

        cut.removeAll {it.id == "1" || it.id == "2"}

        val filterType = 3

        // When
        val filteredList = cut.filterByType(filterType) { it.type == filterType }

        // Then
        assertThat(filteredList).isEqualTo(cut)
    }

    @Test
    fun `When filtering the list by all type, Then the returned a list with all the items`() {

        // Given
        val cut = arrayListOf(
            FiatWallet(
                id = "1",
                name = "EUR Wallet",
                fiatId = "6",
                balance = 400.0,
                isDefault = false,
                deleted = false
            ),
            CryptocoinWallet(
                id = "2",
                name = "Test BTC Wallet 2",
                balance = 0.0,
                isDefault = false,
                cryptocoinId = "1",
                deleted = true
            ),
            MetalWallet(
                id = "3",
                name = "Test Palladium Wallet",
                balance = 200.0,
                isDefault = false,
                metalId = "5",
                deleted = false
            )
        )
        val filterType = 0

        // When
        val filteredList = cut.filterByType(filterType) { it.type == filterType }

        // Then
        assertThat(filteredList).isEqualTo(cut)
    }

    @Test
    fun `When sorting the list by type and balance, Then the returned a list is sorted`() {

        // Given
        val cut = arrayListOf(
            MetalWallet(
                id = "4",
                name = "Test Palladium Wallet",
                balance = 200.0,
                isDefault = false,
                metalId = "5",
                deleted = false
            ),
            CryptocoinWallet(
                id = "3",
                name = "Test BTC Wallet 2",
                balance = 150.0,
                isDefault = false,
                cryptocoinId = "1",
                deleted = true
            ),
            CryptocoinWallet(
                id = "2",
                name = "Test BTC Wallet 3",
                balance = 100.0,
                isDefault = false,
                cryptocoinId = "1",
                deleted = true
            ),
            FiatWallet(
                id = "1",
                name = "EUR Wallet",
                fiatId = "6",
                balance = 400.0,
                isDefault = false,
                deleted = false
            )
        )

        val firtItemId = "1"
        val secondItemId = "2"
        val thirdtemId = "3"
        val fourthItemId = "4"

        // When
        val filteredList = cut.sortedWith(compareBy({ it.type }, { it.balance }))

        // Then
        assertThat(filteredList[0].id).isEqualTo(firtItemId)
        assertThat(filteredList[1].id).isEqualTo(secondItemId)
        assertThat(filteredList[2].id).isEqualTo(thirdtemId)
        assertThat(filteredList[3].id).isEqualTo(fourthItemId)
    }
}