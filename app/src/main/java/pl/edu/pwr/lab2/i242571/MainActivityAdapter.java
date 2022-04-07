package pl.edu.pwr.lab2.i242571;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {
    List<TaskModel> values;
    View.OnClickListener itemClickListener;

    MainActivityAdapter(List<TaskModel> values, View.OnClickListener itemClickListener){ // OnItemClickListener itemClickListener
        this.values = values;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount(){
        return values.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTv;
        TextView dueDateTv;
        ImageView iconIv;
        ImageView statusIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.title_tv);
            dueDateTv = itemView.findViewById(R.id.dueDate_tv);
            iconIv = itemView.findViewById(R.id.icon_iv);
            statusIv = itemView.findViewById(R.id.status_iv);
        }

        public void bind(TaskModel task){ //OnItemClickListener clickListener
            titleTv.setText(task.getTitle());
            statusIv.setImageResource(TaskModel.Status.statusToImage(task.getStatus()));
            iconIv.setImageResource(TaskModel.TaskType.typeToImage(task.getType()));
            dueDateTv.setText(task.getDueDate());
            //itemView.setOnClickListener(clickListener.onItemClicked(task));
        }

    }

    @Override
    public MainActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(this.itemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainActivityAdapter.ViewHolder holder, int position) {
        holder.bind(values.get(position));
    }
}