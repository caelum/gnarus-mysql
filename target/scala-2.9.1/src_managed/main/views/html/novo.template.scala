
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
object novo extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Form[Exercise],play.api.mvc.Call,play.api.templates.Html] {

    /**/
    def apply/*1.4*/(exerciseForm: Form[Exercise],whichAction:play.api.mvc.Call=routes.Exercises.create):play.api.templates.Html = {
        _display_ {

Seq(format.raw/*1.88*/("""
  """),_display_(Seq(/*2.4*/main("Cadastro de exercicios")/*2.34*/ {_display_(Seq(format.raw/*2.36*/("""
  """),_display_(Seq(/*3.4*/helper/*3.10*/.form(action=whichAction)/*3.35*/{_display_(Seq(format.raw/*3.36*/("""
	  <h1>Cadastre novo exercicio</h1>	  
	  <br />
	  <input type="hidden" name="id"  value=""""),_display_(Seq(/*6.44*/exerciseForm("id")/*6.62*/.value)),format.raw/*6.68*/(""""/>	  	  
	  """),_display_(Seq(/*7.5*/helper/*7.11*/.inputText(exerciseForm("content"),'style -> "width:500px"))),format.raw/*7.70*/("""
	  """),_display_(Seq(/*8.5*/helper/*8.11*/.textarea(exerciseForm("queryValidator"),'style -> "width:500px",'rows -> 10, 'cols -> 10))),format.raw/*8.101*/("""
	  """),_display_(Seq(/*9.5*/helper/*9.11*/.inputText(exerciseForm("expectedResult")))),format.raw/*9.53*/("""	  	 	  
	  <br/>
	  <input type="submit" value="Cadastrar" class="botao" /> 
  	  
   """)))})),format.raw/*13.5*/("""
""")))})),format.raw/*14.2*/("""     
"""))}
    }
    
    def render(exerciseForm:Form[Exercise],whichAction:play.api.mvc.Call) = apply(exerciseForm,whichAction)
    
    def f:((Form[Exercise],play.api.mvc.Call) => play.api.templates.Html) = (exerciseForm,whichAction) => apply(exerciseForm,whichAction)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Fri May 04 17:12:30 BRT 2012
                    SOURCE: /Users/albertoluizsouza/ambiente/desenvolvimento/scala/runner-exercise/app/views/novo.scala.html
                    HASH: b9511edfae555cc101693cf67bff6ffdf05cbafb
                    MATRIX: 530->3|686->87|719->91|757->121|791->123|824->127|838->133|871->158|904->159|1027->252|1053->270|1080->276|1123->290|1137->296|1217->355|1251->360|1265->366|1377->456|1411->461|1425->467|1488->509|1607->597|1640->599
                    LINES: 19->1|22->1|23->2|23->2|23->2|24->3|24->3|24->3|24->3|27->6|27->6|27->6|28->7|28->7|28->7|29->8|29->8|29->8|30->9|30->9|30->9|34->13|35->14
                    -- GENERATED --
                */
            