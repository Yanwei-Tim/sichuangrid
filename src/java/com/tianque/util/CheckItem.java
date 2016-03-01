package com.tianque.util;

public class CheckItem<T> {
	private T obj;
	private boolean checked;

	public CheckItem(T obj, boolean checked) {
		this.obj = obj;
		this.checked = checked;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
