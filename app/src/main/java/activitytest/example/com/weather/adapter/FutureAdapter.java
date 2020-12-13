package activitytest.example.com.weather.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.List;

import activitytest.example.com.weather.R;
import activitytest.example.com.weather.db.bean.seven7d.Daily;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FutureAdapter extends RecyclerView.Adapter<FutureAdapter.FutureViewHolder> {

    private final Context context;
    private final List<Daily> dailyList;
    private View view;

    public FutureAdapter(Context context, List<Daily> futureList) {
        this.context=context;
        this.dailyList=futureList;

    }

    static class FutureViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView tempMax;
        TextView tempMin;
        TextView textWhite;
        TextView textNight;
        ImageView icon;
        ImageView iconNight;
        TextView uv;
        public FutureViewHolder(@NonNull View itemView) {
            super ( itemView );
            date=itemView.findViewById ( R.id.fxDate );
            tempMax=itemView.findViewById ( R.id.tempMax );
            tempMin=itemView.findViewById ( R.id.tempMin );
            textWhite = itemView.findViewById ( R.id.whiteText );
            textNight = itemView.findViewById ( R.id.nightText );
            uv = itemView.findViewById ( R.id.uv );
            icon = itemView.findViewById ( R.id.icon );
            iconNight = itemView.findViewById ( R.id.iconNight );
        }
    }

    @NonNull
    @Override
    public FutureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=LayoutInflater.from ( context ).inflate ( R.layout.card_7d_weather,parent,false );
        return new FutureViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull FutureViewHolder holder, int position) {

        Daily daily = dailyList.get ( position );
        holder.date.setText ( daily.getFxDate () );
        holder.textNight.setText ( daily.getTextNight () );
        holder.textWhite.setText ( daily.getTextDay () );
        holder.tempMin.setText ( daily.getTempMin () );
        holder.tempMax.setText ( daily.getTempMax () );
        holder.uv.setText ( daily.getUvIndex () );
        Glide.with ( view ).load ( "https://raw.githubusercontent.com/qwd/WeatherIcon/master/weather-icon-S2/128/"+daily.getIconDay ()+".png" ).into ( holder.icon );
        Glide.with ( view ).load ( "https://raw.githubusercontent.com/qwd/WeatherIcon/master/weather-icon-S2/128/"+daily.getIconNight ()+".png"  ).into ( holder.iconNight );
    }


    @Override
    public int getItemCount() {
        return dailyList.size ();
    }



}
