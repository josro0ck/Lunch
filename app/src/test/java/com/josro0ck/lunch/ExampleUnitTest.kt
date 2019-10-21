package com.josro0ck.lunch


import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.stream.Stream

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

data class TestData(
    val input: String?,
    val expected: String?
)

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ExampleUnitTest {

    val ingredient = createTestIngredient()
    val ingredient2 = createTestIngredient(name = "mayo")
    val listOfIngredients = listOf(ingredient, ingredient2)

//    Using this there's no need of @Before
//    private val toTest = init().apply{}
//    init {}


//    Libraries to check:
//    jUnit5, MockK, AssertJ

//    classes and methods are final by default in kotlin
//  Mockito relies in subclassing
//    solution? interfaces or making your classes open


    @Test
    fun `addition is correct`() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    //    Allows to group and read in a nicer way the output of the tests
    @Nested
    inner class NumericTests {

        @Test
        fun `addition is correct`() {
            assertEquals(4, 2 + 2)
        }

        @Test
        fun `test float assertions`() {
//            assertThat(4.0001f).isCloseTo(4.0f, Offset.offset(.001f))
            assertThat(4.0001f).isCloseTo(4.0f) //Using Extensions to avoid irrelevant repetition
        }
    }


    @Nested
    inner class ObjectTest {
        @Test
        fun `testing data classes ignore some fields`() {
            assertThat(ingredient).isEqualToIgnoringGivenFields(
                createTestIngredient()
                , "acquisitionDate"
            )
        }

        @Test
        fun `testing data classes only compare certain fields`() {
            assertThat(ingredient).isEqualToComparingOnlyGivenFields(
                createTestIngredient()
                , "name"
            )
        }

//        This will not work for this data class because of Date
//        @Test
//        fun `testing list of data classes`() {
//            assertThat(List<Ingredient>(1){ingedient})
//                .containsExactly(
//                    Ingredient("mustard", Date(1), 0, RatioScale.KILOS),
//                    Ingredient("mustard", Date(1), 0, RatioScale.KILOS)
//                )
//        }

        @Test
        fun `testing list of data classes ignoring field`() {
            assertThat(listOfIngredients)
                .usingElementComparatorIgnoringFields("acquisitionDate")
                .containsExactly(
                    createTestIngredient(),
                    createTestIngredient("mayo")
                )
        }

        @Test
        fun `testing list of data classes only by certain fields`() {
            assertThat(listOfIngredients)
                .usingElementComparatorOnFields("name")
                .containsExactly(
                    createTestIngredient(),
                    createTestIngredient("mayo")
                )
        }
    }

//    @Nested
//    inner class ParameterizedTests {
//
//        @ParameterizedTest
//        @MethodSource("stringProvider")
//        fun `parse valid strings`(testData: TestData) {
//            assertThat(testData.input).isEqualTo(testData.expected)
//        }
//
//        private fun stringProvider() = Stream.of(
//            TestData("a", "a"),
//            TestData("b", "b"),
//            TestData("c", "c")
//        )
//
//
//    }
}
