package com.example.parul.twsearch;


import android.content.Context;

final public class Log {

	public Log(Context context) {

	}
	
	/**Print the message;
	 * @param message
	 */
	static final public void i(String message){
		android.util.Log.i(Constants.TAG, message);
	}
	/**Print warning message.
	 * @param message
	 */
	static final public void w(String message){
		android.util.Log.w(Constants.TAG, message);
	}

	/**Print error message.
	 * @param message
	 */
	static final public void e(String message){
		android.util.Log.e(Constants.TAG, message);
	}
	
	/**Print error warning with message. 
	 * @param message
	 * @param throwable
	 */
	static final public void w(String message, Throwable throwable){
		android.util.Log.w(Constants.TAG, message, throwable);
	}

	/**Print error log with message. 
	 * @param message
	 * @param throwable
	 */
	static final public void e(String message, Throwable throwable){
		android.util.Log.e(Constants.TAG, message, throwable);
	}
	
	/**Print debug log. 
	 * @param message
	 */
	static final public void d(String message){
		if(Constants.isDebugMode)
		android.util.Log.d(Constants.TAG, message);
	}
	
	/**Print debug error log with message; 
	 * @param message
	 * @param throwable
	 */
	static final public void d(String message, Throwable throwable){
		if(throwable==null){
			d(message);
			return;
		}
		if(Constants.isDebugMode)
		android.util.Log.d(Constants.TAG, message, throwable);
	}
	
	
}
