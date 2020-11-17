package fdm

import java.util.Currency

/**
 * Scala supports eithers, which are a generic way to store one of two or more pieces of information at
 * the same time. For example, the either `Either[Boolean, Double]` stores either a `Boolean`, or
 * a `Double`.
 *
 * Eithers come with lots of free functionality, including constructors, deconstructors, equality
 * and hash code, and string representation for debugging.
 *
 * Because eithers can be one of two values, it is necessary to use pattern matching in order to
 * figure out which of the two possible types an either value contains.
 *
 * Like tuples, eithers are immutable: once constructed, they cannot change.
 *
 * Eithers are examples of "anonymous sums": they are types formed using "sum composition" of other
 * types.
 */
object eithers {

  /**
   * EXERCISE 1
   *
   * Using both a type alias, and Scala's `Either` type, construct a type called `IntOrString` that
   * can either hold an `Int` or a `String`.
   */
  type IntOrString = TODO

  /**
   * EXERCISE 2
   *
   * Construct a value of type `IntOrString` that contains the string "Sherlock", using the
   * `Right(_)` constructor for `Either`.
   */
  lazy val intOrString: IntOrString = TODO

  /**
   * EXERCISE 3
   *
   * Using both a type alias, and Scala's `Either` type, construct a type called
   * `PaymentMethod` that can be either `CreditCard` or `WireTransfer`.
   */
  type PaymentMethod = TODO

  final case class CreditCard()
  final case class WireTransfer()

  /**
   * EXERCISE 4
   *
   * Construct a value of type `PaymentMethod` that contains a value of type `CreditCard`, using the
   * `Left(_)` constructor for `Either`.
   */
  lazy val paymentMethod: PaymentMethod = TODO
}

/**
 * Scala supports case classes, which are a generic way to store two or more pieces of
 * information at the same time, where each piece of information can have a user-defined label
 * associated with it. Case classes are nicer than tuples because the elements of the tuple can
 * be accessed by identifiers, instead of by indices. Like tuples, case classes come with lots of
 * free functionality, including constructors, deconstructors, equality and hash code, string
 * representation for debugging, and a copy method.
 *
 * Case classes can be thought of as "records" that have zero or more "fields"; or they can be
 * thought of as defining "tables" that have zero or more "columns".
 *
 * Case classes are immutable: once constructed, they cannot change. However, there are simple ways
 * to created a new value from an existing value, in which some field has been "changed".
 *
 * Case classes are examples of "labeled products": they are types formed using "product
 * composition"  of other types, where each term of the product can be accessed by a user-defined
 * (unique) label.
 */
object enum_basics {

  /**
   * EXERCISE 1
   *
   * Using enums, construct a `ChessPieceType` type that can be one of the different types that
   * any chess piece can be, including a pawn, knight, bishop, rook, king, or queen.
   */
  sealed trait ChessPieceType
  object ChessPieceType {
    case object Pawn extends ChessPieceType
  }

  /**
   * EXERCISE 2
   *
   * Using the enum that you created, construct a value of type `ChessPieceType` that holds a
   * `Pawn`.
   */
  lazy val chessPieceType: ChessPieceType = TODO

  /**
   * EXERCISE 3
   *
   * Using enums, construct a `Currency` type, whose cases can hold currency-specific values, for
   * USD, EURO, and other currencies.
   */
  sealed trait Currency
  object Currency {
    final case class USD(dollars: Int, cents: Int) extends Currency
  }

  /**
   * EXERCISE 3
   *
   * Using the enum that you created, construct a value of type `Currency` that holds 9 dollars and
   * 9 cents of USD.
   */
  lazy val currency: Currency = TODO
}

/**
 * Like case classes, Scala's enums come equipped with useful functionality that all "data
 * classes" should have. In particular, they have equality, hash; code, and string representation,
 * as well as support for pattern matching on individual cases of the enum.
 */
