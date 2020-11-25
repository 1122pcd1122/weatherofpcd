package activitytest.example.com.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements WeatherFragment.OnHeadlineSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        if (fragment instanceof WeatherFragment){
            WeatherFragment weatherFragment= (WeatherFragment) fragment;
            weatherFragment.setOnHeadlineSelectedListener ( this );
        }
    }

    @Override
    public void onArticleSelected(int position) {

    }
}