package com.lumston.finvivirchallenge.framework.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
class TestDispatcherImpl(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
): DispatcherProvider {
    override fun main(): CoroutineDispatcher {
        return testDispatcher
    }

    override fun default(): CoroutineDispatcher {
        return testDispatcher
    }

    override fun io(): CoroutineDispatcher {
        return testDispatcher
    }
}