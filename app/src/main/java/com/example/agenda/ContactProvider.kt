package com.example.agenda

class ContactProvider {
    companion object{
        val contactList = listOf<ContactData>(
            ContactData(
                "Pilar",
                "Pina",
                "pilipin@gmail.com",
                "697878412",
                R.drawable.profile2
            ),
            ContactData(
                "Pepe",
                "González",
                "pepe@gmail.com",
                "678945213",
                R.drawable.man
            ),
            ContactData(
                "Ana",
                "García",
                "agarcia@gmail.com",
                "632541789",
                R.drawable.girl
            ),
            ContactData(
                "Antonio",
                "Saez",
                "saezant@gmail.com",
                "625478932",
                R.drawable.profile1
            )
        )
    }
}