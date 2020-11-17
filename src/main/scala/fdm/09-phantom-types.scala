package fdm

object phantom_types {

  /**
   * EXERCISE 1
   *
   * Add a phantom type parameter to `Socket`, which can keep track of the state of the socket:
   * either `Created` or `Connected`. Use this type parameter in the methods below to improve their
   * type safety.
   */
  type Created
  type Connected
  trait Socket

  def createSocket(): Socket                                 = ???
  def connectSocket(address: String, socket: Socket): Socket = ???
  def readSocket(socket: Socket): Array[Byte]                = ???

  /**
   * EXERCISE 2
   *
   * Introduce a type parameter to this data type to model whether a `Path` is a file, a directory,
   * or either a file or a directory. Use this to improve the type safety of the `readFile` and
   * `listDirectory` methods.
   */
  type File
  type Directory
  sealed trait Path
  object Path {
    case object Root                                   extends Path
    final case class ChildOf(path: Path, name: String) extends Path
  }

  def readFile(path: Path): String          = ???
  def listDirectory(path: Path): List[Path] = ???
}
