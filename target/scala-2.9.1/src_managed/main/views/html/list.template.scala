
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
object list extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[List[Exercise],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(exercises:List[Exercise]):play.api.templates.Html = {
        _display_ {

Seq(format.raw/*1.28*/("""
"""),_display_(Seq(/*2.2*/main("Listagem")/*2.18*/ {_display_(Seq(format.raw/*2.20*/("""
	<table>
		<thead>			
			<tr>
				<th>id</th>
				<th>content</th>
			</tr>			
		</thead>		
		<tbody>
			"""),_display_(Seq(/*11.5*/exercises/*11.14*/.map/*11.18*/ { exercise =>_display_(Seq(format.raw/*11.32*/("""
				<tr>
					<th><a href=""""),_display_(Seq(/*13.20*/routes/*13.26*/.Exercises.load(exercise.id))),format.raw/*13.54*/("""">"""),_display_(Seq(/*13.57*/exercise/*13.65*/.id)),format.raw/*13.68*/("""</a></th>
					<th>"""),_display_(Seq(/*14.11*/exercise/*14.19*/.content)),format.raw/*14.27*/("""</th>
				</tr>
			""")))})),format.raw/*16.5*/("""
		</tbody>
	</table>
""")))})),format.raw/*19.2*/("""     
"""))}
    }
    
    def render(exercises:List[Exercise]) = apply(exercises)
    
    def f:((List[Exercise]) => play.api.templates.Html) = (exercises) => apply(exercises)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Fri May 04 16:06:09 BRT 2012
                    SOURCE: /Users/albertoluizsouza/ambiente/desenvolvimento/scala/runner-exercise/app/views/list.scala.html
                    HASH: 0d1d581fc04c2bf92bc67fee344470664181d587
                    MATRIX: 512->1|610->27|641->29|665->45|699->47|836->154|854->163|867->167|914->181|974->210|989->216|1039->244|1073->247|1090->255|1115->258|1166->278|1183->286|1213->294|1264->314|1318->337
                    LINES: 19->1|22->1|23->2|23->2|23->2|32->11|32->11|32->11|32->11|34->13|34->13|34->13|34->13|34->13|34->13|35->14|35->14|35->14|37->16|40->19
                    -- GENERATED --
                */
            