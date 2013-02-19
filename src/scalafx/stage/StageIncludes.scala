/*
 * Copyright (c) 2012, ScalaFX Project
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

package scalafx.stage

import javafx.{ stage => jfxs }

object StageIncludes extends StageIncludes

trait StageIncludes {
  implicit def jfxDirectoryChooser2sfx(dc: jfxs.DirectoryChooser) = new DirectoryChooser(dc)
  implicit def jfxFileChooser2sfx(fc: jfxs.FileChooser) = new FileChooser(fc)
  implicit def jfxFileChooserExtensionFilter2sfx(ef: jfxs.FileChooser.ExtensionFilter) = new FileChooser.ExtensionFilter(ef)
  implicit def jfxPopupWindow2sfx(pw: jfxs.PopupWindow) = new PopupWindow(pw) {}
  implicit def jfxPopup2sfx(p: jfxs.Popup) = new Popup(p)
  implicit def jfxModality2sfx(e: jfxs.Modality) = Modality.jfxEnum2sfx(e)
  implicit def jfxScreen2sfx(s: jfxs.Screen) = new Screen(s)
  implicit def jfxStage2sfx(s: jfxs.Stage) = new Stage(s)
  implicit def jfxStageStyle2sfx(e: jfxs.StageStyle) = StageStyle.jfxEnum2sfx(e)
  implicit def jfxWindow2sfx(w: jfxs.Window) = new Window(w) {}
  implicit def jfxWindowEvent2sfx(we: jfxs.WindowEvent) = new WindowEvent(we) {}
}