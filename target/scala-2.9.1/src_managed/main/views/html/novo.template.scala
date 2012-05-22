
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
	  """),_display_(Seq(/*10.5*/helper/*10.11*/.textarea(exerciseForm("setupQuery"),'style -> "width:1000px",'rows -> 20, 'cols -> 20))),format.raw/*10.98*/("""	  
	  <br/>
	  <input type="submit" value="Cadastrar" class="botao" id="cadastro"/>   	  
   """)))})),format.raw/*13.5*/("""
   <form action=""""),_display_(Seq(/*14.19*/routes/*14.25*/.Exercises.countQuery)),format.raw/*14.46*/("""" method="post" id="countForm">
   	Faça o count para saber quantos resultados vão vir. Antes de executar aqui, lembre de escrever no campo de
   	setup query, o sql para gerar as tabelas e inserir no banco.   	
   	<textarea rows="10" cols="10" style="width:500px" name="query" id="query"></textarea>
   	<input type="button" value="contar" id="count"/>
   </form>
   <script>
   	$(function()"""),format.raw("""{"""),format.raw/*21.18*/("""
   		var countForm = $("#countForm")
   		$("#count").click(function()"""),format.raw("""{"""),format.raw/*23.35*/("""
   			var sqls = joinSQLs($("#query").val())   			
   			var setupQuery = joinSQLs($("#setupQuery").val());   			
	   		$.post(countForm.attr("action"),"""),format.raw("""{"""),format.raw/*26.40*/(""""query":sqls,"setupQuery":setupQuery"""),format.raw("""}"""),format.raw/*26.77*/(""",function(data)"""),format.raw("""{"""),format.raw/*26.93*/("""
	   			$("#expectedResult").attr("value",data.count);
	   		"""),format.raw("""}"""),format.raw/*28.8*/(""")
   		"""),format.raw("""}"""),format.raw/*29.7*/(""");
   	"""),format.raw("""}"""),format.raw/*30.6*/(""");
   	function joinSQLs(sql)"""),format.raw("""{"""),format.raw/*31.28*/("""   		
   		return sql.split(";").join(";\n")
   	"""),format.raw("""}"""),format.raw/*33.6*/("""
   </script>
   
""")))})),format.raw/*36.2*/("""     
"""))}
    }
    
    def render(exerciseForm:Form[Exercise],whichAction:play.api.mvc.Call) = apply(exerciseForm,whichAction)
    
    def f:((Form[Exercise],play.api.mvc.Call) => play.api.templates.Html) = (exerciseForm,whichAction) => apply(exerciseForm,whichAction)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Tue May 22 18:59:33 BRT 2012
                    SOURCE: /Users/albertoluizsouza/ambiente/desenvolvimento/scala/runner-exercise/app/views/novo.scala.html
                    HASH: 31f90d1cf008c3cb6040656748cb89b373b74d5d
                    MATRIX: 530->3|686->87|719->91|757->121|791->123|824->127|838->133|871->158|904->159|1027->252|1053->270|1080->276|1123->290|1137->296|1217->355|1251->360|1265->366|1377->456|1411->461|1425->467|1488->509|1535->526|1550->532|1659->619|1785->714|1835->733|1850->739|1893->760|2335->1155|2454->1227|2655->1381|2739->1418|2802->1434|2910->1496|2964->1504|3018->1512|3095->1542|3191->1592|3241->1611
                    LINES: 19->1|22->1|23->2|23->2|23->2|24->3|24->3|24->3|24->3|27->6|27->6|27->6|28->7|28->7|28->7|29->8|29->8|29->8|30->9|30->9|30->9|31->10|31->10|31->10|34->13|35->14|35->14|35->14|42->21|44->23|47->26|47->26|47->26|49->28|50->29|51->30|52->31|54->33|57->36
                    -- GENERATED --
                */
            