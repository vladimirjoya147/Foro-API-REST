package com.AluraChallenge.Foro.Servicio;

import com.AluraChallenge.Foro.Entidades.Topics;
import com.AluraChallenge.Foro.Repositorio.TopicsRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TopicServicioImpl implements TopicServicio {


    @Autowired
    private TopicsRepositorio topicsRepositorio;

    //listar todos los topicos
    @Override
    public Page<Topics> listaTopics(Pageable pageable) {
        return topicsRepositorio.findAll(pageable);
    }

    // obtener topico por id
    @Override
    public Topics obtenerTopicPorId(Integer id) {
        return topicsRepositorio.findById(id).get();
    }

    @Override
    public Topics guardarTopic(Topics topics) {
       return topicsRepositorio.save(topics);
    }

    @Override
    public void eliminarTopic(Integer id) {
        topicsRepositorio.deleteById(id);
    }

    @Override
    public Topics actualizarTopics(Topics topics) {
       return topicsRepositorio.save(topics);
    }
}
