package kr.poturns.virtualpalace.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.Build;

/**
 * <b> 가속도 센서 AGENT </b>
 *
 * @author Yeonho.Kim
 */
public class AcceleroSensorAgent extends BaseSensorAgent implements SensorEventListener2 {

    // * * * C O N S T A N T S * * * //
    private final SensorManager mSensorManagerF;
    private final Sensor mSensorF;


    // * * * F I E L D S * * * //
    private float axisX;
    private float axisY;
    private float axisZ;


    // * * * C O N S T R U C T O R S * * * //
    public AcceleroSensorAgent(Context context) {
        mSensorManagerF = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensorF = mSensorManagerF.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }


    // * * * I N H E R I T S * * * //
    @Override
    public void startListening() {
        super.startListening();

        mSensorManagerF.registerListener(this, mSensorF, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void stopListening() {
        super.stopListening();

        mSensorManagerF.unregisterListener(this);
    }

    /**
     * @return
     */
    @Override
    public double[] getLatestData() {
        return new double[]{
            mLatestMeasuredTimestamp
        };
    }

    /**
     * Agent Type 반환
     *
     * @return
     */
    @Override
    public int getAgentType() {
        return TYPE_AGENT_ACCELEROMETER;
    }

    /**
     * Called after flush() is completed. All the events in the batch at the point when the flush
     * was called have been delivered to the applications registered for those sensor events. In
     * {@link Build.VERSION_CODES#KITKAT}, applications may receive flush complete events
     * even if some other application has called flush() on the same sensor. Starting with
     * {@link Build.VERSION_CODES#LOLLIPOP}, flush Complete events are sent ONLY to the
     * application that has explicitly called flush(). If the hardware FIFO is flushed due to some
     * other application calling flush(), flush complete event is not delivered to this application.
     * <p/>
     *
     * @param sensor The {@link Sensor Sensor} on which flush was called.
     */
    @Override
    public void onFlushCompleted(Sensor sensor) {

    }

    /**
     * Called when sensor values have changed.
     * <p>See {@link SensorManager SensorManager}
     * for details on possible sensor types.
     * <p>See also {@link SensorEvent SensorEvent}.
     * <p/>
     * <p><b>NOTE:</b> The application doesn't own the
     * {@link SensorEvent event}
     * object passed as a parameter and therefore cannot hold on to it.
     * The object may be part of an internal pool and may be reused by
     * the framework.
     *
     * @param event the {@link SensorEvent SensorEvent}.
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        mLatestMeasuredTimestamp = event.timestamp;

        axisX = event.values[0];
        axisY = event.values[1];
        axisZ = event.values[2];

        onDataMeasured();
    }

    /**
     * Called when the accuracy of the registered sensor has changed.
     * <p/>
     * <p>See the SENSOR_STATUS_* constants in
     * {@link SensorManager SensorManager} for details.
     *
     * @param sensor
     * @param accuracy The new accuracy of this sensor, one of
     *                 {@code SensorManager.SENSOR_STATUS_*}
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}