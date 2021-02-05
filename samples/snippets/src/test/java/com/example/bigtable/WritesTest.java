/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.bigtable;

import static org.junit.Assert.assertThat;

import java.io.IOException;
import org.hamcrest.CoreMatchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

public class WritesTest extends CommonTest {

  @BeforeClass
  public static void beforeClass() throws IOException {
    initializeVariables();
    createTable();
  }

  @AfterClass
  public static void afterClass() throws IOException {
    cleanupTable();
  }

  @Test
  public void testWriteSimple() {
    WriteSimple.writeSimple(projectId, instanceId, TABLE_ID);

    String output = bout.toString();
    assertThat(output, CoreMatchers.containsString("Successfully wrote row"));
  }

  @Test
  public void testWriteBatch() {
    WriteBatch.writeBatch(projectId, instanceId, TABLE_ID);

    String output = bout.toString();
    assertThat(output, CoreMatchers.containsString("Successfully wrote 2 rows"));
  }

  @Test
  public void testWriteConditionally() {
    WriteConditionally.writeConditionally(projectId, instanceId, TABLE_ID);

    String output = bout.toString();
    assertThat(output, CoreMatchers.containsString("Successfully updated row's os_name: true"));
  }

  @Test
  public void testWriteIncrements() {
    WriteIncrementable.writeIncrementable(projectId, instanceId, TABLE_ID);

    String output = bout.toString();
    assertThat(
        output, CoreMatchers.containsString("Successfully updated row phone#4c410523#20190501"));

    WriteIncrement.writeIncrement(projectId, instanceId, TABLE_ID);

    output = bout.toString();
    assertThat(
        output, CoreMatchers.containsString("Successfully updated row phone#4c410523#20190501"));
  }
}
