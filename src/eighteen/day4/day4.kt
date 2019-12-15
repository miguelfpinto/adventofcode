package eighteen.day4

import java.io.File
import java.time.LocalDateTime

fun main() {
    val inputLines = File("input/2018/day4.txt").readLines()

    var events = mutableListOf<RawEvent>()
    val eventRegex = """\[(\d+)\-(\d+)\-(\d+) (\d+):(\d+)] (.*)""".toRegex()

    inputLines.forEach {
        val (year, month, day, hour, minute, log) = eventRegex.find(it)!!.destructured

        events.add( RawEvent( LocalDateTime.of(year.toInt(), month.toInt(), day.toInt(), hour.toInt(), minute.toInt()), log) )
    }
    val sortedEvents = events.sortedWith(compareBy { it.timestamp })

    var sleepLogByGuardId = mutableMapOf<Int, IntArray>()
    val beginShiftRegex = """(\d+)""".toRegex()

    var currentGuardId = 0
    var minuteFellAsleep = 0
    sortedEvents.forEach {
        if (it.log.endsWith("begins shift")) {
            currentGuardId = beginShiftRegex.find(it.log)!!.value.toInt()
        } else if (it.log.contentEquals("falls asleep")) {
            minuteFellAsleep = it.timestamp.minute
        } else { // woke up
            for (minute in minuteFellAsleep until it.timestamp.minute) {
                if (!sleepLogByGuardId.containsKey(currentGuardId)) {
                    sleepLogByGuardId[currentGuardId] = IntArray(60)
                }
                sleepLogByGuardId[currentGuardId]!![minute]++
            }
        }
    }
    var guardIdMostTimeAsleep = 0
    var maxTotalTimeAsleep = 0
    sleepLogByGuardId.forEach {
        var totalMinutesThisGuardSlept = it.value.sum()

        if (totalMinutesThisGuardSlept > maxTotalTimeAsleep) {
            maxTotalTimeAsleep = totalMinutesThisGuardSlept
            guardIdMostTimeAsleep = it.key
        }
    }


    val maxTimesCaughtAsleep = sleepLogByGuardId[guardIdMostTimeAsleep]!!.max()!!
    val minuteMostCaughtAsleep = sleepLogByGuardId[guardIdMostTimeAsleep]!!.indexOf(maxTimesCaughtAsleep)

    println("Guard most asleep #$guardIdMostTimeAsleep. Caught asleep $maxTimesCaughtAsleep times on minute $minuteMostCaughtAsleep")

    println("Answer part 1: " + guardIdMostTimeAsleep * minuteMostCaughtAsleep)

    var maxTotalTimesCaugthAsleep = 0
    var guardIdMoreTimesCaughtAsleep = 0
    var minuteMoreTimesCaughtAsleep = 0
    sleepLogByGuardId.forEach {
        val maxTimesCaughtAsleep = it.value.max()!!
        if (maxTimesCaughtAsleep > maxTotalTimesCaugthAsleep) {
            maxTotalTimesCaugthAsleep = maxTimesCaughtAsleep
            guardIdMoreTimesCaughtAsleep = it.key
            minuteMoreTimesCaughtAsleep = it.value.indexOf(maxTimesCaughtAsleep)
        }
    }

    println("Guard #$guardIdMoreTimesCaughtAsleep caught $maxTotalTimesCaugthAsleep times asleep on minute $minuteMoreTimesCaughtAsleep")
    println("Answer part 2: " + guardIdMoreTimesCaughtAsleep * minuteMoreTimesCaughtAsleep)
}


data class RawEvent(val timestamp: LocalDateTime, val log: String)