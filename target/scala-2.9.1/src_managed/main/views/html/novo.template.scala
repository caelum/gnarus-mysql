
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
   """)))})),format.raw/*12.5*/("""
   <form action=""""),_display_(Seq(/*13.19*/routes/*13.25*/.Exercises.countQuery)),format.raw/*13.46*/("""" method="post" id="countForm">
   	Count para saber o retorno do delete ou update
   	<textarea rows="10" cols="10" style="width:500px" name="query" id="query"></textarea>
   	<input type="button" value="contar" id="count"/>
   </form>
   <script>
   	$(function()"""),format.raw("""{"""),format.raw/*19.18*/("""
   		var countForm = $("#countForm")
   		$("#count").click(function()"""),format.raw("""{"""),format.raw/*21.35*/("""
   			var sqls = $("#query").val().split(";")
   			sqls = sqls.join(";\n")
   			console.log(sqls);
	   		$.post(countForm.attr("action"),"""),format.raw("""{"""),format.raw/*25.40*/(""""query":sqls"""),format.raw("""}"""),format.raw/*25.53*/(""",function(data)"""),format.raw("""{"""),format.raw/*25.69*/("""
	   			$("#expectedResult").attr("value",data.count);
	   		"""),format.raw("""}"""),format.raw/*27.8*/(""")
   		"""),format.raw("""}"""),format.raw/*28.7*/(""");
   	"""),format.raw("""}"""),format.raw/*29.6*/(""");
   </script>
   
""")))})),format.raw/*32.2*/("""     
"""))}
    }
    
    def render(exerciseForm:Form[Exercise],whichAction:play.api.mvc.Call) = apply(exerciseForm,whichAction)
    
    def f:((Form[Exercise],play.api.mvc.Call) => play.api.templates.Html) = (exerciseForm,whichAction) => apply(exerciseForm,whichAction)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Mon May 07 17:54:03 BRT 2012
                    SOURCE: /Users/albertoluizsouza/ambiente/desenvolvimento/scala/runner-exercise/app/views/novo.scala.html
                    HASH: 662f8138e7dd22df3451e52bc445e813d7eb7042
                    MATRIX: 530->3|686->87|719->91|757->121|791->123|824->127|838->133|871->158|904->159|1027->252|1053->270|1080->276|1123->290|1137->296|1217->355|1251->360|1265->366|1377->456|1411->461|1425->467|1488->509|1606->596|1656->615|1671->621|1714->642|2027->908|2146->980|2334->1121|2394->1134|2457->1150|2565->1212|2619->1220|2673->1228|2725->1249
                    LINES: 19->1|22->1|23->2|23->2|23->2|24->3|24->3|24->3|24->3|27->6|27->6|27->6|28->7|28->7|28->7|29->8|29->8|29->8|30->9|30->9|30->9|33->12|34->13|34->13|34->13|40->19|42->21|46->25|46->25|46->25|48->27|49->28|50->29|53->32
                    -- GENERATED --
                */
            