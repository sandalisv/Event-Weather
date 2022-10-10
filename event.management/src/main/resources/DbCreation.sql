CREATE SCHEMA IF NOT EXISTS event;

CREATE TABLE IF NOT EXISTS event.event (
  event_id int(10) NOT NULL AUTO_INCREMENT,
  event_name varchar(255),
  date date DEFAULT NULL,
  start_time time DEFAULT NULL,
  end_time time DEFAULT NULL,
  city varchar(255) DEFAULT NULL,
  country varchar(255) DEFAULT NULL,
  weather_report varchar(2000) DEFAULT NULL,
  PRIMARY KEY (event_id)
);


CREATE TABLE IF NOT EXISTS event.guest (
  guest_id int(10) NOT NULL AUTO_INCREMENT,
  guest_name varchar(255),
  age int(100) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  mobile varchar(255) DEFAULT NULL,
  PRIMARY KEY (guest_id)
);

CREATE TABLE event.event_guest(
reference_id int(10) NOT NULL AUTO_INCREMENT,
event_id int(10) NOT NULL,
guest_id int(10) NOT NULL,
PRIMARY KEY (reference_id),
FOREIGN KEY (event_id) REFERENCES event(event_id),
FOREIGN KEY (guest_id) REFERENCES guest(guest_id)
);