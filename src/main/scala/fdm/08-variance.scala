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
