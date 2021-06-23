package com.dimvvmkotlinbinding.practice_classes

import kotlin.random.Random

class ScopeFunctionsPractice
{
    fun getRandomInt(): Int {
        return Random.nextInt(100).also {
            println("getRandomInt() generated value $it")
        }
    }

    val numberList = mutableListOf<Double>()


    init {
        val i = getRandomInt()
        println("getRandomInt() $i")

        numberList.also {
            println("Populating the list")
            it.add(4.3)
        }
            .apply {
                add(2.71)
                add(3.14)
                add(1.0)
            }
            .also { println("Sorting the list") }
            .sort()


        val numbers = mutableListOf("one", "two", "three")
        val countEndsWithE = numbers.run {
            this.add("four")
            this.add("five")
            this.count { it.endsWith("e") }
        }
        println("There are $countEndsWithE elements that end with e.")


        with(numbers) {
            val firstItem = first()
            val lastItem = last()
            println("First item: $firstItem, last item: $lastItem")
        }

        val vlavd = numbers.let {
            it.add("Six")
            it.first() + it.last()
        }

        println(vlavd)
        numbers.map {
            it.length
        }.filter {
            it > 3
        }.let {
            println(it)
            // and more function calls if needed
        }

        numbers.map { it.length }.filter { it > 3 }.let(::println)


        val str: String? = "Hello"
        val length = str?.let {
            println("let() called on $it")
            it.length
        }

        val numbers2 = listOf("one", "two", "three", "four")
        val modifiedFirstItem = numbers2.first().let { firstItem ->
            println("The first item of the list is '$firstItem'")
            if (firstItem.length >= 5) firstItem else "!" + firstItem + "!"
        }.uppercase()
        println("First item after modifications: '$modifiedFirstItem'" + numbers2)

        runWithoutExtention()
        takeIfTakeUnless()
    }

    fun runWithoutExtention(){
        val hexNumberRegex = run {
            val digits = "0-9"
            val hexDigits = "A-Fa-f"
            val sign = "+-"

            Regex("[$sign]?[$digits$hexDigits]+")
        }

        for (match in hexNumberRegex.findAll("+1234 -FFFF not-a-number")) {
            println(match.value)
        }
    }

    fun takeIfTakeUnless(){
        val number = Random.nextInt(100)

        val evenOrNull = number.takeIf { it % 2 == 0 }
        val oddOrNull = number.takeUnless { it % 2 == 0 }
        println("even: $evenOrNull, odd: $oddOrNull")
    }
}