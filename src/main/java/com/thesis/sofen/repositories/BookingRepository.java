package com.thesis.sofen.repositories;

import com.thesis.sofen.common.EBookingStatus;
import com.thesis.sofen.common.EUserStatus;
import com.thesis.sofen.dto.Interface.create.ICreateDate;
import com.thesis.sofen.dto.Interface.create.ICreateMonth;
import com.thesis.sofen.dto.Interface.create.ICreateWeek;
import com.thesis.sofen.dto.Interface.create.ICreateYear;
import com.thesis.sofen.entities.Booking;
import com.thesis.sofen.request.booking.BookingProfitRequest;
import com.thesis.sofen.request.filter.BookingHistoryRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    @Query(value = """
            SELECT  SUM(b.totalPrice) as label
            FROM Booking b
            WHERE b.bookingStatus = 'SUCCESS'
            AND b.createdAt >= :#{#req.getFrom()}
            AND ( :#{#req.to} IS NULL OR b.createdAt <= :#{#req.to} )
            AND (( :#{#req.type} IS NULL AND b.user IS NULL)
                OR (:#{#req.type} like '%user%' AND b.user IS NOT NULL)
                OR :#{#req.type} like '%all%')
            """)
    List<ICreateDate> getBookingProfitAllTime(BookingProfitRequest req);

    @Query("""
            SELECT EXTRACT(YEAR FROM b.createdAt) AS year,
                   COUNT(*) AS count
            FROM Booking b
            WHERE b.bookingStatus = 'SUCCESS'
            AND  (( :#{#req.type} IS NULL AND b.user IS NULL)
                OR (:#{#req.type} like '%user%' AND b.user IS NOT NULL)
                OR :#{#req.type} like '%all%')
            GROUP BY year
            """)
    List<ICreateYear> countByYear(BookingProfitRequest req);

    @Query(""" 
            SELECT EXTRACT(YEAR FROM b.createdAt) AS year,
                EXTRACT(MONTH FROM b.createdAt) AS month,    
                COUNT(*) AS count
            FROM Booking b
                    WHERE b.bookingStatus = 'SUCCESS'
                    AND  (( :#{#req.type} IS NULL AND b.user IS NULL)
                        OR (:#{#req.type} like '%user%' AND b.user IS NOT NULL)
                        OR :#{#req.type} like '%all%')
                    GROUP BY year, month
                        """)
    List<ICreateMonth> countByMonth(BookingProfitRequest req);


    @Query(value = """ 
            SELECT EXTRACT(YEAR FROM b.createdAt) AS year, 
                EXTRACT('WEEK' FROM b.createdAt) AS month,    
                COUNT(*) AS count
            FROM sofendb.bookings b
                    WHERE b.bookingStatus = 'SUCCESS'
                    AND  (( :#{#req.type} IS NULL AND b.user IS NULL)
                        OR (:#{#req.type} like '%user%' AND b.user IS NOT NULL)
                        OR :#{#req.type} like '%all%')
                    GROUP BY year, month
                        """, nativeQuery = true)
    List<ICreateWeek> countByWeek(BookingProfitRequest req);

    @Query("""
            SELECT TO_CHAR(b.createdAt, 'DD/MM/YYYY') AS date,
                   COUNT(*) AS count
            FROM Booking b
            WHERE b.bookingStatus = 'SUCCESS'
            AND  (( :#{#req.type} IS NULL AND b.user IS NULL)
                OR (:#{#req.type} like '%user%' AND b.user IS NOT NULL)
                OR :#{#req.type} like '%all%')
            AND ( :#{#req.from} IS NULL OR b.createdAt >= :#{#req.from} )
            AND ( :#{#req.to} IS NULL OR b.createdAt <= :#{#req.to} )
            GROUP BY date
            """)
    List<ICreateDate> countByDate(BookingProfitRequest req);


    Page<Booking> findByUserId(UUID userId, Pageable pageable);

    Page<Booking> findByUserIdAndBookingStatus(UUID userId, EBookingStatus eBookingStatus, Pageable pageable);

    @Query("""
            SELECT b
            FROM Booking b
            """)
    @NotNull
    Page<Booking> findAll(Pageable pageable);

    Page<Booking> findByBookingStatus(EBookingStatus eBookingStatus, Pageable pageable);

    @Query(value = """
                select  b.*
                from sofendb.bookings b inner join sofendb.booking_details bd on b.booking_id = bd.booking_id
                inner join sofendb.booking_detail_rooms bdr on bd.booking_detail_id = bdr.booking_detail_id
                inner join sofendb.rooms r ON bdr.room_id = r.room_id
                inner join sofendb.hotels h on h.hotel_id = r.hotel_id
                WHERE (CAST(:#{#request.hotelId} AS uuid) IS NULL OR h.hotel_id = CAST(:#{#request.hotelId} AS uuid))
                AND (CAST(:#{#request.status} AS text) = 'ALL' OR b.booking_status = CAST(:#{#request.status} AS text))
                """, nativeQuery = true)
    Page<Booking> filterBooking(BookingHistoryRequest request, Pageable pageable);

    @Query(value = """
            SELECT  SUM(b.total_price) as count,
                    DATE(b.created_at) as label
            from sofendb.bookings b inner join sofendb.booking_details bd on b.booking_id = bd.booking_id
                inner join sofendb.booking_detail_rooms bdr on bd.booking_detail_id = bdr.booking_detail_id
                inner join sofendb.rooms r ON bdr.room_id = r.room_id
                inner join sofendb.hotels h on h.hotel_id = r.hotel_id
            WHERE b.booking_status = 'SUCCESS'
           AND (CAST(:#{#req.hotelId} AS uuid) IS NULL OR h.hotel_id = CAST(:#{#req.hotelId} AS uuid))
            AND (( :#{#req.type} like '%unuser%' AND b.user_id IS NULL)
                OR (:#{#req.type} like '%user%' AND b.user_id IS NOT NULL)
                OR :#{#req.type} like '%all%')
            GROUP BY label
            ORDER BY label
            """, nativeQuery = true)
    List<ICreateDate> getBookingProfitByDay(BookingProfitRequest req);

    @Query(value = """
            SELECT  SUM(b.total_price) as count,
                    EXTRACT(YEAR FROM b.created_at) AS year,
                    EXTRACT(WEEK FROM b.created_at) AS week
            from sofendb.bookings b inner join sofendb.booking_details bd on b.booking_id = bd.booking_id
                inner join sofendb.booking_detail_rooms bdr on bd.booking_detail_id = bdr.booking_detail_id
                inner join sofendb.rooms r ON bdr.room_id = r.room_id
                inner join sofendb.hotels h on h.hotel_id = r.hotel_id
            WHERE b.booking_status = 'SUCCESS'
            AND (CAST(:#{#req.hotelId} AS uuid) IS NULL OR h.hotel_id = CAST(:#{#req.hotelId} AS uuid))
            AND (( :#{#req.type} like '%unuser%' AND b.user_id IS NULL)
                OR (:#{#req.type} like '%user%' AND b.user_id IS NOT NULL)
                OR :#{#req.type} like '%all%')
            GROUP BY year, week
            ORDER BY year, week
            """, nativeQuery = true)
    List<ICreateWeek> getBookingProfitByWeek(BookingProfitRequest req);

    @Query(value = """
            SELECT  SUM(b.total_price) as count,
                    EXTRACT(YEAR FROM b.created_at) AS year,
                   EXTRACT(MONTH FROM b.created_at) AS month
            from sofendb.bookings b inner join sofendb.booking_details bd on b.booking_id = bd.booking_id
                inner join sofendb.booking_detail_rooms bdr on bd.booking_detail_id = bdr.booking_detail_id
                inner join sofendb.rooms r ON bdr.room_id = r.room_id
                inner join sofendb.hotels h on h.hotel_id = r.hotel_id
            WHERE b.booking_status = 'SUCCESS'
            AND (CAST(:#{#req.hotelId} AS uuid) IS NULL OR h.hotel_id = CAST(:#{#req.hotelId} AS uuid))
            AND (( :#{#req.type} like '%unuser%' AND b.user_id IS NULL)
                OR (:#{#req.type} like '%user%' AND b.user_id IS NOT NULL)
                OR :#{#req.type} like '%all%')
            GROUP BY year, month
            ORDER BY year, month
            """, nativeQuery = true)
    List<ICreateMonth> getBookingProfitByMonth(BookingProfitRequest req);

    @Query(value = """
        SELECT  SUM(b.total_price) as count,
                EXTRACT(YEAR FROM b.created_at) AS year
        from sofendb.bookings b inner join sofendb.booking_details bd on b.booking_id = bd.booking_id
            inner join sofendb.booking_detail_rooms bdr on bd.booking_detail_id = bdr.booking_detail_id
            inner join sofendb.rooms r ON bdr.room_id = r.room_id
            inner join sofendb.hotels h on h.hotel_id = r.hotel_id
        WHERE b.booking_status = 'SUCCESS'
        AND (CAST(:#{#req.hotelId} AS uuid) IS NULL OR h.hotel_id = CAST(:#{#req.hotelId} AS uuid))
        AND (( :#{#req.type} like '%unuser%' AND b.user_id IS NULL)
            OR (:#{#req.type} like '%user%' AND b.user_id IS NOT NULL)
            OR :#{#req.type} like '%all%')
        GROUP BY year
        ORDER BY year
        """, nativeQuery = true)
    List<ICreateYear> getBookingProfitByYear(BookingProfitRequest req);
}
