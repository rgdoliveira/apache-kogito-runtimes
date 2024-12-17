/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.kie.kogito.serverless.workflow.models;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.kie.kogito.MapInput;
import org.kie.kogito.MapInputId;
import org.kie.kogito.MapOutput;
import org.kie.kogito.MappableToModel;
import org.kie.kogito.Model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.JsonNode;

public class JsonNodeModelInput implements Model, MapInput, MapInputId, MapOutput, MappableToModel<JsonNodeModel> {

    private Object workflowdata;
    private Map<String, Object> additionalProperties;

    public Object getWorkflowdata() {
        return workflowdata;
    }

    @JsonAnySetter
    public void setData(String key, JsonNode value) {
        if (additionalProperties == null) {
            additionalProperties = new LinkedHashMap<>();
        }
        additionalProperties.put(key, value);
    }

    @Override
    public JsonNodeModel toModel() {
        if (workflowdata == null) {
            workflowdata = additionalProperties;
            additionalProperties = Collections.emptyMap();
        }
        return new JsonNodeModel(workflowdata, additionalProperties);
    }
}
