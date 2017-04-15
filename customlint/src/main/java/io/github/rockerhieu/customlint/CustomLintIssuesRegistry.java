package io.github.rockerhieu.customlint;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;
import java.util.Collections;
import java.util.List;

public class CustomLintIssuesRegistry extends IssueRegistry {
  public CustomLintIssuesRegistry() {
  }

  @Override public List<Issue> getIssues() {
    return Collections.singletonList(TrackerDetector.INVALID_TRACKER_ACTION_ISSUE);
  }
}
