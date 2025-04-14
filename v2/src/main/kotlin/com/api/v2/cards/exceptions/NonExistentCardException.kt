package com.api.v2.cards.exceptions

class NonExistentCardException(id: String): RuntimeException("Card whose id is $id was not found.")