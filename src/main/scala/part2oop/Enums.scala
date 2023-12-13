package part2oop

object Enums {
  
  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    // add fields and methods
    def openDocument(): Unit =
      if (this == READ) println("opening document...")
      else println("reading not allowed")
  }

  val somePermissions: Permissions = Permissions.READ

  // constructor args
  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4) // 100
    case WRITE extends PermissionsWithBits(2) // 010
    case EXECUTE extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0) // 000
  }

  object PermissionsWithBits{
    def fromBits(bits: Int): PermissionsWithBits =
      PermissionsWithBits.NONE
    }
  // standard API
  val somePermissionsOrdinal = somePermissions.ordinal
  val allPermissions = Permissions.values // Array[Permissions] = Array(READ, WRITE, EXECUTE, NONE)
  val readPermission = Permissions.valueOf("READ") // Permissions = READ
  
  def main(args: Array[String]): Unit = {
    somePermissions.openDocument()
    print(somePermissionsOrdinal)
  }
}

