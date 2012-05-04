// @SOURCE:/Users/albertoluizsouza/ambiente/desenvolvimento/scala/runner-exercise/conf/routes
// @HASH:97001a82a85749afb2a74a4f6ed139ded0edca0f
// @DATE:Fri May 04 17:05:26 BRT 2012

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString

object Routes extends Router.Routes {


// @LINE:6
val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart("/"),DynamicPart("id", """[0-9]+"""))))
                    

// @LINE:7
val controllers_SQLExecutor_execute1 = Route("POST", PathPattern(List(StaticPart("/execute/sql/"),DynamicPart("id", """[^/]+"""))))
                    

// @LINE:8
val controllers_Exercises_form2 = Route("GET", PathPattern(List(StaticPart("/exercise/form"))))
                    

// @LINE:9
val controllers_Exercises_create3 = Route("POST", PathPattern(List(StaticPart("/exercise"))))
                    

// @LINE:10
val controllers_Exercises_load4 = Route("GET", PathPattern(List(StaticPart("/exercise/"),DynamicPart("id", """[^/]+"""))))
                    

// @LINE:11
val controllers_Exercises_list5 = Route("GET", PathPattern(List(StaticPart("/exercise"))))
                    

// @LINE:12
val controllers_Exercises_update6 = Route("POST", PathPattern(List(StaticPart("/exercise/update"))))
                    

// @LINE:15
val controllers_Assets_at7 = Route("GET", PathPattern(List(StaticPart("/assets/"),DynamicPart("file", """.+"""))))
                    
def documentation = List(("""GET""","""/$id<[0-9]+>""","""controllers.Application.index(id:Long)"""),("""POST""","""/execute/sql/$id<[^/]+>""","""controllers.SQLExecutor.execute(id:Long)"""),("""GET""","""/exercise/form""","""controllers.Exercises.form"""),("""POST""","""/exercise""","""controllers.Exercises.create"""),("""GET""","""/exercise/$id<[^/]+>""","""controllers.Exercises.load(id:Long)"""),("""GET""","""/exercise""","""controllers.Exercises.list"""),("""POST""","""/exercise/update""","""controllers.Exercises.update"""),("""GET""","""/assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""))
             
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:6
case controllers_Application_index0(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(_root_.controllers.Application.index(id), HandlerDef(this, "controllers.Application", "index", Seq(classOf[Long])))
   }
}
                    

// @LINE:7
case controllers_SQLExecutor_execute1(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(_root_.controllers.SQLExecutor.execute(id), HandlerDef(this, "controllers.SQLExecutor", "execute", Seq(classOf[Long])))
   }
}
                    

// @LINE:8
case controllers_Exercises_form2(params) => {
   call { 
        invokeHandler(_root_.controllers.Exercises.form, HandlerDef(this, "controllers.Exercises", "form", Nil))
   }
}
                    

// @LINE:9
case controllers_Exercises_create3(params) => {
   call { 
        invokeHandler(_root_.controllers.Exercises.create, HandlerDef(this, "controllers.Exercises", "create", Nil))
   }
}
                    

// @LINE:10
case controllers_Exercises_load4(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(_root_.controllers.Exercises.load(id), HandlerDef(this, "controllers.Exercises", "load", Seq(classOf[Long])))
   }
}
                    

// @LINE:11
case controllers_Exercises_list5(params) => {
   call { 
        invokeHandler(_root_.controllers.Exercises.list, HandlerDef(this, "controllers.Exercises", "list", Nil))
   }
}
                    

// @LINE:12
case controllers_Exercises_update6(params) => {
   call { 
        invokeHandler(_root_.controllers.Exercises.update, HandlerDef(this, "controllers.Exercises", "update", Nil))
   }
}
                    

// @LINE:15
case controllers_Assets_at7(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(_root_.controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String])))
   }
}
                    
}
    
}
                