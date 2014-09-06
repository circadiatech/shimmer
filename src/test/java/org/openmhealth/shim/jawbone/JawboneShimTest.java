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

package org.openmhealth.shim.jawbone;


import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Danilo Bonilla
 */
public class JawboneShimTest {

    @Test
    @SuppressWarnings("unchecked")
    public void testParse() throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("jawbone-moves.json");
        assert url != null;
        InputStream inputStream = url.openStream();

        //todo: Fix assertions here
    }
}
