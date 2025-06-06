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
package org.assertj.core.api;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.function.Consumer;
import java.util.stream.Stream;

import org.assertj.core.util.introspection.FieldSupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("EntryPoint assertions setAllowComparingPrivateFields method")
class EntryPointAssertions_setAllowComparingPrivateFields_Test extends EntryPointAssertionsBaseTest {

  private static final boolean DEFAULT_ALLOW_COMPARING_PRIVATE_FIELDS = FieldSupport.comparison().isAllowedToUsePrivateFields();

  @AfterEach
  void afterEachTest() {
    // reset to the default value to avoid side effects on the other tests
    FieldSupport.comparison().setAllowUsingPrivateFields(DEFAULT_ALLOW_COMPARING_PRIVATE_FIELDS);
  }

  @ParameterizedTest
  @MethodSource("setAllowComparingPrivateFieldsFunctions")
  void should_set_allowComparingPrivateFields_value(Consumer<Boolean> setAllowComparingPrivateFieldsFunction) {
    // GIVEN
    boolean allowComparingPrivateFields = !DEFAULT_ALLOW_COMPARING_PRIVATE_FIELDS;
    // WHEN
    setAllowComparingPrivateFieldsFunction.accept(allowComparingPrivateFields);
    // THEN
    then(FieldSupport.comparison().isAllowedToUsePrivateFields()).isEqualTo(allowComparingPrivateFields);
  }

  private static Stream<Consumer<Boolean>> setAllowComparingPrivateFieldsFunctions() {
    return Stream.of(Assertions::setAllowComparingPrivateFields,
                     BDDAssertions::setAllowComparingPrivateFields,
                     withAssertions::setAllowComparingPrivateFields);
  }

}
