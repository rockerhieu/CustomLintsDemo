package io.github.rockerhieu.customlint;

import com.android.tools.lint.checks.infrastructure.LintDetectorTest;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TrackerDetectorTest extends LintDetectorTest {

  TestFile SendEventBuilderStub = java("package io.github.rockerhieu.customlintsdemo.analytics;\n"
      + "public class SendEventBuilder {\n"
      + "  public SendEventBuilder() {}\n"
      + "  public SendEventBuilder screen(String screen) {return this;}\n"
      + "  public SendEventBuilder category(String category) {return this;}\n"
      + "  public SendEventBuilder action(String action) {return this;}\n"
      + "  public SendEventBuilder label(String label) {return this;}\n"
      + "  public SendEventBuilder value(Long value) {return this;}\n"
      + "  public void send() {}"
      + "}");
  TestFile Example = java("package io.github.rockerhieu.tracking.analytics;\n"
      + "\n"
      + "import io.github.rockerhieu.customlintsdemo.analytics.SendEventBuilder;\n"
      + "\n"
      + "public class InvalidTrackerAction {\n"
      + "  public void example() {\n"
      + "    new SendEventBuilder().action(\"This Is A Very Looooooooooooooooooooong Action Name\")\n"
      + "                          .action(null)\n"
      + "                          .send();\n"
      + "  }\n"
      + "}");

  @Override protected Detector getDetector() {
    return new TrackerDetector();
  }

  @Override protected List<Issue> getIssues() {
    return Arrays.asList(TrackerDetector.INVALID_TRACKER_ACTION_ISSUE);
  }

  /**
   * Test that a java file with an enum has a warning.
   */
  @Test public void testWarnings() throws Exception {
    String warnings = lintProject(Example, SendEventBuilderStub);
    assertThat(warnings,
        is("src/io/github/rockerhieu/tracking/analytics/InvalidTrackerAction.java:7: Warning: The action name must be between 2 and 40 characters long and must consist of alphanumberic characters, _, -, and spaces [InvalidTrackerAction]\n"
            + "    new SendEventBuilder().action(\"This Is A Very Looooooooooooooooooooong Action Name\")\n"
            + "                                  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
            + "0 errors, 1 warnings\n"));
  }
}