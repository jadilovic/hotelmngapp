package com.avlija.hotel.form;

public class BookingForm {

    private int userId;
    
    private int roomNum;
    
    private int reservationId;
    
    private long dateId;

    private String fromDate;
    
    private String toDate; 
 
    private String datetimeField;
 
    private boolean edit;
 
    private boolean singleCheckboxField;
    
    private String colorField;
 
    private String[] multiCheckboxSelectedValues;
 
    private String radioButtonSelectedValue;
    private String dropdownSelectedValue;
    
    public BookingForm() {
    	
    }
    
	public BookingForm(int userId, int roomNum, int reservationId, long dateId, String fromDate, String toDate,
			String datetimeField, boolean edit, boolean singleCheckboxField, String colorField,
			String[] multiCheckboxSelectedValues, String radioButtonSelectedValue, String dropdownSelectedValue) {
		super();
		this.userId = userId;
		this.roomNum = roomNum;
		this.reservationId = reservationId;
		this.dateId = dateId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.datetimeField = datetimeField;
		this.edit = edit;
		this.singleCheckboxField = singleCheckboxField;
		this.colorField = colorField;
		this.multiCheckboxSelectedValues = multiCheckboxSelectedValues;
		this.radioButtonSelectedValue = radioButtonSelectedValue;
		this.dropdownSelectedValue = dropdownSelectedValue;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the roomNum
	 */
	public int getRoomNum() {
		return roomNum;
	}

	/**
	 * @param roomNum the roomNum to set
	 */
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	/**
	 * @return the reservationId
	 */
	public int getReservationId() {
		return reservationId;
	}

	/**
	 * @param reservationId the reservationId to set
	 */
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	/**
	 * @return the dateId
	 */
	public long getDateId() {
		return dateId;
	}

	/**
	 * @param dateId the dateId to set
	 */
	public void setDateId(long dateId) {
		this.dateId = dateId;
	}

	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the datetimeField
	 */
	public String getDatetimeField() {
		return datetimeField;
	}

	/**
	 * @param datetimeField the datetimeField to set
	 */
	public void setDatetimeField(String datetimeField) {
		this.datetimeField = datetimeField;
	}

	/**
	 * @return the edit
	 */
	public boolean isEdit() {
		return edit;
	}

	/**
	 * @param edit the edit to set
	 */
	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	/**
	 * @return the singleCheckboxField
	 */
	public boolean isSingleCheckboxField() {
		return singleCheckboxField;
	}

	/**
	 * @param singleCheckboxField the singleCheckboxField to set
	 */
	public void setSingleCheckboxField(boolean singleCheckboxField) {
		this.singleCheckboxField = singleCheckboxField;
	}

	/**
	 * @return the colorField
	 */
	public String getColorField() {
		return colorField;
	}

	/**
	 * @param colorField the colorField to set
	 */
	public void setColorField(String colorField) {
		this.colorField = colorField;
	}

	/**
	 * @return the multiCheckboxSelectedValues
	 */
	public String[] getMultiCheckboxSelectedValues() {
		return multiCheckboxSelectedValues;
	}

	/**
	 * @param multiCheckboxSelectedValues the multiCheckboxSelectedValues to set
	 */
	public void setMultiCheckboxSelectedValues(String[] multiCheckboxSelectedValues) {
		this.multiCheckboxSelectedValues = multiCheckboxSelectedValues;
	}

	/**
	 * @return the radioButtonSelectedValue
	 */
	public String getRadioButtonSelectedValue() {
		return radioButtonSelectedValue;
	}

	/**
	 * @param radioButtonSelectedValue the radioButtonSelectedValue to set
	 */
	public void setRadioButtonSelectedValue(String radioButtonSelectedValue) {
		this.radioButtonSelectedValue = radioButtonSelectedValue;
	}

	/**
	 * @return the dropdownSelectedValue
	 */
	public String getDropdownSelectedValue() {
		return dropdownSelectedValue;
	}

	/**
	 * @param dropdownSelectedValue the dropdownSelectedValue to set
	 */
	public void setDropdownSelectedValue(String dropdownSelectedValue) {
		this.dropdownSelectedValue = dropdownSelectedValue;
	}
    
}
