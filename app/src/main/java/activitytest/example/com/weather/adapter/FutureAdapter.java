package activitytest.example.com.weather.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import activitytest.example.com.weather.FutureViewModel;
import activitytest.example.com.weather.R;
import activitytest.example.com.weather.db.model.Future;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class FutureAdapter extends RecyclerView.Adapter<FutureAdapter.FutureViewHolder> {

    private final Context context;
    private final List<Future> futureList;

    public FutureAdapter(Context context, List<Future> futureList) {
        this.context=context;
        this.futureList=futureList;

    }

    static class FutureViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView temperature;
        TextView weather;
        TextView day;
        TextView night;
        TextView direct;
        public FutureViewHolder(@NonNull View itemView) {
            super ( itemView );
            date=itemView.findViewById ( R.id.date );
            temperature=itemView.findViewById ( R.id.temperature );
            weather=itemView.findViewById ( R.id.weather );
            day=itemView.findViewById ( R.id.day );
            night=itemView.findViewById ( R.id.night );
            direct=itemView.findViewById ( R.id.direct );
        }
    }

    @NonNull
    @Override
    public FutureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from ( context ).inflate ( R.layout.card_future,parent,false );
        return new FutureViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull FutureViewHolder holder, int position) {
        Future future=futureList.get ( position );
        holder.temperature.setText ( future.getTemperature () );
        holder.direct.setText ( future.getDirect () );
        holder.night.setText ( future.getWid ().getNight () );
        holder.day.setText ( future.getWid ().getDay () );
        holder.weather.setText ( future.getWeather () );
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat ( "yyyy-MM-dd" );
        String format = simpleDateFormat.format ( future.getDate () );
        holder.date.setText ( format );

    }


    @Override
    public int getItemCount() {
        return futureList.size ();
    }



}
