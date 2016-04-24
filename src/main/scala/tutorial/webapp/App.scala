package webapp

import scala.scalajs.js.JSApp

import org.scalajs.jquery.jQuery
import webapp.people._

object App extends JSApp {
  def main(): Unit = {
    jQuery(setupUI _)
  }

  def setupUI(): Unit = {
    val names = StevesData.nums.keys.toSeq.sorted

    val nss = names.foldRight("")((a,b) => s"""<option value="${a}">${a}</option>""" + b)

    jQuery("#name").append(nss)

    jQuery("#click-me-button").click(addClickedMessage _)
  }

  def addClickedMessage(): Unit = {
    val week = jQuery("#week").`val`().toString.toInt
    val name = jQuery("#name").`val`().toString.map(_.toLower)

    val squat = ("Back squat", StevesData.squat(name, week))
    val front = ("Front squat", StevesData.front(name, week))
    val deadlift = ("Deadlift", StevesData.deadlift(name, week))
    val bench = ("Bench press", StevesData.bench(name, week))
    val strict = ("Strict press", StevesData.strict(name, week))

    cleanOld
    
    jQuery("#main").append(doNiceHtmlTable(squat))
    jQuery("#main").append(doNiceHtmlTable(front))
    jQuery("#main").append(doNiceHtmlTable(deadlift))
    jQuery("#main").append(doNiceHtmlTable(bench))
    jQuery("#main").append(doNiceHtmlTable(strict))
  }

  def cleanOld = {
    jQuery("#main").empty()
  }

  def doNiceHtmlTable(w: (String, List[(Int, Double, Int)])): String = {
    val trs = w._2.foldRight("")((a, b) => s"""<tr>
                                      <td>${a._1}</td>
                                      <td>${a._2}</td>
                                      <td>${a._3}<td>
                                    </tr>""" + b )

    s"""<div class="col-md-6">
                    <h4>${w._1}</h4>
                                <table class="table table-striped">
                                  <thead>
                                   <tr>
                                      <th>reps</th>
                                      <th>1RM ratio</th>
                                      <th>weight</th>
                                    </tr>
                                  </thead>
                                  <tbody>
                                    $trs
                                  </tbody>
                                </table>
                              </div>"""

  }
}
