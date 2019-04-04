package com.spring.is.here.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.is.here.domain.County;
import com.spring.is.here.repository.StateRepository;

@Service
public class StateService {

	private StateRepository stateRepository;

	@Autowired
	public StateService(StateRepository stateRepository) {
		this.stateRepository = stateRepository;
	}

	public List<County> getStates() {
		return this.stateRepository.findAll();
	}

}
