package edu.ieti.bidify.service;

import edu.ieti.bidify.dto.MessageDto;
import edu.ieti.bidify.dto.Puja;
import edu.ieti.bidify.model.Producto;
import edu.ieti.bidify.model.Subasta;
import edu.ieti.bidify.model.Usuario;
import edu.ieti.bidify.repository.SubastaRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Servicio que gestiona las operaciones relacionadas con las subastas en el sistema de subastas.
 */
@Service
@Data
@Transactional
public class SubastaService implements Runnable{
    @Autowired
    private SubastaRepository subastaRepository;
    private final Lock lock = new ReentrantLock();
    private boolean activa = true;
    private BigDecimal precioActual = BigDecimal.ZERO;
    private Usuario ganador;
    private Queue<Puja> pujas = new PriorityQueue<>();

    /**
     * Agrega una nueva subasta al sistema.
     *
     * @param -subasta Subasta a agregar.
     * @return La subasta agregada.
     */
    public Subasta addSubasta(Subasta Subasta) {
        Subasta.setId(autoIncrement());
        return subastaRepository.save(Subasta);
    }

    /**
     * Incrementa el ID de las subastas automáticamente.
     *
     * @return Nuevo ID.
     */
    private int autoIncrement(){
        List<Subasta> subastas = subastaRepository.findAll();
        return subastas.isEmpty()? 1 : subastas.stream().max(Comparator.comparing(Subasta::getId)).get().getId() + 1;
    }

    /**
     * Obtiene todas las subastas disponibles en el sistema.
     *
     * @return Lista de subastas.
     */
    public List<Subasta> findAllSubastas(){
        return subastaRepository.findAll();
    }

    /**
     * Obtiene una subasta por su ID.
     *
     * @param id ID de la subasta.
     * @return La subasta encontrada, o un valor opcional vacío si no se encuentra.
     */
    public Optional<Subasta> getSubastaById(int id){
        return subastaRepository.findById(id);
    }

    /**
     * Obtiene una subasta por el producto asociado.
     *
     * @param producto Producto asociado a la subasta.
     * @return La subasta encontrada, o un valor opcional vacío si no se encuentra.
     */
    public Optional<Subasta> getSubastaByProducto(Producto producto){
        return subastaRepository.findByProducto(producto);
    }

    @Override
    public void run() {
        while (activa) {
            // Procesar ofertas en cola de pujas
            lock.lock();
            try {
                Puja puja = pujas.poll();
                if (puja != null) {
                    if (puja.getOferta().compareTo(precioActual) > 0) {
                        precioActual = puja.getOferta();
                        ganador = puja.getPostor();

                        // Notificar aquí
                    }
                }
            } finally {
                lock.unlock();
            }
        }
        // Finalizar la subasta
        changeEstado();
        // Notificar finalización
    }

    /**
     * Recibe una nueva puja durante la subasta.
     *
     * @param postor Usuario que realiza la puja.
     * @param oferta Oferta realizada.
     */
    public void recibirPuja(Usuario postor, BigDecimal oferta) {
        lock.lock();
        try {
            if (activa && oferta.compareTo(precioActual) > 0) {
                pujas.offer(new Puja(postor, oferta));
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Busca una subasta por su subastador.
     *
     * @param subastador Usuario subastador.
     * @return La subasta encontrada, o un valor opcional vacío si no se encuentra.
     */
    public Optional<Subasta> findBySubastador(Usuario subastador){
        return subastaRepository.findBySubastador(subastador);
    }

    /**
     * Finaliza una subasta por su ID.
     *
     * @param subastaId ID de la subasta a finalizar.
     */
    public void finalizarSubasta(int subastaId) {
        Optional<Subasta> subastaOptional = subastaRepository.findById(subastaId);
        Subasta subasta = subastaOptional.get();
        subasta.setEstado(false);
        subastaRepository.save(subasta);
        activa = false;
    }

    /**
     * Cambia el estado de la subasta (activa o inactiva).
     */
    private void changeEstado() {
        activa = !activa;
    }

    /**
     * Agrega un mensaje a la lista de mensajes de una subasta.
     *
     * @param messageDto Objeto MessageDto que representa el mensaje a agregar.
     * @param subastaId ID de la subasta a la que se agrega el mensaje.
     * @return La subasta con el mensaje agregado, o null si la subasta no se encuentra.
     */
    public Subasta addMessage(MessageDto messageDto, int subastaId) {
        Optional<Subasta> subastaOptional = subastaRepository.findById(subastaId);

        if (subastaOptional.isPresent()) {
            Subasta subasta = subastaOptional.get();
            subasta.addMessage(messageDto);
            subastaRepository.save(subasta);
            return subasta;
        } else {
            return null;
        }
    }

    /**
     * Obtiene la lista de mensajes de una subasta.
     *
     * @param subastaId ID de la subasta.
     * @return Lista de mensajes de la subasta, o una lista vacía si la subasta no se encuentra.
     */
    public List<MessageDto> getMessageList(int subastaId) {
        Optional<Subasta> subastaOptional = subastaRepository.findById(subastaId);

        if (subastaOptional.isPresent()) {
            return subastaOptional.get().getMessageList();
        } else {
            return new ArrayList<>();
        }
    }
}
