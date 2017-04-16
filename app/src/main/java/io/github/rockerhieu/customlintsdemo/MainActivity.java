package io.github.rockerhieu.customlintsdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import io.github.rockerhieu.customlintsdemo.analytics.AnalyticsHubTracker;
import io.github.rockerhieu.customlintsdemo.analytics.Tracker;

public class MainActivity extends AppCompatActivity {

  Tracker tracker = new AnalyticsHubTracker();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void onHelloWorldClick(View view) {
    tracker.main_helloWorldClicked();
    Toast.makeText(this, "Hello the world!", Toast.LENGTH_SHORT).show();
  }
}
