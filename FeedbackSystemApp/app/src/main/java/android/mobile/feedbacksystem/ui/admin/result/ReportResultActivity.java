package android.mobile.feedbacksystem.ui.admin.result;

import android.content.Intent;
import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.ui.admin.MainFragmentAdapter;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

public class ReportResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager()));
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.view_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        MainFragmentAdapter adapter = new MainFragmentAdapter(getSupportFragmentManager());
        adapter.addFrag(new ChartResultFragment(), "Illustration");
        adapter.addFrag(new ListResultFragment(), "Result");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share) {
            Uri emailUri = Uri.fromFile(getExportFile());
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback Report");
            sendIntent.putExtra(Intent.EXTRA_STREAM, emailUri);
            sendIntent.setType("text/html");
            startActivity(sendIntent);
        }
        return true;
    }

    File getExportFile() {
        return null;
    }
}
