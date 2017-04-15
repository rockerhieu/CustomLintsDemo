package io.github.rockerhieu.customlintsdemo.analytics;

/**
 * Created by rockerhieu on 4/14/17.
 */

public class DummyTracker implements Tracker {
  @Override public void trackButtonClick() {
    new SendEventBuilder().screen("Main")
        .action("This is a really really really long action name")
        .send();
  }
}
