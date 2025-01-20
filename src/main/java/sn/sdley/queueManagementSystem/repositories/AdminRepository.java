package sn.sdley.queueManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.sdley.queueManagementSystem.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
