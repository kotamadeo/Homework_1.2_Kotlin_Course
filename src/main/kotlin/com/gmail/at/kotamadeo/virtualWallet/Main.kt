package com.gmail.at.kotamadeo.virtualWallet

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*

private val USD_EXCHANGE_RATE = 120.0
private val BANK_COMMISSION_RUB = 35.0
private val BANK_COMMISSION_GROSS = 0.75
private val BANK_COMMISSION_USD = BANK_COMMISSION_RUB / USD_EXCHANGE_RATE
private val decimalFormat = DecimalFormat("#.###")
private val ANSI_RESET = "\u001B[0m"
private val ANSI_BLACK = "\u001B[30m"
private val ANSI_RED = "\u001B[31m"
private val ANSI_GREEN = "\u001B[32m"
private val ANSI_YELLOW = "\u001B[33m"
private val ANSI_BLUE = "\u001B[34m"
private val ANSI_PURPLE = "\u001B[35m"
private val ANSI_CYAN = "\u001B[36m"
private val ANSI_WHITE = "\u001B[37m"

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val random = Random(10)
    var balanceRUB = 0.0
    var balanceUSD = 0.0
    while (true) {
        try {
            printMenu()
            val input = scanner.nextLine()
            if (input == "Выйти" || input == "выйти" || input == "выход") {
                System.out.printf("%sДо встречи!%s%n", ANSI_BLUE, ANSI_RESET)
                break
            } else {
                when (input.toInt()) {
                    1 -> {
                        System.out.printf(
                            "%sУкажите количество денежных средств для пополнения счета:%n>>>%s",
                            ANSI_BLUE,
                            ANSI_RESET
                        )
                        val inputMoneyAmount = scanner.nextLine()
                        val moneyAmount = inputMoneyAmount.toDouble()
                        balanceRUB += moneyAmount
                    }
                    2 -> System.out.printf("%sВаш баланс (в рублях): %s коп.%s%n", ANSI_BLUE, balanceRUB, ANSI_RESET)
                    3 -> {
                        val balanceBDRUB = BigDecimal(balanceRUB.toString())
                        System.out.printf(
                            "%sВаш округленный баланс (в рублях): %s%s%n", ANSI_BLUE,
                            balanceBDRUB.setScale(0, RoundingMode.HALF_DOWN).toString(), ANSI_RESET
                        )
                    }
                    4 -> {
                        balanceUSD = balanceRUB / USD_EXCHANGE_RATE
                        System.out.printf(
                            "%sВаш баланс (в долларах): %s цент.%s%n",
                            ANSI_BLUE,
                            decimalFormat.format(balanceUSD),
                            ANSI_RESET
                        )
                    }
                    5 -> {
                        val balanceBDUSD = BigDecimal(java.lang.Double.toString(balanceUSD))
                        System.out.printf(
                            "%sВаш округленный баланс (в долларах): %s%s%n",
                            ANSI_BLUE,
                            balanceBDUSD.setScale(0, RoundingMode.HALF_DOWN).toString(),
                            ANSI_RESET
                        )
                    }
                    6 -> {
                        System.out.printf(
                            "%sВведите сумму для перевода денежных средств в рублях и укажите  id пользователя:%n>>>%s",
                            ANSI_BLUE, ANSI_RESET
                        )
                        val inputMoneyToTransferRUB = scanner.nextLine()
                        val moneyToTransferRUB = inputMoneyToTransferRUB.toDouble()
                        if (balanceRUB <= moneyToTransferRUB) {
                            System.out.printf(
                                "%sК сожалению, баланс денежных средств недостаточен для совершения операции. " +
                                        "Пополните баланс и попробуйте снова!%s%n", ANSI_RED, ANSI_RESET
                            )
                        } else {
                            balanceRUB -= if (moneyToTransferRUB > 75000.01) {
                                moneyToTransferRUB * BANK_COMMISSION_GROSS + BANK_COMMISSION_RUB
                            } else {
                                moneyToTransferRUB + BANK_COMMISSION_RUB
                            }
                            System.out.printf(
                                "%sПеревод денежных средств пользователю %s успешно завершен!%s%n",
                                ANSI_GREEN,
                                random.nextInt(999999),
                                ANSI_RESET
                            )
                        }
                    }
                    7 -> {
                        System.out.printf(
                            "%sВведите сумму для перевода денежных средств в долларах и укажите  id пользователя:%n>>>%s",
                            ANSI_BLUE,
                            ANSI_RESET
                        )
                        val inputMoneyToTransferUSD = scanner.nextLine()
                        val moneyToTransferUSD = inputMoneyToTransferUSD.toDouble()
                        if (balanceRUB <= moneyToTransferUSD) {
                            System.out.printf(
                                "%sК сожалению, баланс денежных средств недостаточен для совершения операции. " +
                                        "Пополните баланс и попробуйте снова!%s%n", ANSI_RED, ANSI_RESET
                            )
                        } else {
                            balanceRUB -= if (moneyToTransferUSD > 800.01) {
                                moneyToTransferUSD * BANK_COMMISSION_GROSS + BANK_COMMISSION_USD
                            } else {
                                moneyToTransferUSD + BANK_COMMISSION_USD
                            }
                            System.out.printf(
                                "%sПеревод денежных средств пользователю %s успешно завершен!%s%n",
                                ANSI_GREEN,
                                random.nextInt(999999),
                                ANSI_RESET
                            )
                        }
                    }
                }
            }
        } catch (e: NumberFormatException) {
            System.err.printf("К сожалению, вы ввели не цифру! Попробуйте еще раз!%n")
        }
    }
}

fun printMenu() {
    System.out.printf(
        "%sДобро пожаловать в виртуальный кошелек! Возможные функции приложения:%s%n",
        ANSI_YELLOW,
        ANSI_RESET
    )
    System.out.printf("%sВведите цифру, чтобы:%s%n", ANSI_GREEN, ANSI_RESET)
    System.out.printf(
        "%s1. Пополнить счет.%n2. Показать баланс в рублях.%n3. Показать округленный баланс в рублях." +
                "%n4. Показать баланс в долларах по курсу: %s рублей за 1 доллар.%n" +
                "5. Показать округленный баланс в долларах по курсу: %s рублей за 1 доллар.%n" +
                "6. Совершить перевод денежных средств в рублях.%n7. Совершить перевод денежных средств в долларах.%n" +
                "Или'выйти', чтобы завершить работу.%s%n", ANSI_PURPLE, USD_EXCHANGE_RATE, USD_EXCHANGE_RATE, ANSI_RESET
    )
    println(
        ANSI_CYAN + "Обращаем ваше внимание, что минимальная комиссия составляет в рублях " + BANK_COMMISSION_RUB + " коп. или в долларах "
                + decimalFormat.format(BANK_COMMISSION_USD) + " цент., а также " + BANK_COMMISSION_GROSS +
                "% от суммы перевода свыше 75000 рублей (800 долларов)." + ANSI_RESET
    )
}
