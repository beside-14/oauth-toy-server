package com.bside.v8

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class ApplicationTests : BehaviorSpec({

    Given("빌드를 하기 위해서") {
        val a = listOf(0, 1, 2, 3, 4)
        val b = listOf(5, 6, 7, 8, 9)

        When("하나 이상의 테스트가") {
            val c = a + b

            Then("존재해야 한다.") {
                c.size shouldBe 10
                c.sum() shouldBe 45
            }
        }
    }
})
