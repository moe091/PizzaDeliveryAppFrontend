package com.moe.guiseppeconcept;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface HttpReceiver {

	
	public List<Category> getList(InputStream stream);
	public void getResult(List<Category> list);
}
