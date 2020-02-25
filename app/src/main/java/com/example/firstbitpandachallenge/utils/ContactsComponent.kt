package com.example.firstbitpandachallenge.utils

import com.example.firstbitpandachallenge.model.Contact
import com.example.firstbitpandachallenge.model.Time

private const val MAX_RECENT_LIST = 3

class ContactsComponent {

    /**
     * This is a mock list just for testing.
     */
    var contacts: List<Contact> = listOf(
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

    /**
     * Return the [contacts] list sorted by last used, so the first item will be the one that has the
     * most recent date and the last item will be the one with the last recent date. Then we distinct
     * the list by data this mean that we remove the items with duplicated data and we return a list
     * with a maximum of 3 items.
     */
    fun getRecentContacts(): List<Contact> {
        val nonDuplicatedList = contacts.sortedBy { it.last_used }.distinctBy { it.data }

        val maxListItems = when {
            nonDuplicatedList.isNullOrEmpty() -> 0
            (nonDuplicatedList.size < 3) -> nonDuplicatedList.size
            else -> MAX_RECENT_LIST
        }

        return nonDuplicatedList.subList(0, maxListItems)
    }
}