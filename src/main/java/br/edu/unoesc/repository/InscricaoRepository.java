package br.edu.unoesc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.models.Inscricao;
import br.edu.unoesc.models.Usuario;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
  
    Inscricao findByUsuario(Usuario usuario);
}
