package com.vetweb.dao;
//@author renan.rodrigues@metasix.com.br

import java.util.List;

public interface IDAO<E> {
	
    public void salvar(E e);
    
    public List<E> listarTodos();
    
    public E buscarPorId(long id);
    
    public void remover(E e);
    
}
