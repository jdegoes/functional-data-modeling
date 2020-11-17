package fdm

/**
 * Scala supports tuples, which are a generic way to store two or more pieces of information at
 * the same time. For example, the tuple `("Alligator", 42)` stores both a string, as the first
 * component in the tuple, and an integer, as the second component in the tuple.
 *
 * Tuples come with lots of free functionality, including constructors, deconstructors, equality
 * and hash code, string representation for debugging, and a copy method.
 *
 * The elements of a tuple are accessed "by index". For example, `("a", 1)._1` retrieves the first
 * element of the tuple, which is the string `"a"`.
 *
 * Tuples are immutable: once constructed, they cannot change. However, there are simple ways to
 * created a new tuple from an existing tuple, in which some element has been "changed".
 *
 * Scala supports tuples of any "arity": that is, tuples can have as many elements as necessary.
 *
 * Tuples are examples of "anonymous products": they are types formed using "product composition"
 * of other types.
 */
object tuples {

  /**
   * EXERCISE 1
   *
   * Using both a type alias, and Scala's tuple types, construct a type called `Person` that can
   * hold both the name of a `Person` (as a `String`), together with the age of the `Person` (as an
   * `Int`).
   */
  type Person = TODO

  /**
   * EXERCISE 2
   *
   * Using the `Person` type alias that you just created, construct a value that has type `Person`,
   * whose name is "Sherlock Holmes", and whose age is 42.
   */
  lazy val sherlockHolmes: Person = TODO

  /**
   * EXERCISE 3
   *
   * Using both a type alias, and Scala's tuple types, construct a type called `CreditCard` that can
   * hold a credit card number (as a `String`), a credit card expiration date (as a
   * `java.time.YearMonth`), a full name (as a `String`), and a security code (as a `Short`).
   */
  type CreditCard = TODO

  /**
   * EXERCISE 4
   *
   * Using the `CreditCard` type alias that you just created, construct a value that has type
   * `CreditCard`, with details invented by you.
   */
  lazy val creditCard: CreditCard = TODO
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
object case_class_basics {

  /**
   * EXERCISE 1
   *
   * Using case classes, construct a type called `Person` that can hold both the name of a `Person`
   * (as a `String` stored in a field called `name`), together with the age of the `Person` (as an
   * `Int` stored in a field called `age`).
   */
  final case class Person()

  /**
   * EXERCISE 2
   *
   * Using the `Person` case class that you just created, construct a value that has type `Person`,
   * whose name is "Sherlock Holmes", and whose age is 42.
   */
  lazy val sherlockHolmes: Person = TODO

  /**
   * EXERCISE 3
   *
   * Using case classes, construct a type called `CreditCard` that can hold a credit card number (as
   * a `String` stored in a field called `number`), a credit card expiration date (as a
   * `java.time.YearMonth` stored in a field called `expDate`), a full name (as a `String` stored in
   * a field called `name`), and a security code (as a `Short` in a field called `securityCode`).
   */
  final case class CreditCard()

  /**
   * EXERCISE 4
   *
   * Using the `CreditCard` case class that you just created, construct a value that has type
   * `CreditCard`, with details invented by you.
   */
  lazy val creditCard: CreditCard = TODO
}

/**
 * Scala's case classes come equipped with useful functionality that all "data classes" should
 * have. In particular, they have equality and hash code built in, and it does exactly what you
 * would expect: operate on the fields of the case class.
 *
 * In addition, case classes have built in copy methods, which can be used to create new values
 * that are modified in some way with respect to an original value.
 */
object case_class_utilities {
  final case class Person(name: String, age: Int)

  /**
   * EXERCISE 1
   *
   * Construct and compare two values of type `Person` to see if they are equal to each other.
   * Compare using the `==` method, which is available on every value of type `Person`.
   */
  lazy val comparison: Boolean = TODO

  /**
   * EXERCISE 2
   *
   * Construct and compute the hash codes of two values of type `Person` to see if they are equal to
   * each other. By law, if two values are equal, their hash codes must also be equal. Compute the
   * hash code of the `Person` values by calling the `hashCode` method, which is available on every
   * value of type `Person`.
   */
  lazy val hashComparison: Boolean = TODO

