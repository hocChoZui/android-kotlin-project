fun capitalizeWords(input: String): String {
    return input.split(" ").joinToString(" ") { it.capitalize() }
}

fun isValidPassword(password: String): Boolean {
    return password.length >= 8 && !password.contains(" ")
}

fun isValidUsername(username: String): Boolean {
    val regex = "^[a-zA-Z0-9]*$".toRegex()
    return regex.matches(username) && username.length in 12..32 && !username.contains(" ")
}