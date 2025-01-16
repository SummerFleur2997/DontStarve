import platform.posix.powf
import wrapper.Vector2
import kotlin.math.sqrt

fun distance(a: Vector2, b: Vector2) = sqrt(powf(a.x - b.x, 2F) + powf(a.y - b.y, 2F))