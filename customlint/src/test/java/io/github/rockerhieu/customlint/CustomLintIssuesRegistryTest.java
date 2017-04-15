package io.github.rockerhieu.customlint;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by rockerhieu on 4/15/17.
 */

public class CustomLintIssuesRegistryTest {
  private CustomLintIssuesRegistry customLintIssuesRegistry;

  @Before public void setup() {
    customLintIssuesRegistry = new CustomLintIssuesRegistry();
  }

  @Test public void testIssues() throws Exception {
    assertThat(customLintIssuesRegistry.getIssues().size(), is(1));
    assertThat(
        customLintIssuesRegistry.getIssues().contains(TrackerDetector.INVALID_TRACKER_ACTION_ISSUE),
        is(true));
  }
}
