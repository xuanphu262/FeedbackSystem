package android.mobile.feedbacksystem.ui.admin.result;

import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.ui.admin.MainFragmentAdapter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

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
        adapter.addFrag(new ChartResultFragment(), "Chart");
        adapter.addFrag(new ListResultFragment(), "List");
        viewPager.setAdapter(adapter);
    }
}
