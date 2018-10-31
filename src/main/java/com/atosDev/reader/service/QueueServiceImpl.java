package com.atosDev.reader.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atosDev.reader.entity.Queue;
import com.atosDev.reader.repos.QueueRepo;

@Service
@Transactional
@Qualifier("queueServ")
public class QueueServiceImpl implements QueueService {
	@Autowired
	private final QueueRepo queueRepo;

	public QueueServiceImpl(QueueRepo queueRepository) {

		this.queueRepo = queueRepository;
	}

	@Override
	public List<Queue> getAllData() {
		// TODO Auto-generated method stub
		return queueRepo.findAll();
	}

	@Override
	public void deleteQueue(int id) {

		Queue queue = queueRepo.getOne(id);

		if (queue != null) {

			queueRepo.deleteById(id);
			System.out.println(
					"*****************ELIMINADA QUEUE************" + queue.toString() + "***********************");

		} else {

			System.out.println("*****************NO SE HA PODIDO ELIMINAR LA QUEUE**************" + queue.toString());

		}
	}
}
