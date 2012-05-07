package controllers
import play.api.mvc._
import play.api._
import play.api.data._
import play.api.data.Forms._
import models.{ Exercise, ExerciseDAO }
import models.Exercise
import play.api.Play.current
import play.api.libs.json
import play.api.libs.json.Json
import br.com.caelum.gnarus.runner.mysql.MySQL
import br.com.caelum.gnarus.runner.mysql.Databases
import br.com.caelum.gnarus.runner.mysql.SetResults

//@Validations({
// V.Produto.noBeanValidatio,
// V.Produto.nome.required,
// V.Produto.email.isEmail,
// V.Produto.emai.naoRepetido
//})
//
//validator.validate(produto,
//  V.Produto.noBeanValidatio,
// V.Produto.nome.required,
// V.Produto.email.isEmail,
// V.Produto.emai.naoRepetido
//)
//
//
//class produto {
//  public static final DELE_VAL = {
//     V.Produto.noBeanValidatio,
// V.Produto.nome.required,
// V.Produto.email.isEmail,
// V.Produto.emai.naoRepetido
// 
// 	Attr("produto.email").required,
//  };
//}

object Exercises extends Controller with Secured {

  val exerciseForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "content" -> text,
      "queryValidator" -> text,
      "expectedResult" -> optional(number))(
        Exercise.apply)(
          Exercise.unapply))

  def form = withAuth { implicit request =>
      Ok(views.html.novo(exerciseForm))
  }

  def load(id: Long) = withAuth { implicit request =>
    val loadedExercise = ExerciseDAO.load(id)
    val filledForm = exerciseForm.fill(loadedExercise)
    Ok(views.html.novo(filledForm, routes.Exercises.update))
  }

  def update = withAuth { implicit request =>
    exerciseForm.bindFromRequest.fold(errors => BadRequest(views.html.novo(errors)),
      updatedExercise => {
        ExerciseDAO.update(updatedExercise)
        Redirect(routes.Exercises.list())
      })
  }

  def list = withAuth { implicit request =>
    Ok(views.html.list(ExerciseDAO.all))
  }

  def create = withAuth { implicit request =>
    exerciseForm.bindFromRequest.fold(errors => BadRequest(views.html.novo(errors)),
      newExercise => {
        ExerciseDAO.save(newExercise)
        Redirect(routes.Exercises.list())
      })
  }

  def countQuery = withAuth { implicit request =>
    request.body.asFormUrlEncoded match {
      case Some(params) => {
        val query = params.get("query").get(0)
        val count = Databases.mysql { db =>
          db.run(SQLExecutor.baseSql)
          val countResult = db.run(query).asInstanceOf[SetResults]
          countResult.lines.get(0).get(0).value.toInt
        }
        Ok(Json.toJson(
          Map("count" -> count)))
      }
      case _ => BadRequest("nao passou o count")
    }

  }
}