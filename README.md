[author]: https://github.com/cacilhas
[license]: https://github.com/cacilhas/effects/blob/master/COPYING

# Kodumaro Effects

An dependency-free side-effect functional module.

## I/O monad

The I/O monad is a wrapper around side-effect blocks.

```scala
import info.cacilhas.kodumaro.effect._

val hello = IO {println("Hello, World!")}

for (_ ← 1 to 5) hello.perform
```

A monad returning a value:

```scala
val process = IO[Either[Exception, Boolean]] {
  try {
    // Do some side-effect procedure
    Right(true)
  } catch {
    case exc: Exception ⇒ Left(exc)
  }
}

// Run some set up

val processStatus: Either[Exception, Boolean] = process // the I/O monad is performed
```

## Copying

### License

- [The 3-Clause BSD License][license]

### Author

- [Arĥimedeς ℳontegasppα ℭacilhας][author]
