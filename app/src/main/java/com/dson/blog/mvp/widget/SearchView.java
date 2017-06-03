package com.dson.blog.mvp.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dson.blog.R;


public class SearchView extends RelativeLayout {

    private static final int INPUT_MAX_LEN = 30;
    private ImeEditText mInputView;
    private TextView mSearchButton;
    private ImageView mClearButton;
    private SearchListener mSearchListener;
    private String mQuery = "";
    private String mHitQuery;
    private boolean mTriggerQuery;

    public interface SearchListener {

        void onQueryTextChanged(String text);

        void onQueryTextClear();

        void onExecuteQuery(String text);

        void onQueryError();
    }

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    public void initView() {
        mInputView = (ImeEditText) findViewById(R.id.searchview_input);
        mInputView.setSingleLine();
        mInputView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }
        });
        mInputView.setOnKeyPreImeListener(new ImeEditText.OnKeyPreImeListener() {
            @Override
            public boolean onKeyPreIme(int keyCode, KeyEvent event) {
                if (onInterceptKeyEvent(event)) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        mInputView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mTriggerQuery) {
                    mTriggerQuery = false;
                    if (!TextUtils.isEmpty(mQuery)) {
                        switchClearBtn(mQuery);
                    }
                    return;
                }
                if (null == s) {
                    if (mSearchListener != null) {
                        mSearchListener.onQueryError();
                    }
                } else {
                    mQuery = s.toString().trim();
                    if (mQuery.length() < INPUT_MAX_LEN) {
                        if (mSearchListener != null) {
                            mSearchListener.onQueryTextChanged(mQuery);
                        }
                        switchClearBtn(mQuery);
                    } else {
                        mQuery = mQuery.substring(0, INPUT_MAX_LEN);
                        if (mSearchListener != null) {
                            mSearchListener.onQueryError();
                        }
                    }
                }
            }
        });
        mSearchButton = (TextView) findViewById(R.id.searchview_btn);
        mSearchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSearchListener != null) {
                    mSearchListener.onExecuteQuery(getQuery());
                }
            }
        });
        mClearButton = (ImageView) findViewById(R.id.searchview_clear);
        mClearButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(getQuery())) {
                    setQuery("");
                    if (mSearchListener != null) {
                        mSearchListener.onQueryTextClear();
                    }
                }
            }
        });
    }

    public void setSearchListener(SearchListener listener) {
        this.mSearchListener = listener;
    }

    public void flashQeury() {
        setQuery(getQuery());
    }

    public void setPendingQuery(String query) {
        mTriggerQuery = true;
        setQuery(query);
    }

    public void setQuery(String query) {
        this.mQuery = query.trim();
        if (mInputView != null) {
            mInputView.setText(mQuery);
            mInputView.setSelection(mQuery.length());
        }
    }

    public String getQuery() {
        return mQuery;
    }

    private void switchClearBtn(String query) {
        if (mClearButton != null) {
            if (query.length() == 0) {
                mClearButton.setVisibility(GONE);
            } else {
                mClearButton.setVisibility(VISIBLE);
            }
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return onInterceptKeyEvent(event) || super.dispatchKeyEvent(event);
    }

    private boolean onInterceptKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && (event.getAction() == KeyEvent
                .ACTION_DOWN)) {
            if (!TextUtils.isEmpty(getQuery())) {
                execIMEQuery(getQuery());
            } else if (!TextUtils.isEmpty(mHitQuery)) {
                execIMEQuery(mHitQuery);
            }
            return true;
        }
        return false;
    }

    private void execIMEQuery(String query) {
        if (mSearchListener != null) {
            mSearchListener.onExecuteQuery(query);
        }
        setPendingQuery(query);
    }

    public void setSearchViewHint(String prefixWord, String searchWord) {
        if (mInputView != null) {
            mInputView.setHint(prefixWord);
        }
        mHitQuery = searchWord;
    }
}
