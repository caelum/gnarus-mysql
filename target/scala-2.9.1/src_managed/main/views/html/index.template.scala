
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
    	<span id="error" style="display:none;color:red"></span><br/>
    	<input type="hidden" name="returnUri" value=""""),_display_(Seq(/*6.52*/returnUri)),format.raw/*6.61*/(""""/>    	
    	<textarea name="sql" rows="5" cols="15" style="width:500px">"""),_display_(Seq(/*7.67*/lastAttempt/*7.78*/.map(_.query).getOrElse(""))),format.raw/*7.105*/("""</textarea>
    	<input type="button" id="execute" value="Executar query"/>
    </form>
    <script>
     	$(function()"""),format.raw("""{"""),format.raw/*11.20*/("""
     		$("#execute").click(function()"""),format.raw("""{"""),format.raw/*12.39*/("""
     			var form = $("#executeForm");
     			var uri = form.attr("action");
     			$.post(uri,form.serialize(),
     				   function(data)"""),format.raw("""{"""),format.raw/*16.28*/("""
     						var correct = data.correctness == "100" ? true : false;
     						if(correct)"""),format.raw("""{"""),format.raw/*18.24*/("""
			     				var form = $('<form>');
			     				form.attr("""),format.raw("""{"""),format.raw/*20.24*/(""""action": """"),_display_(Seq(/*20.36*/returnUri)),format.raw/*20.45*/("""", "method" : "get", "style":"display:none" """),format.raw("""}"""),format.raw/*20.90*/(""");
			     				var answer = $("<input type='text' name='answer'/>");		     				
			     				answer.attr("value",data.query);
			     				answer.appendTo(form);
			     				var correctness = $("<input type='text' name='correctness'/>");
			     				correctness.attr("value",data.correctness)
			     				correctness.appendTo(form)
			     				form.appendTo($("body"));
			     				form.submit();
     						"""),format.raw("""}"""),format.raw/*29.13*/("""
     						else"""),format.raw("""{"""),format.raw/*30.17*/("""     							
     							var error = $("#error");
     							error.html(data.description);
     							error.show();
     							
     						"""),format.raw("""}"""),format.raw/*35.13*/("""
     			"""),format.raw("""}"""),format.raw/*36.10*/(""");
     		"""),format.raw("""}"""),format.raw/*37.9*/(""");
     	"""),format.raw("""}"""),format.raw/*38.8*/(""");
    </script>
 """)))})))}
    }
    
    def render(exercise:Exercise,lastAttempt:Option[Attempt],returnUri:String) = apply(exercise,lastAttempt,returnUri)
    
    def f:((Exercise,Option[Attempt],String) => play.api.templates.Html) = (exercise,lastAttempt,returnUri) => apply(exercise,lastAttempt,returnUri)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Wed May 16 15:34:12 BRT 2012
                    SOURCE: /Users/albertoluizsouza/ambiente/desenvolvimento/scala/runner-exercise/app/views/index.scala.html
                    HASH: 14501ce4d7c3d3401b4a44c244fd0c034528a98f
                    MATRIX: 530->1|666->65|698->68|733->95|767->97|817->117|831->123|885->156|1066->307|1096->316|1201->391|1220->402|1269->429|1436->549|1522->588|1711->730|1849->821|1956->881|1999->893|2030->902|2122->947|2576->1354|2640->1371|2830->1514|2887->1524|2944->1535|3000->1545
                    LINES: 19->1|22->1|24->3|24->3|24->3|25->4|25->4|25->4|27->6|27->6|28->7|28->7|28->7|32->11|33->12|37->16|39->18|41->20|41->20|41->20|41->20|50->29|51->30|56->35|57->36|58->37|59->38
                    -- GENERATED --
                */
            