  /**
   * EXERCISE 3
   *
   * Create a copy of the `sherlockHolmes` value, but with the age changed to be 10 years less than
   * it is currently. Create the copy using the `copy` method, which is available on every value
   * of type `Person`. Note that using named parameters, you need only specify the field you wish
   * to change in the copy operation.
   */
  lazy val sherlockHolmes: Person = Person("Sherlock Holmes", 42)
  lazy val youngerHolmes: Person  = TODO
}

/**
 * Both tuples and case classes can be used in pattern matching. Pattern matching can be used to
 * pull out fields from the products and store them in named variables, as well as to selectively
 * match for specific patterns of information stored in a value.
 */
object product_patterns {
  final case class Person(name: String, age: Int)

  lazy val sherlockHolmes: Person = Person("Sherlock Holmes", 42)

  /**
   * EXERCISE 1
   *
   * In this pattern match on the value `sherlockHolmes`, the name is being extracted and stored
   * into a variable called `name`, while the age is being extracted and stored into a variable
   * called `age`. On the right hand side of the arrow (`=>`), use a Scala `println` statement to
   * print out the name and the age of the specified person.
   */
  def example1 =
    sherlockHolmes match {
      case Person(name, age) => TODO
    }

  /**
   * EXERCISE 2
   *
   * Pattern match on this tuple and extract out the name of the product into a variable called
   * `name`, and the price of the product into a variable called `price`. Then use a Scala
   * `println` statement to print out the name and price of the product.
   */
  def example2 =
    ("Suitcase", 19.95)

  final case class Employee(name: String, address: Address)
  final case class Address(street: String, number: Int)

  val dilbert = Employee("Dilbert", Address("Baker", 221))

  /**
   * EXERCISE 3
   *
   * Pattern match on `dilbert` and extract out and print the address number. This will involve
   * using a nested pattern match.
   */
  dilbert todo

  /**
   * EXERCISE 4
   *
   * Pattern matches can contain literal values, in which case those slots in the pattern are
   * matched using the `equals` method of the value. Pattern match on `dilbert` again, but this
   * time, with two cases: the case where the name is equal to `"Dilbert"`, and all other cases.
   * Print out the name in each case. Note the ordering of case evaluation, which proceeds from top
   * to bottom.
   */
  dilbert todo

  /**
   * EXERCISE 5
   *
   * Every case of a pattern match may contain a conditional expression, in which case the pattern
   * is matched only if both the base pattern matches, and the boolean expression evaluates to true.
   *
   * Pattern match on `dilbert` again and have two cases: the first one matches any address name
   * that starts with the string `"B"`, and a catch all case that matches all patterns. In both
   * cases, print out the name of the street.
   */
  dilbert todo

  /**
   * EXERCISE 6
   *
   * Any piece of a pattern match may be captured and placed into a new variable by using the as-
   * pattern syntax `x @ ...`, where `x` is any legal variable name. The variable introduced by an
   * as-pattern may be used both in any conditional, or inside the case of the pattern match.
   *
   * In this exercise, pattern match on dilbert, and give a name `a` to the inner `Address`, and
   * then print out that `a` in the case expression.
   */
  dilbert todo

  /**
   * EXERCISE 7
   *
   * Using the `|` symbol, you can match against two alternatives, providing neither introduces
   * new variables. In the context of case classes, this symbol provides a nice way to look for
   * one among a small number of constant values.
   *
   * In this exercise, match for the name "Dilbert" or the name "dilbert", and print out the
   * address of the employee.
   */
  dilbert todo
}

/**
 * Scala's case classes can be generic, which means the types defined by case classes may have
 * generic type parameters. This allows building general-purpose and versatile data structures
 * that can have different types "plugged" into them in different contexts.
 */
object case_class_generics {

  /**
   * EXERCISE 1
   *
   * Convert this non-generic case class into a generic case class, by introducing a new type
   * parameter, called `Payload`, and use this type parameter to define the type of the field called
   * `payload` already defined inside the case class.
   */
  final case class Event(id: String, name: String, time: java.time.Instant, payload: String)

  /**
   * EXERCISE 2
   *
   * Construct a type alias called `EventString`, which is an `Event` but with a `String` payload.
   */
  type EventString = TODO

  /**
   * EXERCISE 2
   *
   * Construct an event that has a payload type of `Int`.
   */
  lazy val eventInt = TODO

  /**
   * EXERCISE 3
   *
   * Convert this non-generic class into a generic class, by introducing a new type parameter,
   * called `Body`, which represents the body type of the request, and use this type parameter to
   * define the type of the field called `body` already defined inside the case class.
   */
  final case class Request(body: Event, sender: String)
}
