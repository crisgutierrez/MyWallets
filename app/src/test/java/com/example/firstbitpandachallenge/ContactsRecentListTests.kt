package com.example.firstbitpandachallenge

import com.example.firstbitpandachallenge.model.*
import com.example.firstbitpandachallenge.utils.ContactsComponent
import com.example.firstbitpandachallenge.utils.filterByType
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ContactsRecentListTests {

    private val contactsComponent = ContactsComponent()

    @Test
    fun `When filtering the list by recent contacts, Then a list with the recent contact without duplicating data sorted and with only 3 items is returned`() {

        // Given
        contactsComponent.contacts = listOf(
            Contact(
                "1",
                "A",
                Time("14-02-2020")
            ),
            Contact(
                "1",
                "B",
                Time("25-02-2020")
            ),
            Contact(
                "1",
                "B",
                Time("24-02-2020")
            ),
            Contact(
                "1",
                "A",
                Time("05-02-2020")
            ),
            Contact(
                "1",
                "D",
                Time("17-02-2020")
            ),
            Contact(
                "1",
                "D",
                Time("21-02-2020")
            ),
            Contact(
                "1",
                "F",
                Time("16-02-2020")
            )
        )

        // When
        val recentContacts = contactsComponent.getRecentContacts()

        // Then
        assertThat(recentContacts[0].last_used.unix).isEqualTo("25-02-2020")
        assertThat(recentContacts[1].last_used.unix).isEqualTo("21-02-2020")
        assertThat(recentContacts[2].last_used.unix).isEqualTo("16-02-2020")
        assertThat(recentContacts.size).isEqualTo(3)
    }

    @Test
    fun `When filtering an empty list by recent contacts, Then an empty is returned`() {

        // Given
        contactsComponent.contacts = emptyList()

        // When
        val recentContacts = contactsComponent.getRecentContacts()

        // Then
        assertThat(recentContacts).isEmpty()
    }

    @Test
    fun `When filtering a list of 2 item with data duplicated by recent contacts, Then a list with the only 1 item (the most recent one) is returned`() {

        // Given
        contactsComponent.contacts = listOf(
            Contact(
                "1",
                "B",
                Time("10-02-2020")
            ),
            Contact(
                "1",
                "B",
                Time("25-02-2020")
            )
        )

        // When
        val recentContacts = contactsComponent.getRecentContacts()

        // Then
        assertThat(recentContacts[0].last_used.unix).isEqualTo("25-02-2020")
        assertThat(recentContacts.size).isEqualTo(1)
    }

    @Test
    fun `When filtering a list of all data duplicated by recent contacts, Then a list with the only 1 item (the most recent one) is returneds`() {

        // Given
        contactsComponent.contacts = listOf(
            Contact(
                "1",
                "B",
                Time("10-02-2020")
            ),
            Contact(
                "1",
                "B",
                Time("25-02-2020")
            ),
            Contact(
                "1",
                "B",
                Time("15-02-2020")
            ),
            Contact(
                "1",
                "B",
                Time("17-02-2020")
            ),
            Contact(
                "1",
                "B",
                Time("23-02-2020")
            ),
            Contact(
                "1",
                "B",
                Time("18-02-2020")
            ),
            Contact(
                "1",
                "B",
                Time("22-02-2020")
            )
        )

        // When
        val recentContacts = contactsComponent.getRecentContacts()

        // Then
        assertThat(recentContacts[0].last_used.unix).isEqualTo("25-02-2020")
        assertThat(recentContacts.size).isEqualTo(1)
    }


    @Test
    fun `When filtering a list of 2 different items by recent contacts, Then the same list but sorted is returned`() {

        // Given
        contactsComponent.contacts = listOf(
            Contact(
                "1",
                "B",
                Time("10-02-2020")
            ),
            Contact(
                "1",
                "D",
                Time("25-02-2020")
            )
        )

        // When
        val recentContacts = contactsComponent.getRecentContacts()

        // Then
        assertThat(recentContacts[0].last_used.unix).isEqualTo("25-02-2020")
        assertThat(recentContacts[1].last_used.unix).isEqualTo("10-02-2020")
    }
}