// @SOURCE:/Users/albertoluizsouza/ambiente/desenvolvimento/scala/runner-exercise/conf/routes
// @HASH:9885006f0fa95083c6aec534371c3a037ef0a582
// @DATE:Sun May 06 20:20:08 BRT 2012

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString


// @LINE:16
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
package controllers {

// @LINE:7
class ReverseSQLExecutor {
    


 
// @LINE:7
def execute(id:Long) = {
   Call("POST", "/execute/sql/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                        

                      
    
}
                            

// @LINE:6
class ReverseApplication {
    


 
// @LINE:6
def index(id:Long) = {
   Call("GET", "/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                        

                      
    
}
                            

// @LINE:16
class ReverseAssets {
    


 
// @LINE:16
def at(file:String) = {
   Call("GET", "/assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                        

                      
    
}
                            

// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
class ReverseExercises {
    


 
// @LINE:13
def countQuery() = {
   Call("POST", "/exercise/countQuery")
}
                                                        
 
// @LINE:12
def update() = {
   Call("POST", "/exercise/update")
}
                                                        
 
// @LINE:8
def form() = {
   Call("GET", "/exercise/form")
}
                                                        
 
// @LINE:9
def create() = {
   Call("POST", "/exercise")
}
                                                        
 
// @LINE:10
def load(id:Long) = {
   Call("GET", "/exercise/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                        
 
// @LINE:11
def list() = {
   Call("GET", "/exercise")
}
                                                        

                      
    
}
                            
}
                    


// @LINE:16
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.javascript {

// @LINE:7
class ReverseSQLExecutor {
    


 
// @LINE:7
def execute = JavascriptReverseRoute(
   "controllers.SQLExecutor.execute",
   """
      function(id) {
      return _wA({method:"POST", url:"/execute/sql/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:6
class ReverseApplication {
    


 
// @LINE:6
def index = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function(id) {
      return _wA({method:"GET", url:"/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:16
class ReverseAssets {
    


 
// @LINE:16
def at = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"/assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                                                        

                      
    
}
                            

// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
class ReverseExercises {
    


 
// @LINE:13
def countQuery = JavascriptReverseRoute(
   "controllers.Exercises.countQuery",
   """
      function() {
      return _wA({method:"POST", url:"/exercise/countQuery"})
      }
   """
)
                                                        
 
// @LINE:12
def update = JavascriptReverseRoute(
   "controllers.Exercises.update",
   """
      function() {
      return _wA({method:"POST", url:"/exercise/update"})
      }
   """
)
                                                        
 
// @LINE:8
def form = JavascriptReverseRoute(
   "controllers.Exercises.form",
   """
      function() {
      return _wA({method:"GET", url:"/exercise/form"})
      }
   """
)
                                                        
 
// @LINE:9
def create = JavascriptReverseRoute(
   "controllers.Exercises.create",
   """
      function() {
      return _wA({method:"POST", url:"/exercise"})
      }
   """
)
                                                        
 
// @LINE:10
def load = JavascriptReverseRoute(
   "controllers.Exercises.load",
   """
      function(id) {
      return _wA({method:"GET", url:"/exercise/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                                                        
 
// @LINE:11
def list = JavascriptReverseRoute(
   "controllers.Exercises.list",
   """
      function() {
      return _wA({method:"GET", url:"/exercise"})
      }
   """
)
                                                        

                      
    
}
                            
}
                    


// @LINE:16
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.ref {

// @LINE:7
class ReverseSQLExecutor {
    


 
// @LINE:7
def execute(id:Long) = new play.api.mvc.HandlerRef(
   controllers.SQLExecutor.execute(id), HandlerDef(this, "controllers.SQLExecutor", "execute", Seq(classOf[Long]))
)
                              

                      
    
}
                            

// @LINE:6
class ReverseApplication {
    


 
// @LINE:6
def index(id:Long) = new play.api.mvc.HandlerRef(
   controllers.Application.index(id), HandlerDef(this, "controllers.Application", "index", Seq(classOf[Long]))
)
                              

                      
    
}
                            

// @LINE:16
class ReverseAssets {
    


 
// @LINE:16
def at(path:String, file:String) = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]))
)
                              

                      
    
}
                            

// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
class ReverseExercises {
    


 
// @LINE:13
def countQuery() = new play.api.mvc.HandlerRef(
   controllers.Exercises.countQuery(), HandlerDef(this, "controllers.Exercises", "countQuery", Seq())
)
                              
 
// @LINE:12
def update() = new play.api.mvc.HandlerRef(
   controllers.Exercises.update(), HandlerDef(this, "controllers.Exercises", "update", Seq())
)
                              
 
// @LINE:8
def form() = new play.api.mvc.HandlerRef(
   controllers.Exercises.form(), HandlerDef(this, "controllers.Exercises", "form", Seq())
)
                              
 
// @LINE:9
def create() = new play.api.mvc.HandlerRef(
   controllers.Exercises.create(), HandlerDef(this, "controllers.Exercises", "create", Seq())
)
                              
 
// @LINE:10
def load(id:Long) = new play.api.mvc.HandlerRef(
   controllers.Exercises.load(id), HandlerDef(this, "controllers.Exercises", "load", Seq(classOf[Long]))
)
                              
 
// @LINE:11
def list() = new play.api.mvc.HandlerRef(
   controllers.Exercises.list(), HandlerDef(this, "controllers.Exercises", "list", Seq())
)
                              

                      
    
}
                            
}
                    
                