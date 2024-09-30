package com.parcel.module.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.parcel.module.constants.BookingStatus;
import com.parcel.module.dto.BookingResponseDto;
import com.parcel.module.exception.BookingNotFound;
import com.parcel.module.model.Booking;
import com.parcel.module.modelMapper.ConvertMapper;
import com.parcel.module.repository.BookingRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BookingServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class BookingServiceImplTest {
    @MockBean
    private BookingRepository bookingRepository;

    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    @MockBean
    private ConvertMapper convertMapper;

    /**
     * Method under test: {@link BookingServiceImpl#createBooking(Booking)}
     */
    @Test
    void testCreateBooking() {
        // Arrange
        Booking booking = new Booking();
        booking.setAdditionalStop(new ArrayList<>());
        booking.setBookingId("42");
        booking.setBookingStatus(BookingStatus.ONGOING);
        booking.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        booking.setDropoffLocation("Dropoff Location");
        booking.setParcelName("Parcel Name");
        booking.setParcelWeight(3);
        booking.setPickupLocation("Pickup Location");
        booking.setReceiverID("Receiver ID");
        booking.setSenderID("Sender ID");
        when(bookingRepository.save(Mockito.<Booking>any())).thenReturn(booking);

        Booking booking2 = new Booking();
        booking2.setAdditionalStop(new ArrayList<>());
        booking2.setBookingId("42");
        booking2.setBookingStatus(BookingStatus.ONGOING);
        booking2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        booking2.setDropoffLocation("Dropoff Location");
        booking2.setParcelName("Parcel Name");
        booking2.setParcelWeight(3);
        booking2.setPickupLocation("Pickup Location");
        booking2.setReceiverID("Receiver ID");
        booking2.setSenderID("Sender ID");

        // Act
        BookingResponseDto actualCreateBookingResult = bookingServiceImpl.createBooking(booking2);

        // Assert
        verify(bookingRepository).save(isA(Booking.class));
        assertSame(booking2, actualCreateBookingResult);
    }

    /**
     * Method under test: {@link BookingServiceImpl#createBooking(Booking)}
     */
    @Test
    void testCreateBooking2() {
        // Arrange
        when(bookingRepository.save(Mockito.<Booking>any())).thenThrow(new BookingNotFound("42"));

        Booking booking = new Booking();
        booking.setAdditionalStop(new ArrayList<>());
        booking.setBookingId("42");
        booking.setBookingStatus(BookingStatus.ONGOING);
        booking.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        booking.setDropoffLocation("Dropoff Location");
        booking.setParcelName("Parcel Name");
        booking.setParcelWeight(3);
        booking.setPickupLocation("Pickup Location");
        booking.setReceiverID("Receiver ID");
        booking.setSenderID("Sender ID");

        // Act and Assert
        assertThrows(BookingNotFound.class, () -> bookingServiceImpl.createBooking(booking));
        verify(bookingRepository).save(isA(Booking.class));
    }

    /**
     * Method under test: {@link BookingServiceImpl#getAllBookings()}
     */
    @Test
    void testGetAllBookings() {
        // Arrange
        when(bookingRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<BookingResponseDto> actualAllBookings = bookingServiceImpl.getAllBookings();

        // Assert
        verify(bookingRepository).findAll();
        assertTrue(actualAllBookings.isEmpty());
    }

    /**
     * Method under test: {@link BookingServiceImpl#getAllBookings()}
     */
    @Test
    void testGetAllBookings2() {
        // Arrange
        Booking booking = new Booking();
        booking.setAdditionalStop(new ArrayList<>());
        booking.setBookingId("42");
        booking.setBookingStatus(BookingStatus.ONGOING);
        booking.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        booking.setDropoffLocation("Dropoff Location");
        booking.setParcelName("Parcel Name");
        booking.setParcelWeight(3);
        booking.setPickupLocation("Pickup Location");
        booking.setReceiverID("Receiver ID");
        booking.setSenderID("Sender ID");

        ArrayList<Booking> bookingList = new ArrayList<>();
        bookingList.add(booking);
        when(bookingRepository.findAll()).thenReturn(bookingList);

        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setAdditionalStop(new ArrayList<>());
        bookingResponseDto.setBookingId("42");
        bookingResponseDto.setBookingStatus(BookingStatus.ONGOING);
        bookingResponseDto.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        bookingResponseDto.setDropoffLocation("Dropoff Location");
        bookingResponseDto.setParcelName("Parcel Name");
        bookingResponseDto.setParcelWeight(3);
        bookingResponseDto.setPickupLocation("Pickup Location");
        bookingResponseDto.setReceiverID("Receiver ID");
        bookingResponseDto.setSenderID("Sender ID");
        when(convertMapper.convertBookingToBookingReponseDto(Mockito.<Booking>any())).thenReturn(bookingResponseDto);

        // Act
        List<BookingResponseDto> actualAllBookings = bookingServiceImpl.getAllBookings();

        // Assert
        verify(convertMapper).convertBookingToBookingReponseDto(isA(Booking.class));
        verify(bookingRepository).findAll();
        assertEquals(1, actualAllBookings.size());
        assertSame(bookingResponseDto, actualAllBookings.get(0));
    }

    /**
     * Method under test: {@link BookingServiceImpl#getAllBookings()}
     */
    @Test
    void testGetAllBookings3() {
        // Arrange
        Booking booking = new Booking();
        booking.setAdditionalStop(new ArrayList<>());
        booking.setBookingId("42");
        booking.setBookingStatus(BookingStatus.ONGOING);
        booking.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        booking.setDropoffLocation("Dropoff Location");
        booking.setParcelName("Parcel Name");
        booking.setParcelWeight(3);
        booking.setPickupLocation("Pickup Location");
        booking.setReceiverID("Receiver ID");
        booking.setSenderID("Sender ID");

        Booking booking2 = new Booking();
        booking2.setAdditionalStop(new ArrayList<>());
        booking2.setBookingId("Booking Id");
        booking2.setBookingStatus(BookingStatus.COMPLETED);
        booking2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        booking2.setDropoffLocation("com.parcel.module.model.Booking");
        booking2.setParcelName("com.parcel.module.model.Booking");
        booking2.setParcelWeight(1);
        booking2.setPickupLocation("com.parcel.module.model.Booking");
        booking2.setReceiverID("com.parcel.module.model.Booking");
        booking2.setSenderID("com.parcel.module.model.Booking");

        ArrayList<Booking> bookingList = new ArrayList<>();
        bookingList.add(booking2);
        bookingList.add(booking);
        when(bookingRepository.findAll()).thenReturn(bookingList);

        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setAdditionalStop(new ArrayList<>());
        bookingResponseDto.setBookingId("42");
        bookingResponseDto.setBookingStatus(BookingStatus.ONGOING);
        bookingResponseDto.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        bookingResponseDto.setDropoffLocation("Dropoff Location");
        bookingResponseDto.setParcelName("Parcel Name");
        bookingResponseDto.setParcelWeight(3);
        bookingResponseDto.setPickupLocation("Pickup Location");
        bookingResponseDto.setReceiverID("Receiver ID");
        bookingResponseDto.setSenderID("Sender ID");
        when(convertMapper.convertBookingToBookingReponseDto(Mockito.<Booking>any())).thenReturn(bookingResponseDto);

        // Act
        List<BookingResponseDto> actualAllBookings = bookingServiceImpl.getAllBookings();

        // Assert
        verify(convertMapper, atLeast(1)).convertBookingToBookingReponseDto(Mockito.<Booking>any());
        verify(bookingRepository).findAll();
        assertEquals(2, actualAllBookings.size());
        assertSame(bookingResponseDto, actualAllBookings.get(0));
        assertSame(bookingResponseDto, actualAllBookings.get(1));
    }

    /**
     * Method under test: {@link BookingServiceImpl#getAllBookings()}
     */
    @Test
    void testGetAllBookings4() {
        // Arrange
        Booking booking = new Booking();
        booking.setAdditionalStop(new ArrayList<>());
        booking.setBookingId("42");
        booking.setBookingStatus(BookingStatus.ONGOING);
        booking.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        booking.setDropoffLocation("Dropoff Location");
        booking.setParcelName("Parcel Name");
        booking.setParcelWeight(3);
        booking.setPickupLocation("Pickup Location");
        booking.setReceiverID("Receiver ID");
        booking.setSenderID("Sender ID");

        ArrayList<Booking> bookingList = new ArrayList<>();
        bookingList.add(booking);
        when(bookingRepository.findAll()).thenReturn(bookingList);
        when(convertMapper.convertBookingToBookingReponseDto(Mockito.<Booking>any())).thenThrow(new BookingNotFound("42"));

        // Act and Assert
        assertThrows(BookingNotFound.class, () -> bookingServiceImpl.getAllBookings());
        verify(convertMapper).convertBookingToBookingReponseDto(isA(Booking.class));
        verify(bookingRepository).findAll();
    }

    /**
     * Method under test: {@link BookingServiceImpl#getBookingById(String)}
     */
    @Test
    void testGetBookingById() {
        // Arrange
        Booking booking = new Booking();
        booking.setAdditionalStop(new ArrayList<>());
        booking.setBookingId("42");
        booking.setBookingStatus(BookingStatus.ONGOING);
        booking.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        booking.setDropoffLocation("Dropoff Location");
        booking.setParcelName("Parcel Name");
        booking.setParcelWeight(3);
        booking.setPickupLocation("Pickup Location");
        booking.setReceiverID("Receiver ID");
        booking.setSenderID("Sender ID");
        Optional<Booking> ofResult = Optional.of(booking);
        when(bookingRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        BookingResponseDto actualBookingById = bookingServiceImpl.getBookingById("42");

        // Assert
        verify(bookingRepository).findById(eq("42"));
        assertSame(booking, actualBookingById);
    }

    /**
     * Method under test: {@link BookingServiceImpl#getBookingById(String)}
     */
    @Test
    void testGetBookingById2() {
        // Arrange
        Optional<Booking> emptyResult = Optional.empty();
        when(bookingRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(BookingNotFound.class, () -> bookingServiceImpl.getBookingById("42"));
        verify(bookingRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link BookingServiceImpl#getBookingById(String)}
     */
    @Test
    void testGetBookingById3() {
        // Arrange
        when(bookingRepository.findById(Mockito.<String>any())).thenThrow(new BookingNotFound("42"));

        // Act and Assert
        assertThrows(BookingNotFound.class, () -> bookingServiceImpl.getBookingById("42"));
        verify(bookingRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link BookingServiceImpl#editAdditionalStop(String, List)}
     */
    @Test
    void testEditAdditionalStop() {
        // Arrange
        Booking booking = new Booking();
        booking.setAdditionalStop(new ArrayList<>());
        booking.setBookingId("42");
        booking.setBookingStatus(BookingStatus.ONGOING);
        booking.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        booking.setDropoffLocation("Dropoff Location");
        booking.setParcelName("Parcel Name");
        booking.setParcelWeight(3);
        booking.setPickupLocation("Pickup Location");
        booking.setReceiverID("Receiver ID");
        booking.setSenderID("Sender ID");
        Optional<Booking> ofResult = Optional.of(booking);

        Booking booking2 = new Booking();
        booking2.setAdditionalStop(new ArrayList<>());
        booking2.setBookingId("42");
        booking2.setBookingStatus(BookingStatus.ONGOING);
        booking2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        booking2.setDropoffLocation("Dropoff Location");
        booking2.setParcelName("Parcel Name");
        booking2.setParcelWeight(3);
        booking2.setPickupLocation("Pickup Location");
        booking2.setReceiverID("Receiver ID");
        booking2.setSenderID("Sender ID");
        when(bookingRepository.save(Mockito.<Booking>any())).thenReturn(booking2);
        when(bookingRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        BookingResponseDto actualEditAdditionalStopResult = bookingServiceImpl.editAdditionalStop("42", new ArrayList<>());

        // Assert
        verify(bookingRepository).findById(eq("42"));
        verify(bookingRepository).save(isA(Booking.class));
        assertSame(booking, actualEditAdditionalStopResult);
    }

    /**
     * Method under test:
     * {@link BookingServiceImpl#editAdditionalStop(String, List)}
     */
    @Test
    void testEditAdditionalStop2() {
        // Arrange
        Booking booking = new Booking();
        booking.setAdditionalStop(new ArrayList<>());
        booking.setBookingId("42");
        booking.setBookingStatus(BookingStatus.ONGOING);
        booking.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        booking.setDropoffLocation("Dropoff Location");
        booking.setParcelName("Parcel Name");
        booking.setParcelWeight(3);
        booking.setPickupLocation("Pickup Location");
        booking.setReceiverID("Receiver ID");
        booking.setSenderID("Sender ID");
        Optional<Booking> ofResult = Optional.of(booking);
        when(bookingRepository.save(Mockito.<Booking>any())).thenThrow(new BookingNotFound("42"));
        when(bookingRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(BookingNotFound.class, () -> bookingServiceImpl.editAdditionalStop("42", new ArrayList<>()));
        verify(bookingRepository).findById(eq("42"));
        verify(bookingRepository).save(isA(Booking.class));
    }

    /**
     * Method under test:
     * {@link BookingServiceImpl#editAdditionalStop(String, List)}
     */
    @Test
    void testEditAdditionalStop3() {
        // Arrange
        Optional<Booking> emptyResult = Optional.empty();
        when(bookingRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(BookingNotFound.class, () -> bookingServiceImpl.editAdditionalStop("42", new ArrayList<>()));
        verify(bookingRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link BookingServiceImpl#editAdditionalStop(String, List)}
     */
    @Test
    void testEditAdditionalStop4() {
        // Arrange
        Booking booking = new Booking();
        booking.setAdditionalStop(new ArrayList<>());
        booking.setBookingId("42");
        booking.setBookingStatus(BookingStatus.ONGOING);
        booking.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        booking.setDropoffLocation("Dropoff Location");
        booking.setParcelName("Parcel Name");
        booking.setParcelWeight(3);
        booking.setPickupLocation("Pickup Location");
        booking.setReceiverID("Receiver ID");
        booking.setSenderID("Sender ID");
        Optional<Booking> ofResult = Optional.of(booking);

        Booking booking2 = new Booking();
        booking2.setAdditionalStop(new ArrayList<>());
        booking2.setBookingId("42");
        booking2.setBookingStatus(BookingStatus.ONGOING);
        booking2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        booking2.setDropoffLocation("Dropoff Location");
        booking2.setParcelName("Parcel Name");
        booking2.setParcelWeight(3);
        booking2.setPickupLocation("Pickup Location");
        booking2.setReceiverID("Receiver ID");
        booking2.setSenderID("Sender ID");
        when(bookingRepository.save(Mockito.<Booking>any())).thenReturn(booking2);
        when(bookingRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        ArrayList<String> additionalStop = new ArrayList<>();
        additionalStop.add("foo");

        // Act
        BookingResponseDto actualEditAdditionalStopResult = bookingServiceImpl.editAdditionalStop("42", additionalStop);

        // Assert
        verify(bookingRepository).findById(eq("42"));
        verify(bookingRepository).save(isA(Booking.class));
        assertSame(booking, actualEditAdditionalStopResult);
    }

    /**
     * Method under test:
     * {@link BookingServiceImpl#editAdditionalStop(String, List)}
     */
    @Test
    void testEditAdditionalStop5() {
        // Arrange
        Booking booking = new Booking();
        booking.setAdditionalStop(new ArrayList<>());
        booking.setBookingId("42");
        booking.setBookingStatus(BookingStatus.ONGOING);
        booking.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        booking.setDropoffLocation("Dropoff Location");
        booking.setParcelName("Parcel Name");
        booking.setParcelWeight(3);
        booking.setPickupLocation("Pickup Location");
        booking.setReceiverID("Receiver ID");
        booking.setSenderID("Sender ID");
        Optional<Booking> ofResult = Optional.of(booking);

        Booking booking2 = new Booking();
        booking2.setAdditionalStop(new ArrayList<>());
        booking2.setBookingId("42");
        booking2.setBookingStatus(BookingStatus.ONGOING);
        booking2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        booking2.setDropoffLocation("Dropoff Location");
        booking2.setParcelName("Parcel Name");
        booking2.setParcelWeight(3);
        booking2.setPickupLocation("Pickup Location");
        booking2.setReceiverID("Receiver ID");
        booking2.setSenderID("Sender ID");
        when(bookingRepository.save(Mockito.<Booking>any())).thenReturn(booking2);
        when(bookingRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        ArrayList<String> additionalStop = new ArrayList<>();
        additionalStop.add("42");
        additionalStop.add("foo");

        // Act
        BookingResponseDto actualEditAdditionalStopResult = bookingServiceImpl.editAdditionalStop("42", additionalStop);

        // Assert
        verify(bookingRepository).findById(eq("42"));
        verify(bookingRepository).save(isA(Booking.class));
        assertSame(booking, actualEditAdditionalStopResult);
    }
}
