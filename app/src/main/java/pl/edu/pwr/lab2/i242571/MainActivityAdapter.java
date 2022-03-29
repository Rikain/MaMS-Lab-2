package pl.edu.pwr.lab2.i242571;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {
    List<String> values;

    MainActivityAdapter(List<String> values){
        this.values = values;
    }

    @Override
    public int getItemCount(){
        return values.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_list_item);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(values.get(position));
    }
}
