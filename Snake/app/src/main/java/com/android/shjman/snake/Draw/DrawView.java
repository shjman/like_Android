package com.android.shjman.snake.Draw;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.android.shjman.snake.App;
import com.android.shjman.snake.Util.Constants;
import com.android.shjman.snake.Util.DirectionEnum;

public class DrawView extends SurfaceView implements SurfaceHolder.Callback {

    private DrawThread drawThread;
    private Paint p;
    private Paint pointSnake;
    private float[] pointsVertical;
    private float[] pointsHorizontal;


    public DrawView(Context context) {
        super(context);
        getHolder().addCallback(this);
        p = new Paint();
        p.setColor(Color.RED);
        p.setStrokeWidth(10);
        pointSnake = new Paint();
        pointSnake.setColor(Color.BLUE);
        pointSnake.setStrokeWidth(20);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    public void setDrawThread(DirectionEnum directionEnum) {
        drawThread.setDirectionEnum(directionEnum);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(getHolder());
        drawThread.setRunning(true);
        drawThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        drawThread.setRunning(false);
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    class DrawThread extends Thread {
        private DirectionEnum directionEnum = null;
        private boolean running = false;
        private SurfaceHolder surfaceHolder;
        private int x = 150;
        private int y = 150;


        public DrawThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }

        @Override
        public void run() {
            Canvas canvas;
            while (running) {
                canvas = null;
                try {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (directionEnum != null) {
                        switch (directionEnum) {
                            case Left:
                                x = x - 100;
                                break;
                            case Right:
                                x = x + 100;
                                break;
                            case Up:
                                y = y - 100;
                                break;
                            case Down:
                                y = y + 100;
                                break;
                        }
                    }
                    if (checkBorder(x, y)) {
                        canvas = surfaceHolder.lockCanvas(null);
                        if (canvas != null) {
                            canvas.drawColor(Color.GREEN);
                            pointsVertical = new float[]{0, 0, 0, 1000};
                            pointsHorizontal = new float[]{0, 0, 1000, 0};
                            for (int i = 0; i < 11; i++) {
                                canvas.drawLines(pointsHorizontal, p);
                                pointsHorizontal[1] += 100;
                                pointsHorizontal[3] += 100;
                                canvas.drawLines(pointsVertical, p);
                                pointsVertical[0] += 100;
                                pointsVertical[2] += 100;
                            }

                            canvas.drawPoint(x, y, pointSnake);
                            Log.e("aaa", "directionEnum      " + directionEnum);
                        }
                    } else {
                        Log.e("aaa", "GG");
                        Intent intent = new Intent(Constants.BROADCAST_ACTION_GAME_FINISHED);
                        //intent.putExtra(GPSService.EXTRA_FINISHED_TRACK_DISTANCE, distanceTrackMeter);
                        LocalBroadcastManager.getInstance(App.getInstance()).sendBroadcast(intent);
                        running = false;
                    }
                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }

        public DirectionEnum getDirectionEnum() {
            return directionEnum;
        }

        public void setDirectionEnum(DirectionEnum directionEnum) {
            this.directionEnum = directionEnum;
        }

        public Boolean checkBorder(int x, int y) {
            if (x >= 50 && x <= 950 && y >= 50 && y <= 950) {
                return true;
            } else {
                return false;
            }
        }
    }
}