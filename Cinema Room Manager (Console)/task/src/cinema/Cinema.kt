package cinema

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seats = readln().toInt()

    val hall = MutableList(rows) {
        MutableList(seats) { false } }
    while (true) {
        menu()
        when (readln()) {
            "1" -> cinema(hall)
            "2" -> buy(hall)
            "3" -> stats(hall)
            else -> return
        }
    }
}

fun stats(hall: MutableList<MutableList<Boolean>>) {
    var ticketsPurchased = 0
    var totalIncome = 0
    var currentIncome = 0
    val allTickets = hall.size * hall[0].size
    for (r in hall.indices) {
        for (c in hall[r].indices) {
            val p = price(hall, r + 1)
            totalIncome += p
            if (hall[r][c]) {
                ticketsPurchased++
                currentIncome += p
            }
        }
    }
    println("Number of purchased tickets: $ticketsPurchased")
    val percentage = "%.2f".format(100.0 * ticketsPurchased.toDouble()/allTickets)
    println("Percentage: $percentage%")
    println("Current income: \$$currentIncome")
    println("Total income: \$$totalIncome")
    println()
}

fun cinema(hall: MutableList<MutableList<Boolean>>) {
    println("Cinema:")
    print("  ")
    for (i in hall[0].indices) print("${i + 1} ")
    println()
    for (i in hall.indices) {
        print("${i + 1} ")
        for (seat in hall[i]) {
            print(if (seat) "B " else "S ")
        }
        println()
    }
    println()
}

fun price(hall: MutableList<MutableList<Boolean>>, row: Int): Int {
    var price = 10
    val rows = hall.size
    val seats = hall[0].size
    if (rows * seats > 60) {
        if (row > rows / 2) price = 8
    }
    return price
}

fun buy(hall: MutableList<MutableList<Boolean>>) {
    var r: Int
    var s: Int
    do {
        println("Enter a row number:")
        r = readln().toInt()
        println("Enter a seat number in that row:")
        s = readln().toInt()

        var ok = true
        if (r < 1 || s < 1 || r > hall.size || s > hall[0].size) {
            println("Wrong input!")
            ok = false
        }
        if (ok && hall[r - 1][s - 1]) {
            println("That ticket has already been purchased!")
            ok = false
        }
    } while ( !ok )

    println("Ticket price: \$${price(hall, r)}")
    hall[r - 1][s - 1] = true

    cinema(hall)
}

fun menu() {
    println("1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
    println("0. Exit")
}
