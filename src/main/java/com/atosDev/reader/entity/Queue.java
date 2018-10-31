package com.atosDev.reader.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUEUES")
public class Queue {

	@Id
	private int id;

	private String data;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Queue [id=" + id + ", data=" + data + "]";
	}

	public Queue(int id, String data) {
		super();
		this.id = id;
		this.data = data;
	}

	public Queue() {
		super();
	}

}
