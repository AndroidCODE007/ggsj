package com.channelsoft.android.ggsj.zxing;

import android.hardware.Camera;

/**
 * Default implementation for Android before API 9 / Gingerbread.
 */
final class DefaultOpenCameraInterface implements OpenCameraInterface {

	/**
	 * Calls {@link Camera#open()}.
	 */
	@Override
	public Camera open() {
		return Camera.open();
	}
}
