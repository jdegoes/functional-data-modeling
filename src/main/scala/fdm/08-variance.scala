package fdm

/**
 * Scala's type system supports _subtyping_, a feature not common in functional programming
 * languages. Subtyping allows the compiler to see and enforce subset / superset relationships
 * between different types. For example, all dogs are animals, and this fact can be encoded using
 * subtyping in any programming language that supports the feature.
 */
object subtyping {
  trait Animal
  trait Dog       extends Animal
  trait Cat       extends Animal
  object Midnight extends Cat
  object Ripley   extends Dog

  type IsSubtypeOf[A, B >: A]
  type IsSupertypeOf[A, B <: A]

  /**
   * EXERCISE 1
   *
   * Determine the relationship between `Animal` and `Dog`, and encode that using either
   * `IsSubtypeOf` or `IsSupertypeOf`.
   */
  type Exercise1 = TODO

  /**
   * EXERCISE 2
   *
   * Determine the relationship between `Dog` and `Animal` (in that order), and encode that using
   * either `IsSubtypeOf` or `IsSupertypeOf`.
   */
  type Exercise2 = TODO

  /**
   * EXERCISE 3
   *
   * Determine the relationship between `Animal` and `Cat`, and encode that using either
   * `IsSubtypeOf` or `IsSupertypeOf`.
   */
  type Exercise3 = TODO

  /**
   * EXERCISE 4
   *
   * Determine the relationship between `Cat` and `Animal` (in that order), and encode that using
   * either `IsSubtypeOf` or `IsSupertypeOf`.
   */
  type Exercise4 = TODO

  /**
   * EXERCISE 5
   *
   * In generic data types and methods, the type operators `<:` ("is a subtype of") and `>:`
   * ("is a supertype of") may be used to enforce subtyping / supertyping constraints on type
   * parameters.
   *
   * In this exercise, use the right type operator such that the examples that should compile do
   * compile, but the examples that should not compile do not compile.
   */
  def isInstanceOf[A, B](a: A): Unit = ???

  lazy val mustCompile1    = isInstanceOf[Ripley.type, Dog](Ripley)
  lazy val mustCompile2    = isInstanceOf[Midnight.type, Cat](Midnight)
  lazy val mustNotCompile1 = isInstanceOf[Ripley.type, Cat](Ripley)
  lazy val mustNotCompile2 = isInstanceOf[Midnight.type, Dog](Midnight)

  /**
   * EXERCISE 6
   *
   * The following data type imposes no restriction on the guests who stay at the hotel. Using
   * the subtyping or supertyping operators, ensure that only animals may stay at the hotel.
   */
  final case class PetHotel[A](rooms: List[A])
}

/**
 * Generic ("parametrically polymorphic") data types with simple, unadorned type parameters are
 * referred to as "invariant". For some invariant generic data type `Box[A]`, there is no
 * relationship between the types `Box[A]` and `Box[B]`, unless `A` and `B` are the same types,
 * in which case, `Box[A]` is the same type as `Box[B]`. If there is a subtyping relationship
 * between `A` and `B`, for example, if `A <: B`, then `Box[A]` is unrelated to (a unique type
 * from) `Box[B]`. In Java, all generic types are invariant, which leads to some significant pains.
 */
object invariance {
  trait Animal
  trait Dog       extends Animal
  trait Cat       extends Animal
  object Midnight extends Cat
  object Ripley   extends Dog

  trait PetHotel[A <: Animal] {
    def book(pet: A): Unit = println(s"Booked a room for ${pet}")
  }

  def bookRipley(dogHotel: PetHotel[Dog]) = dogHotel.book(Ripley)

  def bookMidnight(catHotel: PetHotel[Cat]) = catHotel.book(Midnight)

  /**
   * EXERCISE 1
   *
   * Assuming you have a `PetHotel` that can book any kind of `Animal`. Use this pet hotel to try
   * to call the `bookRipley` and `bookMidnight` functions, to book these two pets at the hotel.
   *
   * Take note of your findings.
   */
  def bookMidnightAndRipley(animalHotel: PetHotel[Animal]): Unit = ???

  trait PetDeliveryService[A <: Animal] {
    def acceptDelivery: A
  }

  def acceptRipley(delivery: PetDeliveryService[Ripley.type]): Ripley.type = delivery.acceptDelivery

  def acceptDog(delivery: PetDeliveryService[Dog]): Dog = delivery.acceptDelivery

  def acceptAnimal(delivery: PetDeliveryService[Animal]): Animal = delivery.acceptDelivery

  /**
   * EXERCISE 2
   *
   * Assuming you have a `PetDeliveryService` that can deliver `Ripley`, try to use the service
   * to call `acceptRipley` (to accept delivery of `Ripley`), `acceptDog` (to accept delivery of
   * a dog, not necessarily Ripley), and `acceptAnimal` (to accept delivery of an animal, not
   * necessarily a dog).
   *
   * Take note of your findings.
   */
  def acceptRipleyDogAnimal(delivery: PetDeliveryService[Ripley.type]): Unit = ???
}

/**
 * So-called declaration-site variance is a feature of Scala that allows you to declare, when you
 * define a data type, whether each type parameter should be invariant, covariant, or contravariant.
 * Invariant is the default, and confers no special treatment. Covariance and contravariance, on
 * the other hand, can help improve the usability of generic data types by allowing Scala to
 * safely infer suptyping and supertype relationships between the generic data types when their
 * type parameters have subtyping and supertype relationships.
 *
 * Covariance can be used on any type parameter that appears in "output" position from all methods
 * of a generic data type. The intuition is that covariance on a type parameter means that the
 * data type has a "surplus" (+) of elements of that type "coming out" of it.
 */
