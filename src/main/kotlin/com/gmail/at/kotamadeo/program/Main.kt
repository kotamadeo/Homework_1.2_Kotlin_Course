package com.gmail.at.kotamadeo.program

import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    while (true) {
        try {
            println("Укажите количество лайков или напишите 'выход', чтобы выйти из программы")
            val input = scanner.nextLine()
            if (input == "Выйти" || input == "Выход" || input == "выход") {
                println("До встречи!")
                break
            } else {
                if (input.contains("1") && !input.contains("11")) {
                    System.out.printf("Ваша запись понравилась %s человеку.%n", input)
                } else {
                    System.out.printf("Ваша запись понравилась %s людям.%n", input)
                }
            }
        } catch (e: Exception) {
            System.err.println("Ошибка ввода!")
            scanner.close()
            break
        }
    }
}