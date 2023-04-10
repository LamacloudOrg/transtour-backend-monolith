package com.transtour.user.infrastructure.persistence.jpa;

import com.transtour.user.domain.Token;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("tokenRepo")
public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = "select t.* from tokens t inner join users u on t.user_id = u.id where u.id = :id and (t.expired = false or t.revoked = false)", nativeQuery = true)
    List<Token> findAllValidTokenByUser(String id);

    Optional<Token> findByToken(String token);

    @Override
    <S extends Token> S save(S entity);

    @Override
    <S extends Token> List<S> saveAll(Iterable<S> iterable);

}