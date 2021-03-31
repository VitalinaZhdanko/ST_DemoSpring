package com.zhdanko.demospring.repository;

import com.zhdanko.demospring.entities.ClientOrder;
import com.zhdanko.demospring.entities.StatusOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<ClientOrder, Integer> {

    @Modifying
    @Query("update ClientOrder o set o.statusOrder=?1 where o.id=?2")
    @Transactional
    void updateStatusByOrderID(StatusOrder statusOrder, int id);

    @Modifying
    @Query("update ClientOrder o set o.comment=:comment where o.id=:id")
    @Transactional
    int updateCommentByOrderID(@Param("comment") String comment, @Param("id") int id);

    Page<ClientOrder> findAll(Pageable pageable);

    void deleteClientOrderById(int orderID);
}
