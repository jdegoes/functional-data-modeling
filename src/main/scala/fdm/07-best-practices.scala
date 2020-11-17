package fdm

/**
 * Some anti-patterns emerge when doing data modeling in Scala. Learning to spot and refactor such
 * anti-patterns is a very valuable skill that will keep data models precise and easy to maintain
 * in the face of changing business requirements.
 *
 */
/**
 * One anti-pattern common in object-oriented data modeling technique is to define different
 * interfaces for different aspects of a data model, relying on inheritance (which is a form of
 * type intersection) to combine multiple aspects into a new type. While this approach is flexible,
 * it's often _too_ flexible, allowing combinations that don't make sense, and making it hard to
 * deal with combinations in a principled way.
 *
 * In this case, refactoring to enumerations (sealed traits _without_ overlaps) and case classes
 * can not only disallow combinations that don't make sense, but give us a principled way to
 * handle all the combinations that do make sense.
 */
object eliminate_intersection {
  trait Event {
    def eventId: String
  }
  trait UserEvent extends Event {
    def userId: String
  }
  trait TimestampedEvent extends Event {
    def timestamp: java.time.Instant
  }
  trait DeviceEvent extends Event {
    def deviceId: String
  }

  object adt {

    /**
     * EXERCISE 1
     *
     * Create a pure case class / enumeration model of `Event`, which permits events which are
     * user events OR device events (but NOT both), and which permits events that have timestamps
     * or lack timestamps; but which always have event ids.
     */
    type Event = TODO
  }
}

/**
 * Another anti-pattern is when all cases of an enumeration share the same field, with the same
 * type and the same meaning. This duplication makes maintenance of the hierarchy more difficult,
 * and also makes it difficult to take advantage of built-in functionality like the `copy` method
 * that comes for free with all case classes.
 *
 * In this case, we can apply an "extract product" refactoring, which turns the enumeration into a
 * case class, and pushes the differences between the cases deeper, into a field of the case class
 * (modeled with a new enumeration).
 */
object extract_product {

  /**
   * EXERCISE 1
   *
   * The following cases of the `AdvertisingEvent` enum all share the `pageUrl` and `data` fields,
   * which have the same type and meaning in each case. Apply the _extract product_ refactoring so
   * that  `AdvertisingEvent` becomes a case class, which stores `pageUrl` and `data`, and
   * introduce a new field called `eventType`, which captures event-specific details for the
   * different event types.
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
   * The following cases of `Card` enum all share the `points` field, and this field has the same
   * type and meaning in each case. Apply the _extract product_ refactoring so that `Card` becomes a
   * case class, and introduce a new field called `cardType`, which captures card-specific details
   * for the different event types.
   */
  sealed trait Card
  object Card {
    final case class Clubs(points: Int)    extends Card
    final case class Diamonds(points: Int) extends Card
    final case class Spades(points: Int)   extends Card
    final case class Hearts(points: Int)   extends Card
  }

  /**
   * EXERCISE 3
   *
   * Apply the _extract product_ refactoring to `Event`.
   */
  sealed trait Event
  object Event {
    final case class UserPurchase(userId: String, amount: Double, timestamp: java.time.Instant)        extends Event
    final case class UserReturn(userId: String, itemId: String, timestamp: java.time.Instant)          extends Event
    final case class SystemRefund(orderId: String, refundAmount: Double, timestamp: java.time.Instant) extends Event
  }
}

/**
 * Another anti-pattern is when a code base has (unsealed) traits that are used for storing data.
 * Such code that encourage runtime type checking using `isInstanceOf` or equivalent, which is
 * neither safe, nor easy to maintain as new subtypes are introduced into the code base.
 *
 * In this case, we can apply a "seal trait" refactoring, which involves sealing the data trait,
 * and relocating all classes that implement the trait into the same file, together with the trait.
 * This will ensure we match against all subtypes in a pattern match, making our code safer and
 * giving us compiler assistance to track down places we need to update when we add a new subtype.
 */
object seal_data_trait {

  /**
   * EXERCISE 1
   *
   * Apply the seal trait refactoring to the following `Document` data model, so the compiler will
   * know about and enforce
   */
  trait Document {
    def documentId: String
  }
  trait OwnedDocument extends Document {
    def ownerId: String
  }
  trait AnonymousDocument extends Document
  trait GroupOwnedDocument extends Document {
    def ownerIds: List[String]
  }
}
