trait Base:
  def zulu: Int
  def alpha: Int
  val mike: Int = 0
  val bravo: Int = 0

trait Api extends Base:
  def foxtrot: Int
  def charlie: Int
  def echo(x: Int): Int
  def delta: Int
  def echo(s: String): Int
  def bravo2: Int
  def alpha: Int

class Fields:
  val whiskey, alpha, tango, delta, kilo, bravo = 0

@main def Test(): Unit =
  println(members[Api])
  println(members[Fields])
