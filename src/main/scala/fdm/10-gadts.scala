package fdm

/**
 * Scala supports _generalized algebraic types_, which are generic (parametrically polymorphic)
 * algebraic enumerations whose cases whose own type parameter is some function of the
 * enumeration's type parameter.
 *
 * Generalized algebraic types are used for internal domain-specific languages (DSLs). Scala 2.x
 * has special (if buggy) support for them.
 */
object gadts {

  /**
   * EXERCISE 1
   *
   * Add two additional cases to the following enumeration, which model conversions to a specified
   * target type. Use the type parameter of the enumeration to capture the type implied by the
   * case of the conversion. For example, `ToInt` extends `ConversionType[Int]` because it models
   * a conversion to an integer.
   */
  sealed trait ConversionType[A]
  object ConversionType {
    case object ToInt extends ConversionType[Int]
  }

  /**
   * EXERCISE 2
   *
   * Implement the following conversion function, by pattern matching on the enumeration, and in
   * each case, converting the string to the target type modeled by the conversion type.
   *
   * Take note of the relationship between the conversion type parameter, and the return value of
   * the `convert` function.
   */
  def convert[A](string: String, conversionType: ConversionType[A]): A =
    ???

  /**
   * EXERCISE 3
   *
   * Give the `file` and `directory` constructors of `Path` types such that, when `get` is called
   * on a path of that type, `file` will produce a `String`, and `directory` will produce a
   * `List[Path]`. Also give a constructor called `Unknown` that produces
   * `Path[Either[String, List[String]]]`.
   */
  final case class Path[Type] private (value: String)
  object Path {
    def file(path: String) = new Path(path)

    def directory(path: String) = new Path(path)
  }
  def get[Type](path: Path[Type]): Type = ???

}

object gadts_classic {
  trait Spreadsheet {
    def get(col: Char, row: Int): CalculatedValue[_]
  }
  sealed trait CalculatedValue[A]
  object CalculatedValue {
    final case class Integer(value: Int) extends CalculatedValue[Int]

    /**
     * EXERCISE 1
     *
     * Add a `Double` constructor to produce a calculated value that is just a constant double.
     * Then add a new case to the pattern match in `calculate` that correctly handles this
     * constructor.
     */
    final case class Double()

    /**
     * EXERCISE 2
     *
     * Add a `SumDouble` constructor to produce a calculated value that is the sum of two other
     * calculated values. Then add a new case to the pattern match in `calculate` that correctly
     * handles this new constructor.
     */
    final case class SumDouble()

    /**
     * EXERCISE 3
     *
     * Add a `MultDouble` constructor to produce a calculated value that is the sum of two other
     * calculated values. Then add a new case to the pattern match in `calculate` that correctly
     * handles this new constructor.
     */
    final case class MultDouble()
  }

  def calculate[A](spreadsheet: Spreadsheet, calculatedValue: CalculatedValue[A]): A =
    calculatedValue match {
      case CalculatedValue.Integer(value) => value
    }
}
