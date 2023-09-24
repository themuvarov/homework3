package m.uvarov.homework1.repository;

import m.uvarov.homework1.model.Order;
import m.uvarov.homework1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByRequestId(String requestId);
}

