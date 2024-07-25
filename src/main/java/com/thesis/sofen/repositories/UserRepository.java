package com.thesis.sofen.repositories;

import com.thesis.sofen.common.ERole;
import com.thesis.sofen.common.EUserStatus;
import com.thesis.sofen.dto.Interface.create.ICreateDate;
import com.thesis.sofen.dto.Interface.create.ICreateMonth;
import com.thesis.sofen.dto.Interface.create.ICreateWeek;
import com.thesis.sofen.dto.Interface.create.ICreateYear;
import com.thesis.sofen.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    @Query(value = "select TO_CHAR(u.createdAt, 'dd-mm-YYYY') as label, count(*) as count" +
                   " from User u " +
                   " where u.role.id = 1" +
                   " group by label")
    List<ICreateDate> countUserCreatedByDate();

    @Query(value = """
                        SELECT EXTRACT(YEAR FROM "created_at") AS year,
                               EXTRACT(WEEK FROM "created_at") AS week,
                               COUNT(*) AS count
                        FROM sofendb.users
                        GROUP BY year, week
                        ORDER BY year, week
            """, nativeQuery = true)
    List<ICreateWeek> countUserCreatedByWeek();

    @Query(value = """
            SELECT EXTRACT(YEAR FROM "created_at") AS year,
                   EXTRACT(MONTH FROM "created_at") AS month,
                   COUNT(*) AS count
            FROM sofendb.users
            GROUP BY year, month
            ORDER BY year, month;
            """, nativeQuery = true)
    List<ICreateMonth> countUserCreatedByMonth();

    @Query(value = """
            SELECT EXTRACT(YEAR FROM "created_at") AS year,
                   COUNT(*) AS count
            FROM sofendb.users
            GROUP BY year
            ORDER BY year;
            """, nativeQuery = true)
    List<ICreateYear> countUserCreatedByYear();


    @Query("SELECT COUNT(u) FROM User u WHERE u.role.role = :role")
    int countByRole(ERole role);

    @Query("SELECT u FROM User u WHERE u.role.role= :role")
    Page<User> getUsersByRole(ERole role, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.role.role = :eRole " +
           "AND (:status IS NULL OR  u.status = :status)" +
           "AND (:search IS NULL OR u.fullName LIKE %:search% ) " +
           "AND (:search IS NULL OR u.email LIKE %:search% ) ")
    Page<User> getUsersByRoleAndStatus(ERole eRole, EUserStatus status, Pageable pageable, String search);
}
