package work.icu007.tabexperiment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 通过view中的R.id.toolbar实例化一个 toolbar对象；
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        // 将 toolbar设置为 ActionBar；
        setSupportActionBar(toolbar);

        // 通过view中的R.id.tab_layout 创建TabLayout的实例。
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        // 为每个tabLayout设置文本
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label1));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label2));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label3));

        // 设置选项卡为完全填充
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // 使用 PagerAdapter 管理fragments中的页面视图。
        // 每个页面都由它自己的fragment表示。
        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        // 设置点击侦听器
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            // 选项卡被选择时的处理事件。
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}