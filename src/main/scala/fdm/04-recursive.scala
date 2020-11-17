package fdm

package fdm

/**
 * Scala data types constructed from enums and case classes may be *recursive*: that is, a top-
 * level definition may contain references to values of the same type.
 */
object recursive {

  /**
   * EXERCISE 1
   *
   * Create a recursive data type that models a user of a social network, who has friends; and
   * whose friends may have other friends, and so forth.
   */
  final case class User()

  /**
   * EXERCISE 2
   *
   * Create a recursive data type that models numeric operations on integers, including addition,
   * multiplication, and subtraction.
   */
  sealed trait NumericExpression
  object NumericExpression {
    final case class Literal(value: Int) extends NumericExpression
  }

  /**
   * EXERCISE 3
   *
   * Create a `EmailTrigger` recursive data type which models the conditions in which to trigger
   * sending an email. Include common triggers like on purchase, on shopping cart abandonment, etc.
   */
  sealed trait EmailTrigger
  object EmailTrigger {
    case object OnPurchase                                         extends EmailTrigger
    final case class Both(left: EmailTrigger, right: EmailTrigger) extends EmailTrigger
  }
}

/**
 * As Scala is an eager programming language, in which expressions are evaluated eagerly, generally
 * from left to right, top to bottom, the tree-like data structures created with case classes and
 * enumerations do not contain cycles. However, with some work, you can model cycles. Cycles are
 * usually for fully general-purpose graphs.
 */
object cyclically_recursive {
  final case class Snake(food: Snake)

  /**
   * EXERCISE 1
   *
   * Create a snake that is eating its own tail. In order to do this, you will have to use a
   * `lazy val`.
   */
  val snake: Snake = ???

  /**
   * EXERCISE 2
   *
   * Create two employees "Tim" and "Tom" who are each other's coworkers. You will have to change
   * the `coworker` field from `Employee` to `() => Employee` (`Function0`), also called a "thunk",
   * and you will have to use a `lazy val` to define the employees.
   */
  final case class Employee(name: String, coworker: Employee)

  /**
   * EXERCISE 3
   *
   * Develop a List-like recursive structure that is sufficiently lazy, it can be appended to
   * itself.
   */
  sealed trait LazyList[+A] extends Iterable[A]
  object LazyList {
    def apply[A](el: A): LazyList[A] = ???

    // The syntax `=>` means a "lazy parameter". Such parameters are evaluated wherever they are
    // referenced "by name".
    def concat[A](left: => LazyList[A], right: => LazyList[A]): LazyList[A] = ???
  }

  lazy val infiniteList: LazyList[Int] = LazyList.concat(LazyList(1), infiniteList)
}
