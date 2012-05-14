
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._
/**/
object login extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[SystemUser],play.api.templates.Html] {

    /**/
    def apply/*1.4*/(loginForm: Form[SystemUser]):play.api.templates.Html = {
        _display_ {

Seq(format.raw/*1.33*/("""
  """),_display_(Seq(/*2.4*/main("Faça seu login")/*2.26*/ {_display_(Seq(format.raw/*2.28*/("""
  """),_display_(Seq(/*3.4*/helper/*3.10*/.form(action=routes.Authentication.authenticate)/*3.58*/{_display_(Seq(format.raw/*3.59*/("""	  	 
	  """),_display_(Seq(/*4.5*/helper/*4.11*/.inputText(loginForm("login")))),format.raw/*4.41*/("""
	  """),_display_(Seq(/*5.5*/helper/*5.11*/.inputPassword(loginForm("password")))),format.raw/*5.48*/("""	  	 	  
	  <br/>
	  <input type="submit" value="Logar"/>   	  
   """)))})),format.raw/*8.5*/("""

""")))})),format.raw/*10.2*/("""     
"""))}
    }
    
    def render(loginForm:Form[SystemUser]) = apply(loginForm)
    
    def f:((Form[SystemUser]) => play.api.templates.Html) = (loginForm) => apply(loginForm)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Mon May 14 17:56:41 BRT 2012
                    SOURCE: /Users/albertoluizsouza/ambiente/desenvolvimento/scala/runner-exercise/app/views/login.scala.html
                    HASH: 34718f23d23cde4a783f1f0766da7028ff408ce9
                    MATRIX: 515->3|616->32|649->36|679->58|713->60|746->64|760->70|816->118|849->119|888->129|902->135|953->165|987->170|1001->176|1059->213|1157->281|1191->284
                    LINES: 19->1|22->1|23->2|23->2|23->2|24->3|24->3|24->3|24->3|25->4|25->4|25->4|26->5|26->5|26->5|29->8|31->10
                    -- GENERATED --
                */
            