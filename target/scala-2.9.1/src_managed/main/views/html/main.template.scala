
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
object main extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Html,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(title: String)(content: Html):play.api.templates.Html = {
        _display_ {

Seq(format.raw/*1.32*/("""

<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(Seq(/*7.17*/title)),format.raw/*7.22*/("""</title>
        <script src=""""),_display_(Seq(/*8.23*/routes/*8.29*/.Assets.at("javascripts/jquery-1.7.1.min.js"))),format.raw/*8.74*/("""" type="text/javascript"></script>
    </head>
    <body>
        """),_display_(Seq(/*11.10*/content)),format.raw/*11.17*/("""
    </body>
</html>
"""))}
    }
    
    def render(title:String,content:Html) = apply(title)(content)
    
    def f:((String) => (Html) => play.api.templates.Html) = (title) => (content) => apply(title)(content)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Fri May 04 17:05:30 BRT 2012
                    SOURCE: /Users/albertoluizsouza/ambiente/desenvolvimento/scala/runner-exercise/app/views/main.scala.html
                    HASH: 7116b603c93ffb8b4673bfe76513035e32327aa8
                    MATRIX: 509->1|611->31|694->84|720->89|781->120|795->126|861->171|959->238|988->245
                    LINES: 19->1|22->1|28->7|28->7|29->8|29->8|29->8|32->11|32->11
                    -- GENERATED --
                */
            