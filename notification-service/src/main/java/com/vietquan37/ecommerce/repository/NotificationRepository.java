package com.vietquan37.ecommerce.repository;

import com.vietquan37.ecommerce.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
