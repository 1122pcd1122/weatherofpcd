package activitytest.example.com.weather.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.List;

import activitytest.example.com.weather.BR;
import activitytest.example.com.weather.R;
import activitytest.example.com.weather.db.bean.seven7d.Daily;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class FutureAdapter extends RecyclerView.Adapter<FutureAdapter.FutureViewHolder> {

    private final Context context;
    private final List<Daily> dailyList;


    public FutureAdapter(Context context, List<Daily> futureList) {
        this.context=context;
        this.dailyList=futureList;

    }

    static class FutureViewHolder extends RecyclerView.ViewHolder {
        public FutureViewHolder(@NonNull View itemView) {
            super ( itemView );
        }

    }

    @NonNull
    @Override
    public FutureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ViewDataBinding binding = DataBindingUtil.inflate ( LayoutInflater.from ( context ), R.layout.card_7d_weather, parent, false );
        return new FutureViewHolder ( binding.getRoot () );
    }

    @Override
    public void onBindViewHolder(@NonNull FutureViewHolder holder, int position) {
        ViewDataBinding binding = DataBindingUtil.getBinding ( holder.itemView );
        Daily daily = dailyList.get ( position );
        assert binding != null;
        binding.setVariable ( BR.daily,daily );
        Glide.with ( holder.itemView ).load ( "https://raw.githubusercontent.com/qwd/WeatherIcon/master/weather-icon-S2/128/"+daily.getIconDay ()+".png" ).into ( (ImageView) binding.getRoot ().findViewById ( R.id.icon ) ) ;
        View viewById = binding.getRoot ().findViewById ( R.id.backColor );

        String[] colors = {"#0A4EFB","#35AE93","#6B3DFC","#EFA406","#35AC92","#8B8989","#EED5B7"};
        viewById.setBackgroundColor ( Color.parseColor ( colors[position] ) );
        binding.executePendingBindings ();


    }


    @Override
    public int getItemCount() {
        return dailyList.size ();
    }



}
