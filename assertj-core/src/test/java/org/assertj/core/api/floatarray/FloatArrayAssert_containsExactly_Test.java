/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2025 the original author or authors.
 */
package org.assertj.core.api.floatarray;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;
import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.testkit.FloatArrays.arrayOf;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.FloatArrayAssert;
import org.assertj.core.api.FloatArrayAssertBaseTest;
import org.junit.jupiter.api.Test;

class FloatArrayAssert_containsExactly_Test extends FloatArrayAssertBaseTest {

  @Override
  protected FloatArrayAssert invoke_api_method() {
    return assertions.containsExactly(1.0f, 2.0f);
  }

  @Override
  protected void verify_internal_effects() {
    verify(arrays).assertContainsExactly(getInfo(assertions), getActual(assertions), arrayOf(1.0f, 2.0f));
  }

  @Test
  void should_pass_with_precision_specified_as_last_argument() {
    // GIVEN
    float[] actual = arrayOf(1.0f, 2.0f);
    // THEN
    assertThat(actual).containsExactly(arrayOf(1.01f, 2.0f), withPrecision(0.1f));
  }

  @Test
  void should_pass_when_multiple_expected_values_are_the_same_according_to_the_given_precision() {
    // GIVEN
    float[] actual = arrayOf(-1.71f, -1.51f, -1.51f);
    // THEN
    assertThat(actual).containsExactly(arrayOf(-1.7f, -1.6f, -1.4101f), within(0.1f));
  }

  @Test
  void should_pass_with_precision_specified_in_comparator() {
    // GIVEN
    float[] actual = arrayOf(1.0f, 2.0f);
    // THEN
    assertThat(actual).usingComparatorWithPrecision(0.1f)
                      .containsExactly(1.01f, 2.0f);
  }

}
