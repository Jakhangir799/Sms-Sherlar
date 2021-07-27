package uz.pdp.student.jahongir.sevgisherlariapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uz.pdp.student.jahongir.sevgisherlariapp.databinding.ItemCategoryBinding;
import uz.pdp.student.jahongir.sevgisherlariapp.models.SmsSher;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {
    private List<SmsSher> list;
    private OnItemClickListener listener;

    public RvAdapter(List<SmsSher> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (list.get(position).getIsliked()){
            holder.binding.likeIcon.setVisibility(View.VISIBLE);
        }else {
            holder.binding.likeIcon.setVisibility(View.INVISIBLE);
        }
        holder.binding.nameSher.setText(list.get(position).getName());
        holder.binding.descSher.setText(list.get(position).getDescription());

        holder.itemView.setOnClickListener(v -> {
         listener.OnItemClick(list.get(position),position);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        private ItemCategoryBinding binding;
        public MyViewHolder(@NonNull ItemCategoryBinding binding1) {
            super(binding1.getRoot());
            binding = binding1;
        }
    }
    public interface OnItemClickListener{
        void OnItemClick(SmsSher smsSher,int position);
    }
}
