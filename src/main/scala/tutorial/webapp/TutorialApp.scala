package tutorial.webapp

import scala.scalajs.js.JSApp

import org.scalajs.jquery.jQuery
import tutorial.webapp.people._

object TutorialApp extends JSApp {
  def main(): Unit = {
    jQuery(setupUI _)
  }

  def setupUI(): Unit = {
    jQuery("#click-me-button").click(addClickedMessage _)
  }

  def addClickedMessage(): Unit = {
    val week = jQuery("#week").`val`().toString.toInt
    val name = jQuery("#name").`val`().toString.map(_.toLower)

    jQuery("#main").append("<p>Squat: " + StevesData.squat(name, week) + "</p>")
    jQuery("#main").append("<p>Front Squat: " + StevesData.front(name, week) + "</p>")
    jQuery("#main").append("<p>Deadlift: " + StevesData.deadlift(name, week) + "</p>")
    jQuery("#main").append("<p>Bench: " + StevesData.bench(name, week) + "</p>")
    jQuery("#main").append("<p>Strict: " + StevesData.strict(name, week) + "</p>")
  }
}