object enum_utilities {
  sealed trait Card
  object Card {
    final case class Clubs(points: Int)    extends Card
    final case class Diamonds(points: Int) extends Card
    final case class Spades(points: Int)   extends Card
    final case class Hearts(points: Int)   extends Card
  }

  Card.Clubs(10) match {
    case Card.Clubs(10) => println("Matched!")
    case _              => println("Did not match!")
  }

  lazy val card: Card = Card.Spades(10)

  /**
   * EXERCISE 1
   *
   * Pattern match on the value `card`. If the value is a `Spades`, then print out the number of
   * points of the card. Otherwise, ignore it. Note: You can match all values using the wildcard
   * (`_`), e.g. `case _ => `.
   */
  card todo

  /**
   * EXERCISE 2
   *
   * As with case classes, pattern matches on the cases of an enum may utilize literals. Pattern
   * match on `card` with a case that looks for `Spades(10)` (a spades card with 10 points), and
   * then have a catch all case. Print out different messages in each case.
   */
  card todo

  /**
   * EXERCISE 3
   *
   * As with case classes, pattern matches on the cases of an enum may utilize conditionals. Pattern
   * match on `card` again, and have two cases: one that looks for `Spades` with points `>= 10`,
   * and a catch-all. Print out distinct messages in each case.
   */
  card todo

  /**
   * EXERCISE 4
   *
   * As with pattern matching on case classes, when pattern matching on enumerations, any piece of
   * a pattern match may be captured and placed into a new variable by using the as-pattern syntax
   * `x @ ...`, where `x` is any legal variable name. The variable introduced by an as-pattern may
   * be used both in any conditional, or inside the case of the pattern match.
   *
   * In this exercise, match for `Spades`, give it a name `spades`, and print it out.
   */
  card todo

  /**
   * EXERCISE 5
   *
   * As with case classes, the `|` pattern operator lets you match against two alternatives,
   * providing neither introduces new variables. In the context of enumerations, this operator
   * provides a nice way to look for one among a small number of different cases.
   *
   * In this exercise, match for either Spades or Diamonds, and print out a message.
   */
  card todo
}

/**
 * Scala's enums can be generic, which means the enum may introduce a type parameter which can
 * be used in the cases of the enum. This allows building general-purpose and versatile data
 * structures that can have different types "plugged" into them in different contexts.
 */
object enum_generics {

  /**
   * EXERCISE 1
   *
   * Take the `AdvertisingEvent` enum, and make it generic, by introducing a new type parameter
   * called `Data`, and using `Data` for the type of the field `Data` that is stored in some of the
   * cases of the enum.
   */
  sealed trait AdvertisingEvent
  object AdvertisingEvent {
    case object None                                                           extends AdvertisingEvent
    final case class Impression(pageUrl: String, data: String)                 extends AdvertisingEvent
    final case class Click(pageUrl: String, elementId: String, data: String)   extends AdvertisingEvent
    final case class Action(pageUrl: String, actionName: String, data: String) extends AdvertisingEvent
  }

  /**
   * EXERCISE 2
   *
   * Create a type alias called `AdvertisingEventString`, which plugs the type `String` into the
   * enum `AdvertisingEvent`, to create an advertising event where the data type is `String`.
   */
  type AdvertisingEventString = TODO

  /**
   * EXERCISE 3
   *
   * Construct an `AdvertisingEvent` that stores data of type `Int`, namely, the integer `42`.
   */
  lazy val advertisingEvent = TODO

  /**
   * EXERCISE 4
   *
   * Take the `ConcatList` enum, and make it generic, by introducing a new type parameter called
   * `Element`, and using `Element` for the type of the field `value` that is stored in some of the
   * cases of the enum.
   */
  sealed trait ConcatList
  object ConcatList {
    case object Empty                                            extends ConcatList
    final case class Concat(left: ConcatList, right: ConcatList) extends ConcatList
    final case class One(value: Int)                             extends ConcatList
  }
}
