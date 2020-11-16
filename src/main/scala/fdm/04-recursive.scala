package fdm

package fdm

/**
 * Scala data types constructed from enums and case classes may be *recursive*: that is, a top-
 * level definition may contain references to values of the same type.
 */
object recursivej {

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
  object NumericExpression {
    case object OnPurchase                                         extends EmailTrigger
    final case class Both(left: EmailTrigger, right: EmailTrigger) extends EmailTrigger
  }
}
