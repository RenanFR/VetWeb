package com.vetweb.dao;
 //@author renanrodrigues
import java.util.List;
import java.util.Optional;

public interface IDAO<E> {
    void salvar(E e);
    List<E> listar();
    E consultarPorId(long id);
    void remover(E e);
    E consultarPorNome(String nome);
    long quantidadeRegistros();
//    E atualizar(E e);
}
