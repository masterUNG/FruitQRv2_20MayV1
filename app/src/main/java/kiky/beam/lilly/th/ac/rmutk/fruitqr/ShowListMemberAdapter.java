package kiky.beam.lilly.th.ac.rmutk.fruitqr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowListMemberAdapter extends RecyclerView.Adapter<ShowListMemberAdapter.ShowListMemberViewHolder>{

    private Context context;
    private ArrayList<String> nameStringArratList, firstnameStringArratList, lastnameStringArratList, phoneStringArratList, typeStringArrayList, addcontactStringArratList;
    private OnClickItem onClickItem;
    private LayoutInflater layoutInflater;

//    private ArrayList<String> stringArrayList = new ArrayList<>();
//    private String[] typeUsers = {"", "Admin", "Farmer", "Product", "Farmer and Product"};
//    private String typeUserSting;



    public ShowListMemberAdapter(Context context,
                                 ArrayList<String> nameStringArratList,
                                 ArrayList<String> firstnameStringArratList,
                                 ArrayList<String> lastnameStringArratList,
                                 ArrayList<String> phoneStringArratList,
//                                 ArrayList<String> typeStringArrayList,
//                                 ArrayList<String> addcontactStringArratList,
                                 OnClickItem onClickItem) {
        this.layoutInflater = LayoutInflater.from(context);
        this.nameStringArratList = nameStringArratList;
        this.firstnameStringArratList = firstnameStringArratList;
        this.lastnameStringArratList = lastnameStringArratList;
        this.phoneStringArratList = phoneStringArratList;
//        this.typeStringArrayList = typeStringArrayList;
//        this.addcontactStringArratList = addcontactStringArratList;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public ShowListMemberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = layoutInflater.inflate(R.layout.recycler_show_list_member, viewGroup, false);
        ShowListMemberViewHolder showListMemberViewHolder = new ShowListMemberViewHolder(view);


        return showListMemberViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ShowListMemberViewHolder showListMemberViewHolder, int i) {

//        typeUserSting = typeUsers[Integer.parseInt(typeStringArrayList.get(8))];
//        Log.d("27AprilV2", "typeUser ==> " + typeUserSting);



        String name = nameStringArratList.get(i);
        String firstname = firstnameStringArratList.get(i);
        String lastname = lastnameStringArratList.get(i);
        String phone = phoneStringArratList.get(i);
//        String type = typeStringArrayList.get(i);
//        String addcontact = addcontactStringArratList.get(i);


        showListMemberViewHolder.nameTextView.setText(name);
        showListMemberViewHolder.firstTextView.setText(firstname);
        showListMemberViewHolder.lastnameTextView.setText(lastname);
        showListMemberViewHolder.phoneTextView.setText(phone);
//        showListMemberViewHolder.typeTextView.setText(type);
//        showListMemberViewHolder.addcontactTextView.setText(addcontact);


        showListMemberViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClickitem(v, showListMemberViewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return nameStringArratList.size();
    }

    public class ShowListMemberViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView, firstTextView, lastnameTextView, phoneTextView, typeTextView, addcontactTextView;

        public ShowListMemberViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.txtName);
            firstTextView = itemView.findViewById(R.id.txtFirstName);
            lastnameTextView = itemView.findViewById(R.id.txtSurname);
            phoneTextView = itemView.findViewById(R.id.txtPhone);
//            typeTextView = itemView.findViewById(R.id.txtType);
//            addcontactTextView = itemView.findViewById(R.id.txtaddcontact);

        }

    }

}

