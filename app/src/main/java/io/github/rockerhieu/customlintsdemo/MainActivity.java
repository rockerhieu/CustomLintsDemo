package io.github.rockerhieu.customlintsdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import io.github.rockerhieu.customlintsdemo.analytics.DummyTracker;
import io.github.rockerhieu.customlintsdemo.analytics.Tracker;

public class MainActivity extends AppCompatActivity {

  Tracker tracker = new DummyTracker();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void onHelloWorldClick(View view) {
    tracker.trackButtonClick();
    Toast.makeText(this, "Hello the world!", Toast.LENGTH_SHORT).show();
  }
}
