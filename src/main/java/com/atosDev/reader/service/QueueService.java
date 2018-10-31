package com.atosDev.reader.service;

import java.util.List;

import com.atosDev.reader.entity.Queue;

public interface QueueService {

	List<Queue> getAllData();

	public void deleteQueue(int i);

}
