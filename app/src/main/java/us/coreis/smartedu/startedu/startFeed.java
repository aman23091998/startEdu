package us.coreis.smartedu.startedu;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class startFeed extends android.support.v4.app.Fragment {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    ArrayList<String> WhoInvites_adapter, WhoInvited_adapter;
    String WhoInvites[], WhoInvited[];
    Button Submit, Invite;
    View dialogView;
    AlertDialog.Builder dialogBuilder;
    LayoutInflater layoutInflater;
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    Matcher matcher;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dialogBuilder = new AlertDialog.Builder(getContext());
        layoutInflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.layout_feed, null);
        WhoInvites = getResources().getStringArray(R.array.who2whom_desc);
        WhoInvited = getResources().getStringArray(R.array.fromWhom);
        WhoInvites_adapter = new ArrayList<>();
        WhoInvited_adapter = new ArrayList<>();
        for (int i = 0; i < WhoInvited.length; ++i) {
            WhoInvites_adapter.add(WhoInvites[i]);
            WhoInvited_adapter.add(WhoInvited[i]);
        }
        RecyclerView feedRV = (RecyclerView) view.findViewById(R.id.feedRV);
        feedRV.setLayoutManager(new LinearLayoutManager(getContext()));
        final FeedAdapter adapter = new FeedAdapter(WhoInvites_adapter, WhoInvited_adapter, getContext());
        feedRV.setAdapter(adapter);
        Submit = (Button) view.findViewById(R.id.feed_submit);
        Invite = (Button) view.findViewById(R.id.feed_invite);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextInputLayout aboutSelf = (TextInputLayout) view.findViewById(R.id.status_update_wrapper);
                final String AddInfo = aboutSelf.getEditText().getText().toString();
                if (AddInfo.length() < 14)
                    aboutSelf.setError(" Must be at-least 15 characters ");
                else {
                    WhoInvites_adapter.add(AddInfo);
                    if (MainActivity.name.length() == 0)
                        WhoInvited_adapter.add(" ");
                    else
                        WhoInvited_adapter.add(" - " + MainActivity.name);
                    dialogView = layoutInflater.inflate(R.layout.feed_dialog_loading, null);
                    dialogBuilder.setView(dialogView);
                    final ProgressBar progressBar = (ProgressBar) dialogView.findViewById(R.id.progressBar_feed);
                    final TextView information = (TextView) dialogView.findViewById(R.id.feed_dialog_text);
                    progressBar.getIndeterminateDrawable().setColorFilter(0xFF02BAA7, PorterDuff.Mode.MULTIPLY);
                    information.setText(" Sending information to our servers ");
                    final Button button1 = (Button) dialogView.findViewById(R.id.feedDialogConfirm1);
                    final Button button2 = (Button) dialogView.findViewById(R.id.feedDialogConfirm2);
                    final AlertDialog aboutDialog = dialogBuilder.create();
                    aboutDialog.show();
                    button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            aboutSelf.setErrorEnabled(false);
                            progressBar.setVisibility(View.GONE);
                            information.setText(Html.fromHtml(" <h3> <font color=\"#02BAA7\"> News Feed Updated </h3 > </font> "));
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.VISIBLE);
                        }

                    });
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            aboutDialog.cancel();
                        }
                    });
                    adapter.notifyDataSetChanged();
                    aboutSelf.getEditText().setText("");
                    hideKeyboard();
                }
            }
        });

        Invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView = layoutInflater.inflate(R.layout.feed_invite_layout, null);
                dialogBuilder.setView(dialogView);
                final AlertDialog invitePage = dialogBuilder.create();
                invitePage.show();
                final TextInputLayout emailWrapper = (TextInputLayout) dialogView.findViewById(R.id.wrapper_email_invite_feed);
                final TextInputLayout nameWrapper = (TextInputLayout) dialogView.findViewById(R.id.wrapper_name_invite_feed);
                Button inviteButton = (Button) dialogView.findViewById(R.id.invite_feed_button);
                inviteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String email = emailWrapper.getEditText().getText().toString();
                        String name = nameWrapper.getEditText().getText().toString();
                        if (!pattern.matcher(email).matches())
                            emailWrapper.setError(" Not a valid email Address");
                        else emailWrapper.setErrorEnabled(false);
                        if (name.length() == 0)
                            nameWrapper.setError("Field can't be left Empty ");
                        else nameWrapper.setErrorEnabled(false);
                        if (name.length() > 0 && pattern.matcher(email).matches()) {
                            invitePage.cancel();
                            if (MainActivity.name.length() != 0)
                                WhoInvites_adapter.add(MainActivity.name + " invited " + name + " to ");
                            else
                                WhoInvites_adapter.add(name + " has been invited to  ");
                            WhoInvited_adapter.add(" - <font color=\"#02BAA7\">startEdu</font> ");
                            dialogView = layoutInflater.inflate(R.layout.feed_dialog_loading, null);
                            dialogBuilder.setView(dialogView);
                            final ProgressBar progressBar = (ProgressBar) dialogView.findViewById(R.id.progressBar_feed);
                            final TextView information = (TextView) dialogView.findViewById(R.id.feed_dialog_text);
                            progressBar.getIndeterminateDrawable().setColorFilter(0xFF02BAA7, PorterDuff.Mode.MULTIPLY);
                            information.setText(" Sending Invite ");
                            final Button button1 = (Button) dialogView.findViewById(R.id.feedDialogConfirm1);
                            final Button button2 = (Button) dialogView.findViewById(R.id.feedDialogConfirm2);
                            final AlertDialog inviteDialog = dialogBuilder.create();
                            inviteDialog.show();
                            button1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    progressBar.setVisibility(View.GONE);
                                    information.setText(Html.fromHtml(" <h3> <font color=\"#02BAA7\"> Invitation Sent </h3 > </font> "));
                                    button1.setVisibility(View.GONE);
                                    button2.setVisibility(View.VISIBLE);
                                }

                            });
                            button2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    inviteDialog.cancel();
                                }
                            });
                            adapter.notifyDataSetChanged();
                            nameWrapper.getEditText().setText("");
                            emailWrapper.getEditText().setText("");

                        }
                    }
                });
                hideKeyboard();
            }

        });
        return view;
    }

    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
