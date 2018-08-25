package com.bignerdranch.android.reader.iu;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.bignerdranch.android.reader.R;
import com.bignerdranch.android.reader.iu.fragment.FileFragment;
import com.bignerdranch.android.reader.iu.fragment.ReadListFragment;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity  implements BaseActivity{

    @BindView(R.id.navigation)  BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        addFragment(new ReadListFragment());
        checkPermission();
        navigation();
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }
        }
    }

    private void navigation() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        addFragment(new ReadListFragment());
                        break;
                    case R.id.navigation_dashboard:
                        addFragment(new FileFragment());
                        break;
                    case R.id.navigation_notifications:

                        break;
                }
                return false;
            }
        });
    }

    public void go(){}

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.grant_fragment,fragment)
                .commit();
    }

    @Override
    public void startFragment(int number) {

    }
}
