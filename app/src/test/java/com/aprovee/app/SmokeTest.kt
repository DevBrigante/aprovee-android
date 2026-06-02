package com.aprovee.app

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SmokeTest {
    @Test
    fun truth_isWired() {
        val sum = 2 + 2
        assertThat(sum).isEqualTo(4)
    }
}