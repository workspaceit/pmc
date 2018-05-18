package com.workspaceit.pmc.service;

import com.workspaceit.pmc.dao.StateDao;
import com.workspaceit.pmc.entity.City;
import com.workspaceit.pmc.entity.State;
import com.workspaceit.pmc.validation.city.CityForm;
import com.workspaceit.pmc.validation.state.StateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mi_rafi on 1/1/18.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StateService {
    private StateDao stateDao;

    @Autowired
    public void setStateDao(StateDao stateDao) {
        this.stateDao = stateDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public State getById(int id){
        return this.stateDao.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<State> getAll(){
        return this.stateDao.getAll();
    }

    @Transactional
    public void create(State state) {
        stateDao.insert(state);
    }

    @Transactional(rollbackFor = Exception.class)
    public State create(StateForm stateForm){
        State state = getStateFromStateForm(stateForm);
        this.create(state);
        return state;
    }

    private State getStateFromStateForm(StateForm stateForm){
        State state = new State();
        state.setName(stateForm.getStateName());
        state.setCode(stateForm.getCode());
        return state;
    }

}
