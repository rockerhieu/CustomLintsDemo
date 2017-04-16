package io.github.rockerhieu.customlintsdemo.analytics;

/**
 * Created by rockerhieu on 4/14/17.
 */

public class AnalyticsHubTracker implements Tracker {
  @Override public void main_helloWorldClicked() {
    new SendEventBuilder().screen("main")
        .action("This is a really really really long action name")
        .send();
  }
}
