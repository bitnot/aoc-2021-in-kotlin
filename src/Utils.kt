import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

fun <T> checkEquals(actual: T, expected: T) =
    check(actual == expected) { "$actual was expected to be $expected" }

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()
fun readTestInput(day: Int) = readInput("Day${day.toString().padStart(2, '0')}_test_input")
fun readInput(day: Int) = readInput("Day${day.toString().padStart(2, '0')}_real_input")

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
