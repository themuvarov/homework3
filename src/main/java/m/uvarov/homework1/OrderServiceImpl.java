package m.uvarov.homework1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m.uvarov.homework1.model.Order;
import m.uvarov.homework1.model.User;
import m.uvarov.homework1.repository.OrderRepository;
import m.uvarov.homework1.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderServiceImpl {

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    public Order create(Order order, String requestId) {
        if (orderRepository.findByRequestId(requestId).isPresent()){
            log.error("Order {} already created", requestId);
            return null;
        }

        if (userRepository.findById(order.getUserId()).isPresent()) {
            order.setRequestId(requestId);
            Order order1 = orderRepository.save(order);
            log.info("Order {} {} created", requestId, order1.getId());
            return order1;
        }

        log.error("User {} not found", order.getUserId());
        return null;
    }

}