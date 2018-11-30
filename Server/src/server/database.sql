CREATE TABLE Rentals
(
    rentalID int NOT NULL,
    rentalType varchar(12),                  --apartment, single house, vacation
    rooms int NOT NULL,                      --#of rooms
    garages int NOT NULL,                    --#garages
    yards int NOT NULL,                      --0,1,2,3
    costType varchar(7) NOT NULL,            --yearly,monthly etc...
    apartmentDesription varchar(50) NOT NULL, --common area etc...
    vacationRentalTypes varchar(15) NOT NULL,--single,duplex etc...
    maximumOccupancy int NOT NULL,
    cost int NOT NULL,
	PRIMARY KEY (rentalID)
);

CREATE TABLE Tenants
(
    tenantID int NOT NULL,
    firstName varchar(20) NOT NULL,
    lastName varchar(30) NOT NULL,
    phoneNumber int NOT NULL,
    billingAddress varchar(50) NOT NULL,
	PRIMARY KEY (tenantID)
);

CREATE TABLE Contracts
(
    lateFeeParameters varchar(40) NOT NULL,
    depositAmount int NOT NULL,
    leaseStartDate date NOT NULL,
    leaseEndDate date NOT NULL,
	rentalId int NOT NULL,
	tenantID int NOT NULL,
    FOREIGN KEY (rentalID) REFERENCES Rentals(rentalID),
    FOREIGN KEY (tenantID) REFERENCES Tenants(tenantID)
);




