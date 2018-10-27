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
import android.view.Window;
import android.view.WindowManager;
import com.bignerdranch.android.reader.R;
import com.bignerdranch.android.reader.constants.Constants;
import com.bignerdranch.android.reader.iu.fragment.FileFragment;
import com.bignerdranch.android.reader.iu.fragment.ReadListFragment;
import com.bignerdranch.android.reader.state.StateManager;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity  implements BaseActivity {

    private int currentPageId = -1;
    @BindView(R.id.navigation) BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noActionBar();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (StateManager.StateApp.isMainActivityDestroy()) {
            checkStateFragment();
            StateManager.StateApp.setMainActivityDestroy(false);
        }else {
            addFragment(new ReadListFragment());
            StateManager.StateApp.setStateFragment(Constants.FRAG1);
        }
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
                if (currentPageId == item.getItemId()) {
                    return false;
                }else {
                    currentPageId = item.getItemId();
                    choiceTab(item);
                }
                return true;
            }
        });
    }

    private void choiceTab(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                addFragment(new ReadListFragment());
                StateManager.StateApp.setStateFragment(Constants.FRAG1);
                break;
            case R.id.navigation_dashboard:
                addFragment(new FileFragment());
                StateManager.StateApp.setStateFragment(Constants.FRAG2);
                break;
            case R.id.navigation_notifications:
                break;
        }
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.grant_fragment,fragment)
                .commit();
    }

    @Override
    public void startFragment(String path, String format) {
        Fragment rListFragment = new ReadListFragment();
        Bundle bundleSent = new Bundle();
        bundleSent.putString(Constants.TYPE_FILE_KEY, format);
        bundleSent.putString(Constants.PATH_FILE, path);
        rListFragment.setArguments(bundleSent);
        StateManager.StateApp.setStateFragment(Constants.FRAG1);
        mBottomNavigationView.setSelectedItemId( R.id.navigation_home);
        addFragment(rListFragment);
    }

    private void checkStateFragment() {
        switch (StateManager.StateApp.getStateFragment()) {
            case Constants.FRAG1:
                addFragment(new  ReadListFragment());
                break;
            case Constants.FRAG2:
                addFragment(new FileFragment());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StateManager.StateApp.setMainActivityDestroy(true);
    }

    private void noActionBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
