package main;

public class RecordThread {
	  // record duration, in milliseconds
    static final long RECORD_TIME = 15000;  // 15 secs
 
	/**
     * Entry to run the program
     */
    public void record() {
        final JavaSoundRecorder recorder = new JavaSoundRecorder();
        
 
        // creates a new thread that waits for a specified
        // of time before stopping
        Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(RECORD_TIME);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                recorder.finish();
            }
        });
 
        stopper.start();
 
        // start recording
        recorder.start();
    }
}
