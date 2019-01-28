package com.android.shjman.snake.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.android.shjman.snake.R;
import com.android.shjman.snake.Draw.DrawView;
import com.android.shjman.snake.Util.Constants;
import com.android.shjman.snake.Util.DirectionEnum;
import com.android.shjman.snake.Util.ResultDialogFragment;
import com.android.shjman.snake.App;

public class SnakeFiledActivity extends AppCompatActivity {
    private Button btnLeft;
    private Button btnRight;
    private Button btnUp;
    private Button btnDown;

    private DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinate_field);
        btnLeft = findViewById(R.id.btn_left);
        btnRight = findViewById(R.id.btn_right);
        btnDown = findViewById(R.id.btn_down);
        btnUp = findViewById(R.id.btn_up);


        drawView = new DrawView(this);
        FrameLayout container = (FrameLayout) findViewById(R.id.container);
        container.addView(drawView);

        btnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                drawView.setDrawThread(DirectionEnum.Left);
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                drawView.setDrawThread(DirectionEnum.Right);
            }
        });
        btnUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                drawView.setDrawThread(DirectionEnum.Up);
            }
        });
        btnDown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                drawView.setDrawThread(DirectionEnum.Down);
            }
        });

    }

    public void showGG() {
        ResultDialogFragment.newInstance().show(getSupportFragmentManager(), null);
    }


    private BroadcastReceiver receiverEndGame = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            showGG();
            Log.e("eee", "onReceive");
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(App.getInstance()).registerReceiver(receiverEndGame, new IntentFilter(Constants.BROADCAST_ACTION_GAME_FINISHED));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(App.getInstance()).unregisterReceiver(receiverEndGame);
        super.onPause();
    }

}