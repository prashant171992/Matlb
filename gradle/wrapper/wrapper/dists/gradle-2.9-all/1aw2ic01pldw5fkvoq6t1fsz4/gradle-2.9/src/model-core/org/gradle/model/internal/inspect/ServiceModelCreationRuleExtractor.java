/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.model.internal.inspect;

import org.gradle.model.internal.core.ModelCreator;
import org.gradle.model.internal.core.ModelCreators;
import org.gradle.model.internal.core.Service;

public class ServiceModelCreationRuleExtractor extends AbstractUnmanagedModelCreationRuleExtractor<Service> {
    @Override
    protected ModelCreator buildCreator(ModelCreators.Builder builder) {
        return builder.service(true).build();
    }

    @Override
    protected String getNameFromAnnotation(MethodRuleDefinition<?, ?> ruleDefinition) {
        return ruleDefinition.getAnnotation(Service.class).value();
    }
}