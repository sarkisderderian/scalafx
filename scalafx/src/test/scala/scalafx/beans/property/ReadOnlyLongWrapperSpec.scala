/*
 * Copyright (c) 2012-2013, ScalaFX Project
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

package scalafx.beans.property

import org.junit.runner.RunWith
import org.scalatest.BeforeAndAfterEach
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers.convertToAnyShouldWrapper
import org.scalatest.matchers.ShouldMatchers.equal


/** ReadOnlyLongWrapper Spec tests. */
@RunWith(classOf[JUnitRunner])
class ReadOnlyLongWrapperSpec extends FlatSpec with BeforeAndAfterEach {

  "A ReadOnlyLongWrapper" should "be an instance of LongProperty" in {
    val p = new ReadOnlyLongWrapper()
    assert(p.isInstanceOf[LongProperty])
  }

  it should "have public field `readOnlyProperty` that is an instance of `ReadOnlyLongProperty`" in {
    val p = new ReadOnlyLongWrapper()
    assert(p.readOnlyProperty.isInstanceOf[ReadOnlyLongProperty])
  }

  it should "propagate value changes to `readOnlyProperty`" in {
    val p = new ReadOnlyLongWrapper()
    p.value = 987L
    p.readOnlyProperty() should equal(987L)
    p.value = -749L
    p.readOnlyProperty() should equal(-749L)
  }
}