
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
object index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[Exercise,Option[Attempt],String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(exercise:Exercise,lastAttempt:Option[Attempt],returnUri:String):play.api.templates.Html = {
        _display_ {

Seq(format.raw/*1.66*/("""

"""),_display_(Seq(/*3.2*/main("Welcome to Play 2.0")/*3.29*/ {_display_(Seq(format.raw/*3.31*/("""
    <form action=""""),_display_(Seq(/*4.20*/routes/*4.26*/.SQLExecutor.execute(exercise.id))),format.raw/*4.59*/("""" method="post" id="executeForm">
    	<input type="hidden" name="returnUri" value=""""),_display_(Seq(/*5.52*/returnUri)),format.raw/*5.61*/(""""/>    	
    	<textarea name="sql" rows="5" cols="15" style="width:500px">"""),_display_(Seq(/*6.67*/lastAttempt/*6.78*/.map(_.query).getOrElse(""))),format.raw/*6.105*/("""</textarea>
    	<input type="button" id="execute" value="Executar query"/>
    </form>
    <script>
     	$(function()"""),format.raw("""{"""),format.raw/*10.20*/("""
     		$("#execute").click(function()"""),format.raw("""{"""),format.raw/*11.39*/("""
     			var form = $("#executeForm");
     			var uri = form.attr("action");
     			$.post(uri,form.serialize(),
     				   function(data)"""),format.raw("""{"""),format.raw/*15.28*/("""
		     				var form = $('<form>');
		     				form.attr("""),format.raw("""{"""),format.raw/*17.23*/(""""action": """"),_display_(Seq(/*17.35*/returnUri)),format.raw/*17.44*/("""", "method" : "get", "style":"display:none" """),format.raw("""}"""),format.raw/*17.89*/(""");
		     				var answer = $("<input type='text' name='answer'/>");
		     				answer.attr("value",data.query);
		     				answer.appendTo(form);
		     				form.appendTo($("body"));
		     				form.submit();  
     			"""),format.raw("""}"""),format.raw/*23.10*/(""");
     		"""),format.raw("""}"""),format.raw/*24.9*/(""");
     	"""),format.raw("""}"""),format.raw/*25.8*/(""");
    </script>
 """)))})))}
    }
    
    def render(exercise:Exercise,lastAttempt:Option[Attempt],returnUri:String) = apply(exercise,lastAttempt,returnUri)
    
    def f:((Exercise,Option[Attempt],String) => play.api.templates.Html) = (exercise,lastAttempt,returnUri) => apply(exercise,lastAttempt,returnUri)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Fri May 04 16:24:00 BRT 2012
                    SOURCE: /Users/albertoluizsouza/ambiente/desenvolvimento/scala/runner-exercise/app/views/index.scala.html
                    HASH: 9d39c184c5b5acd8f6d0970f7cb9533dfdfc7f1c
                    MATRIX: 530->1|666->65|698->68|733->95|767->97|817->117|831->123|885->156|1000->241|1030->250|1135->325|1154->336|1203->363|1370->483|1456->522|1645->664|1750->722|1793->734|1824->743|1916->788|2183->1008|2240->1019|2296->1029
                    LINES: 19->1|22->1|24->3|24->3|24->3|25->4|25->4|25->4|26->5|26->5|27->6|27->6|27->6|31->10|32->11|36->15|38->17|38->17|38->17|38->17|44->23|45->24|46->25
                    -- GENERATED --
                */
            