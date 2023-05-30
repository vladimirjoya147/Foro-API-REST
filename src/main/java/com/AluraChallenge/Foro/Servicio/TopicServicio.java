package com.AluraChallenge.Foro.Servicio;


import com.AluraChallenge.Foro.Entidades.Topics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


public interface TopicServicio {

    public Page<Topics> listaTopics(Pageable pageable);

    public Topics obtenerTopicPorId(Integer id);

    public Topics guardarTopic(Topics topics);

    public void eliminarTopic(Integer id);

    public Topics actualizarTopics(Topics topics);
}
