package org.example.rest.validation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.example.rest.dao.RoomDao;
import org.example.rest.entity.HotelType;
import org.example.rest.entity.Reservation;
import org.example.rest.service.ReservationService;

@Named
public class ReservationValidator implements ConstraintValidator<ValidateReservation, Reservation> {
    @Inject
    private ReservationService reservationService;

    @Inject
    private RoomDao roomDao;

    @Override
    public void initialize(final ValidateReservation annotation) {}

    @Override
    public boolean isValid(final Reservation request, final ConstraintValidatorContext ctx) {
        final List<String> errors = new ArrayList<>();

        if (request.personName == null || request.personName.trim().isEmpty()) {
            errors.add("Insert your name");
        }

        if (request.startDate == null || request.endDate == null) {
            errors.add("Insert start and end date");
        } else if (request.startDate.after(request.endDate)) {
            errors.add("Start date must be before end date");
        } else if (!reservationService.checkAvailability(request.roomId, request.startDate, request.endDate)) {
            String error = "Sorry, this room is not available for this period.";
            if (roomDao.findById(request.roomId).hotel.type == HotelType.Sanitarium) {
                error = "Sorry, this room is not available for this period. Please check 'Arrival plans' for this hotel.";
            }
            errors.add(error);
        }

        if (!errors.isEmpty()) {
            ctx.disableDefaultConstraintViolation();
            for (final String e : errors) {
                ctx.buildConstraintViolationWithTemplate(e).addConstraintViolation();
            }
            return false;
        }

        return true;
    }

}
