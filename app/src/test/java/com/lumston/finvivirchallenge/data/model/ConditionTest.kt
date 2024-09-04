package com.lumston.finvivirchallenge.data.model

import org.junit.Test

class ConditionTest {
    @Test
    fun `data class copy properties test`() {
        // GIVEN
        val condition = Condition(
            main = "Rain", description = "Rainy day",
            "icon"
        )

        // WHEN
        val copy = condition.copy()

        // THEN
        assert(copy == condition)
    }
}