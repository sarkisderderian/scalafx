/*
 * Copyright (c) 2011, ScalaFX Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the ScalaFX Project nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE SCALAFX PROJECT OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package scalafx

import scalafx.animation.{KeyValue, KeyFrame, Timeline}
import scalafx.application.Application
import scalafx.scene.Scene
import scalafx.scene.shape.Rectangle
import scalafx.stage.Stage
import javafx.scene.paint.Color

object JavaFXAnimation extends Application {
  def rect1 = new Rectangle {
    width = 100
    height = 100
    fill = Color.RED
  }

  def rect2 = new Rectangle {
    width = 50
    height = 50
    fill = Color.LIGHTGREEN
  }

  def timeline = new Timeline {
    cycleCount = INDEFINITE
    autoReverse = true
    keyFrames = List(
      new KeyFrame(50, values = List(
          new KeyValue(rect1, 300),
          new KeyValue(rect2, 500),
          new KeyValue(rect2, 150)
        )
      )
    )
  }
  timeline.play()
  stage = new Stage {
    scene = new Scene {
      content = List(rect1, rect2)
      visible = true
    }
  }
}