object covariance {
  trait Animal
  trait Dog       extends Animal
  trait Cat       extends Animal
  object Midnight extends Cat
  object Ripley   extends Dog

  /**
   * EXERCISE 1
   *
   * Declare `PetDeliveryService` to be covariant on the type parameter `A`. This is legal since
   * `A` never occurs as input to any method on `PetDeliveryService` (it occurs only as output of
   * the `acceptDelivery` method).
   */
  trait PetDeliveryService[A <: Animal] {
    def acceptDelivery: A
  }

  def acceptRipley(delivery: PetDeliveryService[Ripley.type]): Ripley.type = delivery.acceptDelivery

  def acceptDog(delivery: PetDeliveryService[Dog]): Dog = delivery.acceptDelivery

  def acceptAnimal(delivery: PetDeliveryService[Animal]): Animal = delivery.acceptDelivery

  /**
   * EXERCISE 2
   *
   * Assuming you have a `PetDeliveryService` that can deliver `Ripley`, try to use the service
   * to call `acceptRipley` (to accept delivery of `Ripley`), `acceptDog` (to accept delivery of
   * a dog, not necessarily Ripley), and `acceptAnimal` (to accept delivery of an animal, not
   * necessarily a dog).
   *
   * Take note of your findings.
   */
  def acceptRipleyDogAnimal(delivery: PetDeliveryService[Ripley.type]): Unit = ???

  /**
   * EXERCISE 3
   *
   * The rules for covariance imply that in any method that *wants* to take a covariant type
   * parameter as input, it must instead allow any supertype of the type parameter as input.
   *
   * This makes Scala's type system sound. Without it, it would not be safe to allow subtyping
   * on the generic data type based on subtyping of the type parameter.
   *
   * Following the pattern shown in `concat`, make an `append` method that compiles.
   */
  sealed trait List[+A] {
    def concat[A1 >: A](that: List[A1]): List[A1] = ???

    // def append(a: A): List[A]
  }
}

/**
 * Contravariance can be used on any type parameter that appears in "input" position from all
 * methods of a generic data type. The intuition is that contravariance on a type parameter means
 * that the data type has a "deficit" (-) of elements of that type, requiring you feed them in.
 */
object contravariance {
  trait Animal
  trait Dog       extends Animal
  trait Cat       extends Animal
  object Midnight extends Cat
  object Ripley   extends Dog

  /**
   * EXERCISE 1
   *
   * Declare `PetHotel` to be contravariant on the type parameter `A`. This is legal since `A`
   * never occurs as output from any method on `PetHotel` (it occurs only as input to the `book`
   * method).
   */
  trait PetHotel[A <: Animal] {
    def book(pet: A): Unit = println(s"Booked a room for ${pet}")
  }

  def bookRipley(dogHotel: PetHotel[Dog]) = dogHotel.book(Ripley)

  def bookMidnight(catHotel: PetHotel[Cat]) = catHotel.book(Midnight)

  /**
   * EXERCISE 2
   *
   * Assuming you have a `PetHotel` that can book any kind of `Animal`. Use this pet hotel to try
   * to call the `bookRipley` and `bookMidnight` functions, to book these two pets at the hotel.
   *
   * Take note of your findings.
   */
  def bookMidnightAndRipley(animalHotel: PetHotel[Animal]): Unit = ???

  /**
   * EXERCISE 3
   *
   * The rules for contravariance imply that in any method that *wants* to take another generic
   * data structure with the same type parameter, it must instead allow that type parameter to be
   * any subtype of the type parameter.
   *
   * This makes Scala's type system sound.
   *
   * Following the pattern shown in `merge`, make a `fallback` method that compiles.
   */
  sealed trait Consumer[-A] {
    def merge[A1 <: A](that: Consumer[A1]): Consumer[A1] = ???

    /// def fallback[A](that: Consumer[A]): Consumer[A]
  }
}

/**
 * Type parameters are channels of information: and as channels, they can be used, or ignored.
 * Different types can be used to "ignore" a type parameter depending on whether the parameter is
 * declared as covariant or contravariant (or invariant).
 */
object variance_zeros {

  /**
   * EXERCISE 1
   *
   * The type `Nothing` can be used when a covariant type parameter is not being used. For example,
   * an empty list does not use any element type, because it has no elements.
   */
  type Answer1
  type UnusedListElement = List[Answer1]

  /**
   * EXERCISE 2
   *
   * The type `Any` can be used when a contravariant type parameter is not being used. For example,
   * a constant function does not use its input element.
   */
  type Answer2
  type UnusedFunctionInput[+B] = Answer2 => B
}

object advanced_variance {

  /**
   * EXERCISE 1
   *
   * Given that a workflow is designed to consume some input, and either error or produce an
   * output value, choose the appropriate variance for the workflow type parameters.
   */
  final case class Workflow[Input, Error, Output](run: Input => Either[Error, Output]) {
    def map[NewOutput](f: Output => NewOutput): Workflow[Input, Error, NewOutput] = Workflow(i => run(i).map(f))

    /**
     * EXERCISE 2
     *
     * Add the appropriate variance annotations to the following method, and see if you can
     * implement it by following its types.
     */
    // def flatMap[NewOutput](f: Output => Workflow[Input, Error, NewOutput]): Workflow[Input, Error, NewOutput] = ???

    /**
     * EXERCISE 3
     *
     * Add the appropriate variance annotations to the following method, and see if you can
     * implement it by following its types.
     */
    // def fallback(that: Workflow[Input, Error, Output]): Workflow[Input, Error, Output] = ???
  }
}
