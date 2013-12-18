package pl.guice

import net.codingwell.scalaguice._
import net.codingwell.scalaguice.InjectorExtensions._
import com.google.inject.{Guice, AbstractModule}
import javax.inject.{Named, Inject}
import com.google.inject.name.Names

trait Service {
  def foo(): String
}

class ServiceImpl extends Service {
  def foo(): String = {
    return "foo"
  }
}


class Dep @Inject()(s: Service) {

  def bar(): String = {
    return s.foo() + "bar"
  }
}

class MyModule extends AbstractModule with ScalaModule {
  def configure() {
    bind[Service].to[ServiceImpl]
  }
}

case class A @Inject()(@Named("a") a: String, @Named("password") b: String) {
  println(a, b)
}

class ModuleA(password: String) extends AbstractModule with ScalaModule {
  def configure() {
    bind[String].annotatedWith(Names.named("a")).toInstance("a")
    bind[String].annotatedWith(Names.named("password")).toInstance(password)
  }
}

object GuiceTest extends App {
  val injector = Guice.createInjector(new MyModule(), new ModuleA("test123"))
  val dep = injector.instance[Dep]
  println(dep.bar())
  injector.instance[A]
}
