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
package org.assertj.tests.core.api.optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.error.OptionalShouldBePresent.shouldBePresent;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.tests.core.util.AssertionsUtil.expectAssertionError;

import java.util.Optional;

import org.junit.jupiter.api.Test;

@SuppressWarnings("DataFlowIssue")
class OptionalAssert_isNotEmpty_Test {

  @Test
  void should_pass_when_optional_is_present() {
    assertThat(Optional.of("present")).isNotEmpty();
  }

  @Test
  void should_fail_when_optional_is_empty() {
    // GIVEN
    Optional<String> nullActual = Optional.empty();
    // WHEN
    AssertionError error = expectAssertionError(() -> assertThat(nullActual).isNotEmpty());
    // THEN
    then(error).hasMessage(shouldBePresent(Optional.empty()).create());
  }

  @SuppressWarnings("OptionalAssignedToNull")
  @Test
  void should_fail_when_optional_is_null() {
    // GIVEN
    Optional<String> nullActual = null;
    // WHEN
    AssertionError error = expectAssertionError(() -> assertThat(nullActual).isNotEmpty());
    // THEN
    then(error).hasMessage(actualIsNull());
  }
}
