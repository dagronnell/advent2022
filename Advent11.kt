fun advent11Part1() {
    val monkeys = input11.split("Monkey").drop(1).map {
        Monkey.createFrom(it)
    }

    repeat(20) {
        monkeys.forEach { monkey ->
            monkey.items.forEach { item ->
                monkey.inspections++
                val operand = if (monkey.operation.operand == "old") {
                    item
                } else {
                    monkey.operation.operand.toLong()
                }

                val worryLevel = monkey.operation.op.invoke(operand, item).floorDiv(3)
                val receiver = monkey.getThrowToMonkey(worryLevel)
                monkeys.find { it.number == receiver }?.items?.add(worryLevel)
            }

            monkey.items.clear()
        }
    }

    val value = monkeys.map { it.inspections }.sorted().reversed()
        .take(2)
        .reduce { acc, i -> acc * i }

    println(value)
}

fun advent11Part2() {
    val monkeys = input11.split("Monkey").drop(1).map {
        Monkey.createFrom(it)
    }
    val modNumber = monkeys.map { it.testDivisibleNumber }.reduce { acc, i -> acc * i }

    repeat(10000) {
        monkeys.forEach { monkey ->
            monkey.items.forEach { item ->
                monkey.inspections++
                val operand = if (monkey.operation.operand == "old") {
                    item
                } else {
                    monkey.operation.operand.toLong()
                }

                val worryLevel = monkey.operation.op.invoke(operand, item) % modNumber
                val receiver = monkey.getThrowToMonkey(worryLevel)
                monkeys.find { it.number == receiver }?.items?.add(worryLevel)
            }

            monkey.items.clear()
        }
    }

    val value = monkeys.map { it.inspections.toLong() }.sorted().reversed()
        .take(2)
        .reduce { acc, i -> acc * i }

    println(value)
}

data class Monkey(
    val number: Int,
    val items: MutableList<Long>,
    val operation: Operation,
    val testDivisibleNumber: Int,
    var receiverIfTrue: Int,
    var receiverIfFalse: Int,
    var inspections: Int = 0
) {
    fun getThrowToMonkey(worryLevel: Long): Int =
        if (worryLevel % testDivisibleNumber == 0L) {
            receiverIfTrue
        } else {
            receiverIfFalse
        }

    companion object {
        fun createFrom(s: String): Monkey {
            val lines = s.lines()
            var lineCnt = 0
            val number = lines[lineCnt++].substringBefore(":").trim().toInt()
            val items = lines[lineCnt++].substringAfter("Starting items:").split(",").toList()
                .map { it.trim().toLong() }
                .toMutableList()
            val op = Operation.from(lines[lineCnt++].substringAfter("Operation: new = old "))
            val divisibleBy = lines[lineCnt++].substringAfter("divisible by ").toInt()

            return Monkey(
                number = number,
                items = items,
                operation = op,
                testDivisibleNumber = divisibleBy,
                receiverIfTrue = lines[lineCnt++].substringAfter("If true: throw to monkey ").toInt(),
                receiverIfFalse = lines[lineCnt++].substringAfter("If false: throw to monkey ").toInt()
            )
        }
    }
}

data class Operation(val operand: String, val op: (Long, Long) -> (Long)) {
    companion object {
        fun from(s: String): Operation {
            val parts = s.trim().split(" ")
            val op = when (parts[0]) {
                "+" -> { i1: Long, i2: Long -> i1 + i2 }
                "*" -> { i1: Long, i2: Long -> i1 * i2 }
                else -> throw RuntimeException("Can't handle ${parts[0]}")
            }
            return Operation(op = op, operand = parts[1])
        }
    }
}

const val input11_simple = """
Monkey 0:
  Starting items: 79, 98
  Operation: new = old * 19
  Test: divisible by 23
    If true: throw to monkey 2
    If false: throw to monkey 3

Monkey 1:
  Starting items: 54, 65, 75, 74
  Operation: new = old + 6
  Test: divisible by 19
    If true: throw to monkey 2
    If false: throw to monkey 0

Monkey 2:
  Starting items: 79, 60, 97
  Operation: new = old * old
  Test: divisible by 13
    If true: throw to monkey 1
    If false: throw to monkey 3

Monkey 3:
  Starting items: 74
  Operation: new = old + 3
  Test: divisible by 17
    If true: throw to monkey 0
    If false: throw to monkey 1
"""

const val input11 = """
Monkey 0:
  Starting items: 54, 61, 97, 63, 74
  Operation: new = old * 7
  Test: divisible by 17
    If true: throw to monkey 5
    If false: throw to monkey 3

Monkey 1:
  Starting items: 61, 70, 97, 64, 99, 83, 52, 87
  Operation: new = old + 8
  Test: divisible by 2
    If true: throw to monkey 7
    If false: throw to monkey 6

Monkey 2:
  Starting items: 60, 67, 80, 65
  Operation: new = old * 13
  Test: divisible by 5
    If true: throw to monkey 1
    If false: throw to monkey 6

Monkey 3:
  Starting items: 61, 70, 76, 69, 82, 56
  Operation: new = old + 7
  Test: divisible by 3
    If true: throw to monkey 5
    If false: throw to monkey 2

Monkey 4:
  Starting items: 79, 98
  Operation: new = old + 2
  Test: divisible by 7
    If true: throw to monkey 0
    If false: throw to monkey 3

Monkey 5:
  Starting items: 72, 79, 55
  Operation: new = old + 1
  Test: divisible by 13
    If true: throw to monkey 2
    If false: throw to monkey 1

Monkey 6:
  Starting items: 63
  Operation: new = old + 4
  Test: divisible by 19
    If true: throw to monkey 7
    If false: throw to monkey 4

Monkey 7:
  Starting items: 72, 51, 93, 63, 80, 86, 81
  Operation: new = old * old
  Test: divisible by 11
    If true: throw to monkey 0
    If false: throw to monkey 4
"""
