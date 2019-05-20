package kiky.beam.lilly.th.ac.rmutk.fruitqr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TotalFruitAdapter extends RecyclerView.Adapter<TotalFruitAdapter.TotalFruidViewHolder> {

    private Context context;
    private ArrayList<String> nameFruidStringArrayList, amountStringArrayList, unitStringArrayList;
    private OnClickItem onClickItem;
    private LayoutInflater layoutInflater;


    public TotalFruitAdapter(Context context,
                             ArrayList<String> nameFruidStringArrayList,
                             ArrayList<String> amountStringArrayList,
                             ArrayList<String> unitStringArrayList,
                             OnClickItem onClickItem) {
        this.layoutInflater = LayoutInflater.from(context);
        this.nameFruidStringArrayList = nameFruidStringArrayList;
        this.amountStringArrayList = amountStringArrayList;
        this.unitStringArrayList = unitStringArrayList;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public TotalFruidViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = layoutInflater.inflate(R.layout.recycler_list_total_fluit, viewGroup, false);
        TotalFruidViewHolder totalFruidViewHolder = new TotalFruidViewHolder(view);

        return totalFruidViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TotalFruidViewHolder totalFruidViewHolder, int i) {

        String nameFruid = nameFruidStringArrayList.get(i);
        String amount = amountStringArrayList.get(i);
        String unit = unitStringArrayList.get(i);

        totalFruidViewHolder.nameTextView.setText(nameFruid);
        totalFruidViewHolder.amountTextView.setText(amount);
        totalFruidViewHolder.unitTextView.setText(unit);
        totalFruidViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClickitem(v, totalFruidViewHolder.getAdapterPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        return nameFruidStringArrayList.size();
    }

    public class TotalFruidViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView, amountTextView, unitTextView;

        public TotalFruidViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.txtNameFruit);
            amountTextView = itemView.findViewById(R.id.txtAmount);
            unitTextView = itemView.findViewById(R.id.txtUnit);

        }
    }


}
