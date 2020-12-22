package activitytest.example.com.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import activitytest.example.com.weather.BR;
import activitytest.example.com.weather.R;
import activitytest.example.com.weather.db.bean.comfort.ComfortNow;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class AdviceAdapter extends RecyclerView.Adapter<AdviceAdapter.AdviceViewHolder> {

    private final Context context;
    private final List<ComfortNow> comfortNews;

    public AdviceAdapter(Context context, List<ComfortNow> comfortNews) {
        this.context = context;
        this.comfortNews = comfortNews;
    }

    @NonNull
    @Override
    public AdviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding bind = DataBindingUtil.bind ( LayoutInflater.from ( context ).inflate ( R.layout.advice_layout, parent, false ) );


        assert bind != null;
        return new AdviceViewHolder ( bind.getRoot () );
    }

    @Override
    public void onBindViewHolder(@NonNull AdviceViewHolder holder, int position) {
        ViewDataBinding binding = DataBindingUtil.getBinding ( holder.itemView );
        ComfortNow comfortNow = comfortNews.get ( position );
        binding.setVariable ( BR.advice,comfortNow );
        binding.executePendingBindings ();
    }

    @Override
    public int getItemCount() {
        return comfortNews.size ();
    }

    static class AdviceViewHolder extends RecyclerView.ViewHolder {

        public AdviceViewHolder(@NonNull View itemView) {
            super ( itemView );
        }
    }
}
