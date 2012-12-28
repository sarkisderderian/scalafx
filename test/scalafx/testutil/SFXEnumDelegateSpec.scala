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

package scalafx.testutil

import java.util
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import scala.collection.JavaConversions.iterableAsScalaIterable
import scalafx.util.{SFXEnumDelegateCompanion, SFXEnumDelegate}


/** Abstract class that facilitates testing of wrappers for Java enums.
  *
  * IMPORTANT: the second order parameters jfx2sfx and sfx2jfx have to be left unassigned in the derived class.
  * If compiler finds implicit conversion between JavaFX and ScalaFx (and back) it will assign the corresponding
  * implicit functions to those parameters. Make use that you provide implicit conversion include, for instance,
  * `import scalafx.Includes._`
  *
  * Here a complete test implemented using `SFXEnumDelegateSpec`,
  * {{{
  *  import javafx.{geometry => jfxg}
  *  import org.junit.runner.RunWith
  *  import org.scalatest.junit.JUnitRunner
  *  import scalafx.Includes._
  *  import scalafx.testutil.SFXEnumDelegateSpec
  *
  *  `@RunWith(classOf[JUnitRunner])`
  *  class HPosSpec extends SFXEnumDelegateSpec[jfxg.HPos, HPos](
  *    javaClass = classOf[jfxg.HPos],
  *    scalaClass = classOf[HPos],
  *    javaValueOfFun = (s: String) => jfxg.HPos.valueOf(s),
  *    companion = HPos)
  * }}}
  *
  * @tparam J JavaFX enum type
  * @tparam S ScalaFX wrapper type
  *
  * @param javaClass JavaFX class
  * @param scalaClass SFXDelegate subclass related with JavaFX class
  * @param javaValueOfFun `J.valueOf(String)` function.
  * @param companion companion object of the ScalaFX wrapper class.
  */
class SFXEnumDelegateSpec[J <: Enum[J], S <: SFXEnumDelegate[J]](javaClass: Class[J],
                                                                 scalaClass: Class[S],
                                                                 javaValueOfFun: String => J,
                                                                 companion: SFXEnumDelegateCompanion[J, S]
                                                                  )(implicit jfx2sfx: J => S = null, sfx2jfx: S => J = null)
  extends FlatSpec
  with ShouldMatchers
  with EnumComparator {

  private val javaValues = util.EnumSet.allOf(javaClass).toList
  private val scalaClassName = scalaClass.getName
  private val javaClassName = javaClass.getName

  "%s wrapper for JavaFX enum".format(scalaClassName) should "declare all public methods of " + javaClassName in {
    compareDeclaredMethods(javaClass, scalaClass)
  }

  it should "have implicit conversion JFX to SFX" in {
    // Test if the implicit conversion exists
    jfx2sfx should not be (null)
    // Test if the implicit conversion behaves correctly
    for (jfxObject <- javaValues) {
      val sfxObject: S = jfxObject
      sfxObject.delegate should be(jfxObject)
    }
  }

  it should "have implicit conversion SFX to JFX" in {
    // Test if the implicit conversion exists
    sfx2jfx should not be (null)
    // Test if the implicit conversion behaves correctly
    for (sfxObject <- companion.values) {
      val jfxObject: J = sfxObject
      jfxObject should be(sfxObject.delegate)
    }
  }

  it should "declare all public static methods of " + javaClassName in {
    compareStaticMethods(javaClass, scalaClass)
  }

  it should "have the same number of values as " + javaClassName in {
    companion.values.size should equal(javaValues.size)
  }

  it should "return values in the same order as " + javaClassName in {
    companion.values zip javaValues foreach {p => p._1 should equal(p._2)}
  }

  it should "lookup the same values as " + javaClassName in {
    javaValues foreach {jv => companion.valueOf(jv.toString) should equal(jv)}
    companion.values foreach {sv => javaValueOfFun(sv.toString) should equal(sv.delegate)}
  }

  it should "return the same `toString`" in {
    javaValues foreach {jv => companion.valueOf(jv.toString).toString should equal(jv.toString)}
  }

  it should "not find a non registered name among enum constants" in {
    intercept[IllegalArgumentException] {
      companion.valueOf("!@#$%")
    }
  }

  it should "thhrow `IllegalArgumentException` if the argument is `null`" in {
    intercept[IllegalArgumentException] {
      companion.valueOf(null)
    }
  }
}
