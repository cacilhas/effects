[author]: https://github.com/cacilhas
[jcenter]: https://bintray.com/beta/#/kodumaro/maven/kodumaro-effects
[license]: https://github.com/cacilhas/effects/blob/master/COPYING
[packages]: https://github.com/cacilhas/maven/packages

# Kodumaro Effects

An dependency-free side-effect functional module.

## Installation

Kodumaro Effects is hosted by [JCenter][jcenter].

Add to your `build.sbt`:

```sbt
resolvers += "Kodumaro Maven Repository" at "https://raw.github.com/cacilhas/maven/master"
libraryDependencies += "info.cacilhas.kodumaro" %% "kodumaro-effects" % "1.0.0"
```

Or using Git itself:

```sbt
lazy val kodumaroEffects = RootProject(uri("https://github.com/cacilhas/effects.git#master"))
dependsOn(kodumaroEffects)
```

### TODO

- Publish to [Github Packages][packages] (waiting for Github Packages to
  support unauthenticated access).

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
