package us.coreis.smartedu.startedu;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileFieldAdapter extends RecyclerView.Adapter<ProfileFieldAdapter.Fields> {
    Context context;
    String fieldName[];

    public ProfileFieldAdapter(Context context, String fieldName[]) {
        this.context = context;
        this.fieldName = fieldName;
    }

    @Override
    public ProfileFieldAdapter.Fields onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_recycler_layout, parent, false);
        return new Fields(view);
    }

    @Override
    public void onBindViewHolder(ProfileFieldAdapter.Fields holder, int position) {
        holder.fieldTitle.setText(fieldName[position]);
        holder.field.setFocusable(false);
        switch (position) {
            case 0:
                holder.field.setText(MainActivity.number);
                holder.field.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case 1:
                if (MainActivity.age != -1)
                    holder.field.setText("" + MainActivity.age);
                holder.field.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case 2:
                holder.field.setText(MainActivity.company);
                break;
            case 3:
                holder.field.setText(MainActivity.website);
                break;
        }
        holder.field.setFocusable(false);
    }

    @Override
    public int getItemCount() {
        return fieldName.length;
    }



    public class Fields extends RecyclerView.ViewHolder {
        TextView fieldTitle;
        TextInputLayout fieldWrapper;
        EditText field;

        public Fields(final View itemView) {
            super(itemView);
            fieldTitle = (TextView) itemView.findViewById(R.id.field_name);
            fieldWrapper = (TextInputLayout) itemView.findViewById(R.id.wrapper_field_profile);
            field = fieldWrapper.getEditText();
            field.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    field.setFocusableInTouchMode(true);
                    field.setFocusable(true);
                }
            });
            field.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_NEXT) {
                        switch (getAdapterPosition()) {
                            case 0:
                                MainActivity.number = field.getText().toString();
                                break;
                            case 1:
                                MainActivity.age = Integer.parseInt(field.getText().toString(), 10);
                                break;
                            case 2:
                                MainActivity.company = field.getText().toString();
                                break;
                            case 3:
                                MainActivity.website = field.getText().toString();
                                break;
                        }
                        hideKeyboard(context);
                        field.setFocusable(false);
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static void hideKeyboard(Context ctx)
    {
        InputMethodManager inputManager = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(new View(ctx).getWindowToken(), 0);
    }

}
