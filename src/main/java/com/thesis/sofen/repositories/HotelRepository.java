package com.thesis.sofen.repositories;

import com.thesis.sofen.common.EUserStatus;
import com.thesis.sofen.dto.Interface.*;
import com.thesis.sofen.entities.Hotel;
import com.thesis.sofen.request.filter.FilterHotelRequest;
import com.thesis.sofen.request.hotel.SearchHotelRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {
    @Query("SELECT COUNT(h) > 0 FROM Hotel h WHERE h.name = :name")
    boolean existsByName(@Param("name") String name);

    //INNER JOIN  h.rooms r INNER JOIN RoomType rt ON h.id = r.hotel.id AND r.roomType.id = rt.id
//    @Query("""
//            SELECT h.id, rt.id, r.id, r.amount -  SUM(bd.amount) as remain
//            FROM Hotel h
//                INNER JOIN h.rooms r
//                INNER JOIN r.roomType rt
//                INNER JOIN r.bookingDetails bd
//            WHERE
//                 (bd.checkIn <= :#{#filter.checkOut} AND bd.checkOut >= :#{#filter.checkIn})
//                AND (:#{#filter.fromPrice} IS NULL OR rt.price >= :#{#filter.fromPrice})
//                AND (:#{#filter.toPrice} IS NULL OR rt.price <= :#{#filter.toPrice})
//            GROUP BY h.id,  rt.id, r.id
//            """)
//    List<Object> filterHotel(SearchHotelRequest filter);

    @Query(value = """
            SELECT roomTypeName, roomId, MIN(remain1) as remain,adult, children FROM
         (
                   SELECT rt."name" as roomTypeName, r.room_id as roomId, (r.amount - COALESCE(SUM(bd.amount), 0)) as remain1,
                         rt.max_adults as adult, rt.max_children as children
                            from sofendb.hotels h
                                 inner join sofendb.rooms r on r.hotel_id = h.hotel_id
                                 inner join sofendb.room_types rt on rt.room_type_id = r.room_type_id
                                 inner join sofendb.booking_detail_rooms bdr on r.room_id = bdr.room_id
                                 inner join sofendb.booking_details bd on bdr.booking_detail_id = bd.booking_detail_id
                                 inner join sofendb.bookings b on b.booking_id = bd.booking_id
                            where  h.hotel_id = :#{#filter.hotelId}
                                    and h.status = 'ACTIVE' and r.status = 'ACTIVE'
                                    and ((b.check_in <= :#{#filter.checkOut} AND b.check_out >= :#{#filter.checkIn})
                                        or (b.check_in >= :#{#filter.checkIn} and :#{#filter.checkOut} >= b.check_out ))
                                    and ( b.booking_status = 'SUCCESS'
                                        or (b.booking_status = 'PENDING' and date_add(b.created_at, interval  '30 minutes') >  now() ))
                           group by rt."name", r.room_id, rt.max_adults, rt.max_children
                    UNION ALL
                    select rt."name" as roomTypeName, r.room_id as roomId,r.amount  as remain1,
                             rt.max_adults as adult,
                              rt.max_children as children
                           from sofendb.hotels h
                                    inner join sofendb.rooms r on r.hotel_id = h.hotel_id
                                    inner join sofendb.room_types rt on rt.room_type_id = r.room_type_id
                           where h.hotel_id = :#{#filter.hotelId}
                             	and h.status = 'ACTIVE' and r.status = 'ACTIVE'
                           group by rt."name", r.room_id, rt.max_adults, rt.max_children
                ) as sub
                
              GROUP BY
                  roomTypeName, roomId, adult, children
              ORDER BY
                  roomTypeName, roomId;  
                """, nativeQuery = true)
    List<IRoomFilter> filterHotel1(SearchHotelRequest filter);

    @Query(value = """
            select rt."name" as roomTypeName, r.room_id as roomId,r.amount -  sum(bd.number_of_rooms)  as remain,
             rt.max_adults as adult,
              rt.max_children as children,r.amount = r.amount as isAvailable
                                       from sofendb.hotels h
                                                inner join sofendb.rooms r on r.hotel_id = h.hotel_id
                                                inner join sofendb.room_types rt on rt.room_type_id = r.room_type_id
                                       where h.hotel_id = :#{#filter.hotelId}
                                       group by rt."name", r.room_id, rt.max_adults, rt.max_children;
            """, nativeQuery = true)
    List<IRoomFilter> getRoomByHotelId(SearchHotelRequest filter);

    @Query("""
                        SELECT MIN(rt.price)
                        FROM Hotel h INNER JOIN h.rooms r INNER JOIN r.roomType rt 
                        WHERE h.id = :hotelId
                        
            """)
    Long getMinPriceByHotelId(@Param("hotelId") UUID hotelId);

    @Query("""
            SELECT s.price as price, sd.name as name, sd.description as description
            FROM Hotel h INNER JOIN h.services s INNER JOIN s.hotelServiceDetails sd
            WHERE h.id = :hotelId AND sd.language.code = :lang
                """)
    List<IServiceResponse> getServicesByHotelIdAndLang(@Param("hotelId") UUID hotelId, @Param("lang") String lang);

    @Query("""
            SELECT pd.name as name, pd.description as description
            FROM Hotel h INNER JOIN h.policies p INNER JOIN p.policyDetails pd
            WHERE h.id = :hotelId AND pd.language.code = :lang
                """)
    List<IPolicyResponse> getPoliciesByHotelIdAndLang(@Param("hotelId") UUID hotelId, @Param("lang") String lang);

    @Query("""
            SELECT h
            FROM Hotel h
            WHERE (:nation IS NULL OR h.hotelAddress.nation = :nation)
            AND (:province IS NULL OR h.hotelAddress.province = :province)
            """)
    List<Hotel> getHotelByDestination(@Param("nation") String nation, @Param("province") String province);

    @Query("""
            SELECT h.id as id, h.name as name
            FROM Hotel h
            """)
    List<IHotelName> getListHotelName();

    @Query("SELECT COUNT(h) FROM Hotel h")
    int countHotels();

    @Query("""
            SELECT h
            FROM Hotel h
            WHERE (:status IS NULL OR h.status = :status )
            """)
    Page<Hotel> filterHotel(@Param("status") EUserStatus status, Pageable pageable );
}
