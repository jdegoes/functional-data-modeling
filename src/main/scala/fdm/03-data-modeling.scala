package fdm

/**
  * The following exercises test your ability to model various entities using case classes.
  */
object product_modeling:
  /**
    * EXERCISE 1 
    * 
    * Using a case class, create a model of a product, which has a name, description, and a price.
    * 
    */
  final case class Product()

  /**
    * EXERCISE 2
    * 
    * Using a case class, create a model of a a user profile, which has a picture URL, and text-
    * based location (indicating the geographic area where the user is from).
    */
  final case class UserProfile()

  /**
    * EXERCISE 3
    * 
    * Using a case class, create a model of an item that can be posted on LinkedIn's feed. This 
    * item contains a subject and some text.
    */
  final case class FeedItem()

/**
  * The following exercises test your ability to model various entities using enums.
  */
object sum_modeling:
  /**
   * EXERCISE 1
   * 
   * Using an enum, create a model of a color, which could be `Red`, `Green`, `Blue`, or `Custom`, 
   * and if `Custom`, then it should store `red`, `green`, and `blue` components individually, as
   * an integer (`Int`) value.
   */
  enum Color:
    case Red 

  /**
   * EXERCISE 2
   * 
   * Using an enum, create a model of an web event, which could be either a page load for a certain
   * URL, a click on a particular button, or a click to a specific URL.
   */
  enum WebEvent:
    case PageLoad(url: String)

  /**
   * EXERCISE 3
   * 
   * Using an enum, create a model of an age bracket, which could be baby, child, young adult, 
   * teenager, adult, mature adult, or senior adult.
   */
  enum AgeBracket:
    case Child

/**
  * The following exercises test your ability to model various entities using both case classes and 
  * enums.
  */
object mixed_modeling:
  /**
   * EXERCISE 1
   * 
   * Using only case classes and enums, create a model of an order for an e-commerce platform, which 
   * would consist of a number of items, each with a certain price, and an overall price, including 
   * shipping and handling charges.
   */
  type Order = TODO 


  /**
   * EXERCISE 2
   * 
   * Using only case classes and enums, create a model of an `Email`, which contains a subject,
   * a body, a recipient, and a from address.
   */
  type Email = TODO

  /**
   * EXERCISE 3
   * 
   * Using only case classes and enums, create a model of a page layout for a content-management 
   * system, which could consist of predefined elements, such as a news feed, a photo gallery, 
   * and other elements, arranged in some well-defined way relative to each other.
   */
  type PageLayout = TODO 
