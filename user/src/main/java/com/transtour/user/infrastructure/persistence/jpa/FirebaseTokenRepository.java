package com.transtour.user.infrastructure.persistence.jpa;

import com.transtour.user.domain.SessionTokens;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("tokenRepo")
public interface FirebaseTokenRepository extends JpaRepository<SessionTokens, Integer> {

    @Query(value = "select st.* from transtour.session_tokens st inner join transtour.users u on st.user_id = u.user_id where u.user_id  = :id and (st.expired = false or st.revoked = false)", nativeQuery = true)
    List<SessionTokens> findAllValidTokenByUser(String id);

    Optional<SessionTokens> findByToken(String token);

    @Override
    <S extends SessionTokens> S save(S entity);

    @Override
    <S extends SessionTokens> List<S> saveAll(Iterable<S> iterable);

}