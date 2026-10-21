import scala.quoted.*

inline def members[T]: String = ${ membersImpl[T] }

def membersImpl[T: Type](using Quotes): Expr[String] =
  import quotes.reflect.*
  val universal = Set(defn.AnyClass, defn.MatchableClass, defn.ObjectClass)
  def show(syms: List[Symbol]) =
    syms.filterNot(sym => universal(sym.owner)).map { sym =>
      val params = sym.paramSymss.flatten.map(_.name).mkString("(", ", ", ")")
      s"${sym.owner.name}.${sym.name}${if sym.isDefDef then params else ""}"
    }.mkString(", ")
  val sym = TypeRepr.of[T].typeSymbol
  val lines = List("methods" -> sym.methodMembers, "fields" -> sym.fieldMembers).collect {
    case (kind, syms) if show(syms).nonEmpty => s"${sym.name} $kind: ${show(syms)}"
  }
  Expr(lines.mkString("\n"))
