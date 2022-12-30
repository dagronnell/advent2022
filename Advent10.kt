import kotlin.math.abs

fun advent10_1() {
    val ops = input10.lines().filter { it.isNotEmpty() }
    var x = 1
    var cycles = 0
    var sum = 0
    ops.forEach { op ->
        when (op.substringBefore(" ")) {
            "noop" -> {
                cycles++
                sum += calcSignalStrength(cycles, x) ?: 0
            }

            "addx" -> {
                cycles++
                sum += calcSignalStrength(cycles, x) ?: 0
                cycles++
                sum += calcSignalStrength(cycles, x) ?: 0
                x += op.substringAfter(" ").toInt()
            }
        }
    }

    println(sum)
}

fun advent10_2() {
    val ops = input10.lines().filter { it.isNotEmpty() }
    var x = 1
    var cycles = 0
    ops.forEach { op ->
        when (op.substringBefore(" ")) {
            "noop" -> {
                cycles++
                draw(cycles, x)
            }

            "addx" -> {
                cycles++
                draw(cycles, x)
                cycles++
                draw(cycles, x)
                x += op.substringAfter(" ").toInt()
            }
        }
    }
}

fun draw(cycles: Int, x: Int) {
    val pos = (cycles - 1) % 40
    if (pos == 0) {
        println()
    }
    val char = if (abs(pos - x) <= 1) {
        '#'
    } else {
        '.'
    }
    print(char)
}

fun calcSignalStrength(cycles: Int, x: Int): Int? {
    if ((cycles - 20) % 40 == 0) {
        return cycles * x
    }
    return null
}

const val input10_simple = """
addx 15
addx -11
addx 6
addx -3
addx 5
addx -1
addx -8
addx 13
addx 4
noop
addx -1
addx 5
addx -1
addx 5
addx -1
addx 5
addx -1
addx 5
addx -1
addx -35
addx 1
addx 24
addx -19
addx 1
addx 16
addx -11
noop
noop
addx 21
addx -15
noop
noop
addx -3
addx 9
addx 1
addx -3
addx 8
addx 1
addx 5
noop
noop
noop
noop
noop
addx -36
noop
addx 1
addx 7
noop
noop
noop
addx 2
addx 6
noop
noop
noop
noop
noop
addx 1
noop
noop
addx 7
addx 1
noop
addx -13
addx 13
addx 7
noop
addx 1
addx -33
noop
noop
noop
addx 2
noop
noop
noop
addx 8
noop
addx -1
addx 2
addx 1
noop
addx 17
addx -9
addx 1
addx 1
addx -3
addx 11
noop
noop
addx 1
noop
addx 1
noop
noop
addx -13
addx -19
addx 1
addx 3
addx 26
addx -30
addx 12
addx -1
addx 3
addx 1
noop
noop
noop
addx -9
addx 18
addx 1
addx 2
noop
noop
addx 9
noop
noop
noop
addx -1
addx 2
addx -37
addx 1
addx 3
noop
addx 15
addx -21
addx 22
addx -6
addx 1
noop
addx 2
addx 1
noop
addx -10
noop
noop
addx 20
addx 1
addx 2
addx 2
addx -6
addx -11
noop
noop
noop
"""

const val input10 = """
noop
noop
noop
addx 5
noop
addx 1
addx 2
addx 5
addx 2
addx 1
noop
addx 5
noop
addx -1
noop
addx 5
noop
noop
addx 5
addx 1
noop
noop
addx 3
addx 2
noop
addx -38
noop
addx 3
addx 2
addx -5
addx 12
addx 2
addx 27
addx -40
addx 19
addx 2
addx 19
addx -18
addx 2
addx 5
addx 2
addx -23
addx 22
addx 4
addx -34
addx -1
addx 5
noop
addx 2
addx 1
addx 20
addx -17
noop
addx 25
addx -17
addx -2
noop
addx 3
addx 19
addx -12
addx 3
addx -2
addx 3
addx 1
noop
addx 5
noop
noop
addx -37
addx 3
addx 4
noop
addx 24
addx -6
addx -15
addx 2
noop
addx 6
addx -2
addx 6
addx -12
addx -2
addx 19
noop
noop
noop
addx 3
noop
addx 7
addx -2
addx -24
addx -11
addx 4
addx 3
addx -2
noop
addx 7
addx -2
addx 2
noop
addx 3
addx 7
noop
addx -2
addx 5
addx 2
addx 5
noop
noop
noop
addx 3
addx -35
addx 35
addx -21
addx -14
noop
addx 5
addx 2
addx 33
addx -7
addx -23
addx 5
addx 2
addx 1
noop
noop
addx 5
addx -1
noop
addx 3
addx -23
addx 30
addx 1
noop
addx 4
addx -17
addx 11
noop
noop
"""