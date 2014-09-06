/*
 * Copyright 2014 Open mHealth
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openmhealth.schema.pojos.build;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.joda.time.DateTime;
import org.junit.Test;
import org.openmhealth.schema.pojos.StepCount;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static org.junit.Assert.*;
import static org.openmhealth.schema.pojos.generic.DurationUnitValue.DurationUnit.*;

/**
 * @author Danilo Bonilla
 */
public class StepBuilderTest {

    @Test
    public void testParse() throws IOException, ProcessingException {
        final String STEP_COUNT_SCHEMA = "schemas/step-count-1.0.json";

        URL url = Thread.currentThread().getContextClassLoader().getResource(STEP_COUNT_SCHEMA);
        assertNotNull(url);

        ObjectMapper mapper = new ObjectMapper();

        InputStream inputStream = url.openStream();
        JsonNode schemaNode = mapper.readTree(inputStream);

        final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        final JsonSchema schema = factory.getJsonSchema(schemaNode);

        ProcessingReport report;

        StepCountBuilder builder = new StepCountBuilder();

        StepCount invalidStepCount = builder.build();
        String invalidJson = mapper.writeValueAsString(invalidStepCount);

        report = schema.validate(mapper.readTree(invalidJson));
        System.out.println(report);
        assertFalse("Expected invalid result but got success", report.isSuccess());

        StepCount stepCount = new StepCountBuilder()
            .withStartAndDuration(new DateTime(), 3d, sec)
            .setSteps(345)
            .build();

        String rawJson = mapper.writeValueAsString(stepCount);

        StepCount deserialized = mapper.readValue(rawJson, StepCount.class);

        assertNotNull(deserialized.getEffectiveTimeFrame().getDateTime());
        assertNotNull(deserialized.getEffectiveTimeFrame().getDuration().getUnit());
        assertNotNull(deserialized.getEffectiveTimeFrame().getDuration().getValue());
        assertNotNull(deserialized.getStepCount());

        report = schema.validate(mapper.readTree(rawJson));
        System.out.println(report);

        assertTrue("Expected valid result!", report.isSuccess());
    }

}
