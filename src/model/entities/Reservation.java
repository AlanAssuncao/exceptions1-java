package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {

	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation() {
	}

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException {
		if (!checkOut.after(checkIn) ) {
			throw new DomainException("Erro para a reserva: O check-out tem que ser em data posterior ao check-in.");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public void updateDates(Date checkIn, Date checkOut) throws DomainException {		
		Date now = new Date();		
		if(checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("Erro para a reserva: A reserva tem que ser feita com datas futuras.");
		}
		if (!checkOut.after(checkIn) ) {
			throw new DomainException("Erro para a reserva: O check-out tem que ser em data posterior ao check-in.");
		}		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	@Override
	public String toString() {
		return "Quarto "
				+ roomNumber
				+ ", check-in: "
				+ sdf.format(checkIn)
				+ ", check-out: "
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " noites.";
	}
			
}
