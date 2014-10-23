package pl.guice

import net.codingwell.scalaguice._
import net.codingwell.scalaguice.InjectorExtensions._
import com.google.inject.{Guice, AbstractModule}
import javax.inject.{Provider, Named, Inject}
import com.google.inject.name.Names

trait Foo {
  def foo(): String
}

class FooImpl extends Foo {
  def foo(): String = {
    return "foo"
  }
}


class Bar @Inject()(s: Foo) {

  def bar(): String = {
    return s.foo() + "bar"
  }
}

trait Zoo {
  def zoo(): String
}

class ZooProvider extends Provider[Zoo] {
  def get(): Zoo = new Zoo {
    def zoo(): String = {
      return "zoo"
    }
  }
}

class FooBarZooModule extends AbstractModule with ScalaModule {
  def configure() {
    bind[Foo].to[FooImpl]
    bind[Bar]
    bind[Zoo].toProvider(classOf[ZooProvider])
    //bind[Zoo].toProvider[ZooProvider]
  }
}

class FooBarZoo @Inject()(foo: Foo, bar: Bar, zoo: Zoo) {
  println(foo.foo(), bar.bar(), zoo.zoo())
}

case class TwoStrings @Inject()(@Named("user") a: String, @Named("password") b: String) {
  println(a, b)
}

class TwoStringModule(user: String, password: String) extends AbstractModule with ScalaModule {
  def configure() {
    bind[String].annotatedWith(Names.named("user")).toInstance(user)
    bind[String].annotatedWith(Names.named("password")).toInstance(password)
  }
}

trait A {
  def foo(): String
}

class AProvider extends javax.inject.Provider[A] {
  def get(): A = new A {
    def foo(): String = "foo"
  }
}

class AModule extends AbstractModule with ScalaModule {
  def configure() {
    bind[A].toProvider(classOf[AProvider]) // ok
   //bind[A].toProvider[AProvider] // fails!
  }
}

object GuiceTest extends App {
  println(Guice.createInjector(new AModule()).instance[A].foo())

  val injector = Guice.createInjector(new TwoStringModule(user = "user", password = "test123"), new FooBarZooModule())
  injector.instance[TwoStrings]
  injector.instance[FooBarZoo]
}
