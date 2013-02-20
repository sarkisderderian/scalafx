package scalafx.scene.media

import scalafx.Includes._
import scalafx.application.JFXApp.PrimaryStage
import scalafx.application.{JFXApp, Platform}
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.scene.layout.StackPane

/** Plays a video an displays text related at set video duration. */
object MediaEventTester extends JFXApp {

  val markerText = new Label {
    alignmentInParent = Pos.TOP_CENTER
    style = "-fx-padding: 20; -fx-font-size: 24pt; -fx-text-fill: white;"
  }

  val url = "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv"
  val media = new Media(url)
  media.markers ++= Map(
    "Clouds" -> (100 ms),
    "Planes" -> (3000 ms),
    "Parashoote" -> (8000 ms),
    "Car" -> (13000 ms),
    "..." -> (15000 ms)
  )

  val mediaPlayer = new MediaPlayer(media)
  mediaPlayer.onMarker = (event: MediaMarkerEvent) => Platform.runLater {markerText.text = event.marker.getKey}

  val mediaView = new MediaView(mediaPlayer) {
    onError = (event: MediaErrorEvent) => println("Media view error: " + event)
  }
  val root = new StackPane {
    content +=(mediaView, markerText)
    style = "-fx-background-color: black;"
  }

  stage = new PrimaryStage {
    title = "MediaEventTester"
    scene = new Scene(root, 540, 320)
  }

  mediaPlayer.play()
}