package com_dog.dog.dog_java.dogdom.ProfileStructure;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com_dog.dog.dog_java.dogdom.Modules.ItemFAQ;
import com_dog.dog.dog_java.dogdom.R;

public class AdapterFAQ extends RecyclerView.Adapter<AdapterFAQ.ViewHolderFAQ> {

    private ArrayList<ItemFAQ> arrayFAQ;

    public AdapterFAQ(ArrayList<ItemFAQ> arrayFAQ){
        this.arrayFAQ = arrayFAQ;
    }

    @NonNull
    @Override
    public ViewHolderFAQ onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_faq, parent, false);
        return new AdapterFAQ.ViewHolderFAQ(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFAQ holder, int position) {
        ItemFAQ itemFAQ = arrayFAQ.get(position);
        holder.answerText.setText(itemFAQ.getAnswer());
        holder.questionText.setText(itemFAQ.getQuestion());
        int cp = position + 1;
        holder.numQText.setText(cp+ ". ");


    }

    @Override
    public int getItemCount() {
        return arrayFAQ.size();
    }

    public class ViewHolderFAQ extends RecyclerView.ViewHolder {

        private TextView answerText, numQText, questionText;

        public ViewHolderFAQ(@NonNull View itemView) {
            super(itemView);

            answerText = itemView.findViewById(R.id.text_answer);
            numQText = itemView.findViewById(R.id.text_num_q);
            questionText = itemView.findViewById(R.id.text_question);


        }
    }
